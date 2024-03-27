package com.prem.ylan.controller;

import com.prem.ylan.helper.FileUploadDownloadHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@RestController
public class FileUploadDownloadController {
    private static final String UPLOAD_FOLDER = "//home/prem/Music";
    private static final String UPLOAD_FOLDERs = "d:/prem/";

    @Autowired
    FileUploadDownloadHelper fileUploadDownloadHelper;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file")  String file) {
        return ResponseEntity.ok("Successful");

    }


//    @Autowired
//    private FileStorageService fileStorageService;

    @PostMapping("/uploadFile")
    public String uploadFile(HttpServletRequest request) {
        MultipartFile multipartFile = (MultipartFile) request.getAttribute("file");
//        String fileName = fileStorageService.storeFile(multipartFile);

//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/api/downloadFile/")
//                .path(fileName)
//                .toUriString();

        return "ok";
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName, @RequestParam String folderPath) {
        return fileUploadDownloadHelper.downloadFile(folderPath, fileName);
    }

    // for demonstration purpose
    @GetMapping("/downloads/{fileName:.+}")
    public ResponseEntity<byte[]> downloadFiles(@PathVariable String fileName) {
        return fileUploadDownloadHelper.downloadFile(UPLOAD_FOLDERs, fileName);
    }
}
