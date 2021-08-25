package com.example.sqlitelearn;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.sqlitelearn.StudentSchema.*;

import java.util.Date;
import java.util.UUID;

public class StudentCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public StudentCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Student getStudent() {
        String uuidString = getString(getColumnIndex(StudentTable.Cols.UUID));
        String name = getString(getColumnIndex(StudentTable.Cols.NAME));
        long date = getLong(getColumnIndex(StudentTable.Cols.BIRTH_DATE));
        int gender = getInt(getColumnIndex(StudentTable.Cols.GENDER));
        Student student = new Student();
        student.setUUID(UUID.fromString(uuidString));
        student.setName(name);
        student.setBirthDate(new Date(date));
        student.setGender(gender != 0);

        return student;
    }

}
