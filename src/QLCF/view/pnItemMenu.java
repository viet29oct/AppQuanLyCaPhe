/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package QLCF.view;
import java.awt.*;
import java.io.File;
import javax.swing.*;
import QLCF.DAO.SanPhamDAO;
import QLCF.model.SanPham;
/**
 *
 * @author NoutSpace
 */
public class pnItemMenu extends javax.swing.JPanel {
    private String maSP;
    private JLabel lblImage;
    private JLabel lblName;
    private JLabel lblPrice;
    private JButton btnEdit, btnDelete;
    private ImageIcon imageIcon;
    
    /**
     * Creates new form pnItemMenu
     */
    public pnItemMenu() {
        initComponents();
        
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(150, 220));
        setBackground(new Color(240, 240, 240));
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        // Hinh anh mon
        lblImage = new JLabel("Chọn ảnh", SwingConstants.CENTER);
        lblImage.setPreferredSize(new Dimension(150, 120));
        lblImage.setOpaque(true);
        lblImage.setBackground(Color.LIGHT_GRAY);
        lblImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chooseImage();
            }
        });

        // ten + gia
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        lblName = new JLabel("Tên món", SwingConstants.CENTER);
        lblPrice = new JLabel("Giá: 0đ", SwingConstants.CENTER);
        lblName.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(lblName);
        infoPanel.add(lblPrice);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
        btnEdit = new JButton("Sửa");
        btnDelete = new JButton("Xóa");
        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);

        add(lblImage, BorderLayout.NORTH);
        add(infoPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        // sua mon
        btnEdit.addActionListener(e -> {
            String newName = JOptionPane.showInputDialog(this, "Nhập tên món:", lblName.getText());
            if(newName == null || newName.trim().isEmpty()) return;

            String priceStr = JOptionPane.showInputDialog(this, "Nhập giá món:", lblPrice.getText().replace("Giá: ", "").replace("đ",""));
            double newGia;
            try {
                newGia = Double.parseDouble(priceStr);
            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Giá không hợp lệ!");
                return;
            }

            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(this);
            byte[] newImage = dataHA;
            if(result == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                try {
                    newImage = java.nio.file.Files.readAllBytes(file.toPath());
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }

            SanPhamDAO dao = new SanPhamDAO();
            SanPham existing = dao.getSanPhamByMaSP(this.maSP);
            String currentLoai = (existing != null) ? existing.getLoai() : "";

            SanPham sp = new SanPham();
            sp.setMaSP(this.maSP);
            sp.setTenSP(newName);
            sp.setGia(newGia);
            sp.setLoai(currentLoai);
            sp.setHinhanh(newImage);

            boolean ok = dao.updateSanPham(sp);
            if (ok) {
                setTen(newName);
                setGia(newGia);
                setAnh(newImage);
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
            }
        });

        // xoa mon
        btnDelete.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,"Bạn có chắc muốn xóa món này?","Xác nhận",JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {

                SanPhamDAO dao = new SanPhamDAO();
                dao.deleteSanPham(maSP);

                Container parent = this.getParent();
                if (parent != null) {
                    parent.remove(this);
                    parent.revalidate();
                    parent.repaint();
                }
            }
        });
    }
    
    private byte[] dataHA;
    
    private void chooseImage() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                dataHA = java.nio.file.Files.readAllBytes(file.toPath());
                ImageIcon icon = new ImageIcon(file.getAbsolutePath());
                Image img = icon.getImage().getScaledInstance(150, 120, Image.SCALE_SMOOTH);
                lblImage.setText("");
                lblImage.setIcon(new ImageIcon(img));
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showConfirmDialog(this, "Lỗi đọc ảnh !", "Hệ thống", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public void setId(String id){
        this.maSP = id;
    }

    public void setTen(String ten) {
        lblName.setText(ten);
    }

    public void setGia(double gia) {
        lblPrice.setText("Giá: " + gia + "đ");
    }

    public void setAnh(byte[] dataHA) {
        if (dataHA != null) {
            ImageIcon icon = new ImageIcon(dataHA);
            Image img = icon.getImage().getScaledInstance(150, 120, Image.SCALE_SMOOTH);
            lblImage.setText("");
            lblImage.setIcon(new ImageIcon(img));
        } else {
            lblImage.setText("Không có ảnh");
            lblImage.setIcon(null);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
