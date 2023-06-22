package com.example.cbd.apiGateway.service;


import com.example.cbd.externalapi.service.ImageGeneratorServiceMethods;
import com.example.cbd.storageApi.model.Product;
import com.example.cbd.storageApi.service.StorageServiceMethods;


//extends all other service interfaces!
public interface AppServiceMethods extends StorageServiceMethods<Product>, ImageGeneratorServiceMethods {

}
