package com.example.cbd.externalApi.model;


import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class PexelsImage {

    private String tiny;
    private String portrait;
    private String landscape;

}
