package com.ScootEase.ScootEase.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Bike")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String bikename;

    @Column(nullable = false)
    private  String bikemodel;

    @Column(nullable = false)
    private int priceperday;

    @Column(nullable = false)
    private  int priceafterlimit;

    @Column(nullable = false)
    private int limitperday;

    @Column(nullable = false)
    private boolean availble;

}
