package com.spend.wisely.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Sunil on 12/1/2015.
 */
public class Appsdb extends SQLiteOpenHelper {

    // DataBase Version
    public static final int DATABASE_VERSION = 21;
    // DataBase name
    public static final String DATABASE_NAME = "app.db";

    // Table name
    public static final String APP_TABLE_TASKS = "app_list";

    public static final String APP_ID = "_id";
    public static final String APP_NAME = "name";
    public static final String APP_TIME = "time";

    private static Appsdb instance;


    // Table name
    public static final String WHITE_APP_TABLE_TASKS = "white_app_list";
    public static final String WHITE_APP_ID = "_id";
    public static final String PACKAGE_NAME = "package_name";


    public Appsdb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Singleton implement
    public static Appsdb getInstance(Context context) {
        if (instance == null)
            instance = new Appsdb(context);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_APP_TABLE = "CREATE TABLE " + APP_TABLE_TASKS + "(" + APP_ID
                + " INTEGER PRIMARY KEY," + APP_NAME + " TEXT,"
                + APP_TIME + " TEXT"
                + ")";

        db.execSQL(CREATE_APP_TABLE);


        String CREATE_WHITE_LIST_TABLE = "CREATE TABLE " + WHITE_APP_TABLE_TASKS + "(" + WHITE_APP_ID
                + " INTEGER PRIMARY KEY," + PACKAGE_NAME + " TEXT NOT NULL UNIQUE,"
                + APP_TIME + " TEXT"
                + ")";

        db.execSQL(CREATE_WHITE_LIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + APP_TABLE_TASKS);

        db.execSQL("DROP TABLE IF EXISTS " + WHITE_APP_TABLE_TASKS);
        // Create tables again
        onCreate(db);
    }


    public long add_app(String packageName, String time) {
        long rowID;
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(APP_NAME, packageName);
        values.put(APP_TIME, time);
        rowID = database.insert(APP_TABLE_TASKS, null, values);
        database.close();
        return rowID;
    }


    // Get all uses AppsList
    public ArrayList<Apps> getAllList() {
        ArrayList<Apps> taskList = new ArrayList<Apps>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + APP_TABLE_TASKS;

        // String selectQuery = "SELECT  * FROM  app_list where name=" + "'"+packge_name+"'";

        System.out.println("===========Task Table======" + selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToLast()) {
            do {
                Apps task = new Apps();

                // task.setId(Integer.parseInt(cursor.getString(0)));
                task.setPackageName(cursor.getString(1));
                task.setUseTime(cursor.getString(2));

                taskList.add(task);
            } while (cursor.moveToPrevious());
        }

        db.close();
        return taskList;
    }


    // Get all uses AppsList
    public ArrayList<Apps> getAllUsesAppTasks(String packge_name) {
        ArrayList<Apps> taskList = new ArrayList<Apps>();
        // Select All Query
        // String selectQuery = "SELECT  * FROM " + APP_TABLE_TASKS;

        String selectQuery = "SELECT  * FROM  app_list where name=" + "'" + packge_name + "'";

        System.out.println("===========Task Table======" + selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToLast()) {
            do {
                Apps task = new Apps();

                // task.setId(Integer.parseInt(cursor.getString(0)));
                task.setPackageName(cursor.getString(1));
                task.setUseTime(cursor.getString(2));

                taskList.add(task);
            } while (cursor.moveToPrevious());
        }

        db.close();
        return taskList;
    }


    public void updateAppTimer(String package_name, String time) {
        SQLiteDatabase database = this.getWritableDatabase();
        String updateQuery = "Update app_list set time = '" + time + "' where name=" + "'" + package_name + "'";

        System.out.println("============status==========" + updateQuery);
        Log.d("query", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }


    // add Install app
    public long Install_app(String packageName) {
        long rowID;

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PACKAGE_NAME, packageName);

        rowID = database.insert(WHITE_APP_TABLE_TASKS, null, values);
        database.close();
        System.out.println("===return==rowId============" + rowID);
        return rowID;
    }

    // retrive Install app

    // Get all uses AppsList
    public ArrayList<Apps> getInstallApp() {
        ArrayList<Apps> taskList = new ArrayList<Apps>();
        // Select All Query
        // String selectQuery = "SELECT  * FROM " + APP_TABLE_TASKS;

        String selectQuery = "SELECT  * FROM  white_app_list";

        System.out.println("=====install======Task Table======" + selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToLast()) {
            do {
                Apps task = new Apps();

                task.setId(cursor.getString(0));
                task.setPackageName(cursor.getString(1));
                taskList.add(task);
            } while (cursor.moveToPrevious());
        }

        db.close();
        return taskList;
    }

    // delete Retrive App
    public boolean InstallDelete(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(WHITE_APP_TABLE_TASKS, WHITE_APP_ID + "=" + id, null) > 0;
    }
}
