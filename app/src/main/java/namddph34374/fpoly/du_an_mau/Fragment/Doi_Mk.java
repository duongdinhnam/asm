package namddph34374.fpoly.du_an_mau.Fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import namddph34374.fpoly.du_an_mau.Dbheper.Dbheper;
import namddph34374.fpoly.du_an_mau.R;

public class Doi_Mk extends Fragment {
    private EditText edmkcu, edmkmoi, edmk_again;
    private Button btnluumk;
    private SQLiteDatabase database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.doi_mk, container, false);

        edmkcu = view.findViewById(R.id.passwordTextInputLayout_text_cu);
        edmkmoi = view.findViewById(R.id.passwordTextInputLayout_text_moi);
        edmk_again = view.findViewById(R.id.passwordTextInputLayout_text_nhaplai);
        btnluumk = view.findViewById(R.id.btnluu_doimk);
        String oldmk = edmkcu.getText().toString();
        String newmk = edmkmoi.getText().toString();
        String confirmmk = edmk_again.getText().toString();

        Dbheper dbhelper = new Dbheper(getActivity());
        database = dbhelper.getWritableDatabase();

        btnluumk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldmk = edmkcu.getText().toString();
                String newmk = edmkmoi.getText().toString();
                String confirmmk = edmk_again.getText().toString();

                if (TextUtils.isEmpty(oldmk) || TextUtils.isEmpty(newmk) || TextUtils.isEmpty(confirmmk)) {
                    Toast.makeText(getActivity(), "Không để trống", Toast.LENGTH_SHORT).show();
                } else if (!newmk.equals(confirmmk)) {
                    Toast.makeText(getActivity(), "Mật khẩu mới và mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show();
                } else {
                    // Kiểm tra mật khẩu cũ có đúng không
                    if (checkOldPassword(oldmk)) {
                        // Mật khẩu cũ đúng, thay đổi mật khẩu mới
                        changePassword(newmk);
                        Toast.makeText(getActivity(), "Đã thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        // Đóng Fragment sau khi đổi mật khẩu thành công (hoặc điều chỉnh theo nhu cầu của bạn)
                        // getFragmentManager().beginTransaction().remove(Doimatkhau.this).commit();
                    } else {
                        Toast.makeText(getActivity(), "Mật khẩu cũ không chính xác", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });




        return view;
    }
    private boolean checkOldPassword(String oldmk) {
        // Tạo truy vấn SQL để lấy mật khẩu dựa trên tên người dùng (HOTEN)
        String query = "SELECT MATKHAU FROM nv WHERE HOTEN = ?";
        Cursor cursor = database.rawQuery(query, new String[]{oldmk});

        if (cursor.moveToFirst()) {
            String storedPassword = cursor.getString(0);
            cursor.close();
            return oldmk.equals(storedPassword);
        }
        cursor.close();
        return false;
    }


    private void changePassword(String newPassword) {
        // Cập nhật mật khẩu mới vào cơ sở dữ liệu
        ContentValues values = new ContentValues();
        values.put("MATKHAU", newPassword);
        String whereClause = "HOTEN = ?";
        String[] whereArgs = new String[]{"nam"}; // Thay "nam" bằng tên tài khoản bạn muốn thay đổi mật khẩu
        database.update("nv", values, whereClause, whereArgs);
    }

}
