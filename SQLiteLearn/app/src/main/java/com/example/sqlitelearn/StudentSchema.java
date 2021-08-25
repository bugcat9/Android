package com.example.sqlitelearn;

public class StudentSchema {

    public static final class StudentTable {
        /***
         * 数据库表名
         */
        public static final String TABLE_NAME = "Student";

        /***
         *定义数据表字段
         */
        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String BIRTH_DATE = "birth_date";
            public static final String GENDER = "gender";
        }
    }
}
