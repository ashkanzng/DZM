package com.dzm.app.rest;

import com.dzm.app.domain.Company;
import com.dzm.app.domain.Station;
import com.dzm.app.dto.SearchRequestDto;
import com.dzm.app.repository.CompanyRepository;
import com.dzm.app.service.CompanyService;
import com.dzm.app.service.StationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ExampleProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CompanyResource {

    private final Logger log = LoggerFactory.getLogger(CompanyResource.class);

    private final CompanyService companyService;

    private final StationService stationService;

    public CompanyResource(CompanyService companyService, StationService stationService) {
        this.companyService = companyService;
        this.stationService = stationService;
    }

    @ApiOperation(value = "getCompany", notes = "Returns list of companies")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Company.class, responseContainer = "List")
    })
    @GetMapping("company")
    public ResponseEntity<List<Company>> getCompany() {
        return ResponseEntity.of(Optional.of(companyService.getAll()));
    }

    @PostMapping(value = "company",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Company> addCompany(@RequestBody Company company) {
        return ResponseEntity.ok(companyService.createCompany(company));
    }

    @PostMapping(value = "find-nearest-station", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Station.class, responseContainer = "List")
    })
    public ResponseEntity<Station> findTheNearestStation(@RequestBody SearchRequestDto requestDto) {
        return ResponseEntity.of(stationService.findNearestStations(
                requestDto.getCompanyId(),
                requestDto.getLatitude(),
                requestDto.getLongitude(),
                requestDto.getRadius()));
    }
}
