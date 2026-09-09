package backend;

import entity.Bao;
import entity.Sach;
import entity.TaiLieu;
import entity.TapChi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuanLySach implements IQuanLySach {
    private List<TaiLieu> danhSachTaiLieu = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    private boolean kiemTraMaTonTai(String maTaiLieu) {
        for (TaiLieu tl : danhSachTaiLieu) {
            if (tl.getMaTaiLieu().equals(maTaiLieu)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void themMoiTaiLieu() {
        System.out.println("Nhập mã tài liệu: ");
        String maTaiLieu = scanner.nextLine();

        if (kiemTraMaTonTai(maTaiLieu)) {
            System.out.println("Mã tài liệu đã tồn tại. Vui lòng thử lại!");
            return;
        }

        System.out.println("Nhập tên nhà xuất bản: ");
        String nxb = scanner.nextLine();
        System.out.println("Nhập số bản phát hành: ");
        int soBan = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Chọn loại tài liệu: 1. Sách   2. Tạp chí   3. Báo");
        String loai = scanner.nextLine();

        switch (loai) {
            case "1":
                System.out.println("Nhập tên tác giả: ");
                String tacGia = scanner.nextLine();
                System.out.println("Nhập số trang: ");
                int soTrang = scanner.nextInt();
                scanner.nextLine();
                Sach sach = new Sach(maTaiLieu, nxb, soBan, tacGia, soTrang);
                danhSachTaiLieu.add(sach);
                System.out.println("Đã thêm Sách thành công!");
                break;
            case "2":
                System.out.println("Nhập số phát hành: ");
                int soPH = scanner.nextInt();
                System.out.println("Nhập tháng phát hành: ");
                int thangPH = scanner.nextInt();
                scanner.nextLine();
                TapChi tapChi = new TapChi(maTaiLieu, nxb, soBan, soPH, thangPH);
                danhSachTaiLieu.add(tapChi);
                System.out.println("Đã thêm Tạp chí thành công!");
                break;
            case "3":
                System.out.println("Nhập ngày phát hành (dd/MM/yyyy): ");
                String ngayPH = scanner.nextLine();
                Bao bao = new Bao(maTaiLieu, nxb, soBan, ngayPH);
                danhSachTaiLieu.add(bao);
                System.out.println("Đã thêm Báo thành công!");
                break;
            default:
                System.out.println("Loại tài liệu không hợp lệ!");
        }
    }

    @Override
    public void xoaTaiLieuTheoMa() {
        System.out.println("Nhập mã tài liệu cần xoá: ");
        String ma = scanner.nextLine();
        boolean removed = danhSachTaiLieu.removeIf(tl -> tl.getMaTaiLieu().equals(ma));
        if (removed) {
            System.out.println("Xoá tài liệu thành công!");
        } else {
            System.out.println("Không tìm thấy tài liệu với mã: " + ma);
        }
    }

    @Override
    public void hienThiThongTin() {

        if (danhSachTaiLieu.isEmpty()) {
            System.out.println("Danh sách tài liệu trống.");
            return;
        }
        System.out.println("---- DANH SÁCH TÀI LIỆU ----");
        for (TaiLieu tl : danhSachTaiLieu) {
            tl.inThongTin();
        }
    }

    @Override
    public void timKiemTheoLoai() {
        System.out.println("Chọn loại cần tìm: 1. Sách   2. Tạp chí   3. Báo");
        String loai = scanner.nextLine();
        boolean found = false;

        for (TaiLieu tl : danhSachTaiLieu) {
            if (loai.equals("1") && tl instanceof Sach) {
                tl.inThongTin();
                found = true;
            } else if (loai.equals("2") && tl instanceof TapChi) {
                tl.inThongTin();
                found = true;
            } else if (loai.equals("3") && tl instanceof Bao) {
                tl.inThongTin();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy tài liệu loại này.");
        }
    }
}