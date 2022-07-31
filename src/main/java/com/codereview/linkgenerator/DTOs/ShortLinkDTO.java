package com.codereview.linkgenerator.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShortLinkDTO {
    private String link;
    private String original;
    private int rank;
    private int count;
}
