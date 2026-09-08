package entity;

public class Bao extends TaiLieu {
    private String ngayPhatHanh;

    public Bao() {
    }

    public Bao(String maTaiLieu, String nhaXuatBan, int soBanPhatHanh, String ngayPhatHanh) {
        super(maTaiLieu, nhaXuatBan, soBanPhatHanh);
        this.ngayPhatHanh = ngayPhatHanh;
    }

    public String getNgayPhatHanh() {
        return ngayPhatHanh;
    }

    public void setNgayPhatHanh(String ngayPhatHanh) {
        this.ngayPhatHanh = ngayPhatHanh;
    }

    @Override
    public void inThongTin() {
        super.inThongTin();
        System.out.printf("| Loại: Báo | Ngày PH: %-15s |\n", ngayPhatHanh);
    }
}