package com.newlife.Newlife.repository;

import com.newlife.Newlife.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResidentRepository extends JpaRepository<Resident,Long>, JpaSpecificationExecutor<Resident> {

    @Query("SELECT a FROM Resident a WHERE a.apartment = :apartment")
    Optional<Resident> findByApartment(@Param("apartment") String apartment);

    @Query("SELECT m FROM Resident m WHERE m.apartment = :apartment")
    List<Resident> findAllByApartment(@Param("apartment") String apartment);

    @Query("SELECT a FROM Resident a WHERE a.cpf = :cpf")
    Optional<Resident> findByCpf(@Param("cpf") String cpf);

    @Query("SELECT a FROM Resident a WHERE a.id = :id")
    Optional<Resident> findById(@Param("id") long id);


}
