package com.codereview.linkgenerator.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShortLinkErrorResponse {
    private int status;
    private String message;
    private long timeStamp;
}
