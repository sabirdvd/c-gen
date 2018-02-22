package com.test.gen.impl;

import com.test.common.util.FileUtils;
import com.test.common.util.JavaBeansUtil;
import com.test.gen.Generator;
import com.test.model.Column;
import com.test.model.Table;

import java.io.File;
import java.util.List;

/**
 * Created by shuisheng on 2018/2/5.
 */
public class PoGenerator implements Generator {

    private Table table;
    private StringBuilder sb;

    @Override
    public void generateFile(Table table) {

        this.table = table;
        this.sb = new StringBuilder();

        appendPackage();
        appendClass();
        appendProperties();
        appendGetSetMethod();
        appendFinish();

        System.out.println("Generate po file Begin ...");
        FileUtils.writeFile(table.getJavaClassName() + ".java", sb.toString());
        System.out.println("Generate po file End ...");

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
        sb.append("public class ").append(table.getJavaClassName()).append(" implements Serializable {\n\n");
    }

    /**
     * 拼装 property
     * 例如:
     * private String orderId;//订单id
     * @return
     */
    private void appendProperties() {
        List<Column>  columnList = table.getColumnList();

        for (Column column: columnList) {
            sb.append("\tprivate ")
                    .append(column.getJavaPropertyType()).append(" ")
                    .append(column.getJavaPropertyName())
                    .append(";//")
                    .append(column.getColumnComment())
                    .append("\n\n");

        }

    }


    /**
     * 拼装 get set 方法
     * 例如:
     * @return
     */
    private void appendGetSetMethod() {

        List<Column>  columnList = table.getColumnList();

        String propertyName;
        String returnType;
        String paramType;
        String getMethodName;
        String setMethodName;


        for (Column column: columnList) {

            propertyName = column.getJavaPropertyName();
            returnType = column.getJavaPropertyType();
            paramType = column.getJavaPropertyType();
            getMethodName = JavaBeansUtil.getGetterMethodName(propertyName);
            setMethodName = JavaBeansUtil.getSetterMethodName(propertyName);


            //get
            sb.append("\tpublic ").append(returnType).append(" ").append(getMethodName).append("() {\n")
                    .append("\t\t").append("return ").append(propertyName).append(";\n")
                    .append("\t}\n\n");

            //set
            sb.append("\tpublic void ").append(setMethodName).append("(").append(paramType).append(" ").append(propertyName).append(") {\n")
                    .append("\t\t").append("this.").append(propertyName).append(" = ").append(propertyName).append(";\n")
                    .append("\t}\n\n");


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
