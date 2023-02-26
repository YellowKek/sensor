package com.example.demo.models;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="sensors")
@Data
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    @NotNull(message = "name should not be empty")
    private String name;
}
