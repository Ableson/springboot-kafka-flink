package com.dtinone.datashare.service.impl;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.dtinone.datashare.entity.ListDataContent;
import com.dtinone.datashare.mapper.ListDataContentMapper;
import com.dtinone.datashare.service.IListDataContent;
import com.dtinone.datashare.util.RD;
import com.dtinone.datashare.util.Utils;

@Service
@Slf4j
public class ListDataContentImpl implements IListDataContent{
	
	@Resource
	private ListDataContentMapper mapper;

	@Override
	public RD<?> addItem(ListDataContent param) {
		param.setParentId(Utils.getUID());
		try {
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
	public RD<?> updateItem(ListDataContent param) {
		try {
			return RD.isOk().setData(mapper.updateItem(param));
		} catch (Exception e) {
			log.error(e.getMessage());
			return RD.isFail().setMsg("操作失败");
		}
	}

	@Override
	public RD<?> queryInfo(ListDataContent condition) {
		try {
			return RD.isOk().setData(mapper.queryInfo(condition));
		} catch (Exception e) {
			log.error(e.getMessage());
			return RD.isFail().setMsg("操作失败");
		}
	}

}
