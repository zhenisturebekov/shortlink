package com.codereview.linkgenerator.services;

import com.codereview.linkgenerator.entities.ShortLink;
import com.codereview.linkgenerator.repositories.ShortLinkRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootTest
class ShortLinkServiceImplTest {
    @Mock
    ShortLinkRepository linkRepository;

    @InjectMocks
    ShortLinkService shortLinkService = new ShortLinkServiceImpl();

    @Test
    public void whenShortLinkNotExistFindByIdNull(){
        when(shortLinkService.findById(1)).thenReturn(null);
        ShortLink result = shortLinkService.findById(1);

        Assertions.assertNull(result);
    }

    @Test
    public void whenShortLinkExistFindByIdTrue(){
        ShortLink shortLink = new ShortLink();
        shortLink.setLink("test");
        shortLink.setOriginal("original");
        shortLink.setCount(100);
        shortLink.setRank(5);

        when(shortLinkService.findById(1)).thenReturn(shortLink);
        ShortLink result = shortLinkService.findById(1);

        Assertions.assertEquals("test", result.getLink());
        Assertions.assertEquals("original", result.getOriginal());
        Assertions.assertEquals(100, result.getCount());
        Assertions.assertEquals(5, result.getRank());
    }

    @Test
    void getHighestRank() {
        when(shortLinkService.getHighestRank()).thenReturn(5);
        int result = shortLinkService.getHighestRank();

        Assertions.assertEquals(5, result);
    }
}
