package com.test.gen.impl;

import com.test.common.util.FileUtils;
import com.test.common.util.JavaBeansUtil;
import com.test.gen.Generator;
import com.test.model.Column;
import com.test.model.Table;

import java.util.List;

/**
 * Created by shuisheng on 2018/2/5.
 */
public class MapperGenerator implements Generator {

    private Table table;
    private StringBuilder sb;

    @Override
    public void generateFile(Table table) {

        this.table = table;
        this.sb = new StringBuilder();

        appendPackage();
        appendInteface();
        appendInsert();
        appendGet();
        appendCount();
        appendList();
        appendUpdateById();
        appendFinish();

        System.out.println("Generate po file Begin...");
        FileUtils.writeFile(table.getJavaClassName() + "Mapper.java", sb.toString());
        System.out.println("Generate po file End...");

    }

    /**
     * package
     * @return
     */
    private void appendPackage() {
        sb.append("package ").append(table.getJavaPackageName()).append(".dao;\n\n");
    }


    /**
     * class
     * @return
     */
    private void appendInteface() {
        sb.append("public interface ").append(table.getJavaClassName()).append("Mapper {\n\n");
    }


    private void appendInsert() {
        sb.append("\tint insert").append(table.getJavaClassName()).append("(")
                .append(table.getJavaClassName())
                .append(" obj);\n\n");

        }

    private void appendGet() {
        sb.append("\tList<").append(table.getJavaClassName()).append("> ");
        sb.append("get").append(table.getJavaClassName()).append("(Map<String, Object> params);\n\n");
    }

    private void appendCount() {
        sb.append("\tint count").append(table.getJavaClassName()).append("(Map<String, Object> params);\n\n");
    }

    private void appendList() {
        sb.append("\tList<").append(table.getJavaClassName()).append("> ");

        sb.append("list").append(table.getJavaClassName()).append("(Map<String, Object> params);\n\n");
    }

    private void appendUpdateById() {
        Column column = table.getColumnList().get(0);
        String idColumnName = column.getColumnName();
        sb.append("\tint update").append(table.getJavaClassName()).append("By")
                .append(JavaBeansUtil.getCamelCaseString(idColumnName, true)).append("(Map<String, Object> params);\n\n");
    }




    /**
     * finish
     * @return
     */
    private StringBuilder appendFinish() {
        sb.append("}");
        return sb;
    }



}
