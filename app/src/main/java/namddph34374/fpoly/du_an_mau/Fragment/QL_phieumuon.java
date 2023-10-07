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

import namddph34374.fpoly.du_an_mau.Adapter.PhieumuonAdapter;
import namddph34374.fpoly.du_an_mau.Dao.PhieumuonDAO;
import namddph34374.fpoly.du_an_mau.LopModel.phieuMuon;
import namddph34374.fpoly.du_an_mau.R;

public class QL_phieumuon extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton floatAdd;
    private PhieumuonDAO phieumuonDAO;
    private PhieumuonAdapter phieumuonAdapter;
    private ArrayList<phieuMuon> list = new ArrayList<>();

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



        return view;
    }
}
