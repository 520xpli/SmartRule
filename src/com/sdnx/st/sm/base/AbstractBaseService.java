package com.sdnx.st.sm.base;

import java.util.List;

import org.apache.log4j.Logger;

import com.sdnx.st.util.StDaoFactory;

public abstract class AbstractBaseService<T extends BaseModel> implements BaseService<T>{
	private BaseDao baseDao = StDaoFactory.getBaseDao();
	private static Logger logger = Logger.getLogger(AbstractBaseDao.class);
	public void add(T model){
		try {
			baseDao.add(model);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("���ݿ����ʧ��", e);
		}
	};
	public void update(T model){
		try {
			baseDao.update(model);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("���ݿ����ʧ��", e);
		}
	};
	public void deleteById(String id, String tableName){
		try {
			baseDao.deleteById(id, tableName);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("���ݿ�ɾ��ʧ��", e);
		}
	};
	public void batchInsert(List<BaseModel> modelList){
		try {
			baseDao.batchInsert(modelList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("���ݿ����ʧ��", e);
		}
	};
}
