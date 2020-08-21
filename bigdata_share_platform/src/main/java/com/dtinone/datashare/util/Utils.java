package com.dtinone.datashare.util;

import com.dtinone.datashare.entity.Catalog;
import com.github.pagehelper.PageInfo;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Utils {

	public static String getUID() {
		
		return UUID.randomUUID().toString().replace("-", "").toLowerCase();
	}
	/**
	 * 带有child的目录树
	 * @param Catalogs
	 * @return
	 */
	public static List<Catalog> buildByRecursive(List<Catalog> Catalogs, String name) {
		List<Catalog> list = buildByRecursive(Catalogs);
		List<Catalog> trees = new ArrayList<>();
		if(name != null) {
			for (Catalog catalog : list) {
				if(catalog.getName().equals(name)){
					trees.add(catalog);
					break;
				}
			}
		}
		return trees;
	}

	public static List<Catalog> buildByRecursive(List<Catalog> Catalogs) {
		List<Catalog> trees = new ArrayList<Catalog>();
		for (Catalog Catalog : Catalogs) {
			if (null == Catalog.getParentId()) {
				trees.add(findChildren(Catalog,Catalogs));
			}
		}
		return trees;
	}
	
	public static Catalog findChildren(Catalog Catalog,List<Catalog> Catalogs) {
		for (Catalog it : Catalogs) {
			if(Catalog.getIdKey()==it.getParentId()) {
				if (Catalog.getChildren() == null) {
					Catalog.setChildren(new ArrayList<Catalog>());
				}
				Catalog.getChildren().add(findChildren(it,Catalogs));
			}
		}
		return Catalog;
	}

	/**
	 * 目录树第二类格式 0-0-0-0
	 */
	public static List<NodeInfo> _buildByRecursive(List<Catalog> Catalogs) {
		List<NodeInfo> _list = new ArrayList<>();
		for (Catalog catalog : Catalogs) {
			NodeInfo nodeInfo = new NodeInfo();
			String name = catalog.getName();
			Integer parentId = catalog.getParentId();
			Integer idKey = catalog.getIdKey();
			String remark = catalog.getRemark();
			nodeInfo.setTitle(name);
			nodeInfo.setIdKey(idKey);
			nodeInfo.setParentId(parentId);
			nodeInfo.setRemark(remark);
			_list.add(nodeInfo);
		}
		List<NodeInfo> trees = new ArrayList<>();
		for (NodeInfo obj : _list) {
			if (null == obj.getParentId()) {
				trees.add(_findChildren(obj,_list));
			}
		}
		return trees;
	}
	
	public static NodeInfo _findChildren(NodeInfo nodeInfo,List<NodeInfo> nodeInfos) {
		for (NodeInfo it : nodeInfos) {
			Integer idKey = nodeInfo.getIdKey();//父级
			Integer fatherId = nodeInfo.getParentId();
			
			Integer parentId = it.getParentId();//子集
			//先注解了key
			//String key = nodeInfo.getKey();
			if(idKey == parentId) {//证明it 是否为父级
				if (nodeInfo.getChildren() == null) {
					nodeInfo.setChildren(new ArrayList<NodeInfo>());
				}
				it.setKey(nodeInfo.getKey()+"-"+it.getIdKey());
				nodeInfo.getChildren().add(_findChildren(it,nodeInfos));
			} else {
				if(fatherId == null) {
					nodeInfo.setKey("0");//设置成都为0，其余的子父级不要去改变,因为在上一循环的it就已经设置了key
				}
			}
		}
		return nodeInfo;
	}
	
	/**
	 * 生成信息资源编码
	 * @return
	 */
	public static String getInfoCode () {
		Integer first = getRandom(10);
		Integer second = getRandom(100);
		Integer third = getRandom(1000);
		Integer fourth = getRandom(10000);
		int amount = (int)(Math.random()*100000)+1;
		Integer last = getRandom(amount);
		//System.out.println(first+" "+second+" "+third+" "+fourth+" "+last);
		String code = first.toString()+second+third+fourth+"/"+last;
		return code;
	}
	public static Integer getRandom(int numAmount) {
		try {
			byte[] bytes = new byte[128];
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			random.nextBytes(bytes);
			return random.nextInt(numAmount);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	
	/**
	 * 获得目录树下 节点 与 子节点包含的 子子节点 + 当前自身
	 * @param CatalogList
	 * @param pid
	 * @return
	 */
	public static List<Catalog> treeCatalogList(List<Catalog> CatalogList, Integer pid) {
		List<Catalog> childMenu = new ArrayList<>();
		//---包含当前节点---
		Catalog catalog = new Catalog();
		catalog.setIdKey(pid);
		childMenu.add(catalog);
		//------------
		List<Integer> checkIdIsSun = new ArrayList<>();
		checkIdIsSun.add(pid);
		for (Catalog mu : CatalogList) {
			//遍历出父id等于参数的id，add进子节点集合
			if (checkIdIsSun.contains(mu.getParentId())) {
				//递归遍历下一级
				checkIdIsSun.add(mu.getIdKey());
				treeCatalogList(CatalogList, mu.getIdKey());
				childMenu.add(mu);
			}
		}
		return childMenu;
	}

	/**
	 * 通过子节点 获取 父节点
	 * @param rootName
	 * @param catagoryCode
	 * @param catalogs
	 * @return
	 */
	public static String getParentNodeForCatagoryCode(String rootName,Integer catagoryCode, List<Catalog> catalogs) {
		for (Catalog catalog : catalogs) {
			if(catagoryCode == null) return rootName;
			if(catagoryCode == catalog.getIdKey()) {
				if(rootName.equals("")){
					rootName = catalog.getName();
				}else{
					rootName = catalog.getName()+">"+rootName;
				}
				catagoryCode = catalog.getParentId();
				return getParentNodeForCatagoryCode(rootName,catagoryCode, catalogs);
			}
		}
		return rootName;
	}

	/**
	 * 逻辑分页
	 * @param pageNo
	 * @param pageSize
	 * @param dataList
	 * @return
	 */
	public static PageInfo splitCollection(List<?> dataList, Integer pageNo, Integer pageSize){
		//TODO 逻辑分页功能
		PageInfo pageInfo = new PageInfo();
		int rows = dataList.size();//总数据量
		pageInfo.setTotal(rows);
		int pageNum = (rows-1)/pageSize+1;//页码

		Integer endIndex = pageNum*pageSize;
		Integer startIndex = endIndex - pageSize;
		pageInfo.setList(null);

		pageInfo.setPageNum(pageNo);
		pageInfo.setPageSize(dataList.size());
		pageInfo.setSize(pageSize);
//		pageInfo.setPrePage(null);
//		pageInfo.setNextPage(null);

		return null;
	}


	public static PageInfo handlePageInfo(List<?> dataList, int pageNo, int pageSize){
		List<List<?>> lists = splitCollection(dataList, pageSize);
		int pageNums = lists.size();
		PageInfo pageInfo = new PageInfo();
		if(lists.size() == 0){
			pageInfo.setList(new ArrayList());
			return pageInfo;
		}
		List<?> objects = lists.get(pageNo-1);
		//可优化
		pageInfo.setTotal(dataList.size());
		pageInfo.setList(objects);
		pageInfo.setPageSize(pageSize);
		pageInfo.setPageNum(pageNo);
		pageInfo.setSize(objects.size());
		pageInfo.setStartRow(pageNo*pageSize-pageSize+1);
		pageInfo.setEndRow(objects.size()*pageNo > dataList.size()? dataList.size() : objects.size()*pageNo);
		pageInfo.setPages(pageNums);
		pageInfo.setPrePage(pageNo - 1);
		int nextPage = pageNo+1 >= pageNums? 0 : pageNo+1;
		pageInfo.setNextPage(nextPage);
		pageInfo.setIsFirstPage(pageNo==1);
		pageInfo.setIsLastPage(pageNo==pageNums);
		pageInfo.setHasPreviousPage(pageNo != 1);
		pageInfo.setHasNextPage(pageNo < pageNums);
		pageInfo.setNavigatePages(8);//一个固定值
		int [] navigateArr = new int[lists.size()];
		for(int i = 0;i<lists.size();i++){
			navigateArr[i] = i+1;
		}
		pageInfo.setNavigatepageNums(navigateArr);
		int navFirstPage = navigateArr.length>0?navigateArr[0]:0;
		pageInfo.setNavigateFirstPage(navFirstPage);
		int navLastPage = navigateArr.length>0?navigateArr[navigateArr.length-1]:0;
		pageInfo.setNavigateLastPage(navLastPage);
		return pageInfo;
	}

	/**
	 * 暴力分页
	 * @param list source List
	 * @param splitSize 拆分大小
	 * @return
	 */
	public static List<List<?>> splitCollection(List<?> list,int splitSize) {
		List<List<?>> newList = new ArrayList<>();
		int sizeAll = list.size();
		int splitNum = sizeAll % splitSize;
		int number = sizeAll / splitSize;
		if (splitNum > 0) {
			number += 1;
		}
		for (int i = 0; i < number; i++) {
			int listNum = i + 1;
			int startSize = i * splitSize;
			int endSize = listNum * splitSize;

			if (listNum == number) {
				newList.add(list.subList(startSize, list.size()));
			} else {
				newList.add(list.subList(startSize, endSize));
			}
		}
		return newList;
	}
}
