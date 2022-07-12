package com.dzm.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StationDto {

    @JsonProperty("station_name")
    private String stationName;
    @JsonProperty("company_id")
    private Long companyId;
    private Double latitude;
    private Double longitude;
}
