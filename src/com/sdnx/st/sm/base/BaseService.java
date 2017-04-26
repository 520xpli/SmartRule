package com.sdnx.st.sm.base;

import java.util.List;

public interface BaseService<T extends BaseModel> {
	void add(T model);
	void update(T model);
	void deleteById(String id, String tableName);
	void batchInsert(List<BaseModel> modelList);
}
