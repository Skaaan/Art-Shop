package com.example.cbd.externalapi.Service;

import java.io.IOException;

public interface ImageGeneratorService {

    String getImage(String prompt) throws IOException;

    String getRandomImage();

}
