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



//    @SuppressLint("Range")
//    public List<Top> getTop10() {
//        String sqlTop10 = "SELECT MASPM, COUNT(*) AS soLuong " +
//                "FROM pm " +
//                "GROUP BY MASPM " +
//                "ORDER BY soLuong DESC " +
//                "LIMIT 10";
//
//        List<Top> top10List = new ArrayList<>();
//
//        Cursor cursor = database.rawQuery(sqlTop10, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                int maSach = cursor.getInt(cursor.getColumnIndex("MASPM"));
//                int soLuotMuon = cursor.getInt(cursor.getColumnIndex("soLuong"));
//
//                // Sử dụng mã sách để lấy thông tin chi tiết về sách từ bảng "s" (Sach)
//                Sach sach = getSachByMaSach(maSach);
//
//                if (sach != null) {
//                    Top top = new Top();
//                    top.setTensach(sach.getTens());
//                    top.setSoluong(soLuotMuon);
//                    top10List.add(top);
//                }
//            } while (cursor.moveToNext());
//        }
//
//        cursor.close();
//        return top10List;
//    }
//    private Sach getSachByMaSach(int maSach) {
//        String sql = "SELECT * FROM s WHERE MAS = ?";
//        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(maSach)});
//
//        Sach sach = null;
//
//        if (cursor.moveToFirst()) {
//            String tenSach = cursor.getString(cursor.getColumnIndex("TENS"));
//            int giaSach = cursor.getInt(cursor.getColumnIndex("GIAS"));
//
//            // Tạo đối tượng sách
//            sach = new Sach();
//            sach.setMaS(maSach);
//            sach.setTenS(tenSach);
//            sach.setGiaS(giaSach);
//        }
//
//        cursor.close();
//        return sach;
//    }

//    public thanhVien getThanhVienByMaTV(String matv) {
//        database = this.dbheper.getReadableDatabase();
//        Cursor cursor = null;
//        thanhVien thanhVien = null;
//
//        try {
//            cursor = database.query("tv", null, "MATV=?", new String[]{matv}, null, null, null);
//
//            if (cursor != null && cursor.moveToFirst()) {
//                // Lấy thông tin thành viên từ dữ liệu trả về
//                int maTVIndex = cursor.getColumnIndex("MATV");
//                int hoTenIndex = cursor.getColumnIndex("HOTENTV");
//                int namSinhIndex = cursor.getColumnIndex("NAMSINHTV");
//
//                String maTV = cursor.getString(maTVIndex);
//                String hoTen = cursor.getString(hoTenIndex);
//                String namSinh = cursor.getString(namSinhIndex);
//
//                // Tạo đối tượng thành viên
//                thanhVien = new thanhVien(maTV, hoTen, namSinh);
//            }
//        } finally {
//            if (cursor != null) {
//                cursor.close();
//            }
//        }
//
//        return thanhVien;
//    }


}
