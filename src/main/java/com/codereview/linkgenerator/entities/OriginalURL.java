package com.codereview.linkgenerator.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class OriginalURL {
    @NotBlank(message = "Original link is mandatory")
    private String original;
}
