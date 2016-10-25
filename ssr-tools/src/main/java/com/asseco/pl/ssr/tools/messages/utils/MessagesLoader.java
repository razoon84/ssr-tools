package com.asseco.pl.ssr.tools.messages.utils;

import com.asseco.pl.ssr.tools.messages.exceptions.MessageLoaderExceptions;
import com.asseco.pl.ssr.tools.exceptions.ToolsException;
import com.asseco.pl.ssr.tools.utils.ConstValues;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * Loader for Message. Create {@link Message} object and load values from excel
 *
 * @author Pawel.Kowalski
 * @version 1.0.0
 *
 * @see Message
 */
public class MessagesLoader {

    private final Workbook woorkbook;
    private ArrayList<String> sheets;
    private boolean success = false;

    private MessagesLoader( File file )
            throws IOException, InvalidFormatException {
        this.woorkbook = WorkbookFactory.create( file, null, true );
    }

    /**
     * Create message loader from file
     *
     * @param file excel file to load data
     *
     * @return instance of message loader
     *
     * @throws com.asseco.pl.ssr.tools.messages.exceptions.MessageLoaderExceptions.InitializeException any IOException or InvalidFormatException will occurre
     *
     * @see IOException
     * @see InvalidFormatException
     */
    public static MessagesLoader createLoader( File file )
            throws MessageLoaderExceptions.InitializeException {
        try {
            return new MessagesLoader( file );
        } catch (IOException | InvalidFormatException ex) {
            throw new MessageLoaderExceptions.InitializeException( ex );
        }
    }

    /**
     * Load message from created workbook.
     *
     * @param message        cobol message id
     * @param loadOutMessage if set to true load output message if exists.
     *
     * @return instance of {@link Message} object
     *
     * @throws ToolsException any error occurred is repacked and throw as ToolsException
     *
     * @see Message
     */
    public Message loadMessage( String message, boolean loadOutMessage )
            throws ToolsException {

        try {

            // find message in workbook sheet 
            sheets = findMessageSheet( message );

            // check number of sheets 
            if ( sheets.isEmpty() ) {
                throw new MessageLoaderExceptions.NoMessageFoundException();
            } else if ( sheets.size() > 1 ) {
                throw new MessageLoaderExceptions.ToManySheetsException();
            }

            return loadMessage( message, sheets.get( 0 ), loadOutMessage );

        } catch (MessageLoaderExceptions.ToManySheetsException ex) {
            success = false;
        } catch (ToolsException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new MessageLoaderExceptions.MessageLoadException( ex );
        }

        return null;
    }

    /**
     * Load message from created woorkbook.
     *
     * @param message        cobol message id
     * @param sheetName      workbook sheet name
     * @param loadOutMessage if set to true load output message if exists.
     *
     * @return instance of {@link Message} object
     *
     * @throws ToolsException any error occurred is repacked and throw as ToolsException
     *
     * @see Message
     */
    public Message loadMessage( String message, String sheetName, boolean loadOutMessage )
            throws ToolsException {

        try {
            return createMessage( message, sheetName, loadOutMessage );
        } catch (ToolsException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new MessageLoaderExceptions.MessageLoadException( ex );
        }

    }

    /**
     * Return flag of loader success message load. <br>
     * If none exception will occurred and flag is on false it's mean that message was in too many sheets
     *
     * @return true if message was successfull load
     */
    public boolean isSuccess() {
        return this.success;
    }

    /**
     * Sheet names where message was found.
     *
     * @return list of sheet names
     */
    public ArrayList<String> getSheets() {
        return this.sheets;
    }

    private Message createMessage( String messageName, String sheetName, boolean loadOutMessage )
            throws MessageLoaderExceptions.MessageCreateException {

        try {
            Sheet currentSheet = woorkbook.getSheet( sheetName );

            Message message = null;
            boolean firstRowLoaded = false;

            for ( Row row : currentSheet ) {
                Cell name = row.getCell( 0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL );

                if ( name != null && getStringValue( name ).equalsIgnoreCase( messageName ) ) {

                    Cell description = row.getCell( 5, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL );

                    if ( !firstRowLoaded ) {

                        Cell messageOutName = row.getCell( 7, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL );

                        firstRowLoaded = true;
                        message = new Message( getStringValue( name ), getStringValue( description ) );

                        if ( loadOutMessage ) {
                            message.setOutMessage( createMessage( getStringValue( messageOutName ), sheetName, false ) );
                        }
                        continue;
                    }

                    Cell fieldName = row.getCell( 2, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL );
                    Cell fieldFormat = row.getCell( 3, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL );
                    Cell fieldTableName = row.getCell( 10, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL );
                    Cell fieldRequired = row.getCell( 11, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL );

                    if ( fieldTableName != null ) {
                        message.addTableField( getStringValue( fieldTableName ).substring( 0, 6 ), getStringValue( fieldName ), getStringValue( fieldFormat ), getStringValue( description ), ConstValues.T_VALUE.equalsIgnoreCase( getStringValue( fieldRequired ) ) );
                    } else {
                        message.addMessageField( getStringValue( fieldName ), getStringValue( fieldFormat ), getStringValue( description ), ConstValues.T_VALUE.equalsIgnoreCase( getStringValue( fieldRequired ) ) );
                    }

                }
            }

            success = true;

            return message;
        } catch (Exception ex) {
            throw new MessageLoaderExceptions.MessageCreateException( ex );
        }
    }

    private String getStringValue( Cell cell ) {
        if ( cell != null ) {
            switch ( cell.getCellTypeEnum() ) {
                case STRING:
                    return cell.getStringCellValue();
                case NUMERIC:
                    return String.valueOf( cell.getNumericCellValue() );
            }
        }

        return "";
    }

    private ArrayList<String> findMessageSheet( String message ) {

        ArrayList<String> _sheets = new ArrayList<>();

        for ( int i = 0; i < this.woorkbook.getNumberOfSheets(); i++ ) {
            Sheet currentSheet = woorkbook.getSheetAt( i );
            for ( Row row : currentSheet ) {
                Cell cell = row.getCell( 0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL );
                if ( cell != null && cell.getStringCellValue().equalsIgnoreCase( message ) ) {
                    _sheets.add( currentSheet.getSheetName() );
                    break;
                }
            }
        }

        return _sheets;
    }

}
