package com.bertoti.airport.repository;

import com.bertoti.airport.model.Aviao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AviaoRepository extends JpaRepository<Aviao, String> {
}