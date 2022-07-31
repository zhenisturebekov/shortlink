package com.codereview.linkgenerator.components;

import com.codereview.linkgenerator.entities.ShortLink;
import com.codereview.linkgenerator.services.ShortLinkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class ShortLinkStateUpdate {
    private final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

    private ShortLinkServiceImpl shortLinkService;

    @Autowired
    public void setShortLinkService(ShortLinkServiceImpl shortLinkService) {
        this.shortLinkService = shortLinkService;
    }

    public ShortLinkStateUpdate() {
        execute();
    }

    public void update(Integer id){
        queue.add(id);
    }

    private void execute(){
        new Thread(()->{
            while (true){
                try {
                    int id =  queue.take();
                    updateShortLinkCount(id);
                    updateShortLinkRank(id);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();
    }

    private void updateShortLinkCount(int shortLinkId) {
        shortLinkService.incrementCountById(shortLinkId);
    }

    private void updateShortLinkRank(int shortLinkId) {
        ShortLink current = shortLinkService.findById(shortLinkId);
        if (current.getRank() == 1)
            return;

        ShortLink previous = shortLinkService.findByRank(current.getRank() - 1);

        if (current.getCount() > previous.getCount()) {
            shortLinkService.updateRankById(current.getId(), previous.getRank());
            shortLinkService.updateRankById(previous.getId(), current.getRank());
        }

    }
}
