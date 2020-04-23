package com.kalo.dao;

import com.kalo.entity.databaseObject.SequenceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Kalo
 * @create 2020-04-22 13:13
 */
@Mapper
public interface SequenceRepository extends JpaRepository<SequenceInfo, String> {
}
