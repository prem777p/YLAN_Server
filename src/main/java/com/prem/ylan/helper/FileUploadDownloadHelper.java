package com.prem.ylan.helper;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import static org.apache.commons.io.FileUtils.readFileToByteArray;

@Component
public class FileUploadDownloadHelper {

    public ResponseEntity<String> uploadFile(String UPLOAD_FOLDER, MultipartFile file){
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Please select a file to upload");
            }

            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            File targetFile = new File(UPLOAD_FOLDER, fileName);
            FileOutputStream outputStream = new FileOutputStream(targetFile);
            outputStream.write(file.getBytes());
            outputStream.close();

            return ResponseEntity.ok("File uploaded successfully: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }

    public ResponseEntity<byte[]> downloadFile(String UPLOAD_FOLDER, String fileName){
        try {
            // Security check: Prevent directory traversal
            if (fileName.contains("..")) {
                return ResponseEntity.badRequest().build();
            }

            File file = new File(UPLOAD_FOLDER, fileName);

            // Security check: Ensure the file exists and is not a directory
            if (!file.exists() || file.isDirectory()) {
                return ResponseEntity.notFound().build();
            }

            byte[] fileContent = readFileToByteArray(file);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", fileName);

            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
