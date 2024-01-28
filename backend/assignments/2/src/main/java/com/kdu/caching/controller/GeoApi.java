package com.kdu.caching.controller;

import com.kdu.caching.dto.ReverseGeoCodeResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kdu.caching.dto.GeoCodeResponseDto;
import com.kdu.caching.services.GeoCodingService;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for handling API endpoints related to geocoding and reverse geocoding.
 */
@Slf4j
@RestController
public class GeoApi {
    private static final Logger logger = LoggerFactory.getLogger(GeoApi.class);


    private final GeoCodingService geoCodingService;

    public GeoApi(GeoCodingService geoCodingService) {
        this.geoCodingService = geoCodingService;
    }

    @GetMapping("/geocoding")
    public GeoCodeResponseDto geocoding(@RequestParam String address) {
        logger.info("Geocode request: {}",address);
        return geoCodingService.getGeoCode(address);
    }
    @GetMapping("/reverse-geocoding")
    public String reverseGeocoding(@RequestParam("latitude") double latitude, @RequestParam("longitude") double longitude) {
        logger.info("Reverse geocode request: {}, {}",latitude,longitude);
        return geoCodingService.getAddress(new ArrayList<>(List.of(latitude, longitude)));
    }
}

