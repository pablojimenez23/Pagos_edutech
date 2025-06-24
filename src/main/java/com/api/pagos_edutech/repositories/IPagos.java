package com.api.pagos_edutech.repositories;

import com.api.pagos_edutech.models.ModeloPagos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPagos extends JpaRepository<ModeloPagos, Long> {
}
