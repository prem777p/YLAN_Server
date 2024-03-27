package com.prem.ylan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class StreamingService {


    private final FileSystemResourceLoader resourceLoader;

    public StreamingService() {
        this.resourceLoader = new FileSystemResourceLoader();
    }

    public Mono<Resource> getVideo(String PATH){
        return Mono.fromSupplier(()-> resourceLoader.getResource(PATH));
    }
}
