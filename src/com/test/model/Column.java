package com.test.model;

/**
 * Created by shuisheng on 2018/2/5.
 */
public class Column {
    String columnName;//列名
    int jdbcType;//列类型
    String jdbcTypeName;//如果VARCHAR
    String columnComment;//字段备注

    String javaPropertyName;//java字段名称，如orderId
    String javaPropertyType;//java类型名称，如String

    boolean primaryKey;


    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public int getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(int jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getJdbcTypeName() {
        return jdbcTypeName;
    }

    public void setJdbcTypeName(String jdbcTypeName) {
        this.jdbcTypeName = jdbcTypeName;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getJavaPropertyName() {
        return javaPropertyName;
    }

    public void setJavaPropertyName(String javaPropertyName) {
        this.javaPropertyName = javaPropertyName;
    }

    public String getJavaPropertyType() {
        return javaPropertyType;
    }

    public void setJavaPropertyType(String javaPropertyType) {
        this.javaPropertyType = javaPropertyType;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }
}
