package com.prem.ylan.controller;

import com.prem.ylan.service.StreamingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("stream")
public class StreamController {


//    private static final String PATH = "//home/prem/Videos/%s"; // Specify the path for linux
//    private static final String PATH = "D:\\prem\\d.mkv"; // Specify the path for window
    private static final String PATH = "D:/prem/d.mkv"; // Specify the path for window

    @Autowired
    private StreamingService service;

    @GetMapping(value = "{title}", produces = "video/mp4")
    public Mono<Resource> getVideos(@PathVariable String title) {
        return service.getVideo(String.format(PATH));
    }

    @GetMapping(value = "/video", produces = "video/mp4")
    public Mono<Resource> getVideosByFullPath(@RequestBody String fullPath) {
        return service.getVideo(fullPath);
    }
    @GetMapping(value = "/url", produces = "video/mp4")
    public Mono<Resource> getVideosByUrl(@RequestParam String path) {
        return service.getVideo(path);
    }
}
