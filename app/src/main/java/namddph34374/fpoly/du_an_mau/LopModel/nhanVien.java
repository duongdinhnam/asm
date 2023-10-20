package namddph34374.fpoly.du_an_mau.LopModel;

public class nhanVien {
      private int maNv;
      private String hoTen;
      private String tenLogin;
      private String matkhau;

      public nhanVien(String user, String hoten, String pass) {
      }

      public nhanVien(int maNv, String hoTen, String tenLogin, String matkhau) {
            this.maNv = maNv;
            this.hoTen = hoTen;
            this.tenLogin = tenLogin;
            this.matkhau = matkhau;
      }

      public int getMaNv() {
            return maNv;
      }

      public void setMaNv(int maNv) {
            this.maNv = maNv;
      }

      public String getHoTen() {
            return hoTen;
      }

      public void setHoTen(String hoTen) {
            this.hoTen = hoTen;
      }

      public String getTenLogin() {
            return tenLogin;
      }

      public void setTenLogin(String tenLogin) {
            this.tenLogin = tenLogin;
      }

      public String getMatkhau() {
            return matkhau;
      }

      public void setMatkhau(String matkhau) {
            this.matkhau = matkhau;
      }
}
