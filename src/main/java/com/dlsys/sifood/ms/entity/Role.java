package com.dlsys.sifood.ms.entity;

import lombok.Data;

import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


@Data
@Entity
@Table(name = "role")
public class Role implements Serializable{

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
    private Integer flag;

    @PrePersist
    public void beforeSave(){
        this.flag = 1;
    }

}
