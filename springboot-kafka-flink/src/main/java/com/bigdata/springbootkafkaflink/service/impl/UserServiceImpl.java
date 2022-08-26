package com.bigdata.springbootkafkaflink.service.impl;

import com.bigdata.springbootkafkaflink.dao.UserDao;
import com.bigdata.springbootkafkaflink.pojo.User;
import com.bigdata.springbootkafkaflink.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * 
* Title: UserServiceImpl
* Description:
* 用户操作实现类 
* 需要序列化
* Version:1.0.0  
* @author pancm
* @date 2018年1月9日
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3931100428563125997L;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
    private UserDao userDao;
	

	@Override
	public boolean insertBatch(List<User> user) {
		boolean flag=false;
		try {
			flag=userDao.insertBatch(user);
			logger.info("批量新增"+user.size()+"条数据成功!");
		} catch (Exception e) {
			logger.error("批量新增失败!数据:{},原因是:",e);
		}
		return flag;
	}

	@Override
	public List<User> findByUser(User user) {
		return userDao.findByUser(user);
	}


}
