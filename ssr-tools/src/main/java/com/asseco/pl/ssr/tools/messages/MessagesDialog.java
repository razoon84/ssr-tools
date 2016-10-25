package com.asseco.pl.ssr.tools.messages;

import com.asseco.pl.ssr.tools.messages.dialogs.ChooseSheetDialog;
import com.asseco.pl.ssr.tools.messages.dialogs.PleaseWaitDialog;
import com.asseco.pl.ssr.tools.exceptions.ToolsException;
import com.asseco.pl.ssr.tools.messages.utils.Message;
import com.asseco.pl.ssr.tools.messages.utils.MessagesLoader;
import com.asseco.pl.ssr.tools.utils.Utils;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Window;
import java.beans.PropertyChangeEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;

/**
 * Popup window, helps to generate java code from cobol messages in excel
 *
 * @author Pawel.Kowalski
 * @version 1.0.0
 */
public class MessagesDialog extends javax.swing.JDialog {

    private MessagesLoader messageLoader;
    private Message message;
    private final JComboBox<Message.FieldType> comboColumnEditor;

    /**
     * Creates new form MessagesDialog
     *
     * @param parent parent window
     */
    public MessagesDialog( Window parent ) {
        super( parent, ModalityType.TOOLKIT_MODAL );
        this.comboColumnEditor = new JComboBox<>( Message.FieldType.values() );
        initComponents();
    }

    private void setEnableControls( boolean enable ) {
        messageIn.setEnabled( enable );
        messageOut.setEnabled( enable );
    }

    private void setEnableTabs( boolean enable ) {

        messagesTab.setEnabled( enable );
        messagesTab.setEnabledAt( 0, enable );
        messagesTab.setEnabledAt( 1, enable && messageOut.isSelected() );

        messageInTableFieldsLabel.setVisible( enable && messageInTablesTab.getTabCount() > 0 );
        messageInTablesTab.setVisible( enable && messageInTablesTab.getTabCount() > 0 );

        if ( !messageInTablesTab.isVisible() ) {
            messageInTablesTab.removeAll();
        }

        messageOutTableFieldsLabel.setVisible( enable && messageOutTablesTab.getTabCount() > 0 );
        messageOutTablesTab.setVisible( enable && messageOutTablesTab.getTabCount() > 0 );

        if ( !messageOutTablesTab.isVisible() ) {
            messageOutTablesTab.removeAll();
        }
    }

    private void loadTables() {
        if ( message != null ) {

            fillMessageTab( messageInName, messageInDesc, messageInTable, messageInTablesTab, message );

            if ( messageOut.isSelected() && message.getOutMessage() != null ) {

                fillMessageTab( messageOutName, messageOutDesc, messageOutTable, messageOutTablesTab, message.getOutMessage() );

            }
        }
    }

    private void fillMessageTab( JTextField nameControl, JTextField descriptionControl, JTable tableControl, JTabbedPane tabPanel, Message message ) {

        nameControl.setText( message.getMessage() );
        descriptionControl.setText( message.getDescription() );

        ( (MessageTableModel) tableControl.getModel() ).setData( message.getFields() );

        if ( !message.getTables().isEmpty() ) {
            for ( Map.Entry<String, List<Message.Field>> cobolTable : message.getTables().entrySet() ) {
                createAndFillTableTab( tabPanel, cobolTable.getKey(), cobolTable.getValue() );
            }
        }
    }

    private void createAndFillTableTab( JTabbedPane tabPanel, String title, List<Message.Field> fields ) {

        MessageTableModel model = new MessageTableModel();
        model.setData( fields );
        JScrollPane _scrollPane = new JScrollPane();
        JTable _table = new JTable( model, messageInTable.getColumnModel() );
        _table.setIntercellSpacing( new Dimension( 5, 5 ) );

        _scrollPane.setBorder( BorderFactory.createCompoundBorder( new EmptyBorder( 5, 5, 5, 5 ), new EtchedBorder() ) );
        _scrollPane.setViewportView( _table );
        tabPanel.addTab( title, _scrollPane );
    }

    private void clearData() {
        ( (MessageTableModel) messageInTable.getModel() ).clear();
        ( (MessageTableModel) messageOutTable.getModel() ).clear();

        messageInName.setText( null );
        messageInDesc.setText( null );

        messageOutName.setText( null );
        messageOutDesc.setText( null );
        messagesTab.setSelectedIndex( 0 );

        generateButton.setEnabled( false );
    }

    /**
     * Autogenerate code from netbeans designer
     */
    @SuppressWarnings( "unchecked" )
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FileFilter filter = new FileNameExtensionFilter("Excel Files", "xlsx", "xls");
        fileChooserDialog = new javax.swing.JFileChooser();
        fileNameLabel = new javax.swing.JLabel();
        javax.swing.JButton chooseFile = new javax.swing.JButton();
        javax.swing.JLabel _messageInLabel = new javax.swing.JLabel();
        messageIn = new javax.swing.JTextField();
        messageOut = new javax.swing.JCheckBox();
        loadMessageButton = new javax.swing.JButton();
        messagesTab = new javax.swing.JTabbedPane();
        javax.swing.JPanel _messageInTab = new javax.swing.JPanel();
        javax.swing.JPanel _messageInPanel = new javax.swing.JPanel();
        javax.swing.JLabel _messageInFieldsLabel = new javax.swing.JLabel();
        javax.swing.JLabel _messageInName = new javax.swing.JLabel();
        messageInName = new javax.swing.JTextField();
        javax.swing.JLabel _messageInDesc = new javax.swing.JLabel();
        messageInDesc = new javax.swing.JTextField();
        javax.swing.JScrollPane _messageInFieldPane = new javax.swing.JScrollPane();
        messageInTable = new javax.swing.JTable();
        messageInTableFieldsLabel = new javax.swing.JLabel();
        messageInTablesTab = new javax.swing.JTabbedPane();
        javax.swing.JPanel _messageOutTab = new javax.swing.JPanel();
        javax.swing.JPanel _messageOutPanel = new javax.swing.JPanel();
        javax.swing.JLabel _messageOutName = new javax.swing.JLabel();
        messageOutName = new javax.swing.JTextField();
        javax.swing.JLabel _messageOutDesc = new javax.swing.JLabel();
        messageOutDesc = new javax.swing.JTextField();
        javax.swing.JScrollPane _messageOutFieldPane = new javax.swing.JScrollPane();
        messageOutTable = new javax.swing.JTable();
        messageOutTableFieldsLabel = new javax.swing.JLabel();
        messageOutTablesTab = new javax.swing.JTabbedPane();
        generateButton = new javax.swing.JButton();

        fileChooserDialog.setCurrentDirectory(null);
        fileChooserDialog.setDialogTitle("Wybierz plik z komunikatami");
        fileChooserDialog.setFileFilter(filter);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Generator kodu komunikatów COBOL");
        setModal(true);
        setName("Message dialog"); // NOI18N
        setResizable(false);

        chooseFile.setText("Wybierz plik");
        chooseFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseFileActionPerformed(evt);
            }
        });

        _messageInLabel.setText("Komunikat");

        messageIn.setEnabled(false);
        messageIn.getDocument().addDocumentListener(new MessageIdDocumentListener());

        messageOut.setText("Wczytaj komunikat wyjściowy");
        messageOut.setEnabled(false);

        loadMessageButton.setText("Wczytaj");
        loadMessageButton.setEnabled(false);
        loadMessageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadMessageButtonActionPerformed(evt);
            }
        });

        messagesTab.setEnabled(false);

        _messageInTab.setEnabled(false);

        _messageInFieldsLabel.setText("Pola dla komunikatu");

        _messageInName.setText("Nazwa komunikatu");

        messageInName.setEditable(false);

        _messageInDesc.setText("Opis komunikatu");

        messageInDesc.setEditable(false);

        _messageInFieldPane.setBorder(null);

        messageInTable.setModel(new MessageTableModel());
        messageInTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        messageInTable.setIntercellSpacing(new java.awt.Dimension(5, 5));
        messageInTable.getTableHeader().setReorderingAllowed(false);
        _messageInFieldPane.setViewportView(messageInTable);
        if (messageInTable.getColumnModel().getColumnCount() > 0) {
            messageInTable.getColumnModel().getColumn(0).setMinWidth(150);
            messageInTable.getColumnModel().getColumn(0).setPreferredWidth(150);
            messageInTable.getColumnModel().getColumn(0).setMaxWidth(150);
            messageInTable.getColumnModel().getColumn(1).setMinWidth(100);
            messageInTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            messageInTable.getColumnModel().getColumn(1).setMaxWidth(100);
            messageInTable.getColumnModel().getColumn(2).setMinWidth(150);
            messageInTable.getColumnModel().getColumn(2).setPreferredWidth(150);
            messageInTable.getColumnModel().getColumn(2).setMaxWidth(150);
        }
        messageInTable.setDefaultEditor(Message.FieldType.class, new DefaultCellEditor(comboColumnEditor));

        messageInTableFieldsLabel.setText("Pola w tabelach komunikatu");
        messageInTableFieldsLabel.setVisible(false);

        messageInTablesTab.setVisible(false);

        javax.swing.GroupLayout _messageInPanelLayout = new javax.swing.GroupLayout(_messageInPanel);
        _messageInPanel.setLayout(_messageInPanelLayout);
        _messageInPanelLayout.setHorizontalGroup(
            _messageInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(_messageInPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(_messageInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(messageInTablesTab, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(_messageInFieldPane, javax.swing.GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE)
                    .addGroup(_messageInPanelLayout.createSequentialGroup()
                        .addComponent(_messageInName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(messageInName, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(_messageInDesc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(messageInDesc))
                    .addGroup(_messageInPanelLayout.createSequentialGroup()
                        .addGroup(_messageInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(messageInTableFieldsLabel)
                            .addComponent(_messageInFieldsLabel))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        _messageInPanelLayout.setVerticalGroup(
            _messageInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, _messageInPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(_messageInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(_messageInName)
                    .addComponent(messageInName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(messageInDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(_messageInDesc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(_messageInFieldsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(_messageInFieldPane, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messageInTableFieldsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messageInTablesTab, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout _messageInTabLayout = new javax.swing.GroupLayout(_messageInTab);
        _messageInTab.setLayout(_messageInTabLayout);
        _messageInTabLayout.setHorizontalGroup(
            _messageInTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(_messageInTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(_messageInPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        _messageInTabLayout.setVerticalGroup(
            _messageInTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(_messageInTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(_messageInPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        messagesTab.addTab("Komuniakt wejściowy", _messageInTab);

        _messageOutTab.setEnabled(false);

        _messageOutName.setText("Nazwa komunikatu");

        messageOutName.setEditable(false);

        _messageOutDesc.setText("Opis komunikatu");

        messageOutDesc.setEditable(false);

        _messageOutFieldPane.setPreferredSize(new java.awt.Dimension(100, 100));

        messageOutTable.setModel(new MessageTableModel());
        messageOutTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        messageOutTable.setIntercellSpacing(new java.awt.Dimension(5, 5));
        messageOutTable.getTableHeader().setReorderingAllowed(false);
        _messageOutFieldPane.setViewportView(messageOutTable);
        messageOutTable.setColumnModel(messageInTable.getColumnModel());

        messageOutTableFieldsLabel.setText("Pola w tabelach komunikatu");
        messageInTableFieldsLabel.setVisible(false);

        messageInTablesTab.setVisible(false);

        javax.swing.GroupLayout _messageOutPanelLayout = new javax.swing.GroupLayout(_messageOutPanel);
        _messageOutPanel.setLayout(_messageOutPanelLayout);
        _messageOutPanelLayout.setHorizontalGroup(
            _messageOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(_messageOutPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(_messageOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(_messageOutFieldPane, javax.swing.GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE)
                    .addGroup(_messageOutPanelLayout.createSequentialGroup()
                        .addComponent(_messageOutName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(messageOutName, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(_messageOutDesc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(messageOutDesc))
                    .addComponent(messageOutTablesTab, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(_messageOutPanelLayout.createSequentialGroup()
                        .addComponent(messageOutTableFieldsLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        _messageOutPanelLayout.setVerticalGroup(
            _messageOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, _messageOutPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(_messageOutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(_messageOutName)
                    .addComponent(messageOutName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(_messageOutDesc)
                    .addComponent(messageOutDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(_messageOutFieldPane, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messageOutTableFieldsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messageOutTablesTab, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout _messageOutTabLayout = new javax.swing.GroupLayout(_messageOutTab);
        _messageOutTab.setLayout(_messageOutTabLayout);
        _messageOutTabLayout.setHorizontalGroup(
            _messageOutTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(_messageOutTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(_messageOutPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        _messageOutTabLayout.setVerticalGroup(
            _messageOutTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, _messageOutTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(_messageOutPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        messagesTab.addTab("Komunikat wyjściowy", _messageOutTab);

        generateButton.setText("Generuj kod");
        generateButton.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(messagesTab, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fileNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(_messageInLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(messageIn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(messageOut)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chooseFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(loadMessageButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(generateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chooseFile)
                    .addComponent(fileNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(messageIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(_messageInLabel)
                    .addComponent(loadMessageButton)
                    .addComponent(messageOut))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messagesTab)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(generateButton)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(912, 710));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void chooseFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseFileActionPerformed
        if ( fileChooserDialog.showOpenDialog( this ) == JFileChooser.APPROVE_OPTION ) {
            fileNameLabel.setText( fileChooserDialog.getSelectedFile().getName() );
            messageLoader = null;
            setEnableControls( true );
        }
    }//GEN-LAST:event_chooseFileActionPerformed

    private void loadMessageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadMessageButtonActionPerformed

        // create dialogs for loading
        final PleaseWaitDialog waitDialog = new PleaseWaitDialog( SwingUtilities.getWindowAncestor( (Component) evt.getSource() ) );
        final ChooseSheetDialog chooseDialog = new ChooseSheetDialog( SwingUtilities.getWindowAncestor( (Component) evt.getSource() ) );

        // clear old load result from tables
        setEnableTabs( false );
        clearData();

        // create swing worker for background loading 
        SwingWorker<Void, Void> swingWorker = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() {
                try {

                    // load file when messageLoader is not initiated;
                    if ( messageLoader == null ) {
                        messageLoader = MessagesLoader.createLoader( fileChooserDialog.getSelectedFile() );
                    }

                    // load message
                    message = messageLoader.loadMessage( messageIn.getText(), messageOut.isSelected() );

                    // when message wasn't load with success this means the message is in workbook but its on many tabs.
                    // user must select with tab use to load message to avoid dubling table entries                    
                    if ( !messageLoader.isSuccess() ) {
                        chooseDialog.setSheetsList( messageLoader.getSheets() );
                        chooseDialog.setVisible( true );
                        message = messageLoader.loadMessage( messageIn.getText(), chooseDialog.getSheetName(), messageOut.isSelected() );
                    }

                } catch (ToolsException ex) {
                    waitDialog.setVisible( false );
                    Utils.showErrorDialog( rootPane, ex.getMessageWithDesc(), ex.getCode() );
                } catch (Exception ex) {
                    waitDialog.setVisible( false );
                    Utils.showErrorDialog( rootPane, ex.getMessage(), "UKNOWN ERROR" );
                }

                return null;
            }

        };

        // add worker check state
        swingWorker.addPropertyChangeListener( ( PropertyChangeEvent event ) -> {
            if ( event.getPropertyName().equals( "state" ) ) {
                if ( event.getNewValue() == SwingWorker.StateValue.DONE ) {
                    waitDialog.dispose();
                    chooseDialog.dispose();
                }
            }
        } );

        // execute background worker
        swingWorker.execute();

        // show please wait dialog
        waitDialog.setVisible( true );

        // when all is done load data to tables
        loadTables();

        // enable all required controls
        setEnableTabs( true );

        // enable generate button
        generateButton.setEnabled( messageLoader.isSuccess() );

    }//GEN-LAST:event_loadMessageButtonActionPerformed

    class MessageTableModel extends AbstractTableModel {

        private final String[] columns = new String[]{"Cobol Name", "Typ", "Nazwa zmiennej", "Opis"};
        private List<Message.Field> data = new LinkedList<>();

        @Override
        public int getColumnCount() {
            return columns.length;
        }

        @Override
        public String getColumnName( int column ) {
            return columns[ column ];
        }

        @Override
        public Class<?> getColumnClass( int columnIndex ) {
            return ( columnIndex == 1 ) ? Message.FieldType.class : String.class;
        }

        @Override
        public boolean isCellEditable( int rowIndex, int columnIndex ) {
            return columnIndex != 0;
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        public void setData( List<Message.Field> data ) {
            this.data = data;
            fireTableDataChanged();
        }

        public void clear() {
            this.data = new LinkedList<>();
            fireTableDataChanged();
        }

        @Override
        public Object getValueAt( int rowIndex, int columnIndex ) {
            Message.Field row = data.get( rowIndex );

            switch ( columnIndex ) {
                case 0:
                    return row.getCobolName();
                case 1:
                    return row.getJavaType();
                case 2:
                    return row.getJavaVariable();
                case 3:
                    return row.getDescription();
                default:
                    return null;
            }
        }

        @Override
        public void setValueAt( Object aValue, int rowIndex, int columnIndex ) {
            Message.Field row = data.get( rowIndex );

            switch ( columnIndex ) {
                case 1:
                    row.setJavaType( (Message.FieldType) aValue );
                    break;
                case 2:
                    row.setJavaVariable( (String) aValue );
                    break;
                case 3:
                    row.setDescription( (String) aValue );
                    break;
                default:
                    break;
            }
        }

    }

    class MessageIdDocumentListener implements DocumentListener {

        @Override
        public void changedUpdate( DocumentEvent e ) {
        }

        @Override
        public void insertUpdate( DocumentEvent e ) {
            loadMessageButton.setEnabled( true );
        }

        @Override
        public void removeUpdate( DocumentEvent e ) {
            loadMessageButton.setEnabled( e.getOffset() != 0 );
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main( String args[] ) {
        /*
         * Set the Windows look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {

            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger( MessagesDialog.class.getName() ).log( java.util.logging.Level.SEVERE, null, ex );
        }

        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater( () -> {
            MessagesDialog dialog = new MessagesDialog( null );
            dialog.addWindowListener( new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing( java.awt.event.WindowEvent e ) {
                    System.exit( 0 );
                }
            } );
            dialog.setVisible( true );
        } );
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser fileChooserDialog;
    private javax.swing.JLabel fileNameLabel;
    private javax.swing.JButton generateButton;
    private javax.swing.JButton loadMessageButton;
    private javax.swing.JTextField messageIn;
    private javax.swing.JTextField messageInDesc;
    private javax.swing.JTextField messageInName;
    private javax.swing.JTable messageInTable;
    private javax.swing.JLabel messageInTableFieldsLabel;
    private javax.swing.JTabbedPane messageInTablesTab;
    private javax.swing.JCheckBox messageOut;
    private javax.swing.JTextField messageOutDesc;
    private javax.swing.JTextField messageOutName;
    private javax.swing.JTable messageOutTable;
    private javax.swing.JLabel messageOutTableFieldsLabel;
    private javax.swing.JTabbedPane messageOutTablesTab;
    private javax.swing.JTabbedPane messagesTab;
    // End of variables declaration//GEN-END:variables

}
