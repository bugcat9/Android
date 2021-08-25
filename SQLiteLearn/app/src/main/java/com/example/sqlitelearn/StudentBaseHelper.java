package com.example.sqlitelearn;

import com.example.sqlitelearn.StudentSchema.*;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class StudentBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "studentBase.db";
    private static final String TAG = "StudentBaseHelper";
    /***
     * 创建数据表语句
     */
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + StudentTable.TABLE_NAME + " (" +
                    "_id integer primary key autoincrement," +
                    StudentTable.Cols.UUID + "," +
                    StudentTable.Cols.NAME + "," +
                    StudentTable.Cols.BIRTH_DATE + "," +
                    StudentTable.Cols.GENDER +
                    ")";

    /***
     * 删除数据表语句
     */
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + StudentTable.TABLE_NAME;

    public StudentBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v(TAG, "onCreate: is called");
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v(TAG, "onUpgrade: is called");
        //先删除再创建
        if (oldVersion < newVersion) {
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
    }
}
