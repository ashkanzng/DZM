package com.dzm.app.domain;

import java.io.Serial;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "station")
@Data
public class Station extends AbstractEntity {
    @Serial
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name="company_id", nullable=false)
    private Company company;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Station)) return false;
        return getId() != null && getId().equals(((Station)o).getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(company);
    }
}
