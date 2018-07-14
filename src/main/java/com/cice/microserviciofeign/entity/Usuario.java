package com.cice.microserviciofeign.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name="usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue
    @Column(name ="id")
    private Long id;
    @Column(name ="login")
    private String login;
    @Column(name ="password")
    private String password;
}
