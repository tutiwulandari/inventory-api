package com.enigma.api.inventory.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class ImageUploadRequestTest {

    private ImageUploadRequest image;
    private MultipartFile file;

    @Test
    void setImageUpload() {
        ImageUploadRequest expected = new ImageUploadRequest();
        expected.setFile(file);

        ImageUploadRequest actual = new ImageUploadRequest();
        actual.setFile(file);

        assertEquals(expected.getFile(), actual.getFile());

    }

}
