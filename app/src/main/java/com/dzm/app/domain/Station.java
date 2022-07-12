package com.dzm.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.util.Objects;

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
    @Override
    public int hashCode() {
        return Objects.hashCode(this.getId());
    }

}
