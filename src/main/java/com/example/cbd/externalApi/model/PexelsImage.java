package com.example.cbd.externalApi.model;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.cache.annotation.EnableCaching;

import java.util.List;


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
