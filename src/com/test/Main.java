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


        String driver ="com.mysql.jdbc.Driver";
//        String url ="jdbc:mysql://rm-wz93gvt9eu4a5ac14vo.mysql.rds.aliyuncs.com/db_order?useUnicode=true&characterEncoding=utf-8";
//        String user = "root_junyi";
//        String password ="root@junyi123456";

        String url ="jdbc:mysql://rm-wz9m8c1v6f01g7757io.mysql.rds.aliyuncs.com/db_cms?useUnicode=true&characterEncoding=utf-8";
        String user = "root_junyi";
        String password ="root@junyi123456";

//        String tableName = "dt_order";
////        String tableName = "dt_order_deliver";
//        String className = "OrderDeliver";
//        String packageName = "com.test.order";

//        String tableName = "t_schedule_job";
//        String className = "ScheduleJob";
//        String packageName = "com.junyi.schedule";
//        String tableName = "u_user";
//        String className = "User";
//        String packageName = "com.junyi.login";

        String tableName = "t_message_push";
        String className = "MessagePush";
        String packageName = "com.junyi.message";

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
