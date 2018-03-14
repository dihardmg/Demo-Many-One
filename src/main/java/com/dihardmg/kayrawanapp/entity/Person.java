package com.dihardmg.kayrawanapp.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author : Otorus
 * @since : 3/14/18
 */

@Entity
@Data
public class Person {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @NotNull
    @Column(nullable = false)
    private String nama;

    @NotNull
    @Column(nullable = false)
    @Size(min = 9, max = 14)
    private String nohp;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "id_pulsa", nullable = false)
    private Pulsa pulsa;

    

}
