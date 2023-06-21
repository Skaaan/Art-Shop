package com.example.cbd.apiGateway.service;


import com.example.cbd.storageApi.model.Product;
import com.example.cbd.storageApi.service.StorageServiceMethods;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;


//extends all other service interfaces!
public interface AppServiceMethods extends StorageServiceMethods<Product> {

}
