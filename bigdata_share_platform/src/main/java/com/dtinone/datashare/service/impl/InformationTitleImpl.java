package com.dtinone.datashare.service.impl;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.dtinone.datashare.entity.InformationTitle;
import com.dtinone.datashare.mapper.InformationTitleMapper;
import com.dtinone.datashare.service.IInformationTitle;
import com.dtinone.datashare.util.RD;

@Service
@Slf4j
public class InformationTitleImpl implements IInformationTitle{
	
	@Resource
	private InformationTitleMapper mapper;

	@Override
	public RD<?> addItem(InformationTitle param) {
		try {
			if (param.getParentId() == null) return RD.isOk().setMsg("请指定模板");
			return RD.isOk().setData(mapper.addItem(param));
		} catch (Exception e) {
			log.error(e.getMessage());
			return RD.isFail().setMsg("操作失败");
		}
	}

	@Override
	public RD<?> delItem(List<Integer> idKeys) {
		try {
			return RD.isOk().setData(mapper.delItem(idKeys));
		} catch (Exception e) {
			log.error(e.getMessage());
			return RD.isFail().setMsg("操作失败");
		}
	}

	@Override
	public RD<?> changeRemove(List<Integer> idKeys, boolean isOpen) {
		try {
			return RD.isOk().setData(mapper.changeRemove(idKeys, false));
		} catch (Exception e) {
			log.error(e.getMessage());
			return RD.isFail().setMsg("操作失败");
		}
		
	}

	@Override
	public RD<?> updateItem(InformationTitle param) {
		try {
			return RD.isOk().setData(mapper.updateItem(param));
		} catch (Exception e) {
			log.error(e.getMessage());
			return RD.isFail().setMsg("操作失败");
		}
	}

	@Override
	public RD<?> queryInfo(InformationTitle condition) {
		try {
			return RD.isOk().setData(mapper.queryInfo(condition));
		} catch (Exception e) {
			log.error(e.getMessage());
			return RD.isFail().setMsg("操作失败");
		}
	}

}
