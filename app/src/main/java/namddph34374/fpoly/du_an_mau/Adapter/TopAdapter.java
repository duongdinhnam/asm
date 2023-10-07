package namddph34374.fpoly.du_an_mau.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import namddph34374.fpoly.du_an_mau.LopModel.Top;
import namddph34374.fpoly.du_an_mau.R;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.ViewHolde> {

     private Context context;
     private ArrayList<Top> list;

    public TopAdapter(Context context, ArrayList<Top> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TopAdapter.ViewHolde onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_top, parent, false);
        return new ViewHolde(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolde holder, int position) {
    holder.tens.setText("Tên sách: "+list.get(position).tensach);
    holder.soluong.setText("Số lượng: "+list.get(position).soluong);

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolde extends RecyclerView.ViewHolder {
        TextView tens, soluong;

        public ViewHolde(@NonNull View itemView) {
            super(itemView);
            tens = itemView.findViewById(R.id.tens_top);
            soluong = itemView.findViewById(R.id.soluong_top);
        }
    }
}
