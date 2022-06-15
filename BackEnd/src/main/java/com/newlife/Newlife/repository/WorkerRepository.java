package com.newlife.Newlife.repository;

import com.newlife.Newlife.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker,Long>, JpaSpecificationExecutor<Worker> {

    @Query("SELECT a FROM Worker a WHERE a.apartment = :apartment")
    Optional<Worker> findByApartment(@Param("apartment") String apartment);
}
