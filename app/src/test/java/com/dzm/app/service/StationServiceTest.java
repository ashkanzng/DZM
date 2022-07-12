package com.dzm.app.service;

import com.dzm.app.domain.Company;
import com.dzm.app.domain.Station;
import com.dzm.app.repository.CompanyRepository;
import com.dzm.app.repository.StationRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class StationServiceTest {

    @Autowired
    private StationService stationService;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private StationRepository stationRepository;

    @Test
    void findNearestStations() {
        Optional<Station> station = stationService.findNearestStations(null,40.17789813736282,44.50408223162574,50);
        assertTrue(station.isPresent());
        assertEquals(station.get().getStationName(),"Station Paronyan");
    }

    @Test
    void createStation() {
        companyRepository.findAll().stream().findAny().ifPresent( company -> {
            Station newStation = stationService.createStation(new Station("Vardanants",null,40.17550826613885,44.52036023986451),company.getId());
            assertEquals(newStation.getCompany().getId(),company.getId());
        });
    }
}