package com.dzm.app.dto;

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
    private int radius;
    private long companyId;
}
