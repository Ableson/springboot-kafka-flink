package com.dtinone.datashare.mapper;

import com.dtinone.datashare.entity.InformationContent;
import com.dtinone.datashare.entity.InformationContentExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface InformationContentMapper {
    long countByExample(InformationContentExample example);

    int deleteByExample(InformationContentExample example);

    int insert(InformationContent record);

    int insertSelective(InformationContent record);

    List<InformationContent> selectByExampleWithBLOBs(InformationContentExample example);

    List<InformationContent> selectByExample(InformationContentExample example);

    int updateByExampleSelective(@Param("record") InformationContent record, @Param("example") InformationContentExample example);

    int updateByExampleWithBLOBs(@Param("record") InformationContent record, @Param("example") InformationContentExample example);

    int updateByExample(@Param("record") InformationContent record, @Param("example") InformationContentExample example);
}