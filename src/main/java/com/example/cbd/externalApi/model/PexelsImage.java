package com.example.cbd.externalApi.model;


import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class PexelImage {

    private String small;
    private String medium;
    private String large;

}
