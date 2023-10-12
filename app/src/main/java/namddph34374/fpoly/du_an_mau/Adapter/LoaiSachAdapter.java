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
import namddph34374.fpoly.du_an_mau.LopModel.Sach;
import namddph34374.fpoly.du_an_mau.LopModel.loaiSach;
import namddph34374.fpoly.du_an_mau.R;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHolder>{
    private Context context;

    public LoaiSachAdapter(Context context, ArrayList<loaiSach> list, LoaiSachDAO loaiSachDAO) {
        this.context = context;
        this.list = list;
        this.loaiSachDAO = loaiSachDAO;
    }

    private ArrayList<loaiSach> list;
    private loaiSach lsach;
    private LoaiSachDAO loaiSachDAO;




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_loaisach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        lsach = list.get(position);
        holder.maloai.setText("mã loại sách:"+ lsach.getMaLS());
        holder.tenloai.setText("tên loại sách: "+lsach.getTenLS()); // Đảm bảo rằng bạn truy cập thuộc tính đúng
        holder.txtdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                loaiSach s = list.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(R.drawable.running_with_errors_24);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa sách này?");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        loaiSachDAO = new LoaiSachDAO(context);
                        long result = loaiSachDAO.deleteloaiSach(s.getMaLS());
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
                updateDialog(lsach);
            }
        });

        // Truy cập Spinner trong layout của bạn và làm gì đó với nó (nếu cần)
    }
    private void updateDialog(loaiSach ls) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_update_loaisach, null);
        builder.setView(view);
        builder.setCancelable(false);
        Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText edtTenSach;
        Button btnSua, btnHuy;

        edtTenSach = view.findViewById(R.id.edttenloaisach_upls);


        btnSua = view.findViewById(R.id.btnup_upls);
        btnHuy = view.findViewById(R.id.btnHuy_upls);

        edtTenSach.setText(ls.getTenLS());

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaiSachDAO = new LoaiSachDAO(context);
                String ten = edtTenSach.getText().toString();


                if (ten.isEmpty()){
                    Toast.makeText(context, "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }


                ls.setTenLS(ten);


                if (loaiSachDAO.updateloaiSach(ls)>0){
                    list.clear();
                    list.addAll(loaiSachDAO.getALLLS());
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView maloai, tenloai;
        ImageView txtdelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            maloai = itemView.findViewById(R.id.txtmaloai); // Đảm bảo id là đúng
            tenloai = itemView.findViewById(R.id.txttenloai); // Đảm bảo id là đúng
            txtdelete = itemView.findViewById(R.id.imageViewdl); // Đảm bảo id là đúng
        }
    }
}
