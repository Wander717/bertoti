package com.bertoti.airport.repository;

import com.bertoti.airport.model.Passageiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassageiroRepository extends JpaRepository<Passageiro, String> {
}