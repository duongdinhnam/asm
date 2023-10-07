package namddph34374.fpoly.du_an_mau.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import namddph34374.fpoly.du_an_mau.Dao.PhieumuonDAO;
import namddph34374.fpoly.du_an_mau.LopModel.phieuMuon;
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
        holder.tvthanhvien.setText("Thành viên: " + phieumuon.getMaTVpm());
        holder.tvtensach.setText("Tên sách: " + phieumuon.getMaSpm());
        holder.tvtienthue.setText("Tiền thuê: " + String.valueOf(phieumuon.getTienthue()));
        holder.tvngaythue.setText("Ngày thuê: " + phieumuon.getNgaymuon());

        if (phieumuon.getTraSach() == 1) {
            holder.tvtrasach.setText("Đã trả sách");
            holder.tvtrasach.setTextColor(Color.parseColor("#1D0FC6"));
        } else {
            holder.tvtrasach.setText("Chưa trả sách");
            holder.tvtrasach.setTextColor(Color.parseColor("#ED0C0C"));
        }

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
