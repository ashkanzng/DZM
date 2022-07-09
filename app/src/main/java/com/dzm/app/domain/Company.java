package com.dzm.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.Objects;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@Table(name = "company")
public class Company extends AbstractEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "company_name")
    private String companyName;

    @ManyToOne
    @JoinColumn(name="parent_id")
    @JsonIgnore
    private Company parentCompany;

    @OneToMany(mappedBy = "parentCompany",cascade = CascadeType.ALL)
    private Set<Company> companies;

    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL)
    private Set<Station> stations;

    public void setStations(Set<Station> stations) {
        stations.forEach(station -> station.setCompany(this));
        this.stations = stations;
    }

    public void setCompanies(Set<Company> companies) {
        companies.stream()
                .filter(company -> !Objects.equals(company, this))
                .forEach(company -> company.setParentCompany(this));
        this.companies = companies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        return getId() != null && getId().equals(((Company) o).getId());
    }
}
