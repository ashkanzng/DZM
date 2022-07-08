package com.dzm.app.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serial;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "company")
@Data
public class Company extends AbstractEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "company")
    private Set<Station> stations;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        return getId() != null && getId().equals(((Company) o).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(stations);
    }
}
