package namddph34374.fpoly.du_an_mau.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;
import java.util.GregorianCalendar;

import namddph34374.fpoly.du_an_mau.Dao.PhieumuonDAO;
import namddph34374.fpoly.du_an_mau.R;

public class Doanhthu extends Fragment {
    EditText edtTungay, edtDenngay;
    TextView tvDoanhthu;
    Button btnTungay, btnDenngay, btnDoanhthu;
    int year, month, day;
    String start, end;
    private PhieumuonDAO phieumuonDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tong_doanh_thu, container, false);
        edtTungay = view.findViewById(R.id.edTuNgay);
        edtDenngay = view.findViewById(R.id.edDenNgay);
        tvDoanhthu = view.findViewById(R.id.tvDoanhThu);
        btnTungay = view.findViewById(R.id.btnTuNgay);
        btnDenngay = view.findViewById(R.id.btnDenNgay);
        btnDoanhthu = view.findViewById(R.id.btnDoanhThu);

        btnTungay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
             year = calendar.get(calendar.YEAR);
             month = calendar.get(calendar.MONTH);
             day = calendar.get(calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int yearm, int monthm, int dayOfMonth) {
                    start = yearm + "/" + (monthm + 1) + "/" + dayOfMonth;
                    edtTungay.setText(start);
                    }
                },year, month, day);
                datePickerDialog.show();
            }
        });

        btnDenngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(calendar.YEAR);
                month = calendar.get(calendar.MONTH);
                day = calendar.get(calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int yearm, int monthm, int dayOfMonth) {
                        end = yearm + "/" + (monthm + 1) + "/" + dayOfMonth;
                        edtDenngay.setText(end);
                    }
                },year, month, day);
                datePickerDialog.show();

            }
        });

        btnDoanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            phieumuonDAO = new PhieumuonDAO(getContext());
            int tongDoanhThu = phieumuonDAO.getDoanhThu(start, end);
            tvDoanhthu.setText(String.valueOf(tongDoanhThu));
            }
        });
        return view;
    }
}
