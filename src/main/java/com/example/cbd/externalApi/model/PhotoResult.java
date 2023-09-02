package com.example.cbd.externalApi.model;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PhotoResult {

    //todo delete all unnecessary attributes -> should contain only src with some chosen sizes!

    //private int total_results;
    //private int page;
    //private int per_page;
    private List<Photo> photos;
    private String next_page;

}
