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
public class SearchRequestDto {
    private Double latitude;
    private Double longitude;
    private Integer radius;
    @JsonProperty("company_id")
    private Long companyId;
}
