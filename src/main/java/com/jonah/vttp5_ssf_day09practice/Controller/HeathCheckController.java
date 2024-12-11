package com.jonah.vttp5_ssf_day09practice.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HeathCheckController {

    @GetMapping("/health")
    public ResponseEntity<String> checkProgram(){
        //if STATEMENT can connect to redis or open file or some critical function
        if(true){
            return ResponseEntity.ok("{}"); //successful
        }
        return ResponseEntity.status(400).body("{}"); //error

    }
    
}
