/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concertticketsadmin;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

 /**
 * Αλίκη Δαγλή 44841
 * Ανδρέας Μπελόκας 40443
 * Γιώργος ΣΥΡΑΝΙΔΗΣ 39280
 **/
public class AdminFrame extends javax.swing.JFrame {

    private static TicketsAdmin admin;

    /**
     * Creates new form AdminFrame
     */
    public AdminFrame() {
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

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        concertComboBox = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        hallLabel = new javax.swing.JLabel();
        performerLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        dateTable = new javax.swing.JTable();
        addDateButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        hallComboBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tierTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        addTierButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        newButton = new javax.swing.JButton();
        removeTierButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(onClose());
        setTitle("Admin");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                onClose(evt);
            }
        });

        jTabbedPane2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane2StateChanged(evt);
            }
        });

        jLabel1.setText("Concert Title");

        List<String> concerts = admin.getConcerts();
        String[] concertTittles = new String[concerts.size()];
        for(int i = 0 ; i<concerts.size();i++){
            concertTittles[i] = concerts.get(i).split(":")[0];
        }
        concertComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(concertTittles));
        concertComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                concertComboBoxActionPerformed(evt);
            }
        });

        jLabel4.setText("Performer/s");

        jLabel5.setText("Dates :");

        jLabel7.setText("Hall");

        hallLabel.setText(concerts.size()>0?concerts.get(0).split(":")[1]:"");

        performerLabel.setText(concerts.size()>0?concerts.get(0).split(":")[2]:"");

        dateTable.setModel(new javax.swing.table.DefaultTableModel(
            admin.gatDates(concertComboBox.getItemAt(0)),
            new String [] {
                "Day", "Month", "Year", "Time"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(dateTable);

        addDateButton.setText("Add Date");
        addDateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(concertComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(hallLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(performerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5))
                        .addGap(0, 162, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(addDateButton)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(concertComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(hallLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(performerLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addDateButton)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Concerts", jPanel2);

        jLabel2.setText("Tiers :");

        hallComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(admin.getHalls()));
        hallComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hallComboBoxActionPerformed(evt);
            }
        });

        tierTable.setModel(new javax.swing.table.DefaultTableModel(
            admin.getTiers(hallComboBox.getItemAt(0)),
            new String [] {
                "Tier", "No Seats", "Price" , "Numbered"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tierTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tierTable);

        jLabel3.setText("Hall Name");

        addTierButton.setText("Add Tier");
        addTierButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTierButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(addTierButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hallComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(hallComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addTierButton)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Concert Halls", jPanel3);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            admin.getStatistics(),
            new String [] {
                "Concert", "No Performances", "Completion Average"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Statistics", jPanel1);

        newButton.setText("New Concert");
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        removeTierButton1.setText("Remove");
        removeTierButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeTierButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(newButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(removeTierButton1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newButton)
                    .addComponent(removeTierButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hallComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hallComboBoxActionPerformed
        tierTableRefresh();
    }//GEN-LAST:event_hallComboBoxActionPerformed

    private void onClose(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_onClose
        try {
            admin.exit();

        } catch (IOException ex) {
            Logger.getLogger(AdminFrame.class
                    .getName()).log(Level.SEVERE, null, ex);

        }
    }//GEN-LAST:event_onClose

    private void concertComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_concertComboBoxActionPerformed
        dateTable.setModel(new javax.swing.table.DefaultTableModel(
                admin.gatDates(concertComboBox.getSelectedItem()),
                new String[]{
                    "Day", "Month", "Year", "Time"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

        });
    }//GEN-LAST:event_concertComboBoxActionPerformed

    JCheckBox numbered;
    JTextField seatsfield;
    private void addTierButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTierButtonActionPerformed
        JTextField namefield = new JTextField(3);
        seatsfield = new JTextField(3);
        JTextField pricefield = new JTextField(5);
        numbered = new JCheckBox("Numbered");

        JComboBox<String> hallbox = new JComboBox<>(hallComboBox.getModel());
        JFrame frame = new JFrame();
        frame.setSize(300, 10000);
        JPanel myPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        myPanel.add(new JLabel("Tier Name :"));
        myPanel.add(namefield);
        myPanel.add(Box.createVerticalStrut(5));
        myPanel.add(new JLabel("No Seats :"));
        myPanel.add(seatsfield);
        myPanel.add(Box.createVerticalStrut(5));
        myPanel.add(new JLabel("Price :"));
        myPanel.add(pricefield);
        myPanel.add(Box.createVerticalStrut(5));
        myPanel.add(numbered);
        int result = JOptionPane.showConfirmDialog(null, myPanel, "Enter New Concert Date", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION)
            if (admin.addTier((String) hallComboBox.getSelectedItem(), namefield.getText(), seatsfield.getText() , pricefield.getText(), numbered.isSelected() ? "true" : "false"))
                tierTableRefresh();
    }//GEN-LAST:event_addTierButtonActionPerformed

    private void addDateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDateButtonActionPerformed
        JTextField dayfield = new JTextField(3);
        JTextField monthfield = new JTextField(3);
        JTextField yearfield = new JTextField(5);
        JTextField hourfield = new JTextField(3);
        JTextField minutefield = new JTextField(3);

        JComboBox<String> hallbox = new JComboBox<>(hallComboBox.getModel());
        JFrame frame = new JFrame();
        frame.setSize(300, 10000);
        JPanel myPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        myPanel.add(new JLabel("Day :"));
        myPanel.add(dayfield);
        myPanel.add(Box.createVerticalStrut(5));
        myPanel.add(new JLabel("Month:"));
        myPanel.add(monthfield);
        myPanel.add(Box.createVerticalStrut(5));
        myPanel.add(new JLabel("Year:"));
        myPanel.add(yearfield);
        myPanel.add(Box.createVerticalStrut(5));
        myPanel.add(new JLabel("Hour:"));
        myPanel.add(hourfield);
        myPanel.add(Box.createVerticalStrut(5));
        myPanel.add(new JLabel("Minutes:"));
        myPanel.add(minutefield);
        int result = JOptionPane.showConfirmDialog(null, myPanel, "Enter New Concert Date", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION)
            if (admin.adDDate((String) concertComboBox.getSelectedItem(), dayfield.getText(), monthfield.getText(), yearfield.getText(), hourfield.getText() + ":" + minutefield.getText()))
                dateTableRefresh();
    }//GEN-LAST:event_addDateButtonActionPerformed

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        if (jTabbedPane2.getSelectedIndex() == 0)
            addConcert();
        else if (jTabbedPane2.getSelectedIndex() == 1)
            addHall();
    }//GEN-LAST:event_newButtonActionPerformed

    private void removeTierButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeTierButton1ActionPerformed
        if (jTabbedPane2.getSelectedIndex() == 0)
            removeConcert();
        else if (jTabbedPane2.getSelectedIndex() == 1)
            removeHall();
    }//GEN-LAST:event_removeTierButton1ActionPerformed

    private void jTabbedPane2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane2StateChanged
        if (jTabbedPane2.getSelectedIndex() == 0)
            newButton.setText("New Concert");
        else if (jTabbedPane2.getSelectedIndex() == 1)
            newButton.setText("New Hall");
    }//GEN-LAST:event_jTabbedPane2StateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        admin = new TicketsAdmin();
        /* Create and display the form */
        if (admin.connect())
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new AdminFrame().setVisible(true);
                }
            });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addDateButton;
    private javax.swing.JButton addTierButton;
    private javax.swing.JComboBox<String> concertComboBox;
    private javax.swing.JTable dateTable;
    private javax.swing.JComboBox<String> hallComboBox;
    private javax.swing.JLabel hallLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton newButton;
    private javax.swing.JLabel performerLabel;
    private javax.swing.JButton removeTierButton1;
    private javax.swing.JTable tierTable;
    // End of variables declaration//GEN-END:variables

    private int onClose() {
        return EXIT_ON_CLOSE;

    }

    private void tierTableRefresh() {

        tierTable.setModel(new javax.swing.table.DefaultTableModel(
                admin.getTiers(hallComboBox.getSelectedItem()),
                new String[]{
                    "Tier", "No Seats", "Price", "Numbered"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

        });
    }

    private void addConcert() {
        JTextField concertfield = new JTextField(10);
        JTextField performerfield = new JTextField(10);
        JComboBox<String> hallbox = new JComboBox<>(hallComboBox.getModel());
        JFrame frame = new JFrame();
        frame.setSize(300, 10000);
        JPanel myPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        myPanel.add(new JLabel("Concert Title:"));
        myPanel.add(concertfield);
        myPanel.add(Box.createVerticalStrut(5));
        myPanel.add(new JLabel("Select hall:"));
        myPanel.add(hallbox);
        myPanel.add(Box.createVerticalStrut(5));
        myPanel.add(new JLabel("Performer/s:"));
        myPanel.add(performerfield);
        myPanel.setSize(500, 1000);
        int result = JOptionPane.showConfirmDialog(null, myPanel, "Enter Concert Title", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION)
            if (admin.addConcert(concertfield.getText() + ":" + (String) hallbox.getSelectedItem() + ":" + performerfield.getText())) {
                List<String> concerts = admin.getConcerts();
                String[] concertTittles = new String[concerts.size()];
                for (int i = 0; i < concerts.size(); i++)
                    concertTittles[i] = concerts.get(i).split(":")[0];
                concertComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(concertTittles));
                concertComboBox.setSelectedItem(concertfield.getText());
            }
    }

    private void addHall() {
        String hall = JOptionPane.showInputDialog("Enter Hall Name");
        if (hall == null)
            return;
        if (admin.addHall(hall))
            hallComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(admin.getHalls()));
    }

    private void dateTableRefresh() {
        dateTable.setModel(new javax.swing.table.DefaultTableModel(
                admin.gatDates(concertComboBox.getSelectedItem()),
                new String[]{
                    "Day", "Month", "Year", "Time"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }

        });
    }

    private void removeConcert() {
        if (admin.removeConcert((String) concertComboBox.getSelectedItem())) {
            List<String> concerts = admin.getConcerts();
            String[] concertTittles = new String[concerts.size()];
            for (int i = 0; i < concerts.size(); i++)
                concertTittles[i] = concerts.get(i).split(":")[0];
            concertComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(concertTittles));
        }
    }

    private void removeHall() {
        if (admin.removeHall((String) hallComboBox.getSelectedItem()))
            hallComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(admin.getHalls()));
    }
}
