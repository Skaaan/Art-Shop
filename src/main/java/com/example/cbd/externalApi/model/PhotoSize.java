package com.example.cbd.externalApi.model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PhotoSize {
    private String original;
    private String large2x;
    private String large;
    private String medium;
    private String small;
    private String portrait;
    private String landscape;
    private String tiny;
}
