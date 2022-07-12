package com.dzm.app.rest;

import com.dzm.app.domain.Company;
import com.dzm.app.domain.Station;
import com.dzm.app.dto.CompanyDto;
import com.dzm.app.dto.SearchRequestDto;
import com.dzm.app.dto.StationDto;
import com.dzm.app.service.CompanyService;
import com.dzm.app.service.StationService;
import com.dzm.app.service.errors.FieldErrorException;
import com.dzm.app.service.errors.ObjectNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.MissingRequiredPropertiesException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @ApiOperation(value = "Get All Companies", notes = "Returns list of companies")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Company.class, responseContainer = "List")
    })
    @GetMapping("company")
    public ResponseEntity<List<Company>> getCompany() {
        log.info("REST request to get all companies");
        return ResponseEntity.of(Optional.of(companyService.getAll()));
    }

    @ApiOperation(value = "Creat Company")
    @PostMapping(value = "company", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Company> addCompany(@RequestBody CompanyDto companyDto) {
        log.info("REST request to save company : {}", companyDto);
        Company company = new Company();
        company.setStations(companyDto.getStations());
        company.setCompanyName(companyDto.getCompanyName());
        return ResponseEntity.ok(companyService.createCompany(company, companyDto.getParentCompanyId()));
    }

    @ApiOperation(value = "Add Station to Company")
    @PostMapping(value = "station", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Station> addStationToCompany(@RequestBody StationDto stationDto) {
        log.info("REST request to add station to company : {}", stationDto);
        Station station = Station.builder().stationName(stationDto.getStationName()).latitude(stationDto.getLatitude()).longitude(stationDto.getLongitude()).build();
        try {
            return ResponseEntity.ok(stationService.createStation(station, stationDto.getCompanyId()));
        } catch (FieldErrorException fieldErrorException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, fieldErrorException.getMessage());
        } catch (ObjectNotFoundException objectNotFoundException) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, objectNotFoundException.getMessage());
        }
    }

    @ApiOperation(value = "Find The Nearest Station")
    @PostMapping(value = "find-nearest-station", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Station.class, responseContainer = "List")
    })
    public ResponseEntity<Station> findTheNearestStation(@RequestBody SearchRequestDto requestDto) {
        log.info("REST request to find nearest station");
        return ResponseEntity.of(stationService.findNearestStations(requestDto.getCompanyId(), requestDto.getLatitude(), requestDto.getLongitude(), requestDto.getRadius()));
    }
}
