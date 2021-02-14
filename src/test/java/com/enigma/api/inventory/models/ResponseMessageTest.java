package com.enigma.api.inventory.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("ALL")
@ExtendWith(MockitoExtension.class)
class ResponseMessageTest {

    private ResponseMessage responseMessage;

    @Test
    void setResponseMessage() {

        LocalDateTime timestamp = LocalDateTime.now();

        ResponseMessage expected = new ResponseMessage();
        expected.setMessage("message");
        expected.setCode(1);
        expected.setData("data");
        expected.setTimestamp(timestamp);

        ResponseMessage actual = new ResponseMessage();
        actual.setMessage("message");
        actual.setCode(1);
        actual.setData("data");
        actual.setTimestamp(timestamp);

        assertEquals(expected.getMessage(), actual.getMessage());
        assertEquals(expected.getCode(), actual.getCode());
        assertEquals(expected.getData(), actual.getData());
        assertEquals(expected.getData(), actual.getData());
        assertEquals(expected.getTimestamp(), actual.getTimestamp());


    }

}
