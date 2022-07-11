package com.dzm.app.rest;

import com.dzm.app.domain.Station;
import com.dzm.app.dto.CompanyDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class CompanyResourceTest {

    private static final String API_URL = "/api/company";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getCompany() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get(API_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void addCompany() throws Exception {

        CompanyDto companyDto = Mockito.mock(CompanyDto.class);
        Station station = Mockito.mock(Station.class);
        Mockito.when(station.getStationName()).thenReturn("Station A");
        Mockito.when(station.getLatitude()).thenReturn(32.2377482);
        Mockito.when(station.getLongitude()).thenReturn(12.2377482);

        Mockito.when(companyDto.getCompanyName()).thenReturn("Company A");
        Mockito.when(companyDto.getStations()).thenReturn(Set.of(station));
        ObjectMapper mapper = new ObjectMapper();

        System.out.println(mapper.valueToTree(companyDto).toString());

        //mockMvc.perform(MockMvcRequestBuilders.post(API_URL).contentType(MediaType.APPLICATION_JSON).content(mapper.valueToTree(companyDto).asText())).andExpect(status().isOk());

    }

//    @Test
//    void findTheNearestStation() {
//    }
}