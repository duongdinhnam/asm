package namddph34374.fpoly.du_an_mau.LopProduct;

public class phieuMuon {
    private int maPm;
    private String maNVpm;
    private int maTVpm;
    private int maSpm;
    private int tienthue;
    private String ngaymuon;

    public phieuMuon() {
    }

    public phieuMuon( String maNVpm, int maTVpm, int maSpm, int tienthue, String ngaymuon) {
        this.maPm = maPm;
        this.maNVpm = maNVpm;
        this.maTVpm = maTVpm;
        this.maSpm = maSpm;
        this.tienthue = tienthue;
        this.ngaymuon = ngaymuon;
    }

    public int getMaPm() {
        return maPm;
    }

    public void setMaPm(int maPm) {
        this.maPm = maPm;
    }

    public String getMaNVpm() {
        return maNVpm;
    }

    public void setMaNVpm(String maNVpm) {
        this.maNVpm = maNVpm;
    }

    public int getMaTVpm() {
        return maTVpm;
    }

    public void setMaTVpm(int maTVpm) {
        this.maTVpm = maTVpm;
    }

    public int getMaSpm() {
        return maSpm;
    }

    public void setMaSpm(int maSpm) {
        this.maSpm = maSpm;
    }

    public int getTienthue() {
        return tienthue;
    }

    public void setTienthue(int tienthue) {
        this.tienthue = tienthue;
    }

    public String getNgaymuon() {
        return ngaymuon;
    }

    public void setNgaymuon(String ngaymuon) {
        this.ngaymuon = ngaymuon;
    }
}
