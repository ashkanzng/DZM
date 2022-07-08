package com.dzm.app.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "company")
@Builder
public class Company extends AbstractEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "company_name")
    private String companyName;

    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
    private Set<Station> stations;

    public void setStations(Set<Station> stations) {
        stations.forEach(station -> station.setCompany(this));
        this.stations = stations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        return getId() != null && getId().equals(((Company) o).getId());
    }

}
