package com.dtinone.datashare.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dtinone.datashare.entity.DictType;
import com.dtinone.datashare.mapper.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.dtinone.datashare.entity.Catalog;
import com.dtinone.datashare.entity.InformationContents;
import com.dtinone.datashare.entity.ListDataContent;
import com.dtinone.datashare.service.IInformationContents;
import com.dtinone.datashare.util.RD;
import com.dtinone.datashare.util.Utils;
import com.github.pagehelper.util.StringUtil;

@Service
@Slf4j
public class InformationContentsImpl implements IInformationContents {

    @Autowired
    private InformationContentsMapper mapper;

    @Autowired
    private CatalogMapper catalogMapper;
    
    @Resource
    private ListDataContentMapper listDataMapper;

    @Resource
    private DictTypeMapper dictMapper;

    @Override
    @Transactional
    public RD<?> addItem(InformationContents param) {
        try {
            String uid = Utils.getUID();
            List<ListDataContent> listData = param.getListData();
            listData.stream().forEach(o->{
            	o.setParentId(uid);
            });
            if(listData.size() > 0) listDataMapper.addItemBatch(listData);
            param.setIdKey(uid);
            return RD.isOk().setData(mapper.addItem(param));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return RD.isFail().setMsg("操作失败");
        }
    }

    @Override
    public RD<?> delItem(List<String> idKeys) {
        try {
            //同时删除信息项
            return RD.isOk().setData(mapper.delItem(idKeys));
        } catch (Exception e) {
            log.error(e.getMessage());
            return RD.isFail().setMsg("操作失败");
        }
    }

    @Override
    @Transactional
    public RD<?> changeRemove(List<String> idKeys, boolean isOpen) {
        try {
        	//同时删除对应信息项
        	List<ListDataContent> listDatas = listDataMapper.queryInfoForParentId(idKeys);
        	List<Integer> listDataIdks = listDatas.stream().map(ListDataContent::getIdKey).collect(Collectors.toList());
            if(listDataIdks.size()>0) listDataMapper.changeRemove(listDataIdks, false);
            return RD.isOk().setData(mapper.changeRemove(idKeys, false));
        } catch (Exception e) {
            log.error(e.getMessage());
            return RD.isFail().setMsg("操作失败");
        }
    }

    @Override
    @Transactional
    public RD<?> updateItem(InformationContents param) {
        try {
        	List<ListDataContent> listData = param.getListData();
        	//先删除 后修改
            listDataMapper.delItemForParentId(param.getIdKey());
            listData.forEach(o ->{
                if(o.getParentId() == null){
                    //insert
                    o.setParentId(param.getIdKey());
                }
            });
            if(listData.size() > 0) listDataMapper.addItemBatch(listData);
            return RD.isOk().setData(mapper.updateItem(param));
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return RD.isFail().setMsg("操作失败");
        }
    }

    @Override
    public RD<?> updateBatchStatus(List<String> idKeys, Integer status, String remark){
        try {
            return RD.isOk().setData(mapper.updateBatchStatus(idKeys, status, remark));
        } catch (Exception e) {
            log.error(e.getMessage());
            return RD.isFail().setMsg("操作失败");
        }
    }
    @Override
    public RD<?> updateShengHe(List<String> idKeys, Integer status, String remark){
        try {
            return RD.isOk().setData(mapper.updateShengHe(idKeys, status, remark));
        } catch (Exception e) {
            log.error(e.getMessage());
            return RD.isFail().setMsg("操作失败");
        }
    }

    @Override
    public RD<?> updateXiaJia(List<String> idKeys, Integer status, String remark){
        try {
            return RD.isOk().setData(mapper.updateXiaJia(idKeys, status, remark));
        } catch (Exception e) {
            log.error(e.getMessage());
            return RD.isFail().setMsg("操作失败");
        }
    }

    @Override
    public RD<?> updateFaBu(List<String> idKeys, Integer status, String networkId, String remark){
        try {
            return RD.isOk().setData(mapper.updateFaBu(idKeys, status, networkId, remark));
        } catch (Exception e) {
            log.error(e.getMessage());
            return RD.isFail().setMsg("操作失败");
        }
    }

	@Override
	public RD<?> addItemJson(Map<String,Object> param) {

        try {
            //修改功能-有疑问? 前端
            if(param.get("idKey") != null){
                return updItemJson(param,String.valueOf(param.get("idKey")));
            }
            //以下是新增
            InformationContents waitPostData = new InformationContents();
            waitPostData.setCatagoryCode(Integer.parseInt(String.valueOf(param.get("catagoryCode"))));
            Object listData = param.get("listData");
            //固定属性 后台直接get
            List<ListDataContent> listDataContent = JSON.parseArray(JSON.toJSONString(listData),ListDataContent.class);

            String tableName = handleNullForAddOperation(param.get("tableName"));

            String providerFirst = handleNullForAddOperation(param.get("providerFirst"));
            String providerSecond = handleNullForAddOperation(param.get("providerSecond"));
            waitPostData.setTableName(tableName);
            waitPostData.setProviderFirst(providerFirst);
            waitPostData.setProviderSecond(providerSecond);
            waitPostData.setListData(listDataContent);

            String demoModeId = handleNullForAddOperation(param.get("demoModeId"));
            if(StringUtils.isNotBlank(demoModeId)){
                waitPostData.setDemoModeId(Integer.parseInt(demoModeId));
            }

            String categoryRelationCode = handleNullForAddOperation(param.get("categoryRelationCode"));
            if(StringUtils.isNotBlank(categoryRelationCode)){
                waitPostData.setCategoryRelationCode(Integer.parseInt(categoryRelationCode));
            }
            //删除map中已经使用的
            param.keySet().removeIf(key -> key.equals("listData"));
            param.keySet().removeIf(key -> key.equals("tableName"));
            param.keySet().removeIf(key -> key.equals("providerFirst"));
            param.keySet().removeIf(key -> key.equals("providerSecond"));
            param.keySet().removeIf(key -> key.equals("catagoryCode"));
            param.keySet().removeIf(key -> key.equals("demoModeId"));
            param.keySet().removeIf(key -> key.equals("categoryRelationCode"));


            waitPostData.setTableContent(JSON.toJSONString(param));

            return addItem(waitPostData);
        } catch (Exception e){
            log.error(e.getMessage());
            return RD.isFail().setMsg("操作失败");
        }
	}

	public RD<?> updItemJson(Map<String,Object> param, String idKey) {
        try {
            //确认param中 catagoryCode是数字还是名字
            InformationContents waitPostData = new InformationContents();
            Catalog catalog = new Catalog();
            List<Catalog> allCatalog = catalogMapper.queryInfo(catalog);
            Object listData = param.get("listData");
            //固定属性 后台直接get
            List<ListDataContent> listDataContent = null;
            if (listData != null) {
                listDataContent = JSON.parseArray(JSON.toJSONString(listData), ListDataContent.class);
            }

            if (param.get("tableName") != null) {
                waitPostData.setTableName(String.valueOf(param.get("tableName")));
            }
            if (param.get("providerFirst") != null) {
                waitPostData.setProviderFirst(String.valueOf(param.get("providerFirst")));
            }
            if (param.get("providerSecond") != null) {
                waitPostData.setProviderSecond(String.valueOf(param.get("providerSecond")));
            }
            if (param.get("catagoryCode") != null) {
                waitPostData.setCatagoryCode(Integer.parseInt(String.valueOf(param.get("catagoryCode"))));
            }
            //检查集合中是否有信息项的idKey
            waitPostData.setListData(listDataContent);
            String categoryRelationCode = handleNullForAddOperation(param.get("categoryRelationCode"));
            if (StringUtils.isNotBlank(categoryRelationCode)){
                waitPostData.setCategoryRelationCode(Integer.parseInt(categoryRelationCode));
            }

            //删除map中已经使用的
            param.keySet().removeIf(key -> key.equals("listData"));
            param.keySet().removeIf(key -> key.equals("tableName"));
            param.keySet().removeIf(key -> key.equals("providerFirst"));
            param.keySet().removeIf(key -> key.equals("providerSecond"));
            param.keySet().removeIf(key -> key.equals("catagoryCode"));
            param.keySet().removeIf(key -> key.equals("categoryRelationCode"));
            waitPostData.setTableContent(JSON.toJSONString(param));
            String catalogName = Utils.getParentNodeForCatagoryCode("", waitPostData.getCatagoryCode(), allCatalog);
            waitPostData.setCatagoryName(catalogName);
            waitPostData.setIdKey(idKey);
            return updateItem(waitPostData);
        }catch (Exception e){
            log.error(e.getMessage());
            return RD.isFail().setMsg("操作失败");
        }
	}

    @Override
    public RD<?> queryInfo(InformationContents condition, Integer pageNo, Integer pageSize) {
        try {
            //获得所有的目录 - 减少sql
            List<Catalog> catalogs = catalogMapper.queryInfo(null);
            catalogs = Utils.treeCatalogList(catalogs, condition.getCatagoryCode());
            List<Integer> collect = catalogs.stream().map(Catalog::getIdKey).collect(Collectors.toList());
            collect.add(condition.getCatagoryCode());
            PageHelper.startPage(pageNo, pageSize);
            List<InformationContents> queryInfo = mapper.queryInfo(condition,collect);

            return RD.isOk().setData(new PageInfo(queryInfo));
        } catch (Exception e) {
            log.error(e.getMessage());
            return RD.isFail().setMsg("操作失败");
        }

    }

    private String handleNullForAddOperation(Object o){
        String str = "";
        if(o != null) str = o.toString();
        return str;
    }

}
