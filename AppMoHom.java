import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class VatPham {
    String ten;
    Image hinhAnh;
    Color mauNen;

    public VatPham(String ten, Image hinhAnh, Color mauNen) {
        this.ten = ten;
        this.hinhAnh = hinhAnh;
        this.mauNen = mauNen;
    }
}

public class AppMoHom extends JFrame {
    private List<VatPham> khoDo = new ArrayList<>();
    private List<VatPham> bangChuyen = new ArrayList<>();
    private Image anhTamThoi = null;

    private JTextField txtTen;
    private JLabel lblHienThiAnh;
    private VongQuayPanel panelQuay;
    private JLabel lblKetQua;
    private JButton btnQuay;

    private Timer timer;
    private double tocDo = 0;
    private double doLechX = 0;

    public AppMoHom() {
        setTitle("App Mở Hòm Ngẫu Nhiên");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(30, 30, 30));

        // ================= PHẦN TRÊN: NHẬP LIỆU =================
        JPanel pnlNhapLieu = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        pnlNhapLieu.setBackground(new Color(40, 40, 40));
        pnlNhapLieu.setBorder(new EmptyBorder(10, 0, 10, 0)); // Tạo độ thoáng cho thanh trên

        JLabel lbl1 = new JLabel("Tên vật phẩm:");
        lbl1.setFont(new Font("Arial", Font.BOLD, 14));
        lbl1.setForeground(Color.WHITE);
        pnlNhapLieu.add(lbl1);

        txtTen = new JTextField(15);
        txtTen.setFont(new Font("Arial", Font.PLAIN, 16));
        txtTen.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 100, 100), 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        pnlNhapLieu.add(txtTen);

        JButton btnChonAnh = new JButton("TẢI ẢNH LÊN");
        btnChonAnh.setFont(new Font("Arial", Font.BOLD, 12));
        btnChonAnh.setBackground(new Color(70, 70, 70));
        btnChonAnh.setForeground(Color.WHITE);
        btnChonAnh.setFocusPainted(false);

        lblHienThiAnh = new JLabel("Chưa có ảnh");
        lblHienThiAnh.setFont(new Font("Arial", Font.ITALIC, 12));
        lblHienThiAnh.setForeground(Color.GRAY);

        JButton btnThem = new JButton("THÊM VÀO HÒM");
        btnThem.setFont(new Font("Arial", Font.BOLD, 13));
        btnThem.setBackground(new Color(0, 150, 136));
        btnThem.setForeground(Color.WHITE);
        btnThem.setFocusPainted(false);

        pnlNhapLieu.add(btnChonAnh);
        pnlNhapLieu.add(lblHienThiAnh);
        pnlNhapLieu.add(btnThem);
        add(pnlNhapLieu, BorderLayout.NORTH);

        // ================= PHẦN GIỮA: BĂNG CHUYỀN =================
        panelQuay = new VongQuayPanel();
        add(panelQuay, BorderLayout.CENTER);

        // ================= PHẦN DƯỚI: KẾT QUẢ VÀ NÚT QUAY =================
        JPanel pnlDuoi = new JPanel(new BorderLayout());
        pnlDuoi.setBackground(new Color(30, 30, 30));
        pnlDuoi.setBorder(new EmptyBorder(15, 20, 20, 20));

        lblKetQua = new JLabel("HÃY THÊM VẬT PHẨM VÀ NHẤN QUAY!", SwingConstants.CENTER);
        lblKetQua.setFont(new Font("Arial", Font.BOLD, 22));
        lblKetQua.setForeground(new Color(255, 193, 7)); // Màu vàng Gold
        lblKetQua.setBorder(new EmptyBorder(0, 0, 15, 0));
        pnlDuoi.add(lblKetQua, BorderLayout.NORTH);

        btnQuay = new JButton("MỞ HÒM NGẪU NHIÊN");
        btnQuay.setFont(new Font("Arial", Font.BOLD, 26));
        btnQuay.setBackground(new Color(255, 87, 34)); // Màu cam đậm
        btnQuay.setForeground(Color.WHITE);
        btnQuay.setFocusPainted(false);
        btnQuay.setPreferredSize(new Dimension(800, 65));
        btnQuay.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pnlDuoi.add(btnQuay, BorderLayout.SOUTH);

        add(pnlDuoi, BorderLayout.SOUTH);

        // ================= XỬ LÝ SỰ KIỆN =================
        btnChonAnh.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh (JPG, PNG)", "jpg", "png", "jpeg"));
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File fileAnh = fileChooser.getSelectedFile();
                try {
                    Image imgGoc = ImageIO.read(fileAnh);
                    // Cắt và làm mượt ảnh cho vừa khuôn 110x110
                    anhTamThoi = imgGoc.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
                    lblHienThiAnh.setText("Đã chọn ảnh");
                    lblHienThiAnh.setForeground(new Color(76, 175, 80));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Lỗi khi tải ảnh!");
                }
            }
        });

        btnThem.addActionListener(e -> {
            String ten = txtTen.getText().trim();
            if (ten.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên vật phẩm!");
                return;
            }
            
            // Tạo nền màu ngẫu nhiên nhưng tone tối, dịu mắt hơn
            Random r = new Random();
            Color mauNgauNhien = new Color(r.nextInt(100)+50, r.nextInt(100)+50, r.nextInt(100)+50);
            
            khoDo.add(new VatPham(ten, anhTamThoi, mauNgauNhien));
            
            txtTen.setText("");
            anhTamThoi = null;
            lblHienThiAnh.setText("Chưa có ảnh");
            lblHienThiAnh.setForeground(Color.GRAY);
            lblKetQua.setText("Đã thêm: " + ten + " (Tổng: " + khoDo.size() + ")");
            lblKetQua.setForeground(Color.LIGHT_GRAY);
            
            taoBangChuyenMoi();
            panelQuay.repaint();
        });

        timer = new Timer(16, e -> {
            doLechX += tocDo;     
            tocDo *= 0.985;       

            if (tocDo < 0.5) {
                tocDo = 0;
                timer.stop();
                btnQuay.setEnabled(true);
                btnQuay.setText("MỞ HÒM NGẪU NHIÊN");
                xacDinhNguoiChienThang();
            }
            panelQuay.repaint();
        });

        btnQuay.addActionListener(e -> {
            if (khoDo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Hòm đang trống! Hãy thêm vật phẩm vào trước.");
                return;
            }
            
            btnQuay.setEnabled(false);
            btnQuay.setText("ĐANG QUAY...");
            lblKetQua.setText("...");
            lblKetQua.setForeground(Color.WHITE);
            
            taoBangChuyenMoi();
            doLechX = 0;
            tocDo = 45 + new Random().nextDouble() * 25; 
            timer.start();
        });

        setLocationRelativeTo(null);
    }

    private void taoBangChuyenMoi() {
        bangChuyen.clear();
        Random rand = new Random();
        if (!khoDo.isEmpty()) {
            for (int i = 0; i < 150; i++) {
                bangChuyen.add(khoDo.get(rand.nextInt(khoDo.size())));
            }
        }
    }

    private void xacDinhNguoiChienThang() {
        int tamManHinh = panelQuay.getWidth() / 2;
        int khoangCachMoiO = 140; 

        for (int i = 0; i < bangChuyen.size(); i++) {
            int viTriX = (i * khoangCachMoiO) - (int) doLechX;
            if (tamManHinh >= viTriX && tamManHinh <= viTriX + 120) {
                VatPham trungThuong = bangChuyen.get(i);
                lblKetQua.setText("CHÚC MỪNG! BẠN NHẬN ĐƯỢC: " + trungThuong.ten.toUpperCase());
                lblKetQua.setForeground(new Color(255, 193, 7)); // Vàng Gold
                return;
            }
        }
    }

    class VongQuayPanel extends JPanel {
        public VongQuayPanel() {
            setBackground(new Color(25, 25, 25)); // Đen nhám
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int chieuRongO = 120;
            int khoangCach = 140; 
            int y = (getHeight() - chieuRongO) / 2; 

            // Vẽ khối bóng mờ (shadow) phía sau băng chuyền
            g2d.setColor(new Color(20, 20, 20));
            g2d.fillRect(0, y - 10, getWidth(), chieuRongO + 45);

            for (int i = 0; i < bangChuyen.size(); i++) {
                int viTriX = (i * khoangCach) - (int) doLechX;

                if (viTriX > -150 && viTriX < getWidth()) {
                    VatPham vp = bangChuyen.get(i);

                    // Vẽ viền ngoài
                    g2d.setColor(vp.mauNen.brighter());
                    g2d.fillRoundRect(viTriX - 2, y - 2, chieuRongO + 4, chieuRongO + 4, 15, 15);
                    
                    // Vẽ khối vuông chính
                    g2d.setColor(vp.mauNen);
                    g2d.fillRoundRect(viTriX, y, chieuRongO, chieuRongO, 15, 15);
                    
                    if (vp.hinhAnh != null) {
                        g2d.drawImage(vp.hinhAnh, viTriX + 5, y + 5, null);
                    }

                    // Tên vật phẩm (Căn giữa)
                    g2d.setColor(Color.WHITE);
                    g2d.setFont(new Font("Arial", Font.BOLD, 13));
                    FontMetrics fm = g2d.getFontMetrics();
                    int chuX = viTriX + (chieuRongO - fm.stringWidth(vp.ten)) / 2;
                    g2d.drawString(vp.ten, chuX, y + chieuRongO + 20);
                }
            }

            // Vạch đích định vị ở chính giữa màn hình (được thiết kế sắc nét hơn)
            int tamX = getWidth() / 2;
            
            // Vẽ đường Line vàng vắt ngang
            g2d.setColor(new Color(255, 215, 0, 200)); 
            g2d.setStroke(new BasicStroke(3));
            g2d.drawLine(tamX, 0, tamX, getHeight());

            // Vẽ mũi tên tam giác trên và dưới
            g2d.setColor(new Color(255, 215, 0));
            g2d.fillPolygon(new int[]{tamX - 12, tamX + 12, tamX}, new int[]{y - 15, y - 15, y + 5}, 3);
            g2d.fillPolygon(new int[]{tamX - 12, tamX + 12, tamX}, new int[]{y + chieuRongO + 15, y + chieuRongO + 15, y + chieuRongO - 5}, 3);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AppMoHom().setVisible(true);
        });
    }
}
