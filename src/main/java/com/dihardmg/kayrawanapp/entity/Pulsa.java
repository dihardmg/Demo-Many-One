package com.dihardmg.kayrawanapp.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author : Otorus
 * @since : 1/4/18
 */
@Entity
@Table(name = "pulsa")
@Data
public class Pulsa {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotEmpty
    @NotNull
    @Size(min = 3, max = 255)
    @Column(nullable = false)
    private String paket;


    @Size(max = 255)
    private String keterangan;

}
