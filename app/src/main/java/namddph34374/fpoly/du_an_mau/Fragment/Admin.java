package namddph34374.fpoly.du_an_mau.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import namddph34374.fpoly.du_an_mau.Dao.NhanVienDAO;
import namddph34374.fpoly.du_an_mau.LopModel.nhanVien;
import namddph34374.fpoly.du_an_mau.R;

public class Admin extends AppCompatActivity {
    EditText edtUser,edtHoten,edtPass,edtNhapLai;
    Button btnLuu;
    private nhanVien nv;
    private NhanVienDAO nhanVienDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        edtUser = findViewById(R.id.edtUser_admin);
        edtHoten = findViewById(R.id.edtHoten_admin);
        edtPass = findViewById(R.id.edtPass_admin);
        edtNhapLai = findViewById(R.id.edtNhapLai_admin);
        btnLuu = findViewById(R.id.btnLuu_admin);

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString();
                String hoten = edtHoten.getText().toString();
                String pass = edtPass.getText().toString();
                String nhaplai = edtNhapLai.getText().toString();


                if (user.isEmpty()||hoten.isEmpty()||pass.isEmpty()||nhaplai.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!pass.equals(nhaplai)){
                    Toast.makeText(getApplicationContext(), "Nhập lại mật khẩu sai", Toast.LENGTH_SHORT).show();
                    return;
                }

                nhanVien nv = new nhanVien(user,hoten,pass);

                if (nhanVienDAO.addNhanVien(nv) > 0) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    edtHoten.setText("");
                    edtUser.setText("");
                    edtPass.setText("");
                    edtNhapLai.setText("");

                    edtHoten.setFocusable(false);
                    edtUser.setFocusable(false);
                    edtPass.setFocusable(false);
                    edtNhapLai.setFocusable(false);


                }else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}