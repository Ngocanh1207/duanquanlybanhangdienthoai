package giaodienchuan.model.BackEnd.QuanLySanPham;

import giaodienchuan.model.BackEnd.ConnectionDB.ConnectionDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class QuanLySanPhamDAO {

    private ConnectionDB qlspConnection;

    public QuanLySanPhamDAO() {

    }

    public ArrayList<SanPham> readDB() {
        setQlspConnection(new ConnectionDB());
        ArrayList<SanPham> dssp = new ArrayList<>();
        try {
            String qry = "SELECT * FROM sanpham";
            ResultSet r = getQlspConnection().sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String masp = r.getString("MaSP");
                    String loaisp = r.getString("MaLSP");
                    String tensp = r.getString("TenSP");
                    float dongia = r.getFloat("DonGia");
                    int soluong = r.getInt("SoLuong");
                    String url = r.getString("HinhAnh");
                    int trangthai = r.getInt("TrangThai");
                    dssp.add(new SanPham(masp, loaisp, tensp, dongia, soluong, url, trangthai));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng sản phẩm");
        } finally {
            getQlspConnection().closeConnect();
        }
        return dssp;
    }

    public ArrayList<SanPham> search(String columnName, String value) {
        setQlspConnection(new ConnectionDB());
        ArrayList<SanPham> dssp = new ArrayList<>();

        try {
            String qry = "SELECT * FROM sanpham WHERE " + columnName + " LIKE '%" + value + "%'";
            ResultSet r = getQlspConnection().sqlQuery(qry);
            if (r != null) {
                while (r.next()) {
                    String masp = r.getString("MaSP");
                    String loaisp = r.getString("MaLSP");
                    String tensp = r.getString("TenSP");
                    float dongia = r.getFloat("DonGia");
                    int soluong = r.getInt("SoLuong");
                    String url = r.getString("HinhAnh");
                    int trangthai = r.getInt("TrangThai");
                    dssp.add(new SanPham(masp, loaisp, tensp, dongia, soluong, url, trangthai));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi tìm dữ liệu " + columnName + " = " + value + " bảng sản phẩm");
        } finally {
            getQlspConnection().closeConnect();
        }

        return dssp;
    }

    public Boolean add(SanPham sp) {
        setQlspConnection(new ConnectionDB());
        Boolean ok = getQlspConnection().sqlUpdate("INSERT INTO `sanpham` (`MaSP`, `MaLSP`, `TenSP`, `DonGia`, `SoLuong`, `HinhAnh`, `TrangThai`) VALUES ('"
                + sp.getMaSP() + "', '"
                + sp.getMaLSP() + "', '"
                + sp.getTenSP() + "', '"
                + sp.getDonGia() + "', '"
                + sp.getSoLuong() + "', '"
                + sp.getFileNameHinhAnh() + "', '"
                + sp.getTrangThai()+ "');");
        getQlspConnection().closeConnect();
        return ok;
    }

    public Boolean delete(String masp) {
        setQlspConnection(new ConnectionDB());
        Boolean ok = getQlspConnection().sqlUpdate("DELETE FROM `sanpham` WHERE `sanpham`.`MaSP` = '" + masp + "'");
        getQlspConnection().closeConnect();
        return ok;
    }

    public Boolean update(String MaSP, String MaLSP, String TenSP, float DonGia, int SoLuong, String filename, int trangthai) {
        setQlspConnection(new ConnectionDB());
        Boolean ok = getQlspConnection().sqlUpdate("Update SanPham Set "
                + "MaLSP='" + MaLSP
                + "',TenSP='" + TenSP
                + "',DonGia='" + DonGia
                + "',SoLuong='" + SoLuong
                + "',HinhAnh='" + filename
                + "',TrangThai='" + trangthai
                + "' where MaSP='" + MaSP + "'");
        getQlspConnection().closeConnect();
        return ok;
    }

    public Boolean updateSoLuong(String MaSP, int SoLuong) {
        setQlspConnection(new ConnectionDB());
        Boolean ok = getQlspConnection().sqlUpdate("Update SanPham Set "
                + "SoLuong='" + SoLuong
                + "' where MaSP='" + MaSP + "'");
        getQlspConnection().closeConnect();
        return ok;
    }
    
    public Boolean updateTrangThai(String MaSP, int trangthai) {
        setQlspConnection(new ConnectionDB());
        Boolean ok = getQlspConnection().sqlUpdate("Update SanPham Set "
                + "TrangThai='" + trangthai
                + "' where MaSP='" + MaSP + "'");
        getQlspConnection().closeConnect();
        return ok;
    }

    public void close() {
        // Kiểm tra xem qlspConnection có được khởi tạo không trước khi đóng kết nối
        if (getQlspConnection() != null) {
            getQlspConnection().closeConnect();
        }
}

public static void main(String[] args) {
        // Create an instance of QuanLySanPhamDAO
        QuanLySanPhamDAO quanLySanPhamDAO = new QuanLySanPhamDAO();

        // Create a new SanPham object with the given details
        SanPham newProduct = new SanPham("26", "LSP1", "iPhone X", 39, 52, "HinhAnh", 1);

        // Add the new product to the database
        if (quanLySanPhamDAO.add(newProduct)) {
            JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công!");
        } else {
            JOptionPane.showMessageDialog(null, "Thêm sản phẩm thất bại!");
        }

        // Close the connection
        quanLySanPhamDAO.close();
    }

    /**
     * @return the qlspConnection
     */
    public ConnectionDB getQlspConnection() {
        return qlspConnection;
    }

    /**
     * @param qlspConnection the qlspConnection to set
     */
    public void setQlspConnection(ConnectionDB qlspConnection) {
        this.qlspConnection = qlspConnection;
    }
}