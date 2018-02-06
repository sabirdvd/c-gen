package com.test.model;

import java.util.List;

/**
 * Created by shuisheng on 2018/2/5.
 */
public class Table {
    String tableName;
    String javaPackageName;
    String javaClassName;
    String javaClassFullName;
    String namespace;

    List<Column> columnList;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getJavaPackageName() {
        return javaPackageName;
    }

    public void setJavaPackageName(String javaPackageName) {
        this.javaPackageName = javaPackageName;
    }

    public String getJavaClassName() {
        return javaClassName;
    }

    public void setJavaClassName(String javaClassName) {
        this.javaClassName = javaClassName;
    }

    public String getJavaClassFullName() {
        return javaClassFullName;
    }

    public void setJavaClassFullName(String javaClassFullName) {
        this.javaClassFullName = javaClassFullName;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }
}
