package com.usa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usa.entity.EligibilityDetailsEntity;

public interface IEligibilityDetermineRepository extends JpaRepository<EligibilityDetailsEntity, Integer> {

}
