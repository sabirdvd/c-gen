/**
 *    Copyright 2006-2016 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.test.common.types;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * jdbcType与java类名的转换关系
 * 
 * @author shuisheng 2018-02-06
 */
public class JdbcTypeJavaTypeNameTranslator {

    private static Map<Integer, String> jdbcTypeToJavaClassName;
    private static Map<String, Integer> javaClassNameToJdbcType;

    static {
        jdbcTypeToJavaClassName = new HashMap<Integer, String>();
        jdbcTypeToJavaClassName.put(Types.ARRAY, "List"); //$NON-NLS-1$
        jdbcTypeToJavaClassName.put(Types.BIGINT, "Long"); //$NON-NLS-1$
        jdbcTypeToJavaClassName.put(Types.BINARY, "Byte"); //$NON-NLS-1$
        jdbcTypeToJavaClassName.put(Types.BIT, "Byte"); //$NON-NLS-1$
        jdbcTypeToJavaClassName.put(Types.BLOB, "String"); //$NON-NLS-1$
        jdbcTypeToJavaClassName.put(Types.BOOLEAN, "Boolean"); //$NON-NLS-1$
        jdbcTypeToJavaClassName.put(Types.CHAR, "Char"); //$NON-NLS-1$
        jdbcTypeToJavaClassName.put(Types.CLOB, "String"); //$NON-NLS-1$

        jdbcTypeToJavaClassName.put(Types.DATE, "Date"); //$NON-NLS-1$
        jdbcTypeToJavaClassName.put(Types.DECIMAL, "BigDecimal"); //$NON-NLS-1$
        jdbcTypeToJavaClassName.put(Types.DOUBLE, "BigDecimal"); //$NON-NLS-1$

        jdbcTypeToJavaClassName.put(Types.FLOAT, "Float"); //$NON-NLS-1$

        jdbcTypeToJavaClassName.put(Types.INTEGER, "Integer"); //$NON-NLS-1$
        jdbcTypeToJavaClassName.put(Types.JAVA_OBJECT, "Object"); //$NON-NLS-1$

        jdbcTypeToJavaClassName.put(Types.LONGVARCHAR, "String"); //$NON-NLS-1$

        jdbcTypeToJavaClassName.put(Types.NULL, "null"); //$NON-NLS-1$
        jdbcTypeToJavaClassName.put(Types.OTHER, "OTHER"); //$NON-NLS-1$

        jdbcTypeToJavaClassName.put(Types.SMALLINT, "Integer"); //$NON-NLS-1$

        jdbcTypeToJavaClassName.put(Types.TIME, "Date"); //$NON-NLS-1$
        jdbcTypeToJavaClassName.put(Types.TIMESTAMP, "Timestamp"); //$NON-NLS-1$

        jdbcTypeToJavaClassName.put(Types.TINYINT, "Integer"); //$NON-NLS-1$
        jdbcTypeToJavaClassName.put(Types.VARCHAR, "String"); //$NON-NLS-1$

//        javaClassNameToJdbcType = new HashMap<String, Integer>();
//        javaClassNameToJdbcType.put("ARRAY", Types.ARRAY); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("BIGINT", Types.BIGINT); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("BINARY", Types.BINARY); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("BIT", Types.BIT); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("BLOB", Types.BLOB); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("BOOLEAN", Types.BOOLEAN); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("CHAR", Types.CHAR); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("CLOB", Types.CLOB); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("DATALINK", Types.DATALINK); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("DATE", Types.DATE); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("DECIMAL", Types.DECIMAL); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("DISTINCT", Types.DISTINCT); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("DOUBLE", Types.DOUBLE); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("FLOAT", Types.FLOAT); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("INTEGER", Types.INTEGER); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("JAVA_OBJECT", Types.JAVA_OBJECT); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("LONGVARBINARY", Types.LONGVARBINARY); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("LONGVARCHAR", Types.LONGVARCHAR); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("NCHAR", Types.NCHAR); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("NCLOB", Types.NCLOB); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("NVARCHAR", Types.NVARCHAR); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("LONGNVARCHAR", Types.LONGNVARCHAR); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("NULL", Types.NULL); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("NUMERIC", Types.NUMERIC); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("OTHER", Types.OTHER); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("REAL", Types.REAL); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("REF", Types.REF); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("SMALLINT", Types.SMALLINT); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("STRUCT", Types.STRUCT); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("TIME", Types.TIME); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("TIMESTAMP", Types.TIMESTAMP); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("TINYINT", Types.TINYINT); //$NON-NLS-1$
//        javaClassNameToJdbcType.put("String", Types.VARCHAR); //$NON-NLS-1$
    }

    /**
     * Utility Class - no instances
     */
    private JdbcTypeJavaTypeNameTranslator() {
        super();
    }

    /**
     * Translates from a java.sql.Types values to the proper iBATIS string
     * representation of the type.
     * 
     * @param jdbcType
     *            a value from java.sql.Types
     * @return the iBATIS String representation of a JDBC type
     */
    public static String getJavaClassName(int jdbcType) {
        String answer = jdbcTypeToJavaClassName.get(jdbcType);
        if (answer == null) {
            answer = "OTHER"; //$NON-NLS-1$
        }

        return answer;
    }

    /**
     * Translates from the string representation of the type to the
     * java.sql.Types value.
     * 
     * @param jdbcTypeName
     *            the iBATIS String representation of a JDBC type
     * @return a value from java.sql.Types
     */
//    public static int getJdbcType(String jdbcTypeName) {
//        Integer answer = nameToType.get(jdbcTypeName);
//        if (answer == null) {
//            answer = Types.OTHER;
//        }
//
//        return answer;
//    }
}
