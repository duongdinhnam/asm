package namddph34374.fpoly.du_an_mau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;

import namddph34374.fpoly.du_an_mau.Fragment.Doi_Mk;
import namddph34374.fpoly.du_an_mau.Fragment.QL_loaiSach;
import namddph34374.fpoly.du_an_mau.Fragment.QL_phieumuon;
import namddph34374.fpoly.du_an_mau.Fragment.QL_sach;
import namddph34374.fpoly.du_an_mau.Fragment.QL_thanhvien;
import namddph34374.fpoly.du_an_mau.Fragment.TopSach;

public class Manchinh extends AppCompatActivity {
    Context context =  this;

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manchinh);


        Toolbar toolbar = findViewById(R.id.toolbar);
        FrameLayout frameLayout = findViewById(R.id.fl_content);
        NavigationView navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.navigationDrawer);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_menu_24);

        // Xóa đoạn mã này
        Fragment defaultFragment = new QL_sach(); // Thay thế QlPhieuMuonFragment bằng Fragment mặc định bạn muốn hiển thị
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_content, defaultFragment)
                .commit();
        toolbar.setTitle("Quản lý phiếu mượn"); // Thay "Tên Fragment mặc định" bằng tiêu đề bạn muốn hiển thị


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if (item.getItemId() == R.id.nav_Sach) {
                    fragment = new QL_sach();
                }

                if (item.getItemId() == R.id.nav_DoiMatKhau) {
                    fragment = new Doi_Mk();
                }
                if (item.getItemId() == R.id.nav_LoaiSach) {
                    fragment = new QL_loaiSach();
                }
                if (item.getItemId() == R.id.nav_ThanhVien) {
                    fragment = new QL_thanhvien();
                }
                if (item.getItemId() == R.id.nav_PhieuMuon) {
                    fragment = new QL_phieumuon();
                }
                if (item.getItemId() == R.id.nav_TopMuon) {
                    fragment = new TopSach();
                }
                if (item.getItemId() == R.id.nav_DangXuat) {
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
                    builder.setIcon(R.drawable.baseline_error_24);
                    builder.setTitle("Xác nhận thoát ứng dụng");
                    builder.setMessage("Bạn có muốn thoát không này?");
                    builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Manchinh.this, dangnhap.class));
                            Toast.makeText(context, "Successfully Logout", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Manchinh.this, Manchinh.class));
                            Toast.makeText(context, " Logout false", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.show();

                }

                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fl_content, fragment)
                            .commit();
                    toolbar.setTitle(item.getTitle());
                }

                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}

