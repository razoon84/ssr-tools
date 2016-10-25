package com.asseco.pl.ssr.tools.messages.exceptions;

import com.asseco.pl.ssr.tools.exceptions.ToolsException;

/**
 * Message exceptions pack
 *
 * @author Pawel.Kowalski
 * @version 1.0.0
 */
public class MessageExceptions {

    /**
     * Error Code : MSG0001
     */
    public static class UknownCobolFieldFormatException extends ToolsException {

        public UknownCobolFieldFormatException( String format ) {
            super( "MSG0001", String.format( "Nieznany format pola %s ", format ) );
        }

    }

    /**
     * Error Code : MSG0002A / MSG0002B
     */
    public static class UnableToParseFieldSizeExeception extends ToolsException {

        public UnableToParseFieldSizeExeception( String format ) {
            super( "MSG0002A", String.format( "Nie można pobrać wielkości pola dla formatu %s", format ) );
        }

        public UnableToParseFieldSizeExeception( String format, Throwable ex ) {
            super( "MSG0002B", String.format( "Nie można pobrać wielkości pola dla formatu %s", format ), ex );
        }
    }

     /**
     * Error Code : MSG0003A / MSG0003A
     */
    public static class UnableToParseFieldPresisionExeception extends ToolsException {

        public UnableToParseFieldPresisionExeception( String format ) {
            super( "MSG0003A", String.format( "Nie można pobrać wielkości precyzji pola dla formatu %s", format ) );
        }

        public UnableToParseFieldPresisionExeception( String format, Throwable ex ) {
            super( "MSG0003A", String.format( "Nie można pobrać wielkości precyzji pola dla formatu %s", format ), ex );
        }
    }

}
