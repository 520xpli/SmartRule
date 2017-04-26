package com.sdnx.st.sm.base;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.sdnx.st.constants.TableMapClass;
import com.sdnx.st.util.STConnectionManager;
import com.sdnx.st.util.STDBManager;

public abstract class AbstractBaseDao<T extends BaseModel> implements BaseDao<T> {
	private static Logger logger = Logger.getLogger(AbstractBaseDao.class);
	//�����෴����Ϣ
	private Map<String, List<String>> reflectionCatch = new HashMap<String, List<String>>();
	
	private int seq = 1000;

	//����һ������
	public void add(T model) throws Exception {
		if (null == model)
			return;
		//�����������뷽��
		List<T> list = new LinkedList<T>();
		list.add(model);
		batchInsert(list, false);
	};
	
	@Override
	public void addBySelfTransaction(T model) throws Exception{
		if (null == model)
			return;
		//�����������뷽��
		List<T> list = new LinkedList<T>();
		list.add(model);
		batchInsert(list, true);
	}
	
	//��������
	public void update(T model) throws Exception {
		if (null == model)
			return;
		//��ȡclass
		Class<?> cl = model.getClass();
		//��ȡ���ݿ�����
		Connection con = STConnectionManager.getInstance().getConnection();
		PreparedStatement st = null;
		catchReflection(model);
		try {
			//�ж���id�Ƿ����
			Method getid = cl.getDeclaredMethod("getId", new Class[] {});
			String id = (String) getid.invoke(model, new Object[] {});
			if (null == id)
				return;
			//��������sql��ʼ���
			StringBuffer preSql = new StringBuffer(
					"update " + TableMapClass.getTableName(cl.getSimpleName()) + " set ");
			List<String> fields = reflectionCatch.get(model.getClass().getCanonicalName());
			int flag = 1;
			//ͨ�����乹������sql set���
			for (int i = 0, j = fields.size(); i < j; i++) {
				String fieldName = fields.get(i);
				if("id".equals(fieldName)){
					continue;
				}
				char[] cs = fieldName.toCharArray();
				Method m;
				if (cs[0] >= 'a' && cs[0] <= 'z') {
					cs[0] -= 32;
				}
				m = cl.getDeclaredMethod("get" + String.valueOf(cs), new Class[] {});
				Object o = m.invoke(model, new Object[] {});
				if (null != o) {
					preSql.append(fieldName + "=?,");
				}
			}
			preSql.append("id='" + id + "' where id='" + id + "'");
			//�������
			st = con.prepareStatement(preSql.toString());
			//prepareStatement�������
			for (int i = 0, j = fields.size(); i < j; i++) {
				String fieldName = fields.get(i);
				if("id".equals(fieldName)){
					continue;
				}
				char[] cs = fieldName.toCharArray();
				Method m;
				if (cs[0] >= 'a' && cs[0] <= 'z') {
					cs[0] -= 32;
				}
				m = cl.getDeclaredMethod("get" + String.valueOf(cs), new Class[] {});
				Object o = m.invoke(model, new Object[] {});
				if (null != o) {
					st.setObject(flag, o);
					flag++;
				}
			}
			logger.debug("ִ��sql" + preSql.toString());
			st.executeUpdate();

		} catch (Exception e) {
			throw e;
		}finally{
			STDBManager.getInstance().close(null, st, con);
		}
	};
	/*
	 *ͨ��id��ѯ����
	 */
	public Object queryById(String id, Class<?> voClassName, String tableName) throws Exception {
		if (null == id | null == voClassName | null == tableName)
			return null;
		//��ȡ���ݿ�����
		Connection con = STConnectionManager.getInstance().getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			Object t = voClassName.newInstance();
			//������ѯ���
			String sql = "select * from " + tableName + " where id='" + id + "'";
			st = con.prepareStatement(sql);
			logger.debug("ִ��sql" + sql);
			st.executeQuery();
			rs = st.getResultSet();
			ResultSetMetaData md = rs.getMetaData();
			int count = md.getColumnCount();
			//ͨ�����佫��ѯ���set������
			if (rs.next()) {
				for (int i = 1; i <= count; i++) {
					String columnName = md.getColumnName(i).toLowerCase();
					Field field = voClassName.getDeclaredField(columnName);
					char[] cs = columnName.toCharArray();
					cs[0] -= 32;
					try{
						Method m = voClassName.getDeclaredMethod("set" + String.valueOf(cs), field.getType());
						m.invoke(t, rs.getObject(i));
					}catch(Exception e){
						
					}
				}
			}
			return t;
		} catch (Exception e) {
			throw e;
		}finally{
			STDBManager.getInstance().close(rs, st, con);
		}
	};
	/*
	 *ͨ��id�ͱ���ɾ������
	 */
	public void deleteById(String id, String tableName) throws Exception {
		if(null == id | null == tableName)
			return;
		//ɾ��sql���
		String sql = "delete from " + tableName + " where id='" + id + "'";
		//��ȡ����
		Connection con = STConnectionManager.getInstance().getConnection();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			logger.debug("ִ��sql" + sql);
			st.executeUpdate();
		} catch (Exception e) {
			throw e;
		}finally{
			STDBManager.getInstance().close(null, st, con);
		}
	};
	/*
	 *ͨ���������������
	 */
	@Override
	public void batchInsertBySelfTransaction(List<T> modelList) throws Exception{
		batchInsert(modelList, true);
	}
	
	@Override
	public void batchInsert(List<T> modelList) throws Exception{
		batchInsert(modelList, false);
	}
	
	public void batchInsert(List<T> modelList, boolean isSelfTransaction) throws Exception {
		if (null == modelList)
			return;
		if (modelList.size() == 0)
			return;
		int length = modelList.size();
		if(length > 1000){
			length = length / 1000;
			for(int i = 0; i < length ; i++){
				List<T> tmpList = modelList.subList(i * 1000, (i + 1) * 1000);
				batchInsert(tmpList);
			}
			batchInsert(modelList.subList(length * 1000, modelList.size()));
			return;
		}
		T model = modelList.get(0);
		Class<?> cl = model.getClass();
		//�����෴����Ϣ
		catchReflection(model);
		//�����������ͷ��
		StringBuffer headSql = new StringBuffer(
				"insert into " + TableMapClass.getTableName(model.getClass().getSimpleName()));
		StringBuffer fieldSql = new StringBuffer(" (");
		StringBuffer valueSql = new StringBuffer(" values ");
		//���������������Ϣ
		List<String> fields = reflectionCatch.get(model.getClass().getCanonicalName());
		for (int i = 0, j = fields.size(); i < j; i++) {
			if (i == j - 1) {
				fieldSql.append(fields.get(i) + ") ");
			} else {
				fieldSql.append(fields.get(i) + ",");
			}
		}
		for(int m = 0, n = modelList.size(); m < n; m++){
			StringBuffer valueSqlTmp = new StringBuffer(" (");
			for (int i = 0, j = fields.size(); i < j; i++) {
				if (i == j - 1) {
					valueSqlTmp.append("?),");
				} else {
					valueSqlTmp.append("?,");
				}
			}
			valueSql.append(valueSqlTmp);
		}
		valueSql.deleteCharAt(valueSql.length() - 1);
		String preSql = headSql.append(fieldSql).append(valueSql).toString();
		//��ȡ���ݿ�����
		Connection con;
		if(isSelfTransaction)
			con = STConnectionManager.getInstance().getSelfTransActionConnection();
		else con = STConnectionManager.getInstance().getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			int flag = 1;
			st = con.prepareStatement(preSql, Statement.RETURN_GENERATED_KEYS);
			for (T calModel : modelList) {
				for (int i = 0, j = fields.size(); i < j; i++) {
					String fieldName = fields.get(i);
					char[] cs = fieldName.toCharArray();
					Method m;
					if (cs[0] >= 'a' && cs[0] <= 'z') {
						cs[0] -= 32;
					}
					Class<?> proClass = cl.getDeclaredField(fieldName).getType();
					Object o = null;
					if("id".equals(fieldName)){
						o = UUID.randomUUID().toString().replaceAll("-", ""); 
					}
					else{
						m = cl.getDeclaredMethod("get" + String.valueOf(cs), new Class[] {});
						o = m.invoke(calModel, new Object[] {});
					}
					//����prepareStatement�������
					if (null == o) {
						if (proClass == String.class)
							st.setNull(flag++, Types.VARCHAR);
						else if (proClass == Short.class)
							st.setNull(flag++, Types.SMALLINT);
						else if (proClass == Integer.class)
							st.setNull(flag++, Types.INTEGER);
						else if (proClass == Long.class)
							st.setNull(flag++, Types.BIGINT);
						else if (proClass == BigDecimal.class)
							st.setNull(flag++, Types.NUMERIC);
						else if (proClass == Date.class)
							st.setNull(flag++, Types.TIMESTAMP);
						else if (proClass == Double.class)
							st.setNull(flag++, Types.DOUBLE);
						else if (proClass == Float.class)
							st.setNull(flag++, Types.FLOAT);
						else if (proClass == java.sql.Date.class)
							st.setNull(flag++, Types.DATE);
						else
							st.setNull(flag++, Types.OTHER);
					} else
						st.setObject(flag++, o);
				}
			}
			logger.debug("ִ��sql" + preSql.toString());
			//���ֻ��һ�����ݣ���ô������������id��ֵ��ԭ������
			st.execute();

		} catch (Exception e) {
			throw e;
		}finally{
			STDBManager.getInstance().close(rs, st, con);
		}
	};

	@Override
	public void deleteBySql(String sql) throws Exception{
		Connection con = STConnectionManager.getInstance().getConnection();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			st.executeUpdate();
		} catch (Exception e) {
			throw e;
		}finally{
			STDBManager.getInstance().close(null, st, con);
		}
	}
	/*
	 *ͨ�����ѯ�б� ����
	 */
	@Override
	public <E> List<E> queryListByModel(T model, Class<E> voClassName) throws Exception {
		if(null == model | null == voClassName)
			return null;
		//���巵���б�
		List<E> list = new LinkedList<E>();
		Connection con = STConnectionManager.getInstance().getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			//������ѯ���ͷ��
			StringBuffer preSql = new StringBuffer("select * from " + 
								  TableMapClass.getTableName(model.getClass().getSimpleName()) + "  where 1=1 ");
			catchReflection(model);
			//��ȡ����
			List<String> fields = reflectionCatch.get(model.getClass().getCanonicalName());
			//������ѯ���
			for(String field : fields){
				char[] cs = field.toCharArray();
				if(cs[0] >= 'a' && cs[0] <= 'z')
					cs[0] -= 32;
				try {
					Method m = model.getClass().getDeclaredMethod("get" + String.valueOf(cs), new Class[]{});
					if(null != m.invoke(model, new Object[]{}))
						preSql.append("and " + field + "=? ");
				} catch (Exception e) {
				}
			}
			PageListData pld = model.getPageListData();
			int start = (pld.getPage() - 1) * pld.getPageSize();
			int end = start + pld.getPageSize();
			//������ҳ��ѯ
			preSql.append("order by lastopertime asc limit " + start + " ," + end);
		    st = con.prepareStatement(preSql.toString());
			int flag = 1;
			//����prepareStatement����
			for(String field : fields){
				char[] cs = field.toCharArray();
				if(cs[0] >= 'a' && cs[0] <= 'z')
					cs[0] -= 32;
				try {
					Method m = model.getClass().getDeclaredMethod("get" + String.valueOf(cs), new Class[]{});
					Object o = m.invoke(model, new Object[]{});
					if(null != o){
						st.setObject(flag, o);
						flag++;
					}
				} catch (Exception e) {
				}
			}
			logger.debug("ִ��sql" + preSql.toString());
			rs = st.executeQuery();
			ResultSetMetaData md = rs.getMetaData();
			int count = md.getColumnCount();
			//��ѯ�����ֵ��vo
			while(rs.next()){
				E o = voClassName.newInstance();
				for(int i = 1; i <= count; i++){
					String column = md.getColumnName(i).toLowerCase();
					char[] cs = column.toCharArray();
					cs[0] -= 32;
					try {
						Method m = voClassName.getDeclaredMethod("set" + String.valueOf(cs), voClassName.getDeclaredField(column).getType());
						m.invoke(o, rs.getObject(i));
					} catch (Exception e) {
					}
				}
				list.add(o);
			}
			
		} catch (Exception e) {
			throw e;
		}finally{
			STDBManager.getInstance().close(rs, st, con);
		}
		return list;
	};
	
	/*
	 * ͨ��sql��ѯ�����б�
	 */
	@Override
	public <E> List<E> queryListBySql(String sql, Class<E> voClassName) throws Exception{
		if(null == sql | null == voClassName)
			return null;
		List<E> list = new LinkedList<E>();
		Connection con = STConnectionManager.getInstance().getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = con.prepareStatement(sql);
			logger.debug("ִ��sql" + sql);
			rs = st.executeQuery();
			ResultSetMetaData md = rs.getMetaData();
			int count = md.getColumnCount();
			//��ѯ�����ֵ��vo
			while(rs.next()){
				E o = voClassName.newInstance();
				for(int i = 1; i <= count; i++){
					String column = md.getColumnName(i).toLowerCase();
					char[] cs = column.toCharArray();
					cs[0] -= 32;
					try {
						Method m = voClassName.getDeclaredMethod("set" + String.valueOf(cs), voClassName.getDeclaredField(column).getType());
						Object t = rs.getObject(i);
						m.invoke(o, rs.getObject(i));
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				list.add(o);
			}
		} catch (Exception e) {
			throw e;
		}finally {
			STDBManager.getInstance().close(rs, st, con);
		}
		return list;
	};
	
	/*
	 *ͨ�����ѯ�б� map
	 */
	public List<Map<String, Object>> queryListMapByModel(T model) throws Exception{
		if(null == model)
			return null;
		//���巵���б�
		List<Map<String, Object>> list = new LinkedList<Map<String,Object>>();
		Connection con = STConnectionManager.getInstance().getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			//������ѯ���ͷ��
			StringBuffer preSql = new StringBuffer("select * from" + 
								  TableMapClass.getTableName(model.getClass().getSimpleName()) + " ta where 1=1 ");
			catchReflection(model);
			//��ȡ����
			List<String> fields = reflectionCatch.get(model.getClass().getCanonicalName());
			//������ѯ���
			for(String field : fields){
				char[] cs = field.toCharArray();
				if(cs[0] >= 'a' && cs[0] <= 'z')
					cs[0] -= 32;
				try {
					Method m = model.getClass().getDeclaredMethod("get" + String.valueOf(cs), new Class[]{});
					if(null != m.invoke(model, new Object[]{}))
						preSql.append("and " + field + "=? ");
				} catch (Exception e) {
				}
			}
			//������ҳ��ѯ
			preSql.append("order by id asc limit " + 0 + "," + Integer.MAX_VALUE);
		    st = con.prepareStatement(preSql.toString());
			int flag = 1;
			//����prepareStatement����
			for(String field : fields){
				char[] cs = field.toCharArray();
				if(cs[0] >= 'a' && cs[0] <= 'z')
					cs[0] -= 32;
				try {
					Method m = model.getClass().getDeclaredMethod("get" + String.valueOf(cs), new Class[]{});
					Object o = m.invoke(model, new Object[]{});
					if(null != o){
						st.setObject(flag, o);
						flag++;
					}
				} catch (Exception e) {
				}
			}
			logger.debug("ִ��sql" + preSql.toString());
			rs = st.executeQuery();
			ResultSetMetaData md = rs.getMetaData();
			int count = md.getColumnCount();
			//��ѯ�����ֵ��vo
			while(rs.next()){
				Map<String,Object> map = new HashMap<String, Object>();
				for(int i = 1; i <= count; i++){
					String column = md.getColumnName(i).toLowerCase();
					char[] cs = column.toCharArray();
					cs[0] -= 32;
					map.put(column, rs.getObject(i));
				}
				list.add(map);
			}
			
		} catch (Exception e) {
			throw e;
		}finally{
			STDBManager.getInstance().close(rs, st, con);
		}
		return list;
	}

	/*
	 * ���ڴ���������
	 */
	private synchronized void catchReflection(T model) {
		if (null == model || null != reflectionCatch.get(model.getClass().getCanonicalName()))
			return;
		List<String> property = new LinkedList<String>();
		Field[] fs = model.getClass().getDeclaredFields();
		for (Field f : fs) {
			//���洢pageListData��id����
			if (!(f.getName().equals("pageListData")))
				property.add(f.getName());
		}
		reflectionCatch.put(model.getClass().getCanonicalName(), property);
	}
	
	//����������
	public String codeGenerator(String modelClassName)  throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String code = TableMapClass.getCodeHead(modelClassName) + format.format(new Date());
		code += seq++;
		return code;
	}
	
	@Override
	public String codeGenerator(String modelClassName, Integer flag, Integer count)  throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String code = TableMapClass.getCodeHead(modelClassName) + format.format(new Date());
		String tmp = String.valueOf(count);
		for(int i = 0, j = 6 - tmp.length(); i < j; i++){
			tmp = "0" + tmp;
		}
		code = code + String.valueOf(flag) + tmp;
		return code;
	}
}
