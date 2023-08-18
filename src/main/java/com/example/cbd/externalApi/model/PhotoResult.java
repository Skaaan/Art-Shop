package com.example.cbd.externalApi.model;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PhotoResult {

    private int total_results;
    private int page;
    private int per_page;
    private List<Photo> photos;
    private String next_page;

}
