package com.dzm.app.domain;

import java.io.Serial;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@Table(name = "station")
public class Station extends AbstractEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "station_name")
    private String stationName;

    @ManyToOne
    @JoinColumn(name="company_id")
    @JsonIgnore
    private Company company;

    @Column(name = "latitude", columnDefinition = "Decimal(20,12)")
    private Double latitude;

    @Column(name = "longitude", columnDefinition = "Decimal(20,12)")
    private Double longitude;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Station)) return false;
        return getId() != null && getId().equals(((Station)o).getId());
    }

}
