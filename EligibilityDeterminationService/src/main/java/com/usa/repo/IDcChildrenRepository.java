package com.usa.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usa.entity.DcChildrenEntity;

public interface IDcChildrenRepository extends JpaRepository<DcChildrenEntity, Integer> {
	public List<DcChildrenEntity> findByCaseNo(int caseNo);
}
