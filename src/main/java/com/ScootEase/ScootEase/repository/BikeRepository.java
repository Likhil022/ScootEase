package com.ScootEase.ScootEase.repository;

import com.ScootEase.ScootEase.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BikeRepository extends JpaRepository<Bike, Long> {

}
