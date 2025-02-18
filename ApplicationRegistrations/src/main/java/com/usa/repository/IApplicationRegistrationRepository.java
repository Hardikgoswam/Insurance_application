package com.usa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usa.entity.CitizenAppRegisterEntity;

public interface IApplicationRegistrationRepository extends JpaRepository<CitizenAppRegisterEntity,Integer> {

}
