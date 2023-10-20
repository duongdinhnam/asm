package namddph34374.fpoly.du_an_mau.Dbheper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbheper extends SQLiteOpenHelper {
    private static final String DB_NAME = "PNLINB.db";
    private static final int VERSION = 20;
    public Dbheper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // bảng nhân viên
        String createTablenv = "CREATE TABLE nv(MANV INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "TENLOGIN TEXT,"+
                "HOTEN TEXT,"+
                "MATKHAU TEXT)";
        db.execSQL(createTablenv);
        String insertnv = "INSERT INTO nv (TENLOGIN, HOTEN, MATKHAU) VALUES "
                + "('nam', 'dương đình nam', '123'), "
                + "( 'abc', 'dương đình nam', 'abc'), "
                + "( 'qaz','dương đình nam' ,'xyz')";
        db.execSQL(insertnv);

        // bảng thành viên
        String createTabletv = "CREATE TABLE tv(MATV INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "HOTENTV TEXT,"+
                "NAMSINHTV TEXT)";
        db.execSQL(createTabletv);
        String inserttv = "INSERT INTO tv ( HOTENTV, NAMSINHTV) VALUES "
                + "( 'nam', '12/01/2004'), "
                + "( 'ngọc', '01/01/2001')";
        db.execSQL(inserttv);

        // bảng loại sách
        String createTablels = "CREATE TABLE ls(MALS INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "TENLS TEXT)";
        db.execSQL(createTablels);

        String insertls = "INSERT INTO ls ( TENLS) VALUES "
                + "( 'Ngôn ngữ game'), "
                + "( 'Lập trình java')";
        db.execSQL(insertls);

        // bảng  sách
        String createTables = "CREATE TABLE s(MAS INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "TENS TEXT,"+
                "GIAS INTEGER,"+
                "MALS TEXT REFERENCES loaiSach(MALS),"+
                "NAMXB INTEGER)";
        db.execSQL(createTables);
        String inserts = "INSERT INTO s ( TENS, GIAS, MALS, NAMXB) VALUES "
                + "( 'doraemon', 10000, 'Ngôn ngữ game', 2004), "
                + "( 'one piece',11000 , 'Ngôn ngữ game', 2002), "
                + "( 'lập trình java', 30000, 'Lập trình java', 2000)";
        db.execSQL(inserts);

        // bảng  phiếu mượn
        String createTablepm = "CREATE TABLE pm(MAPM INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "MANVPM TEXT REFERENCES nhanVien(MANV), " +
                "MATVPM INTEGER REFERENCES thanhVien(MATV), " +
                "MASPM INTEGER REFERENCES Sach(MAS), " +
                "TIENTHUE INTEGER,"+
                "NGAYMUON TEXT,"+
                "TRASACH INTEGER)";
        db.execSQL(createTablepm);
        String insertpm = "INSERT INTO pm ( MANVPM, MATVPM, MASPM, TIENTHUE, NGAYMUON, TRASACH) VALUES "
                + "( '1' , 1 , 1 , 20000, '2023-09-27', 1), "
                + "( '2' , 1 , 2 , 12000, '2023-02-20', 0)";
        db.execSQL(insertpm);

        // admin
        String createTableAdmin = "CREATE TABLE admin(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TENDANGNHAP TEXT," +
                "HOTEN TEXT," +
                "MATKHAU TEXT)";
        db.execSQL(createTableAdmin);
        String dsnd = "INSERT INTO admin (TENDANGNHAP, HOTEN, MATKHAU) VALUES" +
                "('admin','Dương Đình Nam','admin')," +
                "('nam','Dương Đình Nam','123')";
        db.execSQL(dsnd);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableThuThu = "drop table if exists nv";
        db.execSQL(dropTableThuThu);

        String dropTableThanhVien = "drop table if exists tv";
        db.execSQL(dropTableThanhVien);

        String dropTableLoaiSach = "drop table if exists ls";
        db.execSQL(dropTableLoaiSach);

        String dropTableSach = "drop table if exists s";
        db.execSQL(dropTableSach);

        String dropTablePhieuMuon = "drop table if exists pm";
        db.execSQL(dropTablePhieuMuon);

        onCreate(db);
    }
}
