package namddph34374.fpoly.du_an_mau.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import namddph34374.fpoly.du_an_mau.Adapter.SachAdapter;
import namddph34374.fpoly.du_an_mau.Dao.LoaiSachDAO;
import namddph34374.fpoly.du_an_mau.Dao.SachDAO;
import namddph34374.fpoly.du_an_mau.LopModel.Sach;
import namddph34374.fpoly.du_an_mau.LopModel.loaiSach;
import namddph34374.fpoly.du_an_mau.R;

public class QL_sach extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton floatAdd;
    private SachDAO sachDAO;
    private SachAdapter sachAdapter;
    private ArrayList<Sach> list = new ArrayList<>();
    SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_q_l_sach, container, false);
        recyclerView = view.findViewById(R.id.recyclerview_s);
        floatAdd = view.findViewById(R.id.btnadd_s);
        searchView = view.findViewById(R.id.id_serch);

        sachDAO = new SachDAO(getContext());
        list = sachDAO.getAllSanpham();
        sachAdapter = new SachAdapter(getContext(), list, sachDAO);



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(sachAdapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                sachAdapter.getFilter().filter(newText);
                return false;
            }
        });

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mở giao diện thêm sách mới
                showDialogAddSach();
            }
        });


        return view;
    }
    private void showDialogAddSach() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themsach, null);
        builder.setView(view);

        EditText edtTenSach = view.findViewById(R.id.editts);
        EditText edtGiaSach = view.findViewById(R.id.editgiasach);
        Spinner spinnerLoaiSach = view.findViewById(R.id.spiner);

        // Set loại sách lên Spinner
        ArrayList<String> tenLoaiSachList = getTenLoaiSachList();
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_spinner_item,
                tenLoaiSachList
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLoaiSach.setAdapter(spinnerAdapter);

        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Lấy thông tin từ EditText và Spinner
                String tenSach = edtTenSach.getText().toString();
                String giaSach = edtGiaSach.getText().toString();
                String selectedLoaiSach = spinnerLoaiSach.getSelectedItem().toString();

                // Kiểm tra xem các trường dữ liệu có hợp lệ không (ví dụ: không để trống)
                if (TextUtils.isEmpty(tenSach) || TextUtils.isEmpty(giaSach)) {
                    Toast.makeText(getContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                Sach sach = new Sach(tenSach, Integer.parseInt(giaSach),selectedLoaiSach);
                if (sachDAO.addSach(sach) > 0) {
                    Toast.makeText(getContext(), "Thêm sách thành công", Toast.LENGTH_SHORT).show();
                    // Cập nhật RecyclerView sau khi thêm sách
                    list.clear();
                    list.addAll(sachDAO.getAllSanpham());
                    sachAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                } else {
                    Toast.makeText(getContext(), "Thêm sách thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private ArrayList<String> getTenLoaiSachList() {
        LoaiSachDAO loaisachDAO = new LoaiSachDAO(getContext());
        ArrayList<loaiSach> list1 = loaisachDAO.getALLLS();
        ArrayList<String> tenLoaiSachList = new ArrayList<>();

        for (loaiSach loaisach : list1) {
            tenLoaiSachList.add(loaisach.getTenLS());
        }
        return tenLoaiSachList;
    }
}
