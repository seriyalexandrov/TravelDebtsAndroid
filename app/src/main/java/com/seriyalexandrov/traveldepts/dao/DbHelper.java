package com.seriyalexandrov.traveldepts.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.seriyalexandrov.traveldepts.Constants;
import com.seriyalexandrov.traveldepts.model.Dept;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    public DbHelper(Context context) {
        super(context, "td_depts", null, 1);
        db = getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Queries.CREATE_DEPTS_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addNewDept(Dept dept) {

        ContentValues cv = new ContentValues();
        cv.put("deptor", dept.deptorName);
        cv.put("creditor", dept.creditorName);
        cv.put("summ", dept.summ);
        cv.put("currency", dept.currency);
        cv.put("comment", dept.comment);
        db.insert(Constants.DEPTS_TABLE, null, cv);
    }
}