package com.huahua.saselomo.common.dao;

/**
 * DAO层共同基础接口;
 * 类上定义泛型用于约束类中方法的参数类型，
 * 方法的返回值类型或属性类型
 * @author Lin·Y
 *
 */
public interface BaseDao<T> {
	/** 存储一条信息*/
	int saveObject(T t);
	/** 更新一条信息*/
	int updateObject(T t);
}
