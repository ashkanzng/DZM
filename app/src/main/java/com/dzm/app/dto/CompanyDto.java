package com.dzm.app.dto;

import com.dzm.app.domain.Station;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    @JsonProperty("parent_company_id")
    private long parentCompanyId;
    @JsonProperty("company_name")
    private String companyName;
    private Set<Station> stations;
}
