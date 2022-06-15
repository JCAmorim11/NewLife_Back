package com.newlife.Newlife.repository;

import com.newlife.Newlife.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface VisitorRepository extends JpaRepository<Visitor,Long>, JpaSpecificationExecutor<Visitor> {

    @Query("SELECT a FROM Visitor a WHERE a.apartment = :apartment")
    Optional<Visitor> findByApartment(@Param("apartment") String apartment);
}
