package com.test.gen.impl;

import com.test.common.util.FileUtils;
import com.test.common.util.JavaBeansUtil;
import com.test.gen.Generator;
import com.test.model.Column;
import com.test.model.Table;

import java.util.List;

/**
 * Created by shuisheng on 2018/2/6.
 */
public class XmlGenerator implements Generator {

    private Table table;
    private StringBuilder sb;

    @Override
    public void generateFile(Table table) {

        this.table = table;
        this.sb = new StringBuilder();

        appendBegin();
        appendResultMap();
        appendWhereParams();
        appendSelectColumns();
        appendUpdateSetColumns();
        appendInsert();
        appendInsertList();
        appendGetById();
        appendGet();
        appendCount();
        appendList();

        appendUpdateById();
        appendFinish();

        System.out.println("Generate xml file Begin...");
        FileUtils.writeFile(table.getJavaClassName() + "Mapper.xml", sb.toString());
        System.out.println("Generate xml file End...");

    }

    /**
     * begin

     * @return
     */
    private void appendBegin() {
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
                .append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n\n")
                .append("<mapper namespace=\"").append(table.getJavaClassFullName()).append("Mapper\">\n");

    }


    /**
     * resultMap
     * @return
     */
    private void appendResultMap() {
        List<Column> columnList = table.getColumnList();
        Column column = columnList.get(0);
        String columnName = column.getColumnName();
        String jdbcTypeName = column.getJdbcTypeName();
        String javaPropertyName = column.getJavaPropertyName();

        sb.append("\t<resultMap id=\"BaseResultMap\" type=\"").append(table.getJavaClassFullName()).append("\">\n");
        sb.append("\t\t<id column=\"").append(columnName).append("\" jdbcType=\"").append(jdbcTypeName).append("\" property=\"").append(javaPropertyName).append("\" />\n");


        for (int i=1; i<columnList.size(); i++) {

            column = columnList.get(i);

            columnName = column.getColumnName();
            jdbcTypeName = column.getJdbcTypeName();
            javaPropertyName = column.getJavaPropertyName();
            sb.append("\t\t<result column=\"").append(columnName).append("\" jdbcType=\"").append(jdbcTypeName).append("\" property=\"").append(javaPropertyName).append("\" />\n");

        }

        sb.append("\t</resultMap>\n\n");
    }



    /**
     * UpdateSetColumns
     * @return
     */
    private void appendSelectColumns() {
        List<Column> columnList = table.getColumnList();
        Column column;
        String columnName;
        String jdbcTypeName;
        String javaPropertyName;
        String javaPropertyParaName;

        sb.append("\t<!-- 动态查询字段, 给通用方法使用 -->\n");
        sb.append("\t<sql id=\"SelectColumns\">\n");

        for (int i=0; i<columnList.size(); i++) {
            column = columnList.get(i);
            columnName = column.getColumnName();
            jdbcTypeName = column.getJdbcTypeName();
            javaPropertyName = column.getJavaPropertyName();
            javaPropertyParaName = JavaBeansUtil.getCamelCaseString(columnName, true);


            sb.append("\t\t\t<if test=\"c").append(javaPropertyParaName).append(" == true\"> ");
            sb.append(",").append(columnName);
            sb.append("</if>\n");
        }
        sb.append("\t</sql>\n\n");

    }

    /**
     * UpdateSetColumns
     * @return
     */
    private void appendUpdateSetColumns() {
        List<Column> columnList = table.getColumnList();
        Column column;
        String columnName;
        String jdbcTypeName;
        String javaPropertyName;
        String javaPropertyParaName;

        sb.append("\t<!-- 可能更改的字段, 给通用方法使用 -->\n");
        sb.append("\t<sql id=\"UpdateSetColumns\">\n");
        sb.append("\t\t<set>\n");

        for (int i=0; i<columnList.size(); i++) {
            column = columnList.get(i);
            columnName = column.getColumnName();
            jdbcTypeName = column.getJdbcTypeName();
            javaPropertyName = column.getJavaPropertyName();
            javaPropertyParaName = JavaBeansUtil.getCamelCaseString(columnName, true);


            sb.append("\t\t\t<if test=\"s").append(javaPropertyParaName).append(" != null\"> ");
            sb.append(columnName).append(" = #{s").append(javaPropertyParaName).append(", jdbcType=").append(jdbcTypeName).append("},");
            sb.append("</if>\n");
        }

        sb.append("\t\t</set>\n");
        sb.append("\t</sql>\n\n");

    }


    /**
     * WhereParams
     * @return
     */
    private void appendWhereParams() {
        List<Column> columnList = table.getColumnList();
        Column column;
        String columnName;
        String jdbcTypeName;
        String javaPropertyName;
        String javaPropertyParaName;

        sb.append("\t<!-- 可能用于查询的条件参数, 给通用方法使用 -->\n");
        sb.append("\t<sql id=\"WhereParams\">\n");

        for (int i=0; i<columnList.size(); i++) {
            column = columnList.get(i);
            columnName = column.getColumnName();
            jdbcTypeName = column.getJdbcTypeName();
            javaPropertyName = column.getJavaPropertyName();
            javaPropertyParaName = JavaBeansUtil.getCamelCaseString(columnName, true);

            sb.append("\t\t<if test=\"p").append(javaPropertyParaName).append(" != null\"> ");
            sb.append("AND ").append(columnName).append(" = #{ p").append(javaPropertyParaName).append(", jdbcType=").append(jdbcTypeName).append("}");
            sb.append("</if>\n");
        }

        sb.append("\t</sql>\n\n");

    }


    /**
     * insert
     * @return
     */
    private void appendInsert() {
        List<Column> columnList = table.getColumnList();
        Column column = columnList.get(0);
        String columnName;
        String jdbcTypeName;
        String javaPropertyName;

        sb.append("\t<!-- 插入数据-单条 -->\n");
        sb.append("\t<insert id=\"insert").append(table.getJavaClassName());
        sb.append("\" useGeneratedKeys=\"true\" keyProperty=\"");
        sb.append(column.getJavaPropertyName());
        sb.append("\" parameterType=\"").append(table.getJavaClassFullName()).append("\">\n");
        sb.append("\t\tinsert into ").append(table.getTableName()).append(" (\n");
        sb.append("\t\t<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n");


        for (int i=0; i<columnList.size(); i++) {
            column = columnList.get(i);
            columnName = column.getColumnName();
            jdbcTypeName = column.getJdbcTypeName();
            javaPropertyName = column.getJavaPropertyName();

            sb.append("\t\t\t<if test=\"").append(javaPropertyName).append(" != null\"> ");
            sb.append(columnName).append(", </if>\n");
        }

        sb.append("\t\t</trim>\n");
        sb.append("\t\t<trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">\n");

        for (int i=0; i<columnList.size(); i++) {
            column = columnList.get(i);
            columnName = column.getColumnName();
            jdbcTypeName = column.getJdbcTypeName();
            javaPropertyName = column.getJavaPropertyName();

            sb.append("\t\t\t<if test=\"").append(javaPropertyName).append(" != null\"> ");
            sb.append(" #{").append(javaPropertyName).append(", jdbcType=").append(jdbcTypeName).append("},</if>\n");
        }

        sb.append("\t\t</trim>\n");
        sb.append("\t</insert>\n\n");
    }


    /**
     * insert
     * @return
     */
    private void appendInsertList() {
        List<Column> columnList = table.getColumnList();
        Column column = columnList.get(0);
        String columnName;
        String jdbcTypeName;
        String javaPropertyName;

        sb.append("\t<!-- 插入数据-多条 -->\n");
        sb.append("\t<insert id=\"insert").append(table.getJavaClassName()).append("List\" parameterType=\"list\">\n");
        sb.append("\t\tinsert into ").append(table.getTableName()).append(" (\n");

        for (int i=0; i<columnList.size(); i++) {
            column = columnList.get(i);
            columnName = column.getColumnName();
            jdbcTypeName = column.getJdbcTypeName();
            javaPropertyName = column.getJavaPropertyName();

            sb.append("\t\t\t").append(columnName).append(",\n");
        }

        sb.append("\t\t)values\n");
        sb.append("\t\t<foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">(\n");

        for (int i=0; i<columnList.size(); i++) {
            column = columnList.get(i);
            columnName = column.getColumnName();
            jdbcTypeName = column.getJdbcTypeName();
            javaPropertyName = column.getJavaPropertyName();

            sb.append("\t\t\t#{item.").append(javaPropertyName).append("},\n");
        }

        sb.append("\t\t)</foreach>\n");
        sb.append("\t</insert>\n\n");
    }


    /**
     * select
     * @return
     */
    private void appendGetById() {
        List<Column> columnList = table.getColumnList();
        Column column = columnList.get(0);

        sb.append("\t<!-- 通过id查询 -->\n");
        sb.append("\t<select id=\"get").append(table.getJavaClassName()).append("ById\" parameterType=\"long\" resultMap=\"BaseResultMap\">\n");
        sb.append("\t\tSELECT\n");
        sb.append("\t\t\t").append(column.getColumnName()).append("\n");
        sb.append("\t\t<include refid=\"SelectColumns\"/>\n");


        sb.append("\t\t FROM ").append(table.getTableName()).append("\n");
        sb.append("\t\t WHERE id={pId}\n");


        sb.append("\t</select>\n\n");

    }

    /**
     * select
     * @return
     */
    private void appendGet() {
        List<Column> columnList = table.getColumnList();
        Column column = columnList.get(0);

        sb.append("\t<!-- 通用查询方法 -->\n");
        sb.append("\t<select id=\"get").append(table.getJavaClassName()).append("\" parameterType=\"map\" resultMap=\"BaseResultMap\">\n");
        sb.append("\t\tSELECT\n");
        sb.append("\t\t\t").append(column.getColumnName()).append("\n");
        sb.append("\t\t<include refid=\"SelectColumns\"/>\n");


        sb.append("\t\t FROM ").append(table.getTableName()).append("\n");
        sb.append("\t\t WHERE 1=1\n");
        sb.append("\t\t<include refid=\"WhereParams\"/>\n");


        sb.append("\t</select>\n\n");

    }

    /**
     * selectCount
     * @return
     */
    private void appendCount() {
        List<Column> columnList = table.getColumnList();
        Column column;
        String columnName;
        String jdbcTypeName;
        String javaPropertyName;
        String javaPropertyParaName;


        sb.append("\t<!-- 查数量 -->\n");
        sb.append("\t<select id=\"count").append(table.getJavaClassName()).append("\" parameterType=\"map\" resultType=\"int\">\n");
        sb.append("\t\tSELECT count(*)\n");
        sb.append("\t\tFROM ").append(table.getTableName()).append("\n");
        sb.append("\t\tWHERE 1=1\n");
        sb.append("\t\t<include refid=\"WhereParams\"/>\n");
        sb.append("\t</select>\n\n");
    }

    /**
     * selectList
     * @return
     */
    private void appendList() {
        List<Column> columnList = table.getColumnList();
        Column column = columnList.get(0);
        String columnName;
        String jdbcTypeName;
        String javaPropertyName;
        String javaPropertyParaName;

        String idColumnName = column.getColumnName();

        sb.append("\t<!-- 分页查询 -->\n");

        sb.append("\t<select id=\"list").append(table.getJavaClassName()).append("\" parameterType=\"map\" resultMap=\"BaseResultMap\">\n");

        sb.append("\t\tSELECT\n");
        sb.append("\t\t\ta.").append(column.getColumnName()).append("\n");
        for (int i=1; i<columnList.size(); i++) {
            column = columnList.get(i);
            sb.append("\t\t\t,a.").append(column.getColumnName()).append("\n");
        }
        sb.append("\t\tFROM ").append(table.getTableName()).append(" a,\n");
        sb.append("\t\t(\n");

        column = columnList.get(0);
        sb.append("\t\tSELECT ").append(column.getColumnName()).append(" from ").append(table.getTableName()).append("\n");
        sb.append("\t\tWHERE 1=1\n");

        sb.append("\t\t<include refid=\"WhereParams\"/>\n");

        sb.append("\t\t) b where a.").append(idColumnName).append(" = b.").append(idColumnName).append("\n");

        sb.append("\t</select>\n\n");
    }


    /**
     * updateById
     * @return
     */
    private void appendUpdateById() {
        List<Column> columnList = table.getColumnList();
        Column firstColumn = columnList.get(0);
        String columnName = firstColumn.getColumnName();
        String jdbcTypeName = firstColumn.getJdbcTypeName();
        String javaPropertyName = firstColumn.getJavaPropertyName();
        String javaPropertyParaName = JavaBeansUtil.getCamelCaseString(columnName, true);
        String idColumnName = JavaBeansUtil.getCamelCaseString(columnName, true);

        sb.append("\t<!-- 更新，通用方法 -->\n");
        sb.append("\t<update id=\"update").append(table.getJavaClassName()).append("By").append(idColumnName).append("\"");
        sb.append(" parameterType=\"map\">\n");
        sb.append("\t\tupdate ").append(table.getTableName()).append("\n");
        sb.append("\t\t<include refid=\"UpdateSetColumns\"/>\n");
        sb.append("\t\twhere ").append(columnName).append(" = #{p").append(javaPropertyParaName).append(", jdbcType=").append(jdbcTypeName).append("}\n");
        sb.append("\t</update>\n\n");

    }


    /**
     * finish
     * @return
     */
    private void appendFinish() {
        sb.append("</mapper>\n");
    }




}
