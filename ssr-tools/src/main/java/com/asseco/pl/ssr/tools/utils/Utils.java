package com.asseco.pl.ssr.tools.utils;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 * Utility methods
 * 
 * @author Pawel.Kowalski
 * @version 1.0.0
 * 
 */
public class Utils {

    /**
     * Shows error dialog of specified title and message
     * 
     * @param parrent parrent component should be windows or frame
     * @param message message 
     * @param title dialog tile
     * 
     * @see Component
     */
    public static void showErrorDialog( Component parrent, String message, String title ) {
        JOptionPane.showMessageDialog( parrent, message != null ? message : "Wewnętrzny błąd aplikacji", title, JOptionPane.ERROR_MESSAGE );
    }
}
