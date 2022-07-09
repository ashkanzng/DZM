package com.dzm.app.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

/**
 * Base abstract class for entities
 */
@MappedSuperclass
@Data
public abstract class AbstractEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

}
