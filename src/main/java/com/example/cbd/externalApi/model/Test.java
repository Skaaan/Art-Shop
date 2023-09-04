package com.example.cbd.externalApi.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Test {

    private PexelsImage images;

    private String nextPage;
}
