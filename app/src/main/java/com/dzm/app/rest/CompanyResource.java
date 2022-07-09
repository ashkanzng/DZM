package com.dzm.app.rest;

import com.dzm.app.repository.CompanyRepository;
import com.dzm.app.repository.StationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CompanyResource {

    private final Logger log = LoggerFactory.getLogger(CompanyResource.class);

    private final CompanyRepository companyRepository;

    private final StationRepository stationRepository;

    public CompanyResource(CompanyRepository companyRepository, StationRepository stationRepository) {
        this.companyRepository = companyRepository;
        this.stationRepository = stationRepository;
    }

    @GetMapping("company")
    public ResponseEntity<Object> getCompany(){
        return ResponseEntity.of(Optional.of(companyRepository.findAll()));
    }

    @GetMapping("station")
    public ResponseEntity<Object> getStations(){
        return ResponseEntity.of(Optional.of(stationRepository.findAllNearest(40.181634444726775,44.508565357788,25)));
    }
}
