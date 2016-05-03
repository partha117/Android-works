package com.example.partha.emergency;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Partha on 4/20/2016.
 */
public class DBOpenHelper extends SQLiteOpenHelper {



    public static final String DB_NAME = "App_database";
    public static String DB_PATH;
    private SQLiteDatabase database;
    private Context context;

    public static final String Address_TABLE = "Address";

    public static final String ID_FIELD = "ID";
    public static final String NAME_FIELD = "NAME";
    public static final String LOCATION_FIELD = "LOCATION";
    public static final String CONTACT_FIELD = "CONTACT";
    public static final String AREA_FIELD = "AREA";
    public static final String ORGANIZATION_TYPE_FIELD = "ORGANIZATION_TYPE";

    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;

        // database path /data/data/pkg-name/databases/
        String packageName = context.getPackageName();
        DB_PATH = "/data/data/" + packageName + "/databases/";
        this.database = openDatabase();

    }

    public SQLiteDatabase getDatabase() {
        return this.database;
    }

    public SQLiteDatabase openDatabase() {
        String path = DB_PATH + DB_NAME;
        if (database == null) {
            createDatabase();
            database = SQLiteDatabase.openDatabase(path, null,
                    SQLiteDatabase.OPEN_READWRITE);
        }
        return database;
    }

    private void createDatabase() {
        boolean dbExists = checkDB();
        if (!dbExists) {
            this.getReadableDatabase();
            Log.e(getClass().getName(),
                    "Database doesn't exist. Copying database from assets...");
            copyDatabase();
        } else {
            Log.e(getClass().getName(), "Database already exists");
        }
    }

    private void copyDatabase() {
        try {
            InputStream dbInputStream = null;
            try {
                dbInputStream = context.getAssets().open(DB_NAME);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String path = DB_PATH + DB_NAME;
            OutputStream dbOutputStream = new FileOutputStream(path);
            byte[] buffer = new byte[4096];
            int readCount = 0;
            while ((readCount = dbInputStream.read(buffer)) > 0) {
                dbOutputStream.write(buffer, 0, readCount);
            }

            dbInputStream.close();
            dbOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean checkDB() {
        String path = DB_PATH + DB_NAME;
        File file = new File(path);
        if (file.exists()) {
            Log.e(getClass().getName(), "Database already exists");
            return true;
        }
        Log.e(getClass().getName(), "Database does not exists");
        return false;
    }

    public synchronized void close() {
        if (this.database != null) {
            this.database.close();
        }
    }

    // query
    public ArrayList<AddressBook> getAllAdress(String areaCode,String  organizationType) {
        ArrayList<AddressBook> allEmployees = new ArrayList<AddressBook>();

        String[] slection_args={areaCode,organizationType};
        // SELECT * FROM EMPLOYEE;

        Cursor cursor = this.database.query(Address_TABLE, null, "AREA=? AND ORGANIZATION_TYPE=?",slection_args,
                null, null, NAME_FIELD+" ASC");

        // Cursor cursor = db.rawQuery("SELECT * FROM EMPLOYEE", null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                //
                int id = cursor.getInt(cursor.getColumnIndex(ID_FIELD));
                String name = cursor.getString(cursor
                        .getColumnIndex(NAME_FIELD));
                String location = cursor.getString(cursor
                        .getColumnIndex(LOCATION_FIELD));
                String contact = cursor.getString(cursor
                        .getColumnIndex(CONTACT_FIELD));

                AddressBook addressBook = new AddressBook( name, location, contact);
                allEmployees.add(addressBook);
                cursor.moveToNext();
            }
        }
        cursor.close();

        return allEmployees;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}
