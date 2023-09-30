package namddph34374.fpoly.du_an_mau.LopProduct;

public class nhanVien {
      private int maNv;
      private String hoTen;
      private String matkhau;

      public nhanVien(String string, String cursorString) {
      }

      public nhanVien(int maNv, String hoTen, String matkhau) {
            this.maNv = maNv;
            this.hoTen = hoTen;
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

      public String getMatkhau() {
            return matkhau;
      }

      public void setMatkhau(String matkhau) {
            this.matkhau = matkhau;
      }
}
