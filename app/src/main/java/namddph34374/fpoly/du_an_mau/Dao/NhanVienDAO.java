package namddph34374.fpoly.du_an_mau.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import namddph34374.fpoly.du_an_mau.Dbheper.Dbheper;
import namddph34374.fpoly.du_an_mau.LopProduct.Sach;
import namddph34374.fpoly.du_an_mau.LopProduct.nhanVien;

public class NhanVienDAO {
    private SQLiteDatabase database;
    private Dbheper dbheper;
    public NhanVienDAO(Context context){dbheper = new Dbheper(context);}

//    public ArrayList<nhanVien> getAllSanpham(){
//        ArrayList<nhanVien> list = new ArrayList<>();
//        database = dbheper.getReadableDatabase();
//        Cursor cursor = database.rawQuery("SELECT * FROM  nv", null);
//        while (cursor.moveToNext()){
//            nhanVien nv = new nhanVien(
//                    cursor.getString(1),
//                    cursor.getString(2));
//            nv.setMaNv(cursor.getInt(0));
//            list.add(nv);
//        }
//        return list;
//    }
//
//    public long addnhanVien(nhanVien nv){
//        database = dbheper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("HOTEN", nv.getHoTen());
//        values.put("MATKHAU", nv.getMatkhau());
//        return database.insert("nv", null, values);
//    }
//    public boolean checklogin(String username,String password){
//        SQLiteDatabase database =dbheper.getReadableDatabase();
//        String sql="SELECT * FROM nv WHERE HOTEN =? AND MATKHAU=?";
//        Cursor cs=database.rawQuery(sql,new String[]{
//                username,password
//        });
//        int count =cs.getCount();
//        return (count>0);

    public boolean login(String username, String password) {
        SQLiteDatabase database = dbheper.getReadableDatabase(); // thêm this.dBhelper

        // Chuỗi câu lệnh SQL để truy vấn người dùng dựa trên tên đăng nhập và mật khẩu
        String query = "SELECT * FROM nv WHERE HOTEN = ? AND MATKHAU = ?";

        // Thực thi câu lệnh SQL với tham số truyền vào
        Cursor cursor = database.rawQuery(query, new String[]{username, password});

        // Kiểm tra xem có dữ liệu trả về từ truy vấn không
        boolean loggedIn = cursor.moveToFirst();

        // Đóng con trỏ Cursor sau khi sử dụng
        cursor.close();

        // Trả về kết quả đăng nhập
        return loggedIn;
    }
}
