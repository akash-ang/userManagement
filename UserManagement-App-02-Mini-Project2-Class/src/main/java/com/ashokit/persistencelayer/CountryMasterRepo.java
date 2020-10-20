package com.ashokit.persistencelayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashokit.entities.CountryMasterEntity;

@Repository
public interface CountryMasterRepo extends JpaRepository<CountryMasterEntity, Integer> {

}
