package namddph34374.fpoly.du_an_mau.LopModel;

public class Sach {
    private int mas;
    private String tens;
    private int gias;
    private String mals;
    private int namxb;


    public Sach() {
    }

    public Sach( String tens, int gias, String mals, int namxb) {
        this.mas = mas;
        this.tens = tens;
        this.gias = gias;
        this.mals = mals;
        this.namxb = namxb;
    }

    public int getMas() {
        return mas;
    }

    public void setMas(int mas) {
        this.mas = mas;
    }

    public String getTens() {
        return tens;
    }

    public void setTens(String tens) {
        this.tens = tens;
    }

    public int getGias() {
        return gias;
    }

    public void setGias(int gias) {
        this.gias = gias;
    }

    public String getMals() {
        return mals;
    }

    public void setMals(String mals) {
        this.mals = mals;
    }

    public int getNamxb() {
        return namxb;
    }

    public void setNamxb(int namxb) {
        this.namxb = namxb;
    }
}
