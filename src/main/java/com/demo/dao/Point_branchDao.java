package com.demo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.pmp.entity.PointBranchEntity;

@Repository
public interface Point_branchDao {
	
	public PointBranchEntity findPointBranchById(@Param("pointId")Integer pointId);

}
