package com.codereview.linkgenerator.controllers;

import com.codereview.linkgenerator.DTOs.ShortLinkDTO;
import com.codereview.linkgenerator.DTOs.ShortLinkDTOMapper;
import com.codereview.linkgenerator.entities.ShortLink;
import com.codereview.linkgenerator.exceptions.ShortLinkNotFoundException;
import com.codereview.linkgenerator.services.ShortLinkService;
import com.codereview.linkgenerator.services.ShortLinkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stats")
public class ShortLinkStatsHandler {
    private ShortLinkService shortLinkService;
    private ShortLinkDTOMapper modelMapper;

    @Value("${linkGenerator.stats.prefix}")
    private String prefix;

    @Autowired
    public void setShortLinkService(ShortLinkServiceImpl shortLinkService) {
        this.shortLinkService = shortLinkService;
    }

    @Autowired
    public void setModelMapper(ShortLinkDTOMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{link}")
    @ResponseStatus(code = HttpStatus.OK)
    public ShortLinkDTO getStat(@NotBlank(message = "Link is mandatory") @PathVariable String link) {
        ShortLink shortLink = shortLinkService.findShortLinkByLink(prefix + link);
        if (shortLink == null)
            throw new ShortLinkNotFoundException("Short link " + link + " is not found");

        return modelMapper.getShortLinkDTO(shortLink);
    }

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public List<ShortLinkDTO> getShortLinkPageSortedByCount(
            @RequestParam(defaultValue = "0") int page,
            @Size(min = 1, max = 100, message = "Count should be set between 1 to 100")
            @RequestParam(defaultValue = "5") int count) {

        Pageable list = PageRequest.of(page, count, Sort.by("count").descending());
        Page<ShortLink> shortLinks = shortLinkService.findAll(list);

        List<ShortLinkDTO> shortLinkDTOS = new ArrayList<>();
        for (ShortLink link : shortLinks) {
            ShortLinkDTO shortLinkDTO = modelMapper.getShortLinkDTO(link);
            shortLinkDTOS.add(shortLinkDTO);
        }

        return shortLinkDTOS;
    }
}
