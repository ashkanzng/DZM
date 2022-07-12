package com.dzm.app.rest;

import com.dzm.app.domain.Company;
import com.dzm.app.domain.Station;
import com.dzm.app.dto.CompanyDto;
import com.dzm.app.dto.SearchRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.hamcrest.Matchers.hasItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class CompanyResourceTest {

    private static final String API_URL_COMPANY = "/api/company";
    private static final String API_URL_SEARCH = "/api/find-nearest-station";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getCompany() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(API_URL_COMPANY)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void addCompany() throws Exception {
        Station station = Station.builder()
                .stationName("Station Republic Square")
                .latitude(40.177391879910395)
                .longitude(44.51295960190806)
                .build();
        CompanyDto companyDto = CompanyDto.builder().companyName("Company A").stations(Set.of(station)).build();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mockMvc.perform(MockMvcRequestBuilders.post(API_URL_COMPANY).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(companyDto))).andExpect(status().isOk());
    }

    @Test
    void findTheNearestStation() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        SearchRequestDto searchRequestDto = new SearchRequestDto(40.17789813736282,44.50408223162574,50,null);
        mockMvc.perform(MockMvcRequestBuilders.post(API_URL_SEARCH).contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(searchRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.stationName").value("Station Paronyan"));

    }
}