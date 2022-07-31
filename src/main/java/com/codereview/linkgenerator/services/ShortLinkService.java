package com.codereview.linkgenerator.services;

import com.codereview.linkgenerator.entities.ShortLink;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ShortLinkService {
    boolean isShortLinkExist(String shortLink);

    void save(ShortLink link);

    Page<ShortLink> findAll(Pageable pageable);

    ShortLink findShortLinkByLink(String shortLink);

    ShortLink findShortLinkByLinkCache(String shortLink);

    void updateRankById(int id, int rank);

    ShortLink findByRank(int rank);

    void incrementCountById(int id);

    int getHighestRank();

    ShortLink findById(int id);
}
