package com.dlsys.sifood.ms.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Entity
@Table(name = "template")
public class Template implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotEmpty(message = "no puede ser vacio")
    private String name;

    @Column
    @NotEmpty(message = "no puede ser vacio")
    private String url;

    @Column
    private Integer flag;

    @PrePersist
    public void beforeSave() {
        this.flag = 1;
    }
}
