package com.asseco.pl.ssr.tools.messages.dialogs;

import java.awt.Window;

/**
 * Utility dialog shows Please Wait whit progress bar
 *
 * @author Pawel.Kowalski
 * @version 1.0.0
 *
 */
public class PleaseWaitDialog extends javax.swing.JDialog {

    /**
     * Creates new form PleaseWaitDialog
     *
     * @param parent reference to parent window
     */
    public PleaseWaitDialog( Window parent ) {
        super( parent );
        initComponents();
        setLocationRelativeTo( parent );
    }

    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.JPanel _mainPanel = new javax.swing.JPanel();
        pleaseWaitLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setModalityType(java.awt.Dialog.ModalityType.DOCUMENT_MODAL);
        setName("Please Wait"); // NOI18N
        setUndecorated(true);
        setType(java.awt.Window.Type.POPUP);

        _mainPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pleaseWaitLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pleaseWaitLabel.setText("Proszę czekać");

        progressBar.setIndeterminate(true);

        javax.swing.GroupLayout _mainPanelLayout = new javax.swing.GroupLayout(_mainPanel);
        _mainPanel.setLayout(_mainPanelLayout);
        _mainPanelLayout.setHorizontalGroup(
            _mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(_mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(_mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pleaseWaitLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(progressBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                .addContainerGap())
        );
        _mainPanelLayout.setVerticalGroup(
            _mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, _mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pleaseWaitLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(_mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(_mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel pleaseWaitLabel;
    private javax.swing.JProgressBar progressBar;
    // End of variables declaration//GEN-END:variables
}
