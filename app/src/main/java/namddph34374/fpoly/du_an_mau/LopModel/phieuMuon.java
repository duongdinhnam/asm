package namddph34374.fpoly.du_an_mau.LopModel;

public class phieuMuon {
    private int maPm;
    private String maNVpm;
    private String tenTVpm;
    private String tenSachSpm;
    private int tienthue;
    private String ngaymuon;
    private int traSach;

    public phieuMuon() {
    }

    public phieuMuon( String maNVpm, String tenTVpm, String tenSachSpm, int tienthue, String ngaymuon, int traSach) {
        this.maPm = maPm;
        this.maNVpm = maNVpm;
        this.tenTVpm = tenTVpm;
        this.tenSachSpm = tenSachSpm;
        this.tienthue = tienthue;
        this.ngaymuon = ngaymuon;
        this.traSach = traSach;
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

    public String getTenTVpm() {
        return tenTVpm;
    }

    public void setTenTVpm(String tenTVpm) {
        this.tenTVpm = tenTVpm;
    }

    public String getTenSachSpm() {
        return tenSachSpm;
    }

    public void setTenSachSpm(String tenSachSpm) {
        this.tenSachSpm = tenSachSpm;
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

    public int getTraSach() {
        return traSach;
    }

    public void setTraSach(int traSach) {
        this.traSach = traSach;
    }
}
