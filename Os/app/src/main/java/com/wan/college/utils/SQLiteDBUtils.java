package com.wan.college.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.wan.college.model.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 万文杰 on 2017/2/8.
 */


public class SQLiteDBUtils<T> {

    private static String DB_NAME = "model.db";//默认数据库名称
    private static String TABLE_NAME = "modelinfo";//默认数据表名称
    private static int DB_VERSION = 1;//数据库版本
    private static Class T; //外部传入的Class类
    //表的字段名
    private List<String> KEY_IDS;
    private List<Object> KEY_VALUES;
    private List<String> KEY_TYPES;
    private List<String> KEY_METHODS;
    private List<Class> KEY_PARAMETER_TYPES;

    private SQLiteDatabase mDatabase;
    private Context mContext;
    private DBOpenHelper mDbOpenHelper;//数据库打开帮助类

    /*
        public SQLiteDBUtils(Context context, Object obj) {
            mContext = context;
            getObjAttr(obj);
        }*/
    public SQLiteDBUtils(Context context, Class class_name) {
        mContext = context;
        T = class_name;
        getClassAttr(class_name);
    }

    public SQLiteDBUtils(Context context, Class class_name, String db_name, String table_name, int db_version) {
        mContext = context;
        DB_NAME = db_name;
        TABLE_NAME = table_name;
        DB_VERSION = db_version;
        T = class_name;
        getClassAttr(class_name);

    }
    /**
     * 反射机制获取Object对象，所有成员变量名和属性值
     */
    public void getObjValue(Object obj) {
        KEY_VALUES = new ArrayList<>();
        // 获取对象obj的所有属性域
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0; i < KEY_IDS.size(); i++) {
            try {
                //   System.out.println("name： " + KEY_IDS.get(i));
                Field field = fields[i];
                //   System.out.println("name2： " + field.getName());
                boolean access = field.isAccessible();
                if (!access) field.setAccessible(true);
                //从obj中获取field变量
                Object value = field.get(obj);
                   System.out.println("数据： " + value);
                KEY_VALUES.add(value);
                if (!access) field.setAccessible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void getObjAttr(Object obj) {
        KEY_IDS = new ArrayList<>();
        KEY_VALUES = new ArrayList<>();
        KEY_TYPES = new ArrayList<>();
        // 获取对象obj的所有属性域
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            // 对于每个属性，获取属性名
            String varName = field.getName();
            //提出序列化的隐藏变量名
            if (!varName.equals("$change") && !varName.equals("CREATOR") && !varName.equals("serialVersionUID")) {

                try {
                    boolean access = field.isAccessible();
                    if (!access) field.setAccessible(true);
                    //从obj中获取field变量
                    Object value = field.get(obj);
                    String str = field.getGenericType().toString();
                    String[] strs = str.split("\\.");
                    str = strs[strs.length - 1];
                    System.out.println("变量： " + varName + " = " + value + "  数据类型：" + str);
                    KEY_IDS.add(varName);
                    KEY_VALUES.add(value);
                    KEY_TYPES.add(str);
                    if (!access) field.setAccessible(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    /**
     * 反射机制获取类所有成员变量名和参数类型
     */
    public void getClassAttr(Class classType) {
        KEY_IDS = new ArrayList<>();
        KEY_TYPES = new ArrayList<>();
        KEY_PARAMETER_TYPES = new ArrayList<>();
        KEY_METHODS = new ArrayList<>();
        try {
            // Class classType = Class.forName(classname);
            //   System.out.println("进入： ");
            Field[] fields = classType.getDeclaredFields();
            for (Field field : fields) {
                // 对于每个属性，获取属性名
                String varName = field.getName();
                //提出序列化的隐藏变量名
                if (!varName.equals("$change") && !varName.equals("CREATOR") && !varName.equals("serialVersionUID")) {
                    try {
                        boolean access = field.isAccessible();
                        if (!access) field.setAccessible(true);
                        //从obj中获取field变量
                        String str = field.getGenericType().toString();
                        String[] strs = str.split("\\.");
                        str = strs[strs.length - 1];
                             System.out.println("变量： " + varName +"  数据类型："+str);
                        KEY_IDS.add(varName);
                        KEY_TYPES.add(str);
                        KEY_PARAMETER_TYPES.add(field.getType());
                        if (!access) field.setAccessible(false);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
            Method[] methods = classType.getMethods();
            for (Method method : methods) {
                // 对于每个属性，获取属性方法
                try {
                    boolean access = method.isAccessible();
                    if (!access) method.setAccessible(true);
                    String methodName = method.getName();
                    //过滤含有set和get的方法
                    if (methodName.contains("set") || methodName.contains("get") && !methodName.contains("getClass") && !methodName.contains("getString")) {
                        //    Log.i("SQLite","方法名："+methodName);
                        KEY_METHODS.add(methodName);
                    }
                    if (!access) method.setAccessible(false);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    //打开数据库
    public void openDataBase() {
        Log.i("SQLite", "创建对象");
        mDbOpenHelper = new DBOpenHelper(mContext, DB_NAME, null, DB_VERSION);
        try {
            Log.i("SQLite", "获取可写数据库");
            mDatabase = mDbOpenHelper.getWritableDatabase();//获取可写数据库
        } catch (SQLException e) {
            Log.i("SQLite", "获取只读数据库");
            mDatabase = mDbOpenHelper.getReadableDatabase();//获取只读数据库
        }
        Log.i("SQLite", "数据库已获取");
    }

    //关闭数据库
    public void closeDataBase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }

    //插入一条数据
    public long insertData(Object obj) {
        getObjValue(obj);
        ContentValues values=getContentValues();
        return mDatabase.insert(TABLE_NAME, null, values);
    }

    //删除一条数据
    public long deleteData(String id,Object obj) {
        Log.i("SQLite", "删除一条数据");
        for (int i = 0; i < KEY_IDS.size(); i++) {
            String str_id=KEY_IDS.get(i);
            if(str_id.equals(id)){
                switch (KEY_TYPES.get(i)) {
                    case "String":
                        return mDatabase.delete(TABLE_NAME, str_id + "='" + String.valueOf(obj)+"'", null);
                    case "int":
                        return mDatabase.delete(TABLE_NAME, str_id + "=" + Integer.parseInt(obj==null?"":obj.toString()), null);
                    case "float":
                        return mDatabase.delete(TABLE_NAME, str_id + "=" + Float.parseFloat(obj==null?"":obj.toString()), null);
                    case "double":
                        return mDatabase.delete(TABLE_NAME, str_id + "=" + Double.parseDouble(obj==null?"":obj.toString()), null);
                    case "long":
                        return mDatabase.delete(TABLE_NAME, str_id + "=" + Long.parseLong(obj==null?"":obj.toString()), null);
                    default:
                }
            }
        }
        return -1;
    }

    //删除所有数据
    public long deleteAllData() {
        Log.i("SQLite", "删除所有数据");
        return mDatabase.delete(TABLE_NAME, null, null);
    }

    //更新一条数据
    public long updateData(String id, Object obj) {
        Log.i("SQLite", "更新一条数据" );
        getObjValue(obj);
        ContentValues values=getContentValues();
        for (int i = 0; i < KEY_IDS.size(); i++) {
            String str_id=KEY_IDS.get(i);
            if(str_id.equals(id)){
                switch (KEY_TYPES.get(i)) {
                    case "String":
                        return mDatabase.update(TABLE_NAME, values, id + "='" + values.get(id)+"'", null);
                    default:return mDatabase.update(TABLE_NAME, values, id + "=" + values.get(id), null);
                }
            }
        }
        return -1;
    }

    //查询一条数据
    public List<T> queryData(String id,Object obj) {
        String[] columns = KEY_IDS.toArray(new String[KEY_IDS.size()]);
        Cursor results=null;
        for (int i = 0; i < KEY_IDS.size(); i++) {
            String str_id=KEY_IDS.get(i);
            if(str_id.equals(id)){
                switch (KEY_TYPES.get(i)) {
                    case "String":
                        results= mDatabase.query(TABLE_NAME,columns,
                                str_id + "= '" + String.valueOf(obj)+"'", null, null, null, null);
                        break;
                    case "int":
                        results= mDatabase.query(TABLE_NAME,columns,
                                str_id + "=" + Integer.parseInt(obj==null?"":obj.toString()), null, null, null, null);
                        break;
                    case "float":
                        results= mDatabase.query(TABLE_NAME,columns,
                                str_id + "=" + Float.parseFloat(obj==null?"":obj.toString()), null, null, null, null);
                        break;
                    case "double":
                        results= mDatabase.query(TABLE_NAME,columns,
                                str_id + "=" + Double.parseDouble(obj==null?"":obj.toString()), null, null, null, null);
                        break;
                    case "long":
                        results= mDatabase.query(TABLE_NAME,columns,
                                str_id + "=" + Long.parseLong(obj==null?"":obj.toString()), null, null, null, null);
                        break;
                    default:
                }
                break;
            }
        }
        return convertToClass(results);
    }

    //查询所有数据
    public List<T> queryDataList() {
        String[] columns = KEY_IDS.toArray(new String[KEY_IDS.size()]);
        Log.i("SQLite", "查询所有数据");
        Cursor results = mDatabase.query(TABLE_NAME, columns,
                null, null, null, null, null);
        return convertToClass(results);
    }

    /**
     * 将返回结果集封装成指定输出类的对象
     * @param cursor
     * @return
     */
    private List<T> convertToClass(Cursor cursor) {
        int resultCounts = cursor.getCount();
        Log.i("SQLite", "查询结果总个数：" + String.valueOf(resultCounts));
        if (resultCounts == 0 || !cursor.moveToFirst()) {
            return null;
        }
        List<T> mDataList = new ArrayList<>();
        for (int i = 0; i < resultCounts; i++) {
            Object obj = null;
            try {//得到对象
                obj = T.newInstance();
                //获取到方法对象,假设方法的参数是一个int,method名为setAge
                if (obj != null) {
                    for (int j = 0; j < KEY_IDS.size(); j++) {
                     //   Log.i("SQLite", "方法名：" + KEY_METHODS.get(KEY_IDS.size() + j) + "  参数类型：" + KEY_PARAMETER_TYPES.get(j));
                        Method smethod = T.getMethod(KEY_METHODS.get(KEY_IDS.size() + j), new Class[]{KEY_PARAMETER_TYPES.get(j)});
                        //获得参数Object
                        Object[] arguments = null;
                        switch (KEY_TYPES.get(j)) {
                            case "String":
                                arguments = new Object[]{cursor.getString(cursor.getColumnIndex(KEY_IDS.get(j)))};
                                break;
                            case "int":
                                arguments = new Object[]{cursor.getInt(cursor.getColumnIndex(KEY_IDS.get(j)))};
                                break;
                            case "float":
                                arguments = new Object[]{cursor.getFloat(cursor.getColumnIndex(KEY_IDS.get(j)))};
                                break;
                            case "double":
                                arguments = new Object[]{cursor.getDouble(cursor.getColumnIndex(KEY_IDS.get(j)))};
                                break;
                            case "long":
                                arguments = new Object[]{cursor.getLong(cursor.getColumnIndex(KEY_IDS.get(j)))};
                                break;
                            default:
                                arguments = new Object[]{cursor.getBlob(cursor.getColumnIndex(KEY_IDS.get(j)))};
                        }
                     //   Log.i("SQLite", "数据值：" + cursor.getString(cursor.getColumnIndex(KEY_IDS.get(j))));
                        //执行方法
                        smethod.invoke(obj, arguments);
                    }
                    mDataList.add((T)obj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            cursor.moveToNext();
        }
        return mDataList;
    }

    /**
     * 数据表打开帮助类
     */
    private class DBOpenHelper extends SQLiteOpenHelper {

        public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i("SQLite", "数据库建表");
            db.execSQL(concatSql());
            Log.i("SQLite", "建表成功");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i("SQLite", "数据库版本更新");
            final String sqlStr = "DROP TABLE IF EXISTS " + TABLE_NAME;
            db.execSQL(sqlStr);
            onCreate(db);
        }
    }

    /**
     * 拼接SQL字符串函数
     * @return
     */
    private String concatSql() {
        Log.i("SQLite", "拼接SQL语句");
        StringBuilder sqlStr = new StringBuilder("create table if not exists ").append(TABLE_NAME).append(" (");
        for (int i = 0; i < KEY_IDS.size(); i++) {
            sqlStr.append(KEY_IDS.get(i));
            switch (KEY_TYPES.get(i)) {
                case "String":
                    sqlStr.append(" text");
                    break;
                case "int":
                    sqlStr.append(" integer");
                    break;
                case "float":
                    sqlStr.append(" real");
                    break;
                case "double":
                    sqlStr.append(" real");
                    break;
                case "long":
                    sqlStr.append(" real");
                    break;
                default:
                    sqlStr.append(" blob");
            }
            if (i == 0)
                sqlStr.append(",");//sqlStr.append(" primary key,");//sqlStr.append("primary key autoincrement, ");
            else if (i == KEY_IDS.size() - 1) sqlStr.append(");");
            else sqlStr.append(",");
        }

        Log.i("SQLite", "SQL语句：" + sqlStr.toString());
        return sqlStr.toString();
    }

    /**
     * ContentValues数据强制类型转换
     * @return
     */
    private ContentValues getContentValues(){
        ContentValues values = new ContentValues();
        for (int i = 0; i < KEY_IDS.size(); i++) {
            switch (KEY_TYPES.get(i)) {
                case "String":
                    values.put(KEY_IDS.get(i), String.valueOf(KEY_VALUES.get(i)));
                    System.out.println("插入数据："+String.valueOf(KEY_VALUES.get(i)));
                    break;
                case "int":
                    values.put(KEY_IDS.get(i), Integer.parseInt(KEY_VALUES.get(i).toString()));
                    break;
                case "float":
                    values.put(KEY_IDS.get(i), Float.parseFloat(KEY_VALUES.get(i).toString()));
                    break;
                case "double":
                    values.put(KEY_IDS.get(i), Double.parseDouble(KEY_VALUES.get(i).toString()));
                    break;
                case "long":
                    values.put(KEY_IDS.get(i), Long.parseLong(KEY_VALUES.get(i).toString()));
                    break;
            }
        }
        return values;
    }

    public static String getDbName() {
        return DB_NAME;
    }

    public static void setDbName(String dbName) {
        DB_NAME = dbName;
    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static void setTableName(String tableName) {
        TABLE_NAME = tableName;
    }

    public static int getDbVersion() {
        return DB_VERSION;
    }

    public static void setDbVersion(int dbVersion) {
        DB_VERSION = dbVersion;
    }
}