package com.prem.ylan.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;

@RestController
public class VideoController {

    @GetMapping(value = "/video", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> getVideo() throws IOException {
        Resource video = new ClassPathResource("m.mp4");
        byte[] videoBytes = Files.readAllBytes(video.getFile().toPath());
        return ResponseEntity.ok()
                .contentLength(video.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(video);
    }

}