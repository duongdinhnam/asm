package namddph34374.fpoly.du_an_mau.Dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import namddph34374.fpoly.du_an_mau.Dbheper.Dbheper;
import namddph34374.fpoly.du_an_mau.LopModel.Sach;
import namddph34374.fpoly.du_an_mau.LopModel.Top;
import namddph34374.fpoly.du_an_mau.LopModel.phieuMuon;

public class PhieumuonDAO {
    private SQLiteDatabase database;
    private Dbheper dbheper;
    public PhieumuonDAO(Context context){dbheper = new Dbheper(context);}

    public ArrayList<phieuMuon> getAllPM() {
        ArrayList<phieuMuon> list = new ArrayList<>();
        database = dbheper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM pm", null);

        if (cursor.moveToFirst()) { // Di chuyển con trỏ đến hàng đầu tiên
            do {
                phieuMuon pm = new phieuMuon(
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        cursor.getString(5),
                        cursor.getInt(6)
                );
                pm.setMaPm(cursor.getInt(0)); // Gán mã phiếu mượn

                list.add(pm);
            } while (cursor.moveToNext());
        }

        cursor.close(); // Đóng cursor sau khi sử dụng
        return list;
    }


    public long addphieumuon(phieuMuon m){
        database = dbheper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MANVPM", m.getMaNVpm());
        values.put("MATVPM", m.getMaTVpm());
        values.put("MASPM", m.getMaSpm());
        values.put("TIENTHUE", m.getTienthue());
        values.put("NGAYMUON", m.getNgaymuon());
        values.put("TRASACH", m.getTraSach());
        return database.insert("pm", null, values);
    }
    public boolean updatephieumuon(phieuMuon m){
        database = dbheper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MANVPM", m.getMaNVpm());
        values.put("MATVPM", m.getMaTVpm());
        values.put("MASPM", m.getMaSpm());
        values.put("TIENTHUE", m.getTienthue());
        values.put("NGAYMUON", m.getNgaymuon());
        values.put("TRASACH", m.getTraSach());
        long row = database.update("pm", values, "MAPM=?", new String[]{
                String.valueOf(m.getMaPm())});
        return (row > 0);
    }

    public long deletephiuemuon(int mapm){
        database = dbheper.getWritableDatabase();
        int result = database.delete("pm", "MAPM=?", new String[]{String.valueOf(mapm)});
        return result;
    }
    public ArrayList<Top> getTop10Books() {
        ArrayList<Top> top10List = new ArrayList<>();
        database = dbheper.getReadableDatabase();

        String query = "SELECT s.TENS AS TenSach, COUNT(pm.MASPM) AS SoLuotMuon " +
                "FROM pm " +
                "INNER JOIN s ON pm.MASPM = s.MAS " +
                "GROUP BY pm.MASPM " +
                "ORDER BY SoLuotMuon DESC " +
                "LIMIT 10";

        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String tenSach = cursor.getString(cursor.getColumnIndexOrThrow("TenSach"));
                int soLuotMuon = cursor.getInt(cursor.getColumnIndexOrThrow("SoLuotMuon"));

                Top top = new Top(tenSach, soLuotMuon);
                top10List.add(top);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return top10List;
    }

}
