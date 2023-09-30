package namddph34374.fpoly.du_an_mau.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import namddph34374.fpoly.du_an_mau.Dao.SachDAO;
import namddph34374.fpoly.du_an_mau.LopProduct.Sach;
import namddph34374.fpoly.du_an_mau.R;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.SachViewHolder> {

    private Context context;
    private ArrayList<Sach> list;
    SachDAO sachDAO;
    Sach sach;
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
    holder.txtmas.setText(("Mã sách: "+ list.get(position).getMas()));
    holder.txttensach.setText("Tên sách: "+list.get(position).getTens());
    holder.txttienthue.setText("Tiền thuê: "+list.get(position).getGias());
    holder.txtloaisach.setText("Loại sách: "+list.get(position).getMals());

    sach = list.get(position);
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
