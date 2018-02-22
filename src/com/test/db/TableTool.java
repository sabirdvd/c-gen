package com.test.db;

import com.test.common.types.JdbcTypeJavaTypeNameTranslator;
import com.test.common.types.JdbcTypeNameTranslator;
import com.test.common.util.JavaBeansUtil;
import com.test.model.Column;
import com.test.model.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuisheng on 2018/2/6.
 */
public class TableTool {

    public static void getTableInfo1(Connection con, String tableName) throws SQLException {
        // statement用来执行SQL语句
        Statement statement =con.createStatement();
        //要执行的SQL语句id和content是表review中的项。
        String sql = "SELECT column_name, data_type FROM information_schema.columns WHERE table_name = 'dt_order'";
        ResultSet rs =statement.executeQuery(sql);

        //输出id值和content值
        while(rs.next()) {
            System.out.println(rs.getString("column_name")+ "\t" + rs.getString("data_type"));
        }
        rs.close();

    }


    public static void getTableInfo(Connection con, String tableName) throws SQLException{

        String sql = new StringBuilder("select * from ")
                .append(tableName)
                .append(" limit 1")
                .toString();

        Statement state = con.createStatement();
        ResultSet rs = state.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData() ;

        for(int i = 1; i <= rsmd.getColumnCount(); i++) {

            System.out.println(
                    new StringBuilder()
                            .append(rsmd.getCatalogName(i)).append(' ')
                            .append(rsmd.getTableName(i)).append(' ')
                            .append(rsmd.getColumnName(i)).append(' ')
                            .append(rsmd.getColumnClassName(i)).append(' ')
                            .append(rsmd.getColumnCount()).append(' ')
                            .append(rsmd.getColumnType(i)).append(' ')
                            .append(rsmd.getColumnTypeName(i)).append(' ')
                            .append(rsmd.getColumnLabel(i)).append(' ')
                            .append(rsmd.getColumnDisplaySize(i)).append(' ')
                            .append(rsmd.getSchemaName(i))
            );





//            SQLColumn col = new SQLColumn(colname);
//            SQLType type = simpleType(typeName, itype);
//            if (type.allowsParameters())
//                type.setParameterString("" + size);
//            col.setType(type);
//            col.setIType(itype);
//            col.setSize(size);
//            col.setScale(scale);
//            col.setPrecision(precision);
//            col.setNullable(nullable);
//            col.setReadOnly(rsmd.isReadOnly(i));
//            col.setAutoIncrement(rsmd.isAutoIncrement(i));
//            col.setSearchable(rsmd.isSearchable(i));
//            col.setCurrency(rsmd.isCurrency(i));
//            col.setCaseSensitive(rsmd.isCaseSensitive(i));
//            col.setSigned(rsmd.isSigned(i));
//            col.setClassType(rsmd.getColumnClassName(i));
//            col.setDisName(rsmd.getColumnLabel(i));
//            if ( col.getDisName().length() > 0 )
//                col.setName(col.getDisName()) ;
//            columns.add(col);
        }
    }


    public static Table getTableInfo(Connection con,
                                     String tableName,
                                     String className,
                                     String packageName) throws SQLException{

        DatabaseMetaData dbmd = con.getMetaData();
        ResultSet columnSet = dbmd.getColumns(null, null, tableName, null);

        Table table = new Table();
        table.setJavaClassName(className);
        table.setJavaPackageName(packageName);
        table.setTableName(tableName);
        table.setJavaClassFullName(packageName + ".po." + className);

        List<Column> columnList = new ArrayList<Column>();
        Column col;


//        ResultSetMetaData rsmd = columnSet.getMetaData() ;
//        for(int i = 1; i <= rsmd.getColumnCount(); i++) {
//
//            System.out.println(
//                    new StringBuilder()
//                            .append(rsmd.getCatalogName(i)).append(' ')
//                            .append(rsmd.getTableName(i)).append(' ')
//                            .append(rsmd.getColumnName(i)).append(' ')
//                            .append(rsmd.getColumnClassName(i)).append(' ')
//                            .append(rsmd.getColumnCount()).append(' ')
//                            .append(rsmd.getColumnType(i)).append(' ')
//                            .append(rsmd.getColumnTypeName(i)).append(' ')
//                            .append(rsmd.getColumnLabel(i)).append(' ')
//                            .append(rsmd.getColumnDisplaySize(i)).append(' ')
//                            .append(rsmd.getSchemaName(i))
//            );
//
//
//        }


        while (columnSet.next()) {
            col = new Column();
            col.setColumnName(columnSet.getString("COLUMN_NAME"));
            col.setColumnComment(columnSet.getString("REMARKS"));
            col.setJdbcType(Integer.parseInt(columnSet.getString("DATA_TYPE")));
//            col.setJdbcTypeName(columnSet.getString("TYPE_NAME"));
            col.setJdbcTypeName(JdbcTypeNameTranslator.getJdbcTypeName(col.getJdbcType()));

            col.setJavaPropertyName(JavaBeansUtil.getCamelCaseString(col.getColumnName(), false));
            col.setJavaPropertyType(JdbcTypeJavaTypeNameTranslator.getJavaClassName(col.getJdbcType()));

            columnList.add(col);


        }

        table.setColumnList(columnList);
        return table;

    }
}
