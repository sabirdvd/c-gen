package com.test;

import com.test.common.types.JdbcTypeJavaTypeNameTranslator;
import com.test.common.util.JavaBeansUtil;
import com.test.db.TableTool;
import com.test.gen.Generator;
import com.test.gen.impl.MapperGenerator;
import com.test.gen.impl.PoGenerator;
import com.test.gen.impl.XmlGenerator;
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
            new XmlGenerator().generateFile(table);
            new MapperGenerator().generateFile(table);



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
