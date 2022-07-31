package com.codereview.linkgenerator.services;

import com.codereview.linkgenerator.entities.ShortLink;
import com.codereview.linkgenerator.repositories.ShortLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ShortLinkServiceImpl implements ShortLinkService {
    private ShortLinkRepository linkRepository;

    @Autowired
    public void setLinkRepository(ShortLinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Override
    public boolean isShortLinkExist(String shortLink) {
        return linkRepository.isShortLinkExist(shortLink);
    }

    @Override
    public void save(ShortLink link) {
        linkRepository.save(link);
    }

    @Override
    public ShortLink findShortLinkByLink(String shortLink) {
        return linkRepository.findShortLinkByLink(shortLink);
    }

    @Override
    @Cacheable("link")
    public ShortLink findShortLinkByLinkCache(String shortLink) {
        return linkRepository.findShortLinkByLink(shortLink);
    }

    @Override
    public Page<ShortLink> findAll(Pageable pageable) {
        return linkRepository.findAll(pageable);
    }

    @Override
    public void updateRankById(int id, int rank) {
        linkRepository.updateRank(id, rank);
    }

    @Override
    public ShortLink findByRank(int rank) {
        return linkRepository.findByRank(rank);
    }

    @Override
    public void incrementCountById(int id) {
        linkRepository.incrementCountById(id);
    }

    @Override
    public int getHighestRank() {
        return linkRepository.getHighestRank();
    }

    @Override
    public ShortLink findById(int id) {
        return linkRepository.findById(id);
    }
}
