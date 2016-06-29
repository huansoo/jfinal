package com.jfsnpm.jfsnpm.core.global;

import java.util.List;
import java.util.Map;

import com.jfinal.plugin.activerecord.Record;

import org.snaker.engine.entity.Process;

public interface IFlow {
	public List<Process> getProcessAll();
	public List<Record> getTodoListByUserId(String userId);
	public List<Record> getMyProcess(String userId);
	public List<Record> getMyRelease(String userId);
	public List<Record> getMyHistory(String userId);
	public Map<String,Object> getTaskModelsArgs(String userId,String processId ,Map<String,Object> args);
}
