package com.example.sqlitelearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.example.sqlitelearn.StudentSchema.*;

import java.util.Date;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "MainActivity";

    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建SQLiteDatabase实例
        mDatabase = new StudentBaseHelper(this).getWritableDatabase();

//        Student student = new Student();
//        student.setUUID(UUID.randomUUID());
//        student.setName("zhangsan");
//        student.setBirthDate(new Date());
//        student.setGender(true);
//
//        long id = addStudent(student);
//        if (id == -1) {
//            Log.d(TAG, "add student fail");
//        } else {
//            Log.d(TAG, "add student success and id=" + id);
//        }

//        Cursor cursor = queryStudents(StudentTable.Cols.NAME + "=?", new String[]{"lisi"});
//        StudentCursorWrapper cursorWrapper = new StudentCursorWrapper(cursor);
//        Student student = null;
//        try {
//            if (cursor.getCount() == 0) {
//                Log.d(TAG, "查无此人");
//            } else {
//                cursorWrapper.moveToFirst();
//                student = cursorWrapper.getStudent();
//                Log.d(TAG, student.getUUID().toString());
//            }
//        } finally {
//            cursorWrapper.close();
//        }
//        student.setName("lisi");
//        updateStudent(student);

        deleteStudent(
                StudentTable.Cols.NAME + "=?",
                new String[]{"lisi"}
        );
    }

    private static ContentValues getContentValues(Student student) {
        ContentValues values = new ContentValues();
        values.put(StudentTable.Cols.UUID, student.getUUID().toString());
        values.put(StudentTable.Cols.NAME, student.getName());
        values.put(StudentTable.Cols.BIRTH_DATE, student.getBirthDate().getTime());
        values.put(StudentTable.Cols.GENDER, student.getGender() ? 1 : 0);
        return values;
    }

    public long addStudent(Student student) {
        ContentValues values = getContentValues(student);
        long newRowId = mDatabase.insert(StudentTable.TABLE_NAME, null, values);
        return newRowId;
    }

    private Cursor queryStudents(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                StudentTable.TABLE_NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );
        return cursor;
    }

    private int updateStudent(Student student) {
        String uuidString = student.getUUID().toString();
        ContentValues values = getContentValues(student);
        int count = mDatabase.update(StudentTable.TABLE_NAME, values,
                StudentTable.Cols.UUID + "=?",
                new String[]{uuidString});
        return count;
    }

    private int deleteStudent(String whereClause, String[] whereArgs) {
        return mDatabase.delete(StudentTable.TABLE_NAME, whereClause, whereArgs);
    }
}