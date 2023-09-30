package namddph34374.fpoly.du_an_mau.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import namddph34374.fpoly.du_an_mau.Dbheper.Dbheper;
import namddph34374.fpoly.du_an_mau.LopProduct.Sach;
import namddph34374.fpoly.du_an_mau.LopProduct.loaiSach;

public class LoaiSachDAO {
    private Dbheper dBhelper;
    SQLiteDatabase database;

    public LoaiSachDAO(Context context) {
        dBhelper = new Dbheper(context);
    }

    public ArrayList<loaiSach> getALLLS() {
        ArrayList<loaiSach> list = new ArrayList<>();
        SQLiteDatabase database = dBhelper.getReadableDatabase(); // thêm this.dBhelper
        Cursor cursor = database.rawQuery("SELECT * FROM ls", null);

        while (cursor.moveToNext()) {
            loaiSach lsach = new loaiSach(
                    cursor.getString(1));
                    lsach.setMaLS(cursor.getInt(0));
            list.add(lsach);
        }
        cursor.close(); // đóng con trỏ khi hoàn thành công việc
        return list;
    }
    public long addLoaiSach(loaiSach s){
        database = dBhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENLS", s.getTenLS());
        return database.insert("ls", null, values);
    }
    public boolean updateloaiSach(loaiSach s){
        database = dBhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENLS", s.getTenLS());
        long row = database.update("ls", values, "MALS=?", new String[]{
                String.valueOf(s.getMaLS())});
        return (row > 0);
    }
    public long deleteloaiSach(int mals){
        database = dBhelper.getWritableDatabase();
        int result = database.delete("ls", "MALS=?", new String[]{String.valueOf(mals)});
        return result;
    }
}
