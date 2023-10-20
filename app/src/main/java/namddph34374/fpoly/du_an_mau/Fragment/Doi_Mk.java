package namddph34374.fpoly.du_an_mau.Fragment;

import android.content.ContentValues;
import android.content.DialogInterface;
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

import namddph34374.fpoly.du_an_mau.Dao.AdminDAO;
import namddph34374.fpoly.du_an_mau.Dao.NhanVienDAO;
import namddph34374.fpoly.du_an_mau.Dbheper.Dbheper;
import namddph34374.fpoly.du_an_mau.LopModel.nhanVien;
import namddph34374.fpoly.du_an_mau.R;

public class Doi_Mk extends Fragment {
    private EditText edmkcu, edmkmoi, edmk_again;
    private Button btnluumk;
    private nhanVien nv;
    private AdminDAO adminDAO;
    private NhanVienDAO nhanVienDAO;
    private SQLiteDatabase database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.doi_mk, container, false);

        edmkcu = view.findViewById(R.id.passwordTextInputLayout_text_cu);
        edmkmoi = view.findViewById(R.id.passwordTextInputLayout_text_moi);
        edmk_again = view.findViewById(R.id.passwordTextInputLayout_text_nhaplai);
        btnluumk = view.findViewById(R.id.btnluu_doimk);
        adminDAO = new AdminDAO(getContext());
        nhanVienDAO = new NhanVienDAO(getContext());

        btnluumk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogDoiMK();
            }
        });



        return view;
    }
    private void dialogDoiMK() {
        String oldmk = edmkcu.getText().toString();
        String newmk = edmkmoi.getText().toString();
        String confirmmk = edmk_again.getText().toString();
        if (oldmk.isEmpty() || newmk.isEmpty() || confirmmk.isEmpty()) {
            Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
        } else {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());
            builder.setIcon(R.drawable.baseline_error_24);
            builder.setCancelable(false);
            builder.setTitle("Đổi mật khẩu");
            builder.setMessage("Bạn có chắc chắn muốn đổi mật khẩu không ?");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (newmk.equals(confirmmk)) {
                        if (adminDAO.checkPasswordAndChange(oldmk, oldmk)) {
                            Toast.makeText(getContext(), "Đổi thành công", Toast.LENGTH_SHORT).show();
                            edmkmoi.setText("");
                            edmkcu.setText("");
                            edmk_again.setText("");

                            edmkmoi.setFocusable(false);
                            edmkcu.setFocusable(false);
                            edmk_again.setFocusable(false);

                        } else if (nhanVienDAO.doiMKNv(oldmk, newmk)) {
                            Toast.makeText(getContext(), "Đổi thành công", Toast.LENGTH_SHORT).show();
                            edmkmoi.setText("");
                            edmkcu.setText("");
                            edmk_again.setText("");

                            edmkmoi.setFocusable(false);
                            edmkcu.setFocusable(false);
                            edmk_again.setFocusable(false);
                        } else {
                            Toast.makeText(getContext(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Nhập lại mật khẩu sai", Toast.LENGTH_SHORT).show();
                    }

                }
            });
            builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // bắt sự kiện nhấn nút No
                    dialog.dismiss();
                }
            });
            builder.show();
        }

    }
}
