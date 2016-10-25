package com.asseco.pl.ssr.tools.messages.dialogs;

import java.awt.Window;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 * Utility dialog show combobox with sheets names where specified message has occurred
 *
 * @author Pawel.Kowalski
 * @version 1.0.0
 */
public class ChooseSheetDialog extends javax.swing.JDialog {

    /**
     * Creates new form ChooseSheetDialog
     *
     * @param parent reference to parent window
     */
    public ChooseSheetDialog( Window parent ) {
        super( parent );
        initComponents();
        setLocationRelativeTo( parent );
    }
    
    public void setSheetsList( ArrayList<String> sheets ) {
        sheetCombo.setModel( new DefaultComboBoxModel<>( sheets.toArray() ) );
    }
    
    public String getSheetName() {
        return this.sheetCombo.getSelectedItem().toString();
    }

    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JPanel _mainPanel = new javax.swing.JPanel();
        messageNameLabel = new javax.swing.JLabel();
        sheetCombo = new javax.swing.JComboBox<>();
        chooseButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModalityType(java.awt.Dialog.ModalityType.DOCUMENT_MODAL);
        setName("ChooseSheet"); // NOI18N
        setUndecorated(true);
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        _mainPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        messageNameLabel.setText("<html>Komunikat znajduje się w zbyt wielu arkuszach. By wczytać należy wybrać jeden z poniższych arkuszy</html>");

        chooseButton.setText("Wybierz arkusz");
        chooseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout _mainPanelLayout = new javax.swing.GroupLayout(_mainPanel);
        _mainPanel.setLayout(_mainPanelLayout);
        _mainPanelLayout.setHorizontalGroup(
            _mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(_mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(_mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(_mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(messageNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(sheetCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(_mainPanelLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(chooseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        _mainPanelLayout.setVerticalGroup(
            _mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(_mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(messageNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sheetCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chooseButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(_mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(_mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chooseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseButtonActionPerformed
        setVisible( false );
    }//GEN-LAST:event_chooseButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton chooseButton;
    private javax.swing.JLabel messageNameLabel;
    private javax.swing.JComboBox<Object> sheetCombo;
    // End of variables declaration//GEN-END:variables
}
