package com.test.gen.impl;

import com.test.common.util.FileUtils;
import com.test.common.util.JavaBeansUtil;
import com.test.gen.Generator;
import com.test.model.Column;
import com.test.model.Table;

/**
 * Created by shuisheng on 2018/7/5.
 */
public class DaoImplGenerator implements Generator {

    private Table table;
    private StringBuilder sb;

    @Override
    public void generateFile(Table table) {

        this.table = table;
        this.sb = new StringBuilder();

        appendPackage();
        appendclass();
        appendInsert();
        appendInsertList();
        appendGet();
        appendCount();
        appendList();
        appendUpdateById();
        appendFinish();

        System.out.println("Generate po file Begin...");
        FileUtils.writeFile(table.getJavaClassName() + "DaoImpl.java", sb.toString());
        System.out.println("Generate po file End...");

    }

    /**
     * package
     * @return
     */
    private void appendPackage() {
        sb.append("package ").append(table.getJavaPackageName()).append(".dao.impl;\n\n");
    }


    /**
     * class
     * @return
     */
    private void appendclass() {
        sb.append("@Repository\n");
        sb.append("public class ").append(table.getJavaClassName()).append("DaoImpl {\n\n");

        sb.append("\tprivate static String userNameSpace = \"").append(table.getJavaClassFullName()).append("Mapper.\";\n\n");
        sb.append("\t@Autowired\n" +
                  "\tSqlSession sqlSession;\n\n");

    }


    private void appendInsert() {
        sb.append("\t/**\n" +
                "\t* 单条插入，返回id\n" +
                "\t* @param obj\n" +
                "\t* @return\n" +
                "\t*/\n");
        sb.append("\t@Override\n");
        sb.append("\tlong insert").append(table.getJavaClassName()).append("(")
                .append(table.getJavaClassName())
                .append(" obj) {;\n");
        sb.append("\t\treturn sqlSession.insert(userNameSpace + \"insert").append(table.getJavaClassName()).append("\", obj);\n");
        sb.append("\t}\n\n");
    }



    private void appendInsertList() {
        sb.append("\t/**\n" +
                "\t* 多条插入，返回插入条数\n" +
                "\t* @param objList\n" +
                "\t* @return\n" +
                "\t*/\n");

        sb.append("\t@Override\n");
        sb.append("\tpublic int insert").append(table.getJavaClassName()).append("List(List<")
                .append(table.getJavaClassName())
                .append("> objList){\n");
        sb.append("\t\treturn sqlSession.insert(userNameSpace + \"insert").append(table.getJavaClassName()).append("List\", objList);\n");
        sb.append("\t}\n\n");
    }



    private void appendGetById() {
        sb.append("\t@Override\n");
        sb.append("\tpublic ").append(table.getJavaClassName()).append("> ");
        sb.append("get").append(table.getJavaClassName()).append("ById(long id) {\n");
        sb.append("\t\tif (id > 0) {\n");
        sb.append("\t\t\treturn sqlSession.selectOne(userNameSpace + \"get").append(table.getJavaClassName()).append("ById\", id);\n");
        sb.append("\t\t} else {\n");
        sb.append("\t\t\treturn null;\n");
        sb.append("\t\t}");
        sb.append("\t}\n\n");
    }


    private void appendGet() {
        sb.append("\t@Override\n");
        sb.append("\tpublic List<").append(table.getJavaClassName()).append("> ");
        sb.append("get").append(table.getJavaClassName()).append("(Map<String, Object> params) {\n");
        sb.append("\t\treturn sqlSession.selectList(userNameSpace + \"get").append(table.getJavaClassName()).append("\", params);\n");
        sb.append("\t}\n\n");
    }

    private void appendCount() {
        sb.append("\t@Override\n");
        sb.append("\tpublic int count").append(table.getJavaClassName()).append("(Map<String, Object> params) {\n");
        sb.append("\t\treturn sqlSession.selectOne(userNameSpace + \"count").append(table.getJavaClassName()).append("\", params);\n");
        sb.append("\t}\n\n");
    }

    private void appendList() {
        sb.append("\t@Override\n");
        sb.append("\tpublic List<").append(table.getJavaClassName()).append("> ");
        sb.append("list").append(table.getJavaClassName()).append("(Map<String, Object> params) {\n");
        sb.append("\t\treturn sqlSession.selectList(userNameSpace + \"list").append(table.getJavaClassName()).append("\", params);\n");
        sb.append("\t}\n\n");
    }

    private void appendUpdateById() {
        sb.append("\t@Override\n");
        sb.append("\tpublic int update").append(table.getJavaClassName()).append("ById").append("(Map<String, Object> params) {\n");
        sb.append("\t\treturn sqlSession.update(userNameSpace + \"update").append(table.getJavaClassName()).append("ById\", params);\n");
        sb.append("\t}\n\n");
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
