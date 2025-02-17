package com.vezhur.soa.repository;

import com.vezhur.soa.entity.LabworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LabworkRepository extends JpaRepository<LabworkEntity, Integer>, JpaSpecificationExecutor<LabworkEntity> {

    @Query("SELECT SUM(minimalPoint) FROM LabworkEntity")
    Float getMinimalPointSum();

    // Этот запрос можно бы сделать без вложенности и не возвращая список, если бы @Query знала, что такое LIMIT 1
    // Не ну можно убрать вложенность, вернув весь список записей, но это не лучшее решение
    // А еще пришлось писать lab, т.к. "*" почему-то не работает
    @Query("SELECT lab FROM LabworkEntity lab WHERE lab.difficulty = (SELECT MIN(difficulty) FROM LabworkEntity)")
    List<LabworkEntity> getEasiestLabworks();

    Integer countAllByAuthor_Name(String authorName);
}
