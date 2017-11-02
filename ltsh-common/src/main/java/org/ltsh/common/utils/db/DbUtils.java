package org.ltsh.common.utils.db;



import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Random on 2017/9/27.
 */
public class DbUtils {

    public static String[] getColumns(Class classT) {
        return getColumns(getDbColumns(classT));
    }
    public static String[] getColumns(List<DbColumn> dbColumns) {
        String[] columns = new String[dbColumns.size()];
        for (int i = 0; i < dbColumns.size(); i++) {
            columns[i] = dbColumns.get(i).getColumnName();
        }
        return columns;
    }
    public static Object[] getValues(Object object) {
        List<Object> list = new ArrayList<>();
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field field:declaredFields) {
            if(field.getName().toLowerCase().equals("id") || field.getAnnotation(NoDbColumn.class) != null) {
                continue;
            }
            try {
                PropertyMethod propertyMethod = new PropertyMethod(field.getName(), object.getClass());
                Object o = propertyMethod.getReadMethod().invoke(object);
                list.add(o);
            } catch (Exception e) {
                e.printStackTrace();
//                LogUtils.e(DbUtils.class.getName(),e.getMessage(), e);
            }
        }
        return list.toArray();
    }

    public static String getInsertSql(Class classT) {
        return getInsertSql(getDbColumns(classT));
    }
    public static String getInsertSql(List<DbColumn> dbColumns) {
        String str = "INSERT INTO";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str).append(" ").append(dbColumns.get(0).getTableName()).append("(");
        StringBuffer columndSb = new StringBuffer();
        StringBuffer valueSb = new StringBuffer();
        for (DbColumn dbColumn:dbColumns) {
            if(dbColumn.isPk()) {

            } else {
                columndSb.append(dbColumn.getColumnName()).append(",");
                valueSb.append("?,");
            }

        }
        stringBuffer.append(columndSb.substring(0, columndSb.length() - 1)).append(") values(").append(valueSb.substring(0, valueSb.length() - 1)).append(")");

        return stringBuffer.toString();
    }


    public static String getCreateTable(List<DbColumn> dbColumns) {
        String str = "CREATE TABLE";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str).append(" ").append(dbColumns.get(0).getTableName()).append("(");
        for (DbColumn dbColumn:dbColumns) {
            if(dbColumn.isPk()) {
                stringBuffer.append(dbColumn.getColumnName()).append(" ").append(dbColumn.getDbType()).append(" PRIMARY KEY AUTOINCREMENT").append(",");
            } else {
                stringBuffer.append(dbColumn.getColumnName()).append(" ").append(dbColumn.getDbType()).append(",");
            }
        }
        stringBuffer = new StringBuffer(stringBuffer.substring(0, stringBuffer.length() -1));
        stringBuffer.append(")");
        return stringBuffer.toString();
    }
    public static String getTableName(Class classT) {
        String name = classT.getSimpleName();
        return getColumnName(name, 1).toLowerCase();
    }

    public static List<DbColumn> getDbColumns(Class classT) {
        Field[] fields = classT.getDeclaredFields();
        String tableName = getTableName(classT);
        List<DbColumn> columns = new ArrayList<>();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String name = field.getName();

            NoDbColumn annotation = field.getAnnotation(NoDbColumn.class);
            if(annotation != null) {
                continue;
            }
            Class<?> declaringClass = field.getType();
            DbColumn dbColumn = new DbColumn(getColumnName(name, 0), getDbType(declaringClass), tableName, false);
            if(name.toLowerCase().equals("id")) {
                dbColumn.setPk(true);
            }
            if(dbColumn.isPk()) {
                columns.add(0,dbColumn);
            } else {
                columns.add(dbColumn);
            }


        }
        return columns;
    }


    public static String getColumnName(String filedName, int skip) {
        StringBuffer stringBuffer = new StringBuffer(filedName);
        for (int j = skip; j < stringBuffer.length(); j++) {
            String substring = stringBuffer.substring(j, j + 1);
            if(substring.equals(substring.toUpperCase())) {
                stringBuffer.replace(j, j+1,"_" + substring.toLowerCase());
//                    System.out.println(stringBuffer.substring(i,i+1));
            }
        }
        return stringBuffer.toString();
    }

    public static String getDbType(Type toType) {
        String result = "";
        if(toType == Integer.class || toType == Integer.TYPE) {
            result = "INTEGER";
        } else if(toType == Double.class || toType == Double.TYPE) {
            result = "FLOAT";
        } else if(toType == Boolean.class || toType == Boolean.TYPE) {
            result = "INTEGER";
        } else if(toType == Byte.class || toType == Byte.TYPE) {
            result = "INTEGER";
        } else if(toType == Character.class || toType == Character.TYPE) {
            result = "VARCHAR(50)";
        } else if(toType == Short.class || toType == Short.TYPE) {
            result = "integer";
        } else if(toType == Long.class || toType == Long.TYPE) {
            result = "BIGINT";
        } else if(toType == Float.class || toType == Float.TYPE) {
            result = "FLOAT";
        } else if(toType == BigInteger.class) {
            result = "BIGINT";
        } else if(toType == BigDecimal.class) {
            result = "FLOAT";
        } else if(toType == String.class) {
            result = "VARCHAR(50)";
        }else if(toType == Date.class) {
            result = "DATETIME";
        } else {
            throw new RuntimeException("Don't know about " + toType);
        }
        return result;
    }

    public static void setValue(Class toType, Method method, Object obj, Object value) {

        try {
            if(toType.isArray() && value != null && value.getClass().isArray()) {
                method.invoke(obj, value);
            }else if(toType == Integer.class || toType == Integer.TYPE) {
                method.invoke(obj, (Integer)value);
            } else if(toType == Double.class || toType == Double.TYPE) {
                method.invoke(obj, ((Number)value).doubleValue());
            } else if(toType == Boolean.class || toType == Boolean.TYPE) {
                method.invoke(obj, ((Number)value).intValue() == 0 ? false : true);
            } else if(toType == Byte.class || toType == Byte.TYPE) {
                method.invoke(obj, ((Number)value).byteValue());
            } else if(toType == Character.class || toType == Character.TYPE) {
                method.invoke(obj, ((String)value));
            } else if(toType == Short.class || toType == Short.TYPE) {
                method.invoke(obj, ((Number)value).shortValue());
            } else if(toType == Long.class || toType == Long.TYPE) {
                method.invoke(obj, ((Number)value).longValue());
            } else if(toType == Float.class || toType == Float.TYPE) {
                method.invoke(obj, ((Number)value).floatValue());
            } else if(toType == BigInteger.class) {
                throw new RuntimeException("Don't know about " + toType);
            } else if(toType == BigDecimal.class) {
                method.invoke(obj, new BigDecimal(value + ""));
            } else if(toType == String.class) {
                method.invoke(obj, ((String)value));
            } else {
                throw new RuntimeException("Don't know about " + toType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

