/**
 * 
 */
package com.bigdata.springbootkafkaflink.constant;

/**
* @Title: Constants
* @Description:
* 常量类 
* @Version:1.0.0  
* @author pancm
* @date 2018年5月9日
*/
public class Constants {
	
	public  final static String KAFKA_SPOUT="KAFKA_SPOUT"; 
	public  final static String INSERT_BOLT="INSERT_BOLT"; 
	public  final static String FIELD="insert";
	//用于SkcTopo那个类
	public static final String BROKER_LIST = "192.168.109.134:9093,192.168.109.134:9094,192.168.109.134:9095";

	public static final String TOPIC_NAME = "TEST_02";
}
