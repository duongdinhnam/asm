package namddph34374.fpoly.du_an_mau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import namddph34374.fpoly.du_an_mau.Dao.NhanVienDAO;

public class dangnhap extends AppCompatActivity {
    EditText edname, edpass;
    Button btndangnhap;
    TextView  txtsignup;
    CheckBox checkBox;

    NhanVienDAO nhanVienDAO = new NhanVienDAO(this);
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


        sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);

        String savedUsername = sharedPreferences.getString("Mã nv", "");
        String savedPassword = sharedPreferences.getString("Mật khẩu", "");
        boolean savePasswordChecked = sharedPreferences.getBoolean("Lưu mật khẩu", false);

        edname.setText(savedUsername);
        edpass.setText(savedPassword);
        checkBox.setChecked(savePasswordChecked);

        btndangnhap.setOnClickListener(v -> {
            String username = edname.getText().toString();
            String password = edpass.getText().toString();
            boolean loggedIn = nhanVienDAO.login(username, password);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Mã nv", loggedIn ? username : "");
            editor.putString("Mật khẩu", loggedIn && checkBox.isChecked() ? password : "");
            editor.putBoolean("Lưu mật khẩu", loggedIn && checkBox.isChecked());
            editor.apply();

            if (loggedIn) {
                Intent intent = new Intent(dangnhap.this, Manchinh.class);
                intent.putExtra("fragmentToLoad", "Quản lý loại sách");
                startActivity(intent);
            } else {
                Toast.makeText(dangnhap.this, "Tài khoản hoặc mật khẩu sai!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}