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

import namddph34374.fpoly.du_an_mau.Adapter.LoaiSachAdapter;
import namddph34374.fpoly.du_an_mau.Adapter.SachAdapter;
import namddph34374.fpoly.du_an_mau.Dao.LoaiSachDAO;
import namddph34374.fpoly.du_an_mau.Dao.SachDAO;
import namddph34374.fpoly.du_an_mau.LopProduct.Sach;
import namddph34374.fpoly.du_an_mau.LopProduct.loaiSach;
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


        return view;
    }
}
