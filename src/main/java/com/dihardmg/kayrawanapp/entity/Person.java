package com.dihardmg.kayrawanapp.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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

    @NotEmpty
    @NotNull
    @Column(nullable = false)
    private String nama;


    @Pattern(regexp="(^$|[0-9]{9,12})")
    @Column(nullable = false)
    private String nohp;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "id_pulsa", nullable = false)
    private Pulsa pulsa;

    

}
