package com.ashokit.persistencelayer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashokit.entities.CityMasterEntity;

@Repository
public interface CityMasterRepo extends JpaRepository<CityMasterEntity, Integer> {

	List<CityMasterEntity> findAllByStateId(Integer stateId);

	public List<CityMasterEntity> findByStateId(Integer stateId);

}
