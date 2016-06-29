package com.jfsnpm.jfsnpm.core.web.controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.jfsnpm.jfsnpm.core.util.StringUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by yangchuanhuan on 16/6/9.
 */
public class BaseController extends Controller {
    /**
     * 获取前段请求过来的参数
     * @return
     */
    public Record getParams(){
        Map<String, String[]> map = getRequest().getParameterMap();
        Set<String> keySet = map.keySet();
        Iterator ite = keySet.iterator();
        Record record = new Record();
        while (ite.hasNext()){
            String key = (String) ite.next();
            String[] val = map.get(key);
            if(!StringUtils.isNullString(val[0])){
                record.set(key, val[0]);
            }
        }
        return record;
    }
}
