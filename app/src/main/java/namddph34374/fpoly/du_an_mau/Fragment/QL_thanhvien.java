package namddph34374.fpoly.du_an_mau.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import namddph34374.fpoly.du_an_mau.Adapter.SachAdapter;
import namddph34374.fpoly.du_an_mau.Adapter.ThanhVienAdapter;
import namddph34374.fpoly.du_an_mau.Dao.SachDAO;
import namddph34374.fpoly.du_an_mau.Dao.ThanhVienDAO;
import namddph34374.fpoly.du_an_mau.LopProduct.Sach;
import namddph34374.fpoly.du_an_mau.LopProduct.thanhVien;
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
        return view;
    }
}
