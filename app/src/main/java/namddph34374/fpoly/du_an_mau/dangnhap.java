package namddph34374.fpoly.du_an_mau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import namddph34374.fpoly.du_an_mau.Dao.AdminDAO;
import namddph34374.fpoly.du_an_mau.Dao.NhanVienDAO;
import namddph34374.fpoly.du_an_mau.Fragment.Admin;
import namddph34374.fpoly.du_an_mau.Fragment.QL_phieumuon;

public class dangnhap extends AppCompatActivity {
    EditText edname, edpass;
    Button btndangnhap;
    TextView  txtsignup;
    CheckBox checkBox;
    private AdminDAO adminDAO;
    private NhanVienDAO nhanVienDAO;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        edname = findViewById(R.id.edtendangnhap_dn);
        edpass = findViewById(R.id.edmatkhau_dn);
        checkBox = findViewById(R.id.chkluutt);
        btndangnhap = findViewById(R.id.btndangnhap_dn);
        txtsignup = findViewById(R.id.tvsignup);
        adminDAO = new AdminDAO(this);
        nhanVienDAO = new NhanVienDAO(this);

        sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        String savedUsername = sharedPreferences.getString("loggedInUser", "");
        String savedPassword = sharedPreferences.getString("loggedInPass", "");

        if (!savedUsername.isEmpty() && !savedPassword.isEmpty()) {
            // Đã có username và mật khẩu đã lưu trước đó
            // Gán username và mật khẩu vào EditText hoặc tự động đăng nhập
            edname.setText(savedUsername);
            edpass.setText(savedPassword);

            // Cho checkbox Nhớ mật khẩu có dấu tích v
            checkBox.setChecked(true);
        }
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edname.getText().toString();
                String pass = edpass.getText().toString();
                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(dangnhap.this, "Không được để trống thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (adminDAO.checkUser(user, pass)) {
                    // Lưu tên đăng nhập vào SharedPreferences khi đăng nhập thành công
                    if (checkBox.isChecked()){
                        SharedPreferences sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("loggedInUser", user); // Lưu tên đăng nhập
                        editor.putString("loggedInPass", pass); // Lưu tên đăng nhập
                        editor.apply();
                    }
                    else{
                        sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("loggedInUser");
                        editor.remove("loggedInPass");
                        editor.apply();
                    }
                    Toast.makeText(dangnhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(dangnhap.this, Manchinh.class);
                    startActivity(intent);
                    finish();
                } else if (nhanVienDAO.checkNv(user,pass)) {
                    if (checkBox.isChecked()){
                        SharedPreferences sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("loggedInUser", user); // Lưu tên đăng nhập
                        editor.putString("loggedInPass", pass); // Lưu tên đăng nhập
                        editor.apply();
                    }
                    else{
                        SharedPreferences sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("loggedInUser");
                        editor.remove("loggedInPass");
                        editor.apply();
                    }
                    Toast.makeText(dangnhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(dangnhap.this, Manchinh.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(dangnhap.this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}