package com.codereview.linkgenerator.controllers;

import com.codereview.linkgenerator.entities.GeneratedLink;
import com.codereview.linkgenerator.entities.OriginalURL;
import com.codereview.linkgenerator.entities.ShortLink;
import com.codereview.linkgenerator.exceptions.ShortLinkNotFoundException;
import com.codereview.linkgenerator.services.ShortLinkService;
import com.codereview.linkgenerator.services.ShortLinkServiceImpl;
import com.codereview.linkgenerator.utils.RandomTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ShortLinkGenerateHandler {
    @Value("${linkGenerator.stats.prefix}")
    private String prefix;

    private RandomTokenGenerator randomTokenGenerator;
    private ShortLinkService shortLinkService;

    @Autowired
    public void setRandomTokenGenerator(RandomTokenGenerator randomTokenGenerator) {
        this.randomTokenGenerator = randomTokenGenerator;
    }

    @Autowired
    public void setShortLinkService(ShortLinkServiceImpl shortLinkService) {
        this.shortLinkService = shortLinkService;
    }

    @PostMapping("/generate")
    @ResponseStatus(code = HttpStatus.CREATED)
    public GeneratedLink generate(@Valid @RequestBody OriginalURL originalURL) {
        if (originalURL == null)
            throw new ShortLinkNotFoundException("Required not null data");

        String original = originalURL.getOriginal();
        if (original == null || original.isBlank())
            throw new ShortLinkNotFoundException("Target URL must not be blank");

        String token = generateToken();
        if (token == null)
            throw new ShortLinkNotFoundException("Oops! Short link generation cannot be completed. Please try later");

        createAndSaveShortLink(original, token);

        return new GeneratedLink(token);
    }

    private String generateToken() {
        String token;
        do {
            token = prefix + randomTokenGenerator.generateToken();
        } while (shortLinkService.isShortLinkExist(token));

        return token;
    }

    private void createAndSaveShortLink(String original, String token) {
        int highestRank = shortLinkService.getHighestRank();
        ShortLink shortLink = new ShortLink();
        shortLink.setOriginal(original);
        shortLink.setLink(token);
        shortLink.setRank(highestRank + 1);

        shortLinkService.save(shortLink);
    }
}
