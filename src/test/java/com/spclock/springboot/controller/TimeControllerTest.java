package com.spclock.springboot.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.spclock.springboot.service.TimeService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TimeControllerTest {

    @InjectMocks
    private TimeController timeController;

    @Mock
    private TimeService timeService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    
    
    
    @Test
    public void testConvertToWords_Success() {
        String timeStr = "09:25";
        String expectedTimeInWord="nine twenty-five";
        String expectedResponse = "It's nine twenty-five";
        String[] timeParts = timeStr.split(":");
        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);
        //when(timeService.convertToWords(timeStr)).thenReturn(expectedResponse);//ResponseEntity.ok(result)
        when(timeService.convertTimeToWord(hours,minutes)).thenReturn(expectedTimeInWord);

        ResponseEntity<String> response = timeController.convertToWords(timeStr);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    public void testConvertToWords_InvalidFormat() {
        String timeStr = "07:45:58";

        ResponseEntity<String> response = timeController.convertToWords(timeStr);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid time format.Format must be HH:MM", response.getBody());
    }
    
    @Test
    public void testConvertToWords_InvalidDataFormat() {
        String timeStr = "07.22";

        ResponseEntity<String> response = timeController.convertToWords(timeStr);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid time format.Format must be HH:MM", response.getBody());
    }
    
    @Test
    public void testConvertToWords_InvalidTimeModeFormat() {
        String timeStr = "07:33pm";

        ResponseEntity<String> response = timeController.convertToWords(timeStr);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid time format.Format must be HH:MM", response.getBody());
    }
}