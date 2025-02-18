package com.usa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usa.entity.DcEducationEntity;

public interface IDcEducationRepository extends JpaRepository<DcEducationEntity, Integer> {
	public DcEducationEntity findByCaseNo(int caseno);
}
