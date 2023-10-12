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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import namddph34374.fpoly.du_an_mau.Dao.LoaiSachDAO;
import namddph34374.fpoly.du_an_mau.Dao.SachDAO;
import namddph34374.fpoly.du_an_mau.Dao.ThanhVienDAO;
import namddph34374.fpoly.du_an_mau.LopModel.Sach;
import namddph34374.fpoly.du_an_mau.LopModel.loaiSach;
import namddph34374.fpoly.du_an_mau.LopModel.thanhVien;
import namddph34374.fpoly.du_an_mau.R;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.SachViewHolder> {

    private Context context;
    private ArrayList<Sach> list;
    SachDAO sachDAO;
    Sach sach;
    private LoaiSachDAO loaiSach;
    public SachAdapter(Context context, ArrayList<Sach> list, SachDAO sachDAO) {
        this.context = context;
        this.list = list;
        this.sachDAO = sachDAO;
    }



    @NonNull
    @Override
    public SachAdapter.SachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sach, parent, false);
        return new SachViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SachAdapter.SachViewHolder holder, int position) {
        sach = list.get(position);
    holder.txtmas.setText(("Mã sách: "+ list.get(position).getMas()));
    holder.txttensach.setText("Tên sách: "+list.get(position).getTens());
    holder.txttienthue.setText("Tiền thuê: "+list.get(position).getGias());
    holder.txtloaisach.setText("Loại sách: "+list.get(position).getMals());


    holder.imagexoa.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = holder.getAdapterPosition();
            Sach s = list.get(position);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setIcon(R.drawable.running_with_errors_24);
            builder.setTitle("Xác nhận xóa");
            builder.setMessage("Bạn có chắc chắn muốn xóa sách này?");
            builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sachDAO = new SachDAO(context);
                    long result = sachDAO.deleteSach(s.getMas());
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
            updateDialog(sach);
        }
    });
    }
    private void updateDialog(Sach s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_update_sach, null);
        builder.setView(view);
        builder.setCancelable(false);
        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText edtTenSach,edtTienThue;
        Spinner edtTenLoai;
        Button btnSua, btnHuy;

        edtTenSach = view.findViewById(R.id.edtTenSach_ups);
        edtTienThue = view.findViewById(R.id.edtTienThue_ups);
        edtTenLoai = view.findViewById(R.id.spinerTenLoai_ups);

        btnSua = view.findViewById(R.id.btnup_ups);
        btnHuy = view.findViewById(R.id.btnHuy_ups);

        edtTenSach.setText(s.getTens());
        edtTienThue.setText(String.valueOf(s.getGias()));
        edtTenLoai.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, getTenLoaiSachList()));
        edtTenLoai.setSelection(getTenLoaiSachList().indexOf(sach.getMals()));

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sachDAO = new SachDAO(context);
                String ten = edtTenSach.getText().toString();
                String tien = edtTienThue.getText().toString();
                String loai = edtTenLoai.getSelectedItem().toString();

                if (ten.isEmpty()||tien.isEmpty()){
                    Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!tien.matches("\\d+")){
                    Toast.makeText(context, "Tiền thuê phải là sô", Toast.LENGTH_SHORT).show();
                    return;
                }
                s.setTens(ten);
                s.setGias(Integer.parseInt(tien));
                s.setMals(loai);

                if (sachDAO.updateSach(s)>0){
                    list.clear();
                    list.addAll(sachDAO.getAllSanpham());
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
    private ArrayList<String> getTenLoaiSachList() {
        LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
        ArrayList<loaiSach> list1 = loaiSachDAO.getALLLS();
        ArrayList<String> tenLoaiSachList = new ArrayList<>();

        for (loaiSach ls: list1){
            tenLoaiSachList.add(ls.getTenLS());
        }
        return tenLoaiSachList;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class SachViewHolder extends RecyclerView.ViewHolder{
    TextView txtmas, txttensach, txttienthue, txtloaisach;
    ImageView imagexoa;
        public SachViewHolder(@NonNull View itemView) {
            super(itemView);
            txtmas = itemView.findViewById(R.id.tvmasach_s);
            txttensach = itemView.findViewById(R.id.tvtensach_s);
            txttienthue = itemView.findViewById(R.id.tvtienthue_s);
            txtloaisach = itemView.findViewById(R.id.tvloaisach_s);
            imagexoa = itemView.findViewById(R.id.btnxoa_s);
        }
    }
}
