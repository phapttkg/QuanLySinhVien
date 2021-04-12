package ps08944.phaptt.ps08944_asignment.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    static final String dbName = "QuanLy";
    static final int versionDB = 1;

    public DbHelper(Context context) {
        super(context, dbName, null, versionDB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlLop = "create table lop(" +
                "maLop TEXT PRIMARY KEY," +
                "tenLop TEXT NOT NULL)";
        db.execSQL(sqlLop);



        String sqlSV = "create table sinhvien(" +
                "maSV TEXT PRIMARY KEY," +
                "tenSV TEXT NOT NULL," +
                "maLop TEXT NOT NULL)";
        db.execSQL(sqlSV);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlLop = "drop table if exists lop";
        db.execSQL(sqlLop);
        onCreate(db);
        String sqlSV = "drop table if exists sinhvien";
        db.execSQL(sqlSV);
        onCreate(db);
    }
}
