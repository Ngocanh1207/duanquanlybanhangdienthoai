/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giaodienchuan.model.FrontEnd.FormQuanLy;

import javax.swing.JPanel;

/**
 *
 * @author DELL
 */
public class BanHangForm extends JPanel {

    public BanHangForm(int width, int height) {
    setLayout(null);

    // Đặt chiều cao và chiều rộng cho panel ChonSanPhamPanel
    int chonSanPhamPanelWidth = width - 555;
    int chonSanPhamPanelHeight = height - 90;
    ChonSanPhamPanel cspbh = new ChonSanPhamPanel(0, 0, chonSanPhamPanelWidth, chonSanPhamPanelHeight);
    this.add(cspbh);

    // Đặt chiều cao và chiều rộng cho panel HoaDonBanHang
    int hoaDonBanHangWidth = 550;
    int hoaDonBanHangHeight = height;
    HoaDonBanHang hdbh = new HoaDonBanHang(chonSanPhamPanelWidth, 0, hoaDonBanHangWidth, hoaDonBanHangHeight);
    this.add(hdbh);

        hdbh.setTarget(cspbh);
        cspbh.setTarget(hdbh);
    }
}
