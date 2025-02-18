package com.usa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usa.entity.PlanEntity;

public interface IPlanRepository extends JpaRepository<PlanEntity, Integer> {

}
