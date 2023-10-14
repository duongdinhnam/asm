package namddph34374.fpoly.du_an_mau.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import namddph34374.fpoly.du_an_mau.Adapter.LoaiSachAdapter;
import namddph34374.fpoly.du_an_mau.Dao.LoaiSachDAO;
import namddph34374.fpoly.du_an_mau.LopModel.loaiSach;
import namddph34374.fpoly.du_an_mau.R;

public class QL_loaiSach extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton floatAdd;
    private LoaiSachDAO loaiSachDAO;
    private LoaiSachAdapter sachAdapter;
    private ArrayList<loaiSach> list = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_loaisach, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_ls);
        floatAdd = view.findViewById(R.id.btnadd_ls);

        loaiSachDAO = new LoaiSachDAO(getContext());
        list = loaiSachDAO.getALLLS();
        sachAdapter = new LoaiSachAdapter(getContext(), list, loaiSachDAO);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(sachAdapter);

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogThem();
            }
        });


        return view;
    }
    private void dialogThem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.dialog_add_loaisach, null);
        builder.setView(view);
        builder.setCancelable(false);
        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText edtTenLoai;
        Button btnAdd, btnHuy;

        edtTenLoai = view.findViewById(R.id.edttenloaisach_addls);
        btnAdd = view.findViewById(R.id.btnadd_addls);
        btnHuy = view.findViewById(R.id.btnHuy_addls);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenloai = edtTenLoai.getText().toString();

                if (tenloai.isEmpty()){
                    Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                loaiSach ls = new loaiSach(tenloai);

                if (loaiSachDAO.addLoaiSach(ls) > 0) {
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(loaiSachDAO.getALLLS());
                    sachAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }
}
