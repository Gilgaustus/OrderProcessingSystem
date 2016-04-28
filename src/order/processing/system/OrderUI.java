package order.processing.system;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderUI extends javax.swing.JFrame {

    private OrderListCntl OLC;
    private TransferListCntl TLC;
    private OrderTableModel OTM;
    ArrayList<Order> customerOrderData = new ArrayList<>();
    private int customerID;
    private int selectedItem;
    private boolean performingTransfer;
    
    public OrderUI(OrderListCntl inputOLC, int inputCustomerID) throws SQLException 
    {
        OLC = inputOLC;
        customerID = inputCustomerID;
        OLC.getCustomerOrders(customerOrderData, customerID);
        OTM = new OrderTableModel(customerOrderData);
        performingTransfer = false;
        selectedItem = -1;
        initComponents();
    }
    
    public OrderUI(TransferListCntl inputTLC, OrderListCntl inputOLC, int inputCustomerID, int inputSelectedItem) throws SQLException
    {
        TLC = inputTLC;
        OLC = inputOLC;
        customerID = inputCustomerID;
        selectedItem = inputSelectedItem;
        performingTransfer = true;
        OLC.getCustomerOrders(customerOrderData, customerID);
        OTM = new OrderTableModel(customerOrderData);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        orderTable = new javax.swing.JTable();
        orderDetailButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        orderTable.setModel(OTM);
        jScrollPane1.setViewportView(orderTable);

        orderDetailButton.setText("View Order");
        orderDetailButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderDetailButtonActionPerformed(evt);
            }
        });

        closeButton.setText("X");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(orderDetailButton)
                .addGap(214, 214, 214))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeButton))
                .addGap(18, 18, 18)
                .addComponent(orderDetailButton)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void orderDetailButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderDetailButtonActionPerformed
        int selectedTableRow = orderTable.getSelectedRow();
        int selectedModelRow = orderTable.convertRowIndexToModel(selectedTableRow);
        OrderDetailUI orderDetailUI = new OrderDetailUI(TLC, customerOrderData.get(selectedModelRow).getOrderID(), selectedItem, customerOrderData.get(selectedModelRow).getCart().getCartList(), performingTransfer);
        orderDetailUI.setVisible(true);
    }//GEN-LAST:event_orderDetailButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton orderDetailButton;
    private javax.swing.JTable orderTable;
    // End of variables declaration//GEN-END:variables
}