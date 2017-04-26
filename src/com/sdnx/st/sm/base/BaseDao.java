package com.sdnx.st.sm.base;

import java.util.List;
import java.util.Map;

public interface BaseDao<T extends BaseModel> {
	void add(T model) throws Exception;
	void update(T model) throws Exception;
	Object queryById(String id, Class<?> voClassName, String tableName) throws Exception;
	void deleteById(String string, String tableName) throws Exception;
	void batchInsert(List<T> modelList) throws Exception;
	void deleteBySql(String sql) throws Exception;
	List<Map<String, Object>> queryListMapByModel(T model) throws Exception;
	<E> List<E> queryListBySql(String sql, Class<E> voClassName) throws Exception;
	String codeGenerator(String modelClassName) throws Exception;
	<E> List<E> queryListByModel(T model, Class<E> voClassName) throws Exception;
	void addBySelfTransaction(T model) throws Exception;
	void batchInsertBySelfTransaction(List<T> modelList) throws Exception;
	String codeGenerator(String modelClassName, Integer flag, Integer count) throws Exception;
}
