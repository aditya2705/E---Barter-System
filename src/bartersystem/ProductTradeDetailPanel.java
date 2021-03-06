/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bartersystem;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Sumeet
 */
public class ProductTradeDetailPanel extends javax.swing.JPanel {

    
    ProgramFrame programFrame;
    int productIndex,traderIndex,userIndex;
    UserDatabaseAccess userAccess;
    /**
     * Creates new form ProductDetailPanel
     * @param programFrame
     * @param productIndex
     * @param traderIndex
     * @param userIndex
     */
    public ProductTradeDetailPanel(ProgramFrame programFrame, int productIndex,int traderIndex,int userIndex) {
        
        this.programFrame = programFrame;
        this.productIndex = productIndex;
        this.traderIndex = traderIndex;
        this.userIndex = userIndex;
        userAccess = new UserDatabaseAccess();
        initComponents();
        setComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProductName = new javax.swing.JLabel();
        jProductCategory = new javax.swing.JLabel();
        jTraderName = new javax.swing.JLabel();
        jTraderEmail = new javax.swing.JLabel();
        jQuantity = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jExpectationText = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jProductDescription = new javax.swing.JLabel();
        jTradeButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jOfferedItemBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jViewImage = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(400, 300));

        jProductName.setText("Product Name");

        jProductCategory.setText("Category : ");

        jTraderName.setText("Trader:");

        jTraderEmail.setText("Trader email-id : ");

        jQuantity.setText("Quantity Available :");

        jLabel7.setText("Trader Expects :");

        jExpectationText.setText("expectation text");
        jExpectationText.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel6.setText("Description :");

        jProductDescription.setText("description text");
        jProductDescription.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jTradeButton.setText("Notify");
        jTradeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTradeButtonActionPerformed(evt);
            }
        });

        jOfferedItemBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jOfferedItemBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOfferedItemBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Your offered item :");

        jViewImage.setText("View Image");
        jViewImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jViewImageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jOfferedItemBox, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(67, 67, 67)
                        .addComponent(jTradeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jProductCategory)
                            .addComponent(jProductName)
                            .addComponent(jLabel6)
                            .addComponent(jProductDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jViewImage))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jQuantity)
                            .addComponent(jTraderEmail)
                            .addComponent(jTraderName)
                            .addComponent(jLabel7)
                            .addComponent(jExpectationText, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(30, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTraderName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTraderEmail)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel6))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jQuantity))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jProductName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jProductCategory)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(jProductDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jViewImage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jExpectationText, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(17, 17, 17)
                        .addComponent(jOfferedItemBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTradeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTradeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTradeButtonActionPerformed
        // TODO add your handling code here:
        NotifyTrader();
    }//GEN-LAST:event_jTradeButtonActionPerformed

    private void jOfferedItemBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOfferedItemBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jOfferedItemBoxActionPerformed

    private void jViewImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jViewImageActionPerformed
        try {
            // TODO add your handling code here:
            displayImage();
        } catch (IOException ex) {
            Logger.getLogger(ProductTradeDetailPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jViewImageActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jExpectationText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JComboBox jOfferedItemBox;
    private javax.swing.JLabel jProductCategory;
    private javax.swing.JLabel jProductDescription;
    private javax.swing.JLabel jProductName;
    private javax.swing.JLabel jQuantity;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jTradeButton;
    private javax.swing.JLabel jTraderEmail;
    private javax.swing.JLabel jTraderName;
    private javax.swing.JButton jViewImage;
    // End of variables declaration//GEN-END:variables

    private void setComponents() {
        jProductName.setText(userAccess.getProductName(productIndex));
        jProductCategory.setText("Category : "+userAccess.getProductCategory(productIndex));
        jProductDescription.setText(userAccess.getProductDescription(productIndex));
        jExpectationText.setText(userAccess.getProductExpectation(productIndex));
        jTraderName.setText("Trader: "+userAccess.getUserName(traderIndex));
        jTraderEmail.setText("Trader email-id : "+userAccess.getUserEmail(traderIndex));
        jQuantity.setText("Quantity available : "+userAccess.getQuantity(productIndex));
        setUserItems();
    }

    private void NotifyTrader() {
        
        int offeredIndex = userAccess.getProductId(jOfferedItemBox.getSelectedIndex(),userIndex);
        
        if(userAccess.checkNotificationNotAlreadySent(userIndex,traderIndex,productIndex)){
        userAccess.sendNotification(userIndex,traderIndex,productIndex,offeredIndex);
        JOptionPane.showMessageDialog(null, "Trader Notified");
        }
    }

    private void setUserItems() {
        jOfferedItemBox.removeAllItems();
        
        String[] items = userAccess.getUserItems(userIndex);
        
        int i = 0;
        while(i<items.length){
        jOfferedItemBox.addItem(items[i++]);
        }
        
        
    }
    
    private void displayImage() throws IOException {
        BufferedImage bImage = userAccess.getProductImage(productIndex);
        JOptionPane.showMessageDialog(null,new ImageDisplayPanel(bImage),"Product Image",JOptionPane.PLAIN_MESSAGE);
    }
}
