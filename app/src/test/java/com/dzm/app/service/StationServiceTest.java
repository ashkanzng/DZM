package com.dzm.app.service;

import com.dzm.app.domain.Station;
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

    @Test
    void findNearestStations() {
        Optional<Station> station = stationService.findNearestStations(null,40.17789813736282,44.50408223162574,50);
        assertTrue(station.isPresent());
        assertEquals(station.get().getStationName(),"Station Paronyan");
    }
}