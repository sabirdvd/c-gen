package com.test;

import com.test.common.types.JdbcTypeJavaTypeNameTranslator;
import com.test.common.util.JavaBeansUtil;
import com.test.db.TableTool;
import com.test.gen.Generator;
import com.test.gen.impl.*;
import com.test.model.Column;
import com.test.model.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuisheng on 2018/2/5.
 */
public class Main {
    public static void main(String[] args) {

        String driver ="com.mysql.jdbc.Driver";


        //cms
        String url ="jdbc:mysql://test-com-mysql.cngjxhvg2kok.ap-southeast-2.rds.amazonaws.com/alfred_test?useUnicode=true";
        String user = "test_com_mysql";
        String password ="R735cXHJ&jg5pqbWu0XLILnErr7ztf";



        String tableName = "user_acount";
        String className = "UserAccount";
        String packageName = "cn.hk.techne.user";

        try {
            // 加载驱动程序
            Class.forName(driver);

            // 连续数据库
            Connection conn = DriverManager.getConnection(url, user, password);
            if(!conn.isClosed()) {
                System.out.println("Connecting to the Database...");
            }

//            TableTool.getTableInfo(conn, tableName);

            Table table = TableTool.getTableInfo(conn, tableName, className, packageName);
//
            new PoGenerator().generateFile(table);
            new PoBuilderGenerator().generateFile(table);
            new XmlGenerator().generateFile(table);
            new MapperGenerator().generateFile(table);
            new DaoGenerator().generateFile(table);
            new DaoImplGenerator().generateFile(table);



            conn.close();
        } catch(ClassNotFoundException e) {
            System.out.println("Sorry,can`tfind the Driver!");
            e.printStackTrace();

        } catch(SQLException e) {
            e.printStackTrace();

        } catch(Exception e){
            e.printStackTrace();
        }
    }





}
