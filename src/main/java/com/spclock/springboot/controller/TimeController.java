package com.spclock.springboot.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.spclock.springboot.service.TimeService;

@RestController
public class TimeController {
	
	@Autowired
	private TimeService timeService;

    @ApiOperation(value = "Convert Time to Words")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })

    @GetMapping("/")
    public RedirectView redirectToSwaggerUI() {
        return new RedirectView("/swagger-ui/");
    }
    
    @GetMapping("/current-time")
    public ResponseEntity<String> getCurrentTimeInWords() {
        try {
            LocalTime currentTime = LocalTime.now();
            int hours = currentTime.getHour();
            int minutes = currentTime.getMinute();
    
            String wordTime=timeService.convertTimeToWord(hours,minutes);
            String result = "It's " + wordTime;
    
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid time format.Format must be HH:MM");
        }
    }
    

    @GetMapping("/convert-time")
    public ResponseEntity<String> convertToWords(@RequestParam("time") String timeStr) {
        try {
            String[] timeParts = timeStr.split(":");
    
            if (timeParts.length != 2) {
                return ResponseEntity.badRequest().body("Invalid time format.Format must be HH:MM");
            }
    
            int hours = Integer.parseInt(timeParts[0]);
            int minutes = Integer.parseInt(timeParts[1]);
    
            if ((hours < 0 || hours > 23 || minutes < 0 || minutes > 59) && !(hours == 24 && minutes == 0)) {
                throw new IllegalArgumentException("Invalid time format.Format must be HH:MM");
            }         
    
            String wordTime=timeService.convertTimeToWord(hours,minutes);
            String result = "It's " + wordTime;   
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid time format.Format must be HH:MM");
        }
    }
 

}
