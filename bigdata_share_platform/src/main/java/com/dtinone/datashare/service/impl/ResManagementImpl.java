package com.dtinone.datashare.service.impl;

import com.dtinone.datashare.entity.ResManagement;
import com.dtinone.datashare.entity.ResManagementExample;
import com.dtinone.datashare.mapper.ResManagementMapper;
import com.dtinone.datashare.service.IResManagement;
import com.dtinone.datashare.util.RD;
import com.dtinone.datashare.util.Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class ResManagementImpl implements IResManagement {
    
    @Resource
    private ResManagementMapper resMapper;
    
    @Override
    public RD<?> addOrUpdItem(ResManagement data) {

        try { Integer idKey = null;
            if(StringUtils.isNotBlank(data.getIdKey())){
                //修改
                idKey = resMapper.updateByCondition(data);
            }else{
                //新增
                data.setIdKey(Utils.getUID());
                idKey = resMapper.insert(data);
            }
            log.info("操作成功:id="+idKey);
            return RD.isOk().setData(idKey);
        } catch (Exception e){
            log.info(e.getMessage());
            return RD.isFail().setMsg("操作失败");
        }
    }

    @Override
    public RD<?> queryInfo(ResManagement data) {
        try {
            if(data.getPageNo() != null && data.getPageSize() != null){
                PageHelper.startPage(data.getPageNo(),data.getPageSize());
            }
            List<ResManagement> resManagements = resMapper.selectByCondition(data);
            log.info("查询成功:数量="+resManagements.size());
            return RD.isOk().setData(new PageInfo(resManagements));
        } catch (Exception e){
            log.info(e.getMessage());
            return RD.isFail().setMsg("操作失败");
        }
    }

    @Override
    public RD<?> updateItem(ResManagement data) {
        try{
            int code = resMapper.updateByCondition(data);
            log.info("修改成功:数量="+code);
            return RD.isOk().setData(code);
        } catch (Exception e){
            log.info(e.getMessage());
            return RD.isFail().setMsg("操作失败");
        }
    }

    @Override
    public RD<?> delItem(ResManagement data) {
        try {
            ResManagementExample example = new ResManagementExample();
            ResManagementExample.Criteria criteria = example.createCriteria();
            criteria.andIdKeyEqualTo(data.getIdKey());
            int code = resMapper.deleteByExample(example);
            log.info("删除成功:数量="+code);
            return RD.isOk().setData(code);
        } catch (Exception e){
            log.info(e.getMessage());
            return RD.isFail().setMsg("操作失败");
        }
    }
}
