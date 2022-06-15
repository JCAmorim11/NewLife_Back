package com.newlife.Newlife.repository;

import com.newlife.Newlife.entity.Vehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface VehiclesRepository extends JpaRepository<Vehicles,Long>, JpaSpecificationExecutor<Vehicles> {

    @Query("SELECT a FROM Vehicles a WHERE a.plate = :plate")
    Optional<Vehicles> findByPlate(@Param("plate") String plate);
}
