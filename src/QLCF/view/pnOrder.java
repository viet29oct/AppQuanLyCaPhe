/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package QLCF.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import QLCF.DAO.BanDAO;
import QLCF.DAO.CaLamDAO;
import QLCF.DAO.DetailOrderDAO;
import QLCF.DAO.DoanhThuDAO;
import QLCF.DAO.OrderDAO;
import QLCF.DAO.SanPhamDAO;
import QLCF.model.Ban;
import QLCF.model.DetailOrder;
import QLCF.model.SanPham;

/**
 *
 * @author Admin
 */
public class pnOrder extends javax.swing.JPanel {
    
    private Ban banDangChon = null;
    private final DefaultTableModel modelOrder;
    private Integer currentOrderId = null;
    private boolean isDataChanged = false;
    private final DecimalFormat formatter = new DecimalFormat("#,###"); 
    
    /**
     * Creates new form pnOrder
     */
    public pnOrder() {
        initComponents();
        
        pnContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
        pnContainer.setPreferredSize(new Dimension(400, 350));
        loadBan();
        tblSanPham.setDefaultEditor(Object.class, null);
        loadTableSanPham();
        
        modelOrder = (DefaultTableModel) tblOrder.getModel();
        modelOrder.setRowCount(0);
        
        TableColumnModel columnModelSanPham = tblSanPham.getColumnModel();
        columnModelSanPham.getColumn(0).setPreferredWidth(100);
        columnModelSanPham.getColumn(1).setPreferredWidth(250);
        columnModelSanPham.getColumn(2).setPreferredWidth(100);
        columnModelSanPham.getColumn(3).setPreferredWidth(100);
        
        TableColumnModel columnModelOrder = tblOrder.getColumnModel();
        columnModelOrder.getColumn(0).setPreferredWidth(80);
        columnModelOrder.getColumn(1).setPreferredWidth(200);
        columnModelOrder.getColumn(2).setPreferredWidth(80);
        columnModelOrder.getColumn(3).setPreferredWidth(100);
        columnModelOrder.getColumn(4).setPreferredWidth(120);
        
        tblSanPham.setRowHeight(30);
        tblOrder.setRowHeight(30);
        
    }
    
    private void loadBan(){
        pnContainer.removeAll();
        BanDAO dao = new BanDAO();
        List<Ban> list = dao.getAllBan();
        
        for(Ban b : list){
            JButton btn = new JButton();
            
            btn.setPreferredSize(new Dimension(100, 60));
            btn.setLayout(new BorderLayout());
            
            JPanel p = new JPanel(new GridLayout(2, 1));
            JLabel lbTen = new JLabel(b.getTenBan(), SwingConstants.CENTER);
            JLabel lbTrangThai = new JLabel(b.getTrang_thai(), SwingConstants.CENTER);

            lbTen.setFont(new Font("Segoe UI", Font.BOLD, 14));
            lbTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            
            p.setOpaque(false);
            p.add(lbTen);
            p.add(lbTrangThai);
            
            btn.add(p, BorderLayout.CENTER);
            
            if(b.getTrang_thai().equals("Trống")){
                btn.setBackground(Color.cyan);
            }else{
                btn.setBackground(Color.red);
            }
            
            btn.addActionListener(e -> {
                banDangChon = b;
                
                lbBan.setText("Đang ở: " + b.getTenBan());
                
                OrderDAO orderDAO = new OrderDAO();
                if(b.getTrang_thai().equals("Có người")){
                    currentOrderId = orderDAO.getCurrentOrderIdByBan(b.getMaBan());
                    if(currentOrderId != null){
                        loadTableOrder(currentOrderId);
                        orderDAO.updateBanTrangThai(b.getMaBan(), "Có người");
                    }else{
                        modelOrder.setRowCount(0);
                        txtTongTien.setText("");
                    }
                } else{
                    currentOrderId = null;
                    modelOrder.setRowCount(0);
                    txtTongTien.setText("");
                }
                
                isDataChanged = false;
                
                JOptionPane.showMessageDialog(this, "Bạn chọn: " + b.getTenBan());
            });
            
            pnContainer.add(btn);
        }
        
        pnContainer.revalidate();
        pnContainer.repaint();
    }
    

    private void loadTableSanPham(){
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);
        
        SanPhamDAO dao = new SanPhamDAO();
        List<SanPham> list = dao.getAllSanPham();
        
        for(SanPham sp : list){
            String giaFormat = formatter.format(sp.getGia());
            model.addRow(new Object[]{
                sp.getMaSP(),
                sp.getTenSP(),
                giaFormat,
                sp.getLoai()
            });
        }
    }
    
    private void loadTableOrder(int orderId){
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();
        model.setRowCount(0);
        
        OrderDAO dao = new OrderDAO();
        List<DetailOrder> list = dao.getOrderDetailByOrderId(orderId);
        
        double tongTien = 0;
        
        for(DetailOrder d : list){
            SanPham sanPham = d.getSanPham();
            String tenSP = sanPham.getTenSP();
            double gia = sanPham.getGia();
            String giaFormat = formatter.format(gia);
            String thanhTienFormat = formatter.format(d.getThanh_tien());
            model.addRow(new Object[]{
                d.getMaOrder(),
                tenSP,
                d.getSo_luong(),
                giaFormat,
                thanhTienFormat
            });
            tongTien += d.getThanh_tien();
        }
        
        txtTongTien.setText(formatter.format((long) tongTien));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnContainer = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrder = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        btnXoa = new javax.swing.JButton();
        BtnThanhToan = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        lbBan = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1147, 761));

        pnContainer.setBackground(new java.awt.Color(222, 184, 135));

        javax.swing.GroupLayout pnContainerLayout = new javax.swing.GroupLayout(pnContainer);
        pnContainer.setLayout(pnContainerLayout);
        pnContainerLayout.setHorizontalGroup(
            pnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        pnContainerLayout.setVerticalGroup(
            pnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách chi tiết Order", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        tblOrder.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tblOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã Order", "Tên sản phẩm", "Số lượng", "Giá", "Thành tiền"
            }
        ));
        jScrollPane1.setViewportView(tblOrder);

        jScrollPane2.setViewportView(jScrollPane1);

        jScrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N

        tblSanPham.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Giá", "Loại"
            }
        ));
        jScrollPane3.setViewportView(tblSanPham);

        jScrollPane4.setViewportView(jScrollPane3);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Tổng tiền");

        txtTongTien.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        btnXoa.setBackground(new java.awt.Color(255, 51, 51));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoa.setText("Xoá món");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        BtnThanhToan.setBackground(new java.awt.Color(51, 255, 51));
        BtnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnThanhToan.setText("Thanh toán");
        BtnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnThanhToanActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(153, 255, 153));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setText("Thêm món");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        lbBan.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lbBan.setText("Đang ở: ");

        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReset.setText("Chuyển bàn");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbBan, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(btnThem)))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(BtnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel1)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbBan, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblOrder.getModel();        
        int selectedRow = tblOrder.getSelectedRow();
        
        if(selectedRow == -1){
            JOptionPane.showMessageDialog(this, "Chon mot dong de xoa");
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, "Ban co chac chan muon xoa dong nay khong?", "Xac nhan", JOptionPane.YES_NO_OPTION);
        
        if(confirm == JOptionPane.YES_NO_OPTION){
            model.removeRow(selectedRow);
            isDataChanged = true;
        }
        isDataChanged = true;
        tinhLaiTongTien();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if(banDangChon == null){
            JOptionPane.showMessageDialog(this, "Chọn bàn trước !");
            return;
        }
        
        int row = tblSanPham.getSelectedRow();
        if(row < 0){
            JOptionPane.showMessageDialog(this, "Chọn sản phẩm !");
            return;
        }
        
        int maBan = banDangChon.getMaBan();
        
        OrderDAO dao = new OrderDAO();
        
        currentOrderId = dao.getCurrentOrderIdByBan(maBan);
        if(currentOrderId == null){
            currentOrderId = dao.createOrder(maBan);
            BanDAO banDao = new BanDAO();
            banDao.updateTrangThai(maBan, "Có người");
        }        
        
        String tenSP = (String) tblSanPham.getValueAt(row, 1);
        double donGia;
        try {
            String giaStr = tblSanPham.getValueAt(row, 2).toString();
            donGia = formatter.parse(giaStr).doubleValue();
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Lỗi chuyển đổi giá sản phẩm: " + e.getMessage());
            e.printStackTrace();
            return;
        }
        
        int soLuong = 1;
        
        boolean found = false;
        for(int i=0; i < modelOrder.getRowCount(); i++){
            String tenSPTrongBang = modelOrder.getValueAt(i, 1).toString();
            if(tenSPTrongBang.equals(tenSP)){
                int soLuongCu = (int) modelOrder.getValueAt(i, 2);
                soLuongCu++;
                modelOrder.setValueAt(soLuongCu, i, 2);
                double thanhTien = soLuongCu * donGia;
                modelOrder.setValueAt(formatter.format(thanhTien), i, 4);
                found = true;
                break;
            }
        }
        
        if(!found){
            double thanhTien = soLuong * donGia;
            modelOrder.addRow(new Object[]{
                currentOrderId != null ? currentOrderId : currentOrderId, tenSP, soLuong, formatter.format(donGia), formatter.format(thanhTien)
            });
        }
        
        tinhLaiTongTien();
        isDataChanged  = true;
    }//GEN-LAST:event_btnThemActionPerformed

    private void BtnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnThanhToanActionPerformed
        if(banDangChon == null){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn bàn trước!");
            return;
        }
        if(currentOrderId == null || txtTongTien.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Không có Order nào để thanh toán!");
            return;
        }
        
        double tongTien;
        try {
            tongTien = formatter.parse(txtTongTien.getText()).doubleValue();
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Lỗi chuyển đổi tổng tiền: " + e.getMessage());
            e.printStackTrace();
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Tổng tiền: " + txtTongTien.getText() + " VND\nBạn có chắc chắc muốn thanh toán?", 
                "Xác nhận thanh toán", JOptionPane.YES_NO_OPTION);
        
        if(confirm == JOptionPane.YES_OPTION){
            try{
                DoanhThuDAO doanhThuDAO = new DoanhThuDAO();
                CaLamDAO caLamDAO = new CaLamDAO();
                int maCa = caLamDAO.getCurrentCa();
                
                int maNV = caLamDAO.getMaNVBymaCa(maCa);
                if(maNV == -1){
                    JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên cho ca hiện tại");
                    return;
                }
                doanhThuDAO.insertDoanhThu(currentOrderId, tongTien, maNV, maCa);
                
                OrderDAO orderDAO = new OrderDAO();
                orderDAO.updateOrderTrangThai(currentOrderId, "Đã thanh toán");
                
                BanDAO banDao = new BanDAO();
                banDao.updateTrangThai(banDangChon.getMaBan(), "Trống");
                
                modelOrder.setRowCount(0);
                txtTongTien.setText("");
                banDangChon = null;
                currentOrderId = null;
                isDataChanged = false;
                lbBan.setText("Đang ở:");
                loadBan();
                
                JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi thanh toán: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_BtnThanhToanActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        
        if(banDangChon == null){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn bàn trước để lưu Order!");
            return;
        }
        
        int result = JOptionPane.showConfirmDialog(this, "Bạn có muốn lưu order của bàn trước đó không?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if(result == JOptionPane.CANCEL_OPTION || result == JOptionPane.CLOSED_OPTION){
            return;
        }
        
        if(result == JOptionPane.YES_OPTION){
            try {
                DetailOrderDAO dao = new DetailOrderDAO();
                SanPhamDAO spdao = new SanPhamDAO();
                
                dao.deleteDetailByOrderId(currentOrderId);

                for(int i=0; i < modelOrder.getRowCount(); i++){
                    int maOrder = currentOrderId;
                    String tenSP = modelOrder.getValueAt(i, 1).toString();
                    int soLuong = Integer.parseInt(modelOrder.getValueAt(i, 2).toString());
                    
                    double donGia;
                    double thanhTien;
                    
                    try {
                        String giaStr = modelOrder.getValueAt(i, 3).toString();
                        donGia = formatter.parse(giaStr).doubleValue();
                        
                        String thanhTienStr = modelOrder.getValueAt(i, 4).toString();
                        thanhTien = formatter.parse(thanhTienStr).doubleValue();
                        
                    } catch (ParseException e) {
                        JOptionPane.showMessageDialog(this, "Lỗi chuyển đổi giá/thành tiền khi lưu Order: " + e.getMessage());
                        e.printStackTrace();
                        continue;
                    }
                    
                    String maSP = spdao.getMaSPByTen(tenSP);
                    if(maSP == null){
                        JOptionPane.showMessageDialog(this, "Không tìm thấy mã sản phẩm đã cho: " + tenSP);
                        continue;
                    }

                    dao.insertDetailOrder(maOrder, maSP, tenSP, soLuong, donGia, thanhTien);
                    
                }
                
                if(banDangChon != null){
                     OrderDAO oddao = new OrderDAO();
                     oddao.updateBanTrangThai(banDangChon.getMaBan(), "Có người");
                }
                
                JOptionPane.showMessageDialog(this, "Dữ liệu đã được lưu thành công!");
                isDataChanged  = false;
            
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi lưu dữ liệu!");
                e.printStackTrace();
            }
            
        }else if(result == JOptionPane.NO_OPTION){
        }
        
        modelOrder.setRowCount(0);
        currentOrderId = null;
        banDangChon = null;
        lbBan.setText("Đang ở:");
        txtTongTien.setText("");
        loadBan();
    }//GEN-LAST:event_btnResetActionPerformed

    private void tinhLaiTongTien() {
        double tongTien = 0;
        for(int i = 0; i < modelOrder.getRowCount(); i++){
            try {
                String thanhTienStr = modelOrder.getValueAt(i, 4).toString();
                tongTien += formatter.parse(thanhTienStr).doubleValue();
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Lỗi tính tổng tiền: " + e.getMessage());
                e.printStackTrace();
                return;
            }
        }
        txtTongTien.setText(formatter.format((long) tongTien));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnThanhToan;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lbBan;
    private javax.swing.JPanel pnContainer;
    private javax.swing.JTable tblOrder;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}