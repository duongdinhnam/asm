package namddph34374.fpoly.du_an_mau.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.ArrayList;

import namddph34374.fpoly.du_an_mau.Adapter.PhieumuonAdapter;
import namddph34374.fpoly.du_an_mau.Dao.PhieumuonDAO;
import namddph34374.fpoly.du_an_mau.Dao.SachDAO;
import namddph34374.fpoly.du_an_mau.Dao.ThanhVienDAO;
import namddph34374.fpoly.du_an_mau.LopModel.Sach;
import namddph34374.fpoly.du_an_mau.LopModel.phieuMuon;
import namddph34374.fpoly.du_an_mau.LopModel.thanhVien;
import namddph34374.fpoly.du_an_mau.R;

public class QL_phieumuon extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton floatAdd;
    private PhieumuonDAO phieumuonDAO;
    private PhieumuonAdapter phieumuonAdapter;
    private ArrayList<phieuMuon> list = new ArrayList<>();

    private SachDAO sachDAO;
    private Sach sach;
    private ThanhVienDAO thanhVienDAO;
    private thanhVien thanhVien;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_phieumuon, container, false);

        recyclerView = view.findViewById(R.id.recyclerview_pm);
        floatAdd = view.findViewById(R.id.btnadd_pm);

        phieumuonDAO = new PhieumuonDAO(getContext());
        list = phieumuonDAO.getAllPM();
        phieumuonAdapter = new PhieumuonAdapter(getContext(), list, phieumuonDAO);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(phieumuonAdapter);

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_them();
            }
        });

        return view;
    }
    public void dialog_them() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.dialog_add_phieumuon, null);
        builder.setView(view);
        builder.setCancelable(false);
        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Spinner edtTenSach,edtTenTV;
        CheckBox chkTrangThai;
        Button btnAdd, btnHuy;

        edtTenTV = view.findViewById(R.id.spMaTv_pm);
        edtTenSach = view.findViewById(R.id.spMaS_pm);
        chkTrangThai = view.findViewById(R.id.chkTraSach_pm);
        btnAdd = view.findViewById(R.id.btnSavePM_pm);
        btnHuy = view.findViewById(R.id.btnCancelPM);

        ArrayList<String> tenTVList = getTenTV();
        ArrayAdapter<String> spinnerTV = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_item,
                tenTVList);

        spinnerTV.setDropDownViewResource(android.R.layout.
                        simple_spinner_dropdown_item);
        edtTenTV.setAdapter(spinnerTV);

        ArrayList<String> tenSachList = getTenSachList();      // Lấy danh sách tên sách và lưu vào biến tenSachList
        ArrayAdapter<String> spinnerSach = new ArrayAdapter<>( // Tạo một Adapter để hiển thị danh sách tên sách trong Spinner
                getContext(),                          // Context của Activity hiện tại
                android.R.layout.simple_spinner_item,          // Layout cho mỗi mục trong Spinner
                tenSachList);                                    // Danh sách dữ liệu cho Spinner


        spinnerSach.setDropDownViewResource(android.R.layout.
                        simple_spinner_dropdown_item);         // Đặt layout cho dropdown của Spinner (có thể thay đổi nếu muốn)

        edtTenSach.setAdapter(spinnerSach);                   // Gắn Adapter vào Spinner để hiển thị danh sách tên sách

        ArrayList<Integer> giaTienThueList =
                getGiaTienThueList();                         // Lấy danh sách giá tiền thuê sách và lưu vào biến giaTienThueList

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenTV = edtTenTV.getSelectedItem().toString();
                String tenSach = edtTenSach.getSelectedItem().toString();
                int giaTienThue = giaTienThueList.get(edtTenSach.getSelectedItemPosition()); // lấy giá tiền thuê của cuốn sách được chọn trong Spinner

                if (tenTV.isEmpty() || tenSach.isEmpty()) {
                    Toast.makeText(getContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                String ngay = String.valueOf(LocalDate.now()); // lấy ngày hiện tại
                phieuMuon pm = new phieuMuon();

                pm.setTenTVpm(tenTV);
                pm.setTenSachSpm(tenSach);
                pm.setTienthue(giaTienThue);
                pm.setNgaymuon(ngay);
                if (chkTrangThai.isChecked()) {
                    pm.setTraSach(1);
                } else {
                    pm.setTraSach(0);
                }
                if (phieumuonDAO.addphieumuon(pm) > 0) {
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    list.clear();//xóa tất cả các phần tử trong danh sách list
                    list.addAll(phieumuonDAO.getAllPM());//thêm tất cả các phần tử của danh sách sản phẩm được lấy từ cơ sở dữ liệu vào danh sách list.
                    phieumuonAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                } else {
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
    private ArrayList<Integer> getGiaTienThueList() { // lấy danh sách tiền thuê của tất cả sách
        SachDAO sachDAO = new SachDAO(getContext());
        ArrayList<Sach> list1 = sachDAO.getAllSanpham();
        ArrayList<Integer> giaTienThueList = new ArrayList<>();

        for (Sach sach : list1) {
            giaTienThueList.add(sach.getGias());
        }
        return giaTienThueList;
    }
    private ArrayList<String> getTenSachList() { // lấy danh sách các cuốn sách
        SachDAO sachDAO = new SachDAO(getContext());
        ArrayList<Sach> list1 = sachDAO.getAllSanpham();
        ArrayList<String> tenSachList = new ArrayList<>();

        for (Sach sach : list1) {
            tenSachList.add(sach.getTens());
        }
        return tenSachList;
    }

    private ArrayList<String> getTenTV() { // lấy danh sách các thành viên
        ThanhVienDAO thanhVienDAO = new ThanhVienDAO(getContext());
        ArrayList<thanhVien> list1 = thanhVienDAO.getAllSanpham();
        ArrayList<String> tenTVList = new ArrayList<>();

        for (thanhVien thanhVien : list1) {
            tenTVList.add(thanhVien.getHoTentv());
        }
        return tenTVList;
    }
}
