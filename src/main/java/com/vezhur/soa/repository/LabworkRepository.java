package com.vezhur.soa.repository;

import com.vezhur.soa.entity.LabworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LabworkRepository extends JpaRepository<LabworkEntity, Integer> {

    @Query("SELECT SUM(minimalPoint) FROM LabworkEntity")
    Float minimalPointSum();
}
