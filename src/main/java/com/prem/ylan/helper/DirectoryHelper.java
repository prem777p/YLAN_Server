package com.prem.ylan.helper;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

@Component
public class DirectoryHelper {
    List<String> directoryAndFileNames = new ArrayList<>();

    public List<String> getDirectoryFile(String path){
        File directory = new File(path);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        directoryAndFileNames.add("[DIR] " + file.getName());
                    } else {
                        directoryAndFileNames.add(file.getName());
                    }
                }
            }
        }

        return directoryAndFileNames;
    }

    public Map<String, Object> listDirectoriesAndFiles(String path) {
        Map<String, Object> response = new HashMap<>();

        File directory = new File(path);
        if (directory.exists() && directory.isDirectory()) {
            List<String> directoryNames = new ArrayList<>();
            List<String> fileNames = new ArrayList<>();

            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        directoryNames.add(file.getName());
                    } else {
                        fileNames.add(file.getName());
                    }
                }
            }

            response.put("root_dir_name", directory.getName());
            response.put("directory", directoryNames);
            response.put("file", fileNames);
        }

        return response;
    }
}

