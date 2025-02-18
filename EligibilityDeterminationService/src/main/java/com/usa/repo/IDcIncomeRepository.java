package com.usa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usa.entity.DcIncomeEntity;

public interface IDcIncomeRepository extends JpaRepository<DcIncomeEntity, Integer> {
		public DcIncomeEntity findByCaseNo(int caseno);
}
