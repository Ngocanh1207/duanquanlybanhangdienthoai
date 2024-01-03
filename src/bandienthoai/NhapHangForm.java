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
public class NhapHangForm extends JPanel {

    public NhapHangForm(int width, int height) {
        setLayout(null);

        // Chọn chiều rộng và chiều cao cho panel ChonSanPhamPanel
        int chonSanPhamPanelWidth = width - 555;
        int chonSanPhamPanelHeight = height - 90;

        ChonSanPhamPanel cspnh = new ChonSanPhamPanel(0, 0, chonSanPhamPanelWidth, chonSanPhamPanelHeight);
        this.add(cspnh);

        // Chọn chiều rộng và chiều cao cho panel PhieuNhapHang
        int phieuNhapHangWidth = 550;
        int phieuNhapHangHeight = height;

        PhieuNhapHang pnh = new PhieuNhapHang(chonSanPhamPanelWidth, 0, phieuNhapHangWidth, phieuNhapHangHeight);
        this.add(pnh);

        pnh.setTarget(cspnh);
        cspnh.setTarget(pnh);
    }
}
