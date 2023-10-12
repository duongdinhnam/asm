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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import namddph34374.fpoly.du_an_mau.Dao.ThanhVienDAO;
import namddph34374.fpoly.du_an_mau.LopModel.thanhVien;
import namddph34374.fpoly.du_an_mau.R;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHolder>{
    private Context context;
    private ArrayList<thanhVien> list;
    private ThanhVienDAO thanhVienDAO;

    public ThanhVienAdapter(Context context, ArrayList<thanhVien> list, ThanhVienDAO thanhVienDAO) {
        this.context = context;
        this.list = list;

    }


    @NonNull
    @Override
    public ThanhVienAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_tv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhVienAdapter.ViewHolder holder, int position) {
        holder.tvma.setText("Mã thành viên: "+ list.get(position).getMaTv());
        holder.tvten.setText("Tên thành viên: "+list.get(position).getHoTentv());
        holder.tvnamsinh.setText("Năm sinh: "+list.get(position).getNamSinhTv());
        thanhVien vien = list.get(position);
        holder.btnxoa.setOnClickListener(new View.OnClickListener() {
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
                        thanhVienDAO = new ThanhVienDAO(context);
                        long result = thanhVienDAO.deleteThanhVien(vien.getMaTv());
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDialog(vien);
            }
        });

    }
    private void updateDialog(thanhVien tv) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_updata_thanhvien, null);
        builder.setView(view);
        builder.setCancelable(false);
        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText edtTenTV,edtNamSinh;
        Button btnSua,btnHuy;

        edtTenTV = view.findViewById(R.id.ed_hotentv_uptv);
        edtNamSinh = view.findViewById(R.id.ed_namsinh_uptv);
        btnSua = view.findViewById(R.id.btn_save_thanhvien_uptv);
        btnHuy = view.findViewById(R.id.btn_huy_thanhvien_uptv);

        edtTenTV.setText(tv.getHoTentv());
        edtNamSinh.setText(String.valueOf(tv.getNamSinhTv()));

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thanhVienDAO = new ThanhVienDAO(context);
                String tentv = edtTenTV.getText().toString();
                String namsinh = edtNamSinh.getText().toString();

                if (tentv.isEmpty()||namsinh.isEmpty()){
                    Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!namsinh.matches("\\d+")){
                    Toast.makeText(context, "Năm sinh phải là số", Toast.LENGTH_SHORT).show();
                    return;
                }
                tv.setHoTentv(tentv);
                tv.setNamSinhTv(String.valueOf(Integer.parseInt(namsinh)));

                if (thanhVienDAO.updateThanhVien(tv)>0){
                    list.clear();
                    list.addAll(thanhVienDAO.getAllSanpham());
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




    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvma, tvten, tvnamsinh;
        ImageView btnxoa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvma = itemView.findViewById(R.id.matv_tv);
            tvten = itemView.findViewById(R.id.tentv_tv);
            tvnamsinh = itemView.findViewById(R.id.namsinh_tv);
            btnxoa = itemView.findViewById(R.id.btnxoa_tv);
        }
    }
}
