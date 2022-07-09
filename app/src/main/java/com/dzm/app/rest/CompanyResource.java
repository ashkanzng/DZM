package com.dzm.app.rest;

import com.dzm.app.domain.Company;
import com.dzm.app.repository.CompanyRepository;
import com.dzm.app.service.StationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ExampleProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CompanyResource {

    private final Logger log = LoggerFactory.getLogger(CompanyResource.class);

    private final CompanyRepository companyRepository;

    private final StationService stationService;

    public CompanyResource(CompanyRepository companyRepository, StationService stationService) {
        this.companyRepository = companyRepository;
        this.stationService = stationService;
    }

    @ApiOperation(value = "getCompany", notes = "Returns list of companies")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Company.class, responseContainer = "List" )
    })
    @GetMapping("company")
    public ResponseEntity<List<Company>> getCompany(){
        return ResponseEntity.of(Optional.of(companyRepository.findAll()));
    }

    @GetMapping("station")
    public ResponseEntity<Object> getStations(){
        log.debug("REST request to get nearest station");
        return ResponseEntity.of(Optional.of(stationService.findAllNearestStations(102L)));
    }
}
