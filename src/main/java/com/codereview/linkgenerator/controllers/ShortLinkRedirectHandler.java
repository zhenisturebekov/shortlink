package com.codereview.linkgenerator.controllers;

import com.codereview.linkgenerator.components.ShortLinkStateUpdate;
import com.codereview.linkgenerator.entities.ShortLink;
import com.codereview.linkgenerator.exceptions.ShortLinkNotFoundException;
import com.codereview.linkgenerator.services.ShortLinkService;
import com.codereview.linkgenerator.services.ShortLinkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.constraints.NotBlank;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ShortLinkRedirectHandler {
    @Value("${linkGenerator.stats.prefix}")
    private String prefix;

    private ShortLinkService shortLinkService;
    private ShortLinkStateUpdate shortLinkStateUpdate;

    @Autowired
    public void setShortLinkStateUpdate(ShortLinkStateUpdate shortLinkStateUpdate) {
        this.shortLinkStateUpdate = shortLinkStateUpdate;
    }

    @Autowired
    public void setShortLinkService(ShortLinkServiceImpl shortLinkService) {
        this.shortLinkService = shortLinkService;
    }

    @GetMapping("/l/{link}")
    @ResponseStatus(HttpStatus.FOUND)
    public RedirectView redirect(@NotBlank(message = "Link is mandatory") @PathVariable String link){
        ShortLink shortLink = getShortLinkByLink(link);

        shortLinkStateUpdate.update(shortLink.getId());

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(shortLink.getOriginal());

        return redirectView;
    }

    private ShortLink getShortLinkByLink(String link) {
        ShortLink shortLink = shortLinkService.findShortLinkByLinkCache(prefix + link);
        if (shortLink == null)
            throw new ShortLinkNotFoundException("Short link " + prefix + link + " was not found");

        return shortLink;
    }
}
