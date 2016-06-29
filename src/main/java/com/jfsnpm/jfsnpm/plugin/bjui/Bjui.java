package com.jfsnpm.jfsnpm.plugin.bjui;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.DbPro;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfsnpm.jfsnpm.core.util.AppHelper;
import com.jfsnpm.jfsnpm.core.util.JfsnpmException;


public class Bjui {
	private DbPro DB; 
	public Bjui(){
		DB = Db.use("main");
	}
	public Bjui(String configName){
		DB = Db.use(configName);
	}
	public static Bjui use(){
		return new Bjui();
	}
	public static Bjui use(String configName){
		return new Bjui(configName);
	}
	/**
	 * 
	 * @param tableName 表名
	 * @param json 数据
	 * @return
	 */
	public boolean update(String tableName,String json){
		return update(tableName, json, "", false, "");
	}
	/**
	 * 更新表
	 * @param tableName 表名
	 * @param json Bjui-datagrid的提交数据
	 * @param pK 主键(逗号分隔,若有对于关系"from->to"将JSON中的from列更新到数据库的to列)例"id1,id2"或"id1from->id1to,id2from->id2to"
	 * 					为空则默认使用UUID，若要指定默认值 "from->to->default",只适用于新增的记录
	 * @param updateColumnYN 是否设置更新列
	 * @param updateColumn 更新列(逗号分隔,若有对于关系"from->to"将JSON中的from列更新到数据库的to列)
	 * @return 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean update(String tableName,String json,String pK,boolean updateColumnYN,String updateColumn){
		//空值处理
		if(AppHelper.isEmpty(tableName)) return false;
		if(AppHelper.isEmpty(pK)) pK="id";
		if(AppHelper.isEmpty(json)) return false;
		if(AppHelper.isEmpty(updateColumn)) updateColumn="";
		
		String ids[] = pK.split(",");
		String idsfrom[] = pK.split(",");
		String idsto[] = pK.split(",");
		String idsdf[] = pK.split(",");
		
		
		String cols[] = updateColumn.split(",");
		String colsfrom[] = updateColumn.split(",");
		String colsto[] = updateColumn.split(",");
		
		//分隔字符串
		for(int i=0;i<cols.length;i++){
			String colscolumn[] = cols[i].split("->");
			if(colscolumn.length > 2){
				colsfrom[i] = colscolumn[0];
				colsto[i] = colscolumn[1];
				//colsdf[i] = colscolumn[2];
			}else if(colscolumn.length == 2){
				colsfrom[i] = colscolumn[0];
				colsto[i] = colscolumn[1];
				//colsdf[i] = "__uuid";
			}else{
				colsfrom[i] = colscolumn[0];
				colsto[i] = colscolumn[0];
				//colsdf[i] = "__uuid";
			}
		}
		for(int i=0;i<ids.length;i++){
			String idscolumn[] = ids[i].split("->");
			if(idscolumn.length > 2){
				idsfrom[i] = idscolumn[0];
				idsto[i] = idscolumn[1];
				idsdf[i] = idscolumn[2];
			}else if(idscolumn.length == 2){
				idsfrom[i] = idscolumn[0];
				idsto[i] = idscolumn[1];
				idsdf[i] = "__uuid";
			}else{
				idsfrom[i] = idscolumn[0];
				idsto[i] = idscolumn[0];
				idsdf[i] = "__uuid";
			}
		}
		//处理更新列
		List<HashMap> list = AppHelper.getObjectsFromJson(json, HashMap.class);
		for(Map<String,Object> data:list){
			List<Object> sqlPara = new ArrayList<Object>();
			String whereSql = "";
			String updateSql = "UPDATE "+tableName;
			String insertSql = "INSERT INTO "+tableName;
			String insertValue = " ) VALUES ";
			boolean firstRecord = true;
			boolean newRecordYN = (data.containsKey("addFlag")&&(Boolean) data.get("addFlag"));
			if(updateColumnYN){
				//按设置列处理需要更新的列
				for(int i=0;i<colsfrom.length;i++){
					if(data.containsKey(colsfrom[i])){
						String key = colsto[i];
						if(firstRecord){
							firstRecord = false;
							updateSql = updateSql + " SET "+key+" = ?";
							insertSql = insertSql + " ( "+key;
							insertValue = insertValue + " ( ?";
						}else{
							updateSql = updateSql + " , "+key+" = ?";
							insertSql = insertSql + " , "+key;
							insertValue = insertValue + " , ?";
						}
						Object value = data.get(colsfrom[i]);
						String valuestr = String.valueOf(value);
						if(newRecordYN){
							for(int j=0;j<idsto.length;j++){
								if(key.equals(idsto[j])){
									if(value==null||AppHelper.isEmpty(valuestr)){
										if("__uuid".equals(idsdf[j])){
											value = AppHelper.getUUID();
										}else{
											value = idsdf[j];
										}
									}
									j = idsto.length;
								}
							}
						}
						sqlPara.add(value);
					}
				}
			}else{
				//自动处理更新列
				for(String key : data.keySet()) {
					if("gridNumber".equals(key)||"gridIndex".equals(key)||"addFlag".equals(key)) continue;
					if(firstRecord){
						firstRecord = false;
						updateSql = updateSql + " SET "+key+" = ?";
						insertSql = insertSql + " ( "+key;
						insertValue = insertValue + " ( ?";
					}else{
						updateSql = updateSql + " , "+key+" = ?";
						insertSql = insertSql + " , "+key;
						insertValue = insertValue + " , ?";
					}
					Object value = data.get(key);
					String valuestr = String.valueOf(value);
					if(newRecordYN){
						for(int j=0;j<idsto.length;j++){
							if(key.equals(idsto[j])){
								if(value==null||AppHelper.isEmpty(valuestr)){
									if("__uuid".equals(idsdf[j])){
										value = AppHelper.getUUID();
									}else{
										value = idsdf[j];
									}
								}
								j = idsto.length;
							}
						}
					}
					sqlPara.add(value);
				}
			}//if(updateColumnYN){
			insertValue = insertValue + " )";
			
			if(newRecordYN){
				int re = DB.update(insertSql+insertValue,sqlPara.toArray());
				if(re!=1){
					throw new JfsnpmException("INSERT错误");
				}
			}else{
				for(int i=0;i<idsfrom.length;i++){
					String keyValue = (String) data.get(idsfrom[i]);
					if(AppHelper.isEmpty(keyValue)) throw new JfsnpmException("主键没有找到或者值为空,请刷新表格后再试。");
					if(i==0){
						whereSql = whereSql + " WHERE "+idsto[i]+" = ?";
					}else{
						whereSql = whereSql + " AND "+idsto[i]+" = ?";
					}
					sqlPara.add(data.get(idsfrom[i]));
				}
				int re = DB.update(updateSql+whereSql,sqlPara.toArray());
				if(re!=1){
					throw new JfsnpmException("UPDATE错误");
				}
			}
		}//for(Map<String,String> data:list){
		return true;
	}
	/**
	 * 删除
	 * @param tableName
	 * @param json
	 * @param pK
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean delete(String tableName,String json,String pK){
		//空值处理
		if(AppHelper.isEmpty(tableName)) return false;
		if(AppHelper.isEmpty(pK)) pK="id";
		if(AppHelper.isEmpty(json)) return false;
		String ids[] = pK.split(",");
		List<HashMap> list = AppHelper.getObjectsFromJson(json, HashMap.class);
		for(Map<String,Object> data:list){
			List<Object> sqlPara = new ArrayList<Object>();
			String deleteSql = "DELETE FROM "+tableName;
			String whereSql = "";
			for(int i=0;i<ids.length;i++){
				String keyValue = (String) data.get(ids[i]);
				if(AppHelper.isEmpty(keyValue)) throw new JfsnpmException("主键没有找到或者值为空,请刷新表格后再试。");
				if(i==0){
					whereSql = whereSql + " WHERE "+ids[i]+" = ?";
				}else{
					whereSql = whereSql + " AND "+ids[i]+" = ?";
				}
				sqlPara.add(data.get(ids[i]));
			}
			int re = DB.update(deleteSql+whereSql,sqlPara.toArray());
			if(re!=1){
				throw new JfsnpmException("DELETE错误");
			}
		}
		return true;
	}
	public static String returnSuccessJson(String json){
		return json.replaceAll("\"addFlag\":true", "\"addFlag\":false");
	}
	/**
	 * 自动响应datagrid的分页，排序，筛选。
	 * @param sql "select xxx from xxx where xxx order by xxx"
	 * @param c
	 * @param args sql参数
	 * @return
	 */
	public Page<Record> page(String sql,Controller c,Object... args){
		String[] sqls = getFinalSql(sql, c, true);
		int pageNumber = c.getParaToInt("pageNumber", 1);
		int pageSize = c.getParaToInt("pageSize", 15);
		return DB.paginate(pageNumber, pageSize, sqls[0], sqls[1],args);
	}
	/**
	 * 用特定url的排序分页条件，查询出数据
	 * @param sql
	 * @param url
	 * @param c
	 * @param args
	 * @return
	 */
	public List<Record> find(String sql,String url,Controller c,Object... args){
		c.setAttr("__url", url);
		String[] sqls = getFinalSql(sql, c, false);
		return DB.find(sqls[0]+" "+sqls[1], args);
	}
	private static String[] getFinalSql(String sql,Controller c,boolean updateCookie){
		List<BjuiFilter> filter = BjuiHelper.getFilter(c,updateCookie);
		int sqlFromat = sql.toUpperCase().lastIndexOf("FROM");
		String select = sql.substring(0, sqlFromat);
		String sqlExceptSelect = sql.substring(sqlFromat);
		int whereat = sqlExceptSelect.toUpperCase().lastIndexOf("WHERE");
		int orderat = sqlExceptSelect.toUpperCase().lastIndexOf("ORDER BY");
		if(filter!=null&&filter.size() > 0){//WHERE的添加
			String sqlWhereAdd = "";
			if(whereat <= 0) sqlWhereAdd = " WHERE ";
			for(int i = 0 ; i < filter.size();i++){
				if(i==0) sqlWhereAdd = sqlWhereAdd + " (";
				if(i!=0) sqlWhereAdd = sqlWhereAdd + " AND";
				sqlWhereAdd = sqlWhereAdd + " " + filter.get(i).columnName +
						" "+filter.get(i).columnOperator ;
				if(filter.get(i).columnOperator.equals("like")){
					sqlWhereAdd = sqlWhereAdd + " '%"+filter.get(i).columnValue + "%' ";
				}else{
					sqlWhereAdd = sqlWhereAdd + " '"+filter.get(i).columnValue + "' ";
				}
				if(i==filter.size() - 1) sqlWhereAdd = sqlWhereAdd + " ) ";
			}
			if(whereat > 0 ){
				sqlExceptSelect = sqlExceptSelect.substring(0, whereat + 5) +
						sqlWhereAdd + " AND "+
						sqlExceptSelect.substring(whereat + 5);
			}else{
				if(orderat > 0){
					sqlExceptSelect = sqlExceptSelect.substring(0, orderat) +
							sqlWhereAdd + " "+
							sqlExceptSelect.substring(orderat);
				}else{
					sqlExceptSelect = sqlExceptSelect + sqlWhereAdd;
				}
			}
		}//WHERE添加完毕
		orderat = sqlExceptSelect.toUpperCase().lastIndexOf("ORDER BY");
		BjuiOrder order = BjuiHelper.getOrder(c,updateCookie);
		if(order!=null&&!AppHelper.isEmpty(order.orderField)){//添加ORDER
			String orderField = order.orderField;
			String orderDirection = order.orderDirection;
			String sqlOrderAdd = "";
			String sqlOrdertemp = orderField+" "+orderDirection;
			if(orderat <= 0){
				sqlOrderAdd = " ORDER BY "+sqlOrdertemp;
			}else{
				sqlOrderAdd = " "+sqlOrdertemp+ " ,";
			}
			if(orderat > 0){
				int orderhad = sqlExceptSelect.toUpperCase().indexOf(orderField.toUpperCase(), orderat+8);
				if(orderhad > 0){//原SQL中含有该字段的排序规则
					int orderhadend = sqlExceptSelect.indexOf(",", orderhad);
					if(orderhadend <= 0){
						sqlExceptSelect = sqlExceptSelect.substring(0, orderhad);
						sqlOrderAdd = sqlOrderAdd.substring(0, sqlOrderAdd.length()-1);//删除逗号
					}else{
						sqlExceptSelect = sqlExceptSelect.substring(0, orderhad) +
								sqlExceptSelect.substring(orderhadend);
					}
				}
				sqlExceptSelect = sqlExceptSelect.substring(0, orderat + 8) +
						sqlOrderAdd + " "+
						sqlExceptSelect.substring(orderat + 8);
			}else{
				sqlExceptSelect = sqlExceptSelect + sqlOrderAdd;
			}
		}//ORDER添加完毕
		return new String[]{select,sqlExceptSelect};
	}
}
