package views;

import com.mysql.cj.xdevapi.Result;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class Main_windows extends javax.swing.JFrame {

    public Main_windows() {
        initComponents();        
    }
    
    String ImagePath = null;
    
    public Connection getConnection(){
        Connection conn = null;
        try{            
            conn = DriverManager.getConnection("jdbc:mysql://localhost/inventory_managment","database_user","database_user_password");
            //JOptionPane.showMessageDialog(null, "Connection");
            return conn;
        } catch (SQLException ex){
            //JOptionPane.showMessageDialog(null, "Not Connection");
            return null;
        }      
    }
    
    public ImageIcon ResizeImage(String imagePath, byte[] pic){
        ImageIcon myImage = null;
        
        if(imagePath != null){
            myImage = new ImageIcon(imagePath);
        } else{
            myImage = new ImageIcon(pic);
        }
        
        Image img = myImage.getImage();
        img = img.getScaledInstance(lblImg.getWidth(), lblImg.getHeight(), Image.SCALE_SMOOTH);
        
        ImageIcon image = new ImageIcon(img);
        return image;
    }
    
    private boolean checkInput(){
        if(txtName.getText() == null || txtName.getText() == null || txtAddDate.getText() == null){
            return false;
        }else{
            try{
                Float.parseFloat(txtPrice.getText());
                return true;
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Price Error");
                return false;
            }
        }
    }
    
    public ArrayList<Product> getProductList(){        
        ArrayList<Product> productList = new ArrayList<>();
        Connection conn = getConnection();
        
        String Qurey = "SELECT * FROM product;";
        
        Statement st;
        ResultSet rs;
        Product product;        
        try {
            st = conn.createStatement();
            rs = st.executeQuery(Qurey);            
            while (rs.next()) {
                product = new Product(rs.getInt("id"), 
                        rs.getString("name"), 
                        rs.getFloat("Price"), 
                        rs.getString("add_date"), 
                        rs.getBytes("image"));
                productList.add(product);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        } 
        return productList;
    }
    
    public void showProductListInTable(){
        ArrayList<Product> list = getProductList();
        DefaultTableModel model = (DefaultTableModel)TablePrduct.getModel();
        model.setRowCount(0);
        Object[] row = new Object[4];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getName();
            row[2] = list.get(i).getPrice();
            row[3] = list.get(i).getAddDate();
            
            model.addRow(row);
        }
    }
    
    public void showProduct(int index){
        txtId.setText(Integer.toString(getProductList().get(index).getId()));
        txtName.setText(getProductList().get(index).getName());
        txtPrice.setText(Float.toString(getProductList().get(index).getPrice()));
        txtAddDate.setText(getProductList().get(index).getAddDate());
        lblImg.setIcon(ResizeImage(null, getProductList().get(index).getPicture()));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtAddDate = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        lblImg = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablePrduct = new javax.swing.JTable();
        btnChoose = new javax.swing.JButton();
        btnInsert = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Poppins", 0, 20)); // NOI18N
        jLabel1.setText("ID :");

        txtId.setFont(new java.awt.Font("Poppins", 0, 20)); // NOI18N
        txtId.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 20)); // NOI18N
        jLabel2.setText("Add Date :");

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 20)); // NOI18N
        jLabel3.setText("Name :");

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 20)); // NOI18N
        jLabel4.setText("Price :");

        jLabel5.setFont(new java.awt.Font("Poppins", 0, 20)); // NOI18N
        jLabel5.setText("Image :");

        txtAddDate.setFont(new java.awt.Font("Poppins", 0, 20)); // NOI18N

        txtName.setFont(new java.awt.Font("Poppins", 0, 20)); // NOI18N

        lblImg.setFont(new java.awt.Font("Poppins", 0, 20)); // NOI18N
        lblImg.setOpaque(true);

        txtPrice.setFont(new java.awt.Font("Poppins", 0, 20)); // NOI18N

        TablePrduct.setFont(new java.awt.Font("Poppins", 0, 20)); // NOI18N
        TablePrduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Price", "Add Date"
            }
        ));
        TablePrduct.setOpaque(false);
        TablePrduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablePrductMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablePrduct);

        btnChoose.setFont(new java.awt.Font("Poppins", 0, 20)); // NOI18N
        btnChoose.setText("Choose Image");
        btnChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseActionPerformed(evt);
            }
        });

        btnInsert.setFont(new java.awt.Font("Poppins", 0, 20)); // NOI18N
        btnInsert.setText("Insert");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Poppins", 0, 20)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Poppins", 0, 20)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnChoose, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtName)
                            .addComponent(lblImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtAddDate, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 739, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(107, 723, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtAddDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnChoose))
                    .addComponent(jScrollPane1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsert)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseActionPerformed
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        FileNameExtensionFilter FNEF = new FileNameExtensionFilter("*.images", "jpg", "png");
        file.addChoosableFileFilter(FNEF);
        int result = file.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            lblImg.setIcon(ResizeImage(path, null));
            ImagePath = path;
        }else{
            JOptionPane.showMessageDialog(null, "No selected items");
        }
    }//GEN-LAST:event_btnChooseActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        String quary = null;
        if(checkInput() == true && ImagePath != null){
            try {
                Connection conn = getConnection();
                quary = "INSERT INTO `product` (`id`, `name`, `price`, `add_date`, `image`) "
                        + "VALUES (NULL,?,?,?,?);";
                PreparedStatement ps = conn.prepareStatement(quary);
                ps.setString(1, txtName.getText());
                ps.setString(2, txtPrice.getText());
                ps.setString(3, txtAddDate.getText());
                
                InputStream img = new FileInputStream(ImagePath);
                ps.setBlob(4, img);
                ps.executeUpdate();
                showProductListInTable();
                
                JOptionPane.showMessageDialog(null, "Adding succsess !");
            } catch (FileNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());                
            }
        }else{
            JOptionPane.showMessageDialog(null, "One or More Field are empty");
        }
        showProductListInTable();
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        String updateQuary = null;
        InputStream img = null;
                
        if(checkInput() == true && txtId.getText() != null){
            try {
            Connection conn = getConnection();
            
            if(ImagePath != null){
                try {
                    img = new FileInputStream(ImagePath);                    
                } catch (FileNotFoundException ex) {
                }
            }else{
                
            }
            
            updateQuary = "UPDATE product SET name=?,price=?,add_date=?,image=? WHERE id=?;";
            PreparedStatement ps = conn.prepareStatement(updateQuary);
            ps.setString(1, txtName.getText());
            ps.setString(2, txtPrice.getText());
            ps.setString(3, txtAddDate.getText());
            ps.setBlob(4, img);
            ps.setString(5, txtId.getText());
            ps.executeUpdate();
            showProductListInTable();
            } catch (SQLException ex) {
            
            }
        }else{
            
        }
        
 
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try {
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement("DELETE FROM `product` WHERE `id`=?");
                int id = Integer.parseInt(txtId.getText());
                ps.setInt(1, id);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Product delete");
                showProductListInTable();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "can not product delete");
            }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        showProductListInTable();
    }//GEN-LAST:event_formWindowOpened

    private void TablePrductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablePrductMouseClicked
        int index = TablePrduct.getSelectedRow();
        showProduct(index);
    }//GEN-LAST:event_TablePrductMouseClicked

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main_windows.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_windows.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_windows.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_windows.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_windows().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablePrduct;
    private javax.swing.JButton btnChoose;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImg;
    private javax.swing.JTextField txtAddDate;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPrice;
    // End of variables declaration//GEN-END:variables
}
