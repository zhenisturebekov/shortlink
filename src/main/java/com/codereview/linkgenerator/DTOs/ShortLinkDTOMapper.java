package com.codereview.linkgenerator.DTOs;

import com.codereview.linkgenerator.entities.ShortLink;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ShortLinkDTOMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public ShortLinkDTO getShortLinkDTO(ShortLink shortLink){
        return modelMapper.map(shortLink, ShortLinkDTO.class);
    }

}
