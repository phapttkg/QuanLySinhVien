package ps08944.phaptt.ps08944_asignment.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ps08944.phaptt.ps08944_asignment.model.Lop;

public class LopDAO  {
    private SQLiteDatabase db;

    public LopDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public LopDAO() {
    }

    public long insert(Lop l){
        ContentValues values = new ContentValues();
        values.put("maLop",l.maLop);
        values.put("tenLop",l.tenLop);
        return db.insert("lop",null,values);
    }
    public int update(Lop l){
        ContentValues values = new ContentValues();
        values.put("tenLop",l.tenLop);
        return db.update("lop",values,"maLop=?", new String[]{l.maLop});
    }
    public int delete(String maLop){
        return db.delete("lop","maLop=?", new String[]{maLop});
    }
    public List<Lop> getLop(String sqlLop, String...selectionArgs){
        List<Lop> list = new ArrayList<Lop>();
        Cursor c = db.rawQuery(sqlLop,selectionArgs);
        while (c.moveToNext()){
                Lop l = new Lop();
                l.maLop = c.getString(c.getColumnIndex("maLop"));
                l.tenLop = c.getString(c.getColumnIndex("tenLop"));

                list.add(l);

        }
        return list;
    }
    public List<Lop> getLopAll() {
        String sql = "SELECT * FROM lop";

        return getLop(sql);
    }

    //get sp theo ma
    public Lop getLopMaLop(String maLop) {
        String sqlLop = "SELECT * FROM lop WHERE maLop=?";

        List<Lop> list = getLop(sqlLop, maLop);

        return list.get(0);
    }

    //get   theo ten
    public Lop getLopTenLop(String tenLop) {
        String sqlLop = "SELECT * FROM lop WHERE tenLop=?";

        List<Lop> list = getLop(sqlLop, tenLop);

        return (Lop) list;
    }
}
