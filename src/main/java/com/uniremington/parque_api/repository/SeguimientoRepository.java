package com.uniremington.parque_api.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.uniremington.parque_api.entity.CasosSeguimiento;

public interface SeguimientoRepository extends JpaRepository<CasosSeguimiento, Long> {}