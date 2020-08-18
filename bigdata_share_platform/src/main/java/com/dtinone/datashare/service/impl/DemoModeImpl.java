package com.dtinone.datashare.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.dtinone.datashare.entity.DemoMode;
import com.dtinone.datashare.entity.InformationTitle;
import com.dtinone.datashare.mapper.DemoModeMapper;
import com.dtinone.datashare.mapper.InformationTitleMapper;
import com.dtinone.datashare.service.IDemoMode;
import com.dtinone.datashare.util.RD;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class DemoModeImpl implements IDemoMode{
	
	@Resource
	private DemoModeMapper mapper;

	@Resource
	private InformationTitleMapper titleMapper;
	@Override
	public RD<?> addItem(DemoMode param) {
		try {
			DemoMode checkExists = new DemoMode();
			checkExists.setName(param.getName());
			List<DemoMode> isExists = mapper.queryInfo(checkExists);
			if (isExists.size()> 0) {
				return RD.isOk().setMsg("模板名字已经存在");
			}
			return RD.isOk().setData(mapper.addItem(param));
		} catch (Exception e) {
			log.error(e.getMessage());
			return RD.isFail().setMsg("操作失败");
		}
	}

	@Override
	@Transactional
	public RD<?> delItem(List<Integer> idKeys) {
		try {
			//删除模板并与之对应的title
			titleMapper.changeRemoveByDemoModel(idKeys);
			return RD.isOk().setData(mapper.delItem(idKeys));
		} catch (Exception e) {
			log.error(e.getMessage());
			return RD.isFail().setMsg("操作失败");
		}
	}

	@Override
	@Transactional
	public RD<?> changeRemove(List<Integer> idKeys, boolean isOpen) {
		try {
			//删除模板并与之对应的title
			titleMapper.changeRemoveByDemoModel(idKeys);
			return RD.isOk().setData(mapper.changeRemove(idKeys, false));
		} catch (Exception e) {
			log.error(e.getMessage());
			return RD.isFail().setMsg("操作失败");
		}
		
	}

	@Override
	public RD<?> updateItem(DemoMode param) {
		try {
			return RD.isOk().setData(mapper.updateItem(param));
		} catch (Exception e) {
			log.error(e.getMessage());
			return RD.isFail().setMsg("操作失败");
		}
	}

	@Override
	public RD<?> queryInfo(DemoMode condition) {
		try {
			return RD.isOk().setData(mapper.queryInfo(condition));
		} catch (Exception e) {
			log.error(e.getMessage());
			return RD.isFail().setMsg("操作失败");
		}
	}
	
	@Override
	public RD<?> queryInfoByName(DemoMode condition) {
		try {
			List<DemoMode> queryInfo = mapper.queryInfo(condition);
			//获得子项
			List<DemoMode> sunList = mapper.queryInfoByName(queryInfo);
			return RD.isOk().setData(sunList);
		} catch (Exception e) {
			log.error(e.getMessage());
			return RD.isFail().setMsg("操作失败");
		}
	}

	@Override
	public RD<?> queryTitleForDemoName(DemoMode condition) {
		List<InformationTitle> titles = new ArrayList<>();
		//获得模板的id
		List<DemoMode> demoModes = mapper.queryInfo(condition);
		try {
			if (demoModes.size()>0) {
				//通过模板的id设置到title的parentId 去获得其titles
				InformationTitle title = new InformationTitle();
				title.setParentId(demoModes.get(0).getIdKey());
				titles = titleMapper.queryInfo(title);
			}
//			tites
			titles.sort(( ord1, ord2) -> ord2.getRequire().compareTo(ord1.getRequire()));
			return RD.isOk().setData(titles).setMsg("查询成功");
		} catch (Exception e) {
			log.error(e.getMessage());
			return RD.isFail().setMsg("操作失败");
		}
	}

}
