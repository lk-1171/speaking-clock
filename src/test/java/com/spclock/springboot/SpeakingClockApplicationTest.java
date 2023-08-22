package com.spclock.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpeakingClockApplicationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testConvertToWords_Success() {
        String url = "http://localhost:" + port + "/convert-time?time=08:54";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("It's eight fifty-four", response.getBody());
    }

    @Test
    public void testConvertToWords_InvalidFormat() {
        String url = "http://localhost:" + port + "/convert-time?time=07:44:59";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid time format.Format must be HH:MM", response.getBody());
    }
    
    @Test
    public void testConvertToWords_InvalidDataFormat() {
        String url = "http://localhost:" + port + "/convert-time?time=05.24";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid time format.Format must be HH:MM", response.getBody());
    }
    
    @Test
    public void testConvertToWords_InvalidTimeModeFormat() {
        String url = "http://localhost:" + port + "/convert-time?time=06:45pm";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid time format.Format must be HH:MM", response.getBody());
    }
    
}
