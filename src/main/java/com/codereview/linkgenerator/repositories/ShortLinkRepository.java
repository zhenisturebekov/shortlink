package com.codereview.linkgenerator.repositories;

import com.codereview.linkgenerator.entities.ShortLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ShortLinkRepository extends JpaRepository<ShortLink, Integer> {
    ShortLink findById(int id);

    ShortLink findByRank(int rank);

    ShortLink findShortLinkByLink(String link);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE short SET count = count +1 WHERE id = :id")
    void incrementCountById(int id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE short SET rank = :rank WHERE id = :id")
    void updateRank(int id, int rank);

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM short")
    int getHighestRank();

    @Query(nativeQuery = true,
            value = "SELECT CASE WHEN EXISTS(SELECT * FROM short WHERE link = :link) THEN 'True' ELSE 'False' END ")
    boolean isShortLinkExist(String link);
}
