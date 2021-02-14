package com.enigma.api.inventory.models;

import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("ALL")
public class ImageUploadRequest {
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
