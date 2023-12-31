package namddph34374.fpoly.du_an_mau.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import namddph34374.fpoly.du_an_mau.Dbheper.Dbheper;
import namddph34374.fpoly.du_an_mau.LopModel.nhanVien;

public class NhanVienDAO {
    private SQLiteDatabase database;
    private Dbheper dbheper;
    private Context context;
    private SharedPreferences sharedPreferences;
    public NhanVienDAO(Context context) {
        this.context = context;
        dbheper = new Dbheper(context);
        sharedPreferences = context.getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
    }
    public long addNhanVien(nhanVien nv) {
        database = dbheper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TENLLOGIN", nv.getTenLogin());
        values.put("HOTEN", nv.getHoTen());
        values.put("MATKHAU", nv.getMatkhau());
        return database.insert("nv", null, values);
    }

    public boolean checkNv(String username, String password) { // kiểm tra tài khoản có trong bảng thuthu không
        database = dbheper.getReadableDatabase();
        String[] columns = {"ID"};
        String selection = "TENLOGIN" + "=? and " + "MATKHAU" + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = database.query("nv", columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        database.close();
        return count > 0;
    }
    public boolean doiMKNv(String oldPassword, String newPassword) {
        // Thực hiện kiểm tra mật khẩu cũ và username cũ ở đây
        String username = sharedPreferences.getString("loggedInUser", "");

        if (checkNv(username, oldPassword)) {
            // Mật khẩu cũ đúng
            database = dbheper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("MATKHAU", newPassword); // Cập nhật mật khẩu mới

            // Thực hiện cập nhật mật khẩu mới cho người dùng có tên đăng nhập tương ứng
            database.update("nv", values, "TENLOGIN = ?", new String[]{username});
            database.close();
            return true; // Trả về true nếu đổi mật khẩu thành công
        } else {
            // Mật khẩu cũ không đúng
            return false;
        }
    }
}
