package com.dtinone.datashare.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dtinone.datashare.entity.Catalog;
import com.dtinone.datashare.entity.InformationContent;
import com.dtinone.datashare.entity.InformationContentExample;
import com.dtinone.datashare.entity.ListDataContent;
import com.dtinone.datashare.mapper.CatalogMapper;
import com.dtinone.datashare.mapper.InformationContentMapper;
import com.dtinone.datashare.mapper.ListDataContentMapper;
import com.dtinone.datashare.service.ContentService;
import com.dtinone.datashare.util.Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private InformationContentMapper mapper;

    @Autowired
    private CatalogMapper catalogMapper;

    @Autowired
    private ListDataContentMapper listDataMapper;

    @Override
    public PageInfo<InformationContent> list(InformationContent content, Integer pageNo, Integer pageSize, Integer menu) {
        InformationContentExample example = new InformationContentExample();
        InformationContentExample.Criteria criteria = example.createCriteria();
        //获得当前目录和子目录
        Catalog catalog = new Catalog();
        List<Catalog> catalogs = catalogMapper.queryInfo(catalog);
        Catalog parentObj = null;
        for(Catalog o : catalogs){
            if (o.getIdKey() == content.getCatagoryCode()){
                parentObj = o;
                break;
            }
        }
        catalogs = Utils.treeCatalogList(catalogs, content.getCatagoryCode());
        if (parentObj != null) catalogs.add(0, parentObj);

        List<Integer> collect = catalogs.stream().map(Catalog::getIdKey).collect(Collectors.toList());
        collect.add(content.getCatagoryCode());

        switch (menu){
            case 0:
            case 1:
                criteria.andStatusEqualTo(0);
                break;
            case 2:
                criteria.andStatusEqualTo(1);
                break;
            case 3:
                ArrayList<Integer> list = new ArrayList<>();
                list.add(2);
                list.add(4);
                criteria.andStatusIn(list);
                break;
            case 4:
                criteria.andStatusEqualTo(3);
                break;
            case 5:

            default:
                break;
        }
        criteria.andRemoveEqualTo(0);
        criteria.andCatagoryCodeIn(collect);
        example.setOrderByClause("create_time desc");

        if (StringUtils.isNotBlank(content.getTableName())){
            criteria.andTableNameLike("%"+content.getTableName()+"%");
        }
        if (StringUtils.isNotBlank(content.getProviderFirst())){
            criteria.andProviderFirstLike("%"+content.getProviderFirst()+"%");
        }
        if (StringUtils.isNotBlank(content.getProviderSecond())) {
        	criteria.andProviderSecondLike("%"+content.getProviderSecond()+"%");
        }
        if (null != content.getStatus()) {
        	criteria.andStatusEqualTo(content.getStatus());
        }
        if(StringUtils.isNotBlank(content.getIdKey())){
            criteria.andIdKeyEqualTo(content.getIdKey());
        }
        PageHelper.startPage(pageNo,pageSize);
        List<InformationContent> list = mapper.selectByExample(example);
        for (InformationContent obj : list) {
            //存信息项数据
            ListDataContent listDataContent = new ListDataContent();
            listDataContent.setParentId(obj.getIdKey());
            List<ListDataContent> listDataContents = listDataMapper.queryInfo(listDataContent);
            //存信息项数
            obj.setInfoCount(listDataContents.size());
            obj.setListData(listDataContents);
            String catagoryName = Utils.getParentNodeForCatagoryCode("", obj.getCatagoryCode(), catalogs);
            obj.setCatagoryName(catagoryName);
            if(StringUtils.isNotBlank(obj.getTableContent())){
                obj.setTableContentJson(JSONObject.parseObject(obj.getTableContent()));
                obj.setTableContent(null);
            }
        }
        PageInfo<InformationContent> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

}
