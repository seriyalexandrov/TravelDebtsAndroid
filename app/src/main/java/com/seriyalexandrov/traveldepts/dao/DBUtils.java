package com.seriyalexandrov.traveldepts.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.seriyalexandrov.traveldepts.Constants;
import com.seriyalexandrov.traveldepts.model.Dept;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DBUtils {

    public static List<Dept> getDepts(SQLiteDatabase db) {

        List<Dept> depts = new ArrayList<>();

        Cursor c = db.rawQuery(Queries.SELECT_ALL_DEPTS_QUERY, null);
        if (c == null) return Collections.EMPTY_LIST;
        if (c.moveToFirst()) {
            do {
                String id = c.getString(0);
                String deptor = c.getString(1);
                String creditor = c.getString(2);
                String summ = c.getString(3);
                String currency = c.getString(4);
                String comment = c.getString(5);
                depts.add(new Dept(id, deptor, creditor, summ, currency, comment));
            } while (c.moveToNext());
        }
        c.close();
        return depts;
    }

    public static void removeDeptById(SQLiteDatabase db, String id) {
        Log.i(Constants.LOG_TAG, "id = " + id);
        String params[] = {id};
        db.delete(Constants.DEPTS_TABLE, Queries.DELETE_DEPT_BY_ID_CLAUSE, params);
    }
}
