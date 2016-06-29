package com.jfsnpm.jfsnpm.plugin.sqlinxml;

import com.jfinal.plugin.IPlugin;

public class SqlInXmlPlugin implements IPlugin {

  private String[] paths = null;

  /**
   * default dir is /sql
   */
  public SqlInXmlPlugin() {
  }

  /**
   * set sql file dir
   * @param paths dir
   */
  public SqlInXmlPlugin(String... paths) {
    this.paths = paths;
  }

  @Override
  public boolean start() {
    if (paths != null)
      SqlKit.init(paths);
    else
      SqlKit.init();
    return true;
  }

  @Override
  public boolean stop() {
    SqlKit.clearSqlMap();
    return true;
  }

}
