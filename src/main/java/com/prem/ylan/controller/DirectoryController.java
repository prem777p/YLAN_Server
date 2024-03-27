package com.prem.ylan.controller;

import com.prem.ylan.helper.DirectoryHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
class DirectoryController {

    @Autowired
    DirectoryHelper directoryHelper;

    @PostMapping("/receivepath")
    public Map<String, Object> receivePath(@RequestBody String path) {
        path = path.replaceAll("\"", "");
        return directoryHelper.listDirectoriesAndFiles(path);
    }
    @GetMapping("/directories")
    public Map<String, Object> listDirectoriesAndFiles() {

        String path = "/home"; // Specify the directory path here
      return directoryHelper.listDirectoriesAndFiles(path);
    }
}