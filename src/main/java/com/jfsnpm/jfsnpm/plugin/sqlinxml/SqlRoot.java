package com.jfsnpm.jfsnpm.plugin.sqlinxml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangrenhui on 14-1-5.
 */
@XmlRootElement
public class SqlRoot {

  @XmlElement(name = "sqlGroup")
  List<SqlGroup> sqlGroups = new ArrayList<SqlGroup>();

  void addSqlRoot(SqlGroup sqlGroup) {
    sqlGroups.add(sqlGroup);
  }
}
