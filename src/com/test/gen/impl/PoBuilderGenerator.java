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
public class PoBuilderGenerator implements Generator {

    private Table table;
    private StringBuilder sb;

    @Override
    public void generateFile(Table table) {

        this.table = table;
        this.sb = new StringBuilder();

        appendPackage();
        appendClass();
        appendOrderColumns();
        appendConvertMethod();
        appendFinish();

        System.out.println("Generate poBuilder file Begin ...");
        FileUtils.writeFile(table.getJavaClassName() + "Builder.java", sb.toString());
        System.out.println("Generate poBuilder file End ...");

    }

    /**
     * package
     * @return
     */
    private void appendPackage() {
        sb.append("package ").append(table.getJavaPackageName()).append(".dto;\n\n");
    }


    /**
     * class
     * @return
     */
    private void appendClass() {
        sb.append("public class ").append(table.getJavaClassName()).append(" Builder {\n\n");
    }


    private void appendOrderColumns() {
        sb.append("\tpublic interface orderColumns {\n");

        List<Column>  columnList = table.getColumnList();

        String columnName;
        String jdbcTypeName;
        String javaPropertyName;
        String javaPropertyParaName;

        for (Column column: columnList) {

            columnName = column.getColumnName();
            jdbcTypeName = column.getJdbcTypeName();
            javaPropertyName = column.getJavaPropertyName();
            javaPropertyParaName = JavaBeansUtil.getCamelCaseString(columnName, true);

            sb.append("\t\tString ")
                    .append(javaPropertyParaName)
                    .append(" = \"c")
                    .append(javaPropertyParaName)
                    .append("\";\n");
        }


        sb.append("\t}\n\n");
    }


    private void appendConvertMethod() {
        String javaClassName = table.getJavaClassName();
        sb.append("\tpublic static ").append(javaClassName).append("Dto convert2Dto(").append(javaClassName).append(" ").append(javaClassName).append(") {\n")
                .append("\t\tif (").append(javaClassName).append(" == null) {").append("\n")
                .append("\t\t\treturn null;\n")
                .append("\t\t}")
                .append("\t\t\n")
                .append("\t\treturn dto;\n")
                .append("\t}\n\n");


        sb.append("\tpublic static List<").append(javaClassName).append("Dto> convert2Dto(List<").append(javaClassName).append("> ").append(javaClassName).append("List) {\n")
                .append("\t\tif (").append(javaClassName).append("List == null) {").append("\n")
                .append("\t\t\treturn null;\n")
                .append("\t\t}")
                .append("\t\t\n")
                .append("\t\treturn dtoList;\n")
                .append("\t}");

    }



    /**
     * finish
     * @return
     */
    private void appendFinish() {
        sb.append("}");
    }



}
