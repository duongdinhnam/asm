package namddph34374.fpoly.du_an_mau.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import namddph34374.fpoly.du_an_mau.Dao.PhieumuonDAO;
import namddph34374.fpoly.du_an_mau.Dao.SachDAO;
import namddph34374.fpoly.du_an_mau.Dao.ThanhVienDAO;
import namddph34374.fpoly.du_an_mau.LopModel.Sach;
import namddph34374.fpoly.du_an_mau.LopModel.phieuMuon;
import namddph34374.fpoly.du_an_mau.LopModel.thanhVien;
import namddph34374.fpoly.du_an_mau.R;

public class PhieumuonAdapter extends RecyclerView.Adapter<PhieumuonAdapter.ViewHolder> {

    private Context context;
    private ArrayList<phieuMuon> list;
    private PhieumuonDAO phieumuonDAO;


    public PhieumuonAdapter(Context context, ArrayList<phieuMuon> list, PhieumuonDAO phieumuonDAO) {
        this.context = context;
        this.list = list;
        this.phieumuonDAO = phieumuonDAO;

    }


    @NonNull
    @Override
    public PhieumuonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          View view = LayoutInflater.from(context).inflate(R.layout.item_phieumuon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhieumuonAdapter.ViewHolder holder, int position) {
        phieuMuon phieumuon = list.get(position);

        holder.tvmapm.setText("Mã phiếu mượn: " + phieumuon.getMaPm());
        holder.tvthanhvien.setText("Thành viên: " + phieumuon.getTenTVpm());
        holder.tvtensach.setText("Tên sách: " + phieumuon.getTenSachSpm());
        holder.tvtienthue.setText("Tiền thuê: " + String.valueOf(phieumuon.getTienthue()));
        holder.tvngaythue.setText("Ngày thuê: " + phieumuon.getNgaymuon());

        if (phieumuon.getTraSach() == 1) {
            holder.tvtrasach.setText("Đã trả sách");
            holder.tvtrasach.setTextColor(Color.parseColor("#1D0FC6"));
        } else {
            holder.tvtrasach.setText("Chưa trả sách");
            holder.tvtrasach.setTextColor(Color.parseColor("#ED0C0C"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDialog(phieumuon);
            }
        });
        holder.imgxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(R.drawable.running_with_errors_24);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa sách này?");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        phieumuonDAO = new PhieumuonDAO(context);
                        long result = phieumuonDAO.deletephiuemuon(phieumuon.getMaPm());
                        if (result > 0) {
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            list.remove(position);
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
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
        });
    }
    public void updateDialog(phieuMuon pm) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_update_phieumuon, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Spinner edtTenSach,edtTenTV;
        TextView txtNgayThue, txtTienThue;
        CheckBox chkTrangThai;
        Button btnSua, btnHuy;

        edtTenTV = view.findViewById(R.id.edtTenTV);
        edtTenSach = view.findViewById(R.id.edtTenSach);
        txtNgayThue = view.findViewById(R.id.txtNgayThue);
        txtTienThue = view.findViewById(R.id.txtTienThue);
        chkTrangThai = view.findViewById(R.id.chkTrangThai);
        btnSua = view.findViewById(R.id.btnSua);
        btnHuy = view.findViewById(R.id.btnHuy);

        edtTenTV.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, getTenThanhVienList()));
        edtTenTV.setSelection(getTenThanhVienList().indexOf(pm.getTenTVpm()));

        edtTenSach.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, getTenSachList()));
        edtTenSach.setSelection(getTenSachList().indexOf(pm.getTenSachSpm()));

        txtNgayThue.setText(pm.getNgaymuon());
        txtTienThue.setText(String.valueOf(pm.getTienthue()));
        if (pm.getTraSach() == 1) {
            chkTrangThai.setChecked(true);
        } else {
            chkTrangThai.setChecked(false);
        }
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenTV = edtTenTV.getSelectedItem().toString();
                String tenSach = edtTenSach.getSelectedItem().toString();

                if (chkTrangThai.isChecked()){
                    pm.setTraSach(1);
                }else{
                    pm.setTraSach(0);
                }

                if(tenTV.isEmpty()||tenSach.isEmpty()){
                    Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                pm.setTenTVpm(tenTV);
                pm.setTenSachSpm(tenSach);

                if (phieumuonDAO.updatephieumuon(pm)>0){
                    list.clear();
                    list.addAll(phieumuonDAO.getAllPM());
                    notifyDataSetChanged();
                    dialog.dismiss();
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Lỗi cập nhật", Toast.LENGTH_SHORT).show();
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

    private ArrayList<String> getTenSachList() {
        SachDAO sachDAO = new SachDAO(context);
        ArrayList<Sach> list1 = sachDAO.getAllSanpham();
        ArrayList<String> tenSachList = new ArrayList<>();

        for (Sach sach: list1){
            tenSachList.add(sach.getTens());
        }
        return tenSachList;
    }
    private ArrayList<String> getTenThanhVienList() {
        ThanhVienDAO thanhVienDAO = new ThanhVienDAO(context);
        ArrayList<thanhVien> list1 = thanhVienDAO.getAllSanpham();
        ArrayList<String> tenThanhVienList = new ArrayList<>();

        for (thanhVien tv: list1){
            tenThanhVienList.add(tv.getHoTentv());
        }
        return tenThanhVienList;
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvmapm, tvthanhvien, tvtensach, tvtienthue, tvngaythue, tvtrasach;
        ImageView imgxoa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvmapm = itemView.findViewById(R.id.tvmaphieu_pm);
            tvthanhvien = itemView.findViewById(R.id.tvthanhvien_pm);
            tvtensach = itemView.findViewById(R.id.tvtensach_pm);
            tvtienthue = itemView.findViewById(R.id.tvtienthue_pm);
            tvngaythue = itemView.findViewById(R.id.tvngaythue_pm);
            tvtrasach = itemView.findViewById(R.id.tvtrangthai_pm);
            imgxoa = itemView.findViewById(R.id.imgxoa_pm);
        }
    }
}
