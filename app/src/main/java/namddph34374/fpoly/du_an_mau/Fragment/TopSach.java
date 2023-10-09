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

import java.util.ArrayList;

import namddph34374.fpoly.du_an_mau.Adapter.TopAdapter;
import namddph34374.fpoly.du_an_mau.Dao.PhieumuonDAO;
import namddph34374.fpoly.du_an_mau.LopModel.Top;
import namddph34374.fpoly.du_an_mau.R;

public class TopSach extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Top> list = new ArrayList<>();
    TopAdapter adapter;
    PhieumuonDAO phieumuonDAO;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topsach, container, false);

        recyclerView = view.findViewById(R.id.recyclerview_top);
        phieumuonDAO = new PhieumuonDAO(getContext());
        list = (ArrayList<Top>) phieumuonDAO.getTop10Books();
        adapter = new TopAdapter(getActivity(), list, phieumuonDAO);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
