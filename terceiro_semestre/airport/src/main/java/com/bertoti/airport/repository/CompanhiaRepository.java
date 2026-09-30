package com.bertoti.airport.repository;


import com.bertoti.airport.model.Companhia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanhiaRepository extends JpaRepository<Companhia, String> {
}