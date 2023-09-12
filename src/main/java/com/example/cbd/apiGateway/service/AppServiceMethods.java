package com.example.cbd.apiGateway.service;


import com.example.cbd.externalApi.service.ImageGeneratorServiceMethods;
import com.example.cbd.storageApi.model.Product;
import com.example.cbd.storageApi.service.ProductServiceMethods;


//extends all other service interfaces!
public interface AppServiceMethods extends ProductServiceMethods<Product>, ImageGeneratorServiceMethods {

}
