package com.example.pet.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "socks", schema = "socks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Socks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "socks_id")
    private Long id;

    @Column(name = "color")
    private String color;
    @Column(name = "cottonPart")
    private Integer cottonPart;
}
