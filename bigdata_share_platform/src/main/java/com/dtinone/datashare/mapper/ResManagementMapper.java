package com.dtinone.datashare.mapper;

import com.dtinone.datashare.entity.ResManagement;
import com.dtinone.datashare.entity.ResManagementExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ResManagementMapper {
    long countByExample(ResManagementExample example);

    int deleteByExample(ResManagementExample example);

    int insert(ResManagement record);

    int insertSelective(ResManagement record);

    List<ResManagement> selectByExample(ResManagementExample example);

    List<ResManagement> selectByCondition(ResManagement condition);

    int updateByExampleSelective(@Param("record") ResManagement record, @Param("example") ResManagementExample example);

    int updateByExample(@Param("record") ResManagement record, @Param("example") ResManagementExample example);

    int updateByCondition(ResManagement condition);
}