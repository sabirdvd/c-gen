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
        appendSelectColumns();
        appendWhereParams();
        appendSetColumns();
        appendConvertMethod();

        appendSqlBuilderMethod();
        appendSqlBuilderClass();

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


    private void appendSelectColumns() {
        sb.append("\t//用于定义sql中的select 列， s字母开头，表示set例的意思\n");
        sb.append("\tpublic interface SelectColumns {\n");

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


    private void appendWhereParams() {
        sb.append("\t//用于定义sql中的where 查询条件， p字母开头，表示param的意思\n");
        sb.append("\tpublic interface WhereParams {\n");

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
                    .append(" = \"p")
                    .append(javaPropertyParaName)
                    .append("\";\n");
        }


        sb.append("\t}\n\n");
    }


    private void appendSetColumns() {
        sb.append("\t//用于定义sql中的 set 列, c字母开头，表示columns的意思\n");
        sb.append("\tpublic interface SetColumns {\n");

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
                    .append(" = \"s")
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
                .append("\t}\n\n");

    }



    private void appendSqlBuilderMethod() {
        sb.append("\t//-----------------------------------------------------------------------------\n");
        sb.append("\tpublic static SqlParamsBuilder sqlParamsBuilder() {\n");
        sb.append("\t\treturn new SqlParamsBuilder();\n");
        sb.append("\t}\n");
    }


    private void appendSqlBuilderClass() {
        sb.append("\tpublic static class SqlParamsBuilder {\n\n");
        sb.append("\t\tprivate Map<String, Object> params;\n\n");
        sb.append("\t\tpublic SqlParamsBuilder() {\n\t\t\tthis.params = new HashMap<>();\n\t\t}\n\n");
        sb.append("\t\tpublic Map<String, Object> getParams() {\n\t\t\treturn params;\n\t\t}\n\n");
        sb.append("\t\tpublic void setParams(Map<String, Object> params) {\n\t\t\tthis.params = params;\n\t\t}\n\n");
        sb.append("\t\tpublic void clear() {\n\t\t\tthis.params = new HashMap<>();\n\t\t\t}\n\n");

        appendsqlBuilderSelect();
        appendsqlBuilderWhere();
        appendsqlBuilderSet();


        sb.append("\t}\n");


    }


    private void appendsqlBuilderSelect() {
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

            sb.append("\t\tpublic SqlParamsBuilder select").append(javaPropertyParaName).append("() {\n")
                    .append("\t\t\tthis.params.put(SelectColumns.").append(javaPropertyParaName).append(", true);\n")
                    .append("\t\t\treturn this;\n")
                    .append("\t\t}\n\n");
        }

    }


    private void appendsqlBuilderWhere() {
        List<Column>  columnList = table.getColumnList();

        String columnName;
        String jdbcTypeName;
        String javaPropertyType;
        String javaPropertyName;
        String javaPropertyParaName;

        for (Column column: columnList) {

            columnName = column.getColumnName();
            jdbcTypeName = column.getJdbcTypeName();
            javaPropertyType = column.getJavaPropertyType();
            javaPropertyName = column.getJavaPropertyName();
            javaPropertyParaName = JavaBeansUtil.getCamelCaseString(columnName, true);

            sb.append("\t\tpublic SqlParamsBuilder where").append(javaPropertyParaName).append("(").append(javaPropertyType).append(" ").append(javaPropertyName).append(") {\n")
                    .append("\t\t\tthis.params.put(WhereParams.").append(javaPropertyParaName).append(", ").append(javaPropertyName).append(");\n")
                    .append("\t\t\treturn this;\n")
                    .append("\t\t}\n\n");
        }

    }


    private void appendsqlBuilderSet() {
        List<Column>  columnList = table.getColumnList();

        String columnName;
        String jdbcTypeName;
        String javaPropertyType;
        String javaPropertyName;
        String javaPropertyParaName;

        for (Column column: columnList) {

            columnName = column.getColumnName();
            jdbcTypeName = column.getJdbcTypeName();
            javaPropertyType = column.getJavaPropertyType();
            javaPropertyName = column.getJavaPropertyName();
            javaPropertyParaName = JavaBeansUtil.getCamelCaseString(columnName, true);

            sb.append("\t\tpublic SqlParamsBuilder set").append(javaPropertyParaName).append("(").append(javaPropertyType).append(" ").append(javaPropertyName).append(") {\n")
                    .append("\t\t\tthis.params.put(SetColumns.").append(javaPropertyParaName).append(", ").append(javaPropertyName).append(");\n")
                    .append("\t\t\treturn this;\n")
                    .append("\t\t}\n\n");
        }

    }



    /**
     * finish
     * @return
     */
    private void appendFinish() {
        sb.append("}");
    }



}
