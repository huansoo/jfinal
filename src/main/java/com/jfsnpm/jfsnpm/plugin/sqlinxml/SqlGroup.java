package com.jfsnpm.jfsnpm.plugin.sqlinxml;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement
class SqlGroup {
  @XmlAttribute
  String name;
  @XmlElement(name = "sql")
  List<SqlItem> sqlItems = new ArrayList<SqlItem>();

  void addSqlgroup(SqlItem sqlGroup) {
    sqlItems.add(sqlGroup);
  }

}
