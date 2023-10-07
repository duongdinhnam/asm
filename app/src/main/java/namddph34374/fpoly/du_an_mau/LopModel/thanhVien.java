package namddph34374.fpoly.du_an_mau.LopModel;

public class thanhVien {
    private int maTv;
    private String hoTentv;
    private String namSinhTv;

    public thanhVien(String maTV, String hoTen, String namSinh) {
    }

    public thanhVien( String hoTentv, String namSinhTv) {
        this.maTv = maTv;
        this.hoTentv = hoTentv;
        this.namSinhTv = namSinhTv;
    }

    public int getMaTv() {
        return maTv;
    }

    public void setMaTv(int maTv) {
        this.maTv = maTv;
    }

    public String getHoTentv() {
        return hoTentv;
    }

    public void setHoTentv(String hoTentv) {
        this.hoTentv = hoTentv;
    }

    public String getNamSinhTv() {
        return namSinhTv;
    }

    public void setNamSinhTv(String namSinhTv) {
        this.namSinhTv = namSinhTv;
    }
}
