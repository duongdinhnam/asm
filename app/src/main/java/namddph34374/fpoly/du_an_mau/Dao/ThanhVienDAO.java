package namddph34374.fpoly.du_an_mau.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import namddph34374.fpoly.du_an_mau.Dbheper.Dbheper;
import namddph34374.fpoly.du_an_mau.LopModel.thanhVien;

public class ThanhVienDAO {
    private SQLiteDatabase database;
    private Dbheper dbheper;
    public ThanhVienDAO(Context context){dbheper = new Dbheper(context);}

    public ArrayList<thanhVien> getAllSanpham(){
        ArrayList<thanhVien> list = new ArrayList<>();
        database = dbheper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM  tv", null);
        while (cursor.moveToNext()){
            thanhVien tv = new thanhVien(
                    cursor.getString(1),
                    cursor.getString(2));
            tv.setMaTv(cursor.getInt(0));
            list.add(tv);
        }
        return list;
    }

    public long addThanhVien(thanhVien tv){
        database = dbheper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("HOTENTV", tv.getHoTentv());
        values.put("NAMSINHTV", tv.getNamSinhTv());

        return database.insert("tv", null, values);
    }
    public boolean updateThanhVien(thanhVien tv){
        database = dbheper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("HOTENTV", tv.getHoTentv());
        values.put("NAMSINHTV", tv.getNamSinhTv());
        long row = database.update("tv", values, "MATV=?", new String[]{
                String.valueOf(tv.getMaTv())});
        return (row > 0);
    }
    public long deleteThanhVien(int matv){
        database = dbheper.getWritableDatabase();
        int result = database.delete("tv", "MATV=?", new String[]{String.valueOf(matv)});
        return result;
    }

}
