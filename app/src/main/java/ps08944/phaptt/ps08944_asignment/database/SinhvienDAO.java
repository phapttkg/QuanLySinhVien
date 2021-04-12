package ps08944.phaptt.ps08944_asignment.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ps08944.phaptt.ps08944_asignment.model.SinhVien;

public class SinhvienDAO {
    private SQLiteDatabase db;

    public SinhvienDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();

    }

    public SinhvienDAO() {
    }

    public long insert(SinhVien s) {
        ContentValues values = new ContentValues();
        values.put("maSV", s.maSV);
        values.put("tenSV", s.tenSV);
        values.put("maLop", s.maLop);
        return db.insert("sinhvien", null, values);
    }

    public int update(SinhVien s) {
        ContentValues values = new ContentValues();
        values.put("tenSV", s.tenSV);
        values.put("maLop", s.maLop);

        return db.update("sinhvien", values, "maSV=?", new String[]{s.maSV});
    }


    public int delete(String maSV) {
        return db.delete("sinhvien", "maSV=?", new String[]{maSV});
    }

    public List<SinhVien> getSinhVien(String sqlSV, String... selectionArgs) {

        List<SinhVien> list = new ArrayList<SinhVien>();

        Cursor c = db.rawQuery(sqlSV, selectionArgs);

        while (c.moveToNext()) {

            SinhVien s = new SinhVien();
            s.maSV = c.getString(c.getColumnIndex("maSV"));
            s.tenSV = c.getString(c.getColumnIndex("tenSV"));
            s.maLop = c.getString(c.getColumnIndex("maLop"));


            list.add(s);
        }

        return list;
    }

    public List<SinhVien> getSinhVienAll() {
        String sqlSV = "SELECT * FROM sinhvien";

        return getSinhVien(sqlSV);
    }

    //get sp theo ma
    public SinhVien getSinhVienMaSV(String maSV) {
        String sqlSV = "SELECT * FROM sinhvien WHERE maSV=?";

        List<SinhVien> list = getSinhVien(sqlSV, maSV);

        return list.get(0);
    }

    //get   theo ten
    public SinhVien getSinhVienTenSV(String tenSV) {
        String sqlSV = "SELECT * FROM sinhvien WHERE tenSV=?";

        List<SinhVien> list = getSinhVien(sqlSV, tenSV);

        return (SinhVien) list;
    }

    public List<String> getmaLop() {
        String sqlSV = "select maLop from sinhvien";
        List<String> list = new ArrayList<String>();
        Cursor c = db.rawQuery(sqlSV, null);
        while (c.moveToNext()) {
            String maLop = c.getString(c.getColumnIndex("maLop"));
            list.add(maLop);

        }
        return list;
    }

}