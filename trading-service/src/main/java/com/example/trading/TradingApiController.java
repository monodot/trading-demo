package com.example.trading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradingApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TradingApiController.class);

    @GetMapping("/ready")
    ResponseEntity<String> test() {
        LOGGER.info("Received ready endpoint request");
        return new ResponseEntity<>("Ready", HttpStatus.OK);
    }

    @PostMapping("/trades")
    ResponseEntity<String> newTrade(@RequestBody Trade trade) {
        // LOG something here, post transaction to Redis, etc.
        LOGGER.info("Received new trade: {}", trade);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
