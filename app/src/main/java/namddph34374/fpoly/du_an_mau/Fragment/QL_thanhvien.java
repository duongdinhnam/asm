package namddph34374.fpoly.du_an_mau.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import namddph34374.fpoly.du_an_mau.Adapter.ThanhVienAdapter;
import namddph34374.fpoly.du_an_mau.Dao.ThanhVienDAO;
import namddph34374.fpoly.du_an_mau.LopModel.thanhVien;
import namddph34374.fpoly.du_an_mau.R;

public class QL_thanhvien extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton floatAdd;
    private ThanhVienDAO thanhVienDAO;
    private ThanhVienAdapter thanhVienAdapter;
    private ArrayList<thanhVien> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_thanhvien, container, false);

        recyclerView = view.findViewById(R.id.recyclerview_tv);
        floatAdd = view.findViewById(R.id.btnadd_tv);
        thanhVienDAO = new ThanhVienDAO(getContext());
        list = thanhVienDAO.getAllSanpham();
        thanhVienAdapter = new ThanhVienAdapter(getContext(), list, thanhVienDAO);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(thanhVienAdapter);
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                them();
            }
        });
        return view;
    }
    private void them() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_addthnahvien, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();
        // EditText edtmtv = view.findViewById(R.id.ed_matv);
        EditText edtTentv = view.findViewById(R.id.ed_hotentv);
        EditText edtns = view.findViewById(R.id.ed_namsinh);

        Button btnXacnhan = view.findViewById(R.id.btn_save_thanhvien);
        Button btnHuy = view.findViewById(R.id.btn_huy_thanhvien);

        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = edtTentv.getText().toString();
                String namsinh = edtns.getText().toString();
                thanhVien tv = new thanhVien( ten, namsinh);
                if (ten.length() == 0) {
                    Toast.makeText(getContext(), "Vui lòng nhập thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (thanhVienDAO.addThanhVien(tv) > 0) {
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        list = thanhVienDAO.getAllSanpham();
                        thanhVienAdapter = new ThanhVienAdapter(getContext(), (ArrayList<thanhVien>) list, thanhVienDAO);

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(thanhVienAdapter);
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
