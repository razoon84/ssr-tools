package com.asseco.pl.ssr.tools.messages.exceptions;

import com.asseco.pl.ssr.tools.exceptions.ToolsException;

/**
 * Message loader exception pack
 *
 * @author Pawel.Kowalski
 * @version 1.0.0
 */
public abstract class MessageLoaderExceptions {

    /**
     * Error Code : MSGLOAD0001
     */
    public static class InitializeException extends ToolsException {

        public InitializeException( Throwable ex ) {
            super( "MSGLOAD0001", "Błąd inicjalizacji loadera komunikatów", ex );
        }

    }

    /**
     * Error Code : MSGLOAD0002
     */
    public static class NotInitializeException extends ToolsException {

        public NotInitializeException() {
            super( "MSGLOAD0002", "Loader komunikatów nie został zainicjalizowany" );
        }
    }

    /**
     * Error Code : MSGLOAD0003
     */
    public static class ToManySheetsException extends ToolsException {

        public ToManySheetsException() {
            super( "MSGLOAD0003", "Komunikat znajduje sie w zbyt wielu arkuszach" );
        }
    }

    /**
     * Error Code : MSGLOAD0004
     */
    public static class NoMessageFoundException extends ToolsException {

        public NoMessageFoundException() {
            super( "MSGLOAD0004", "Nie znaleziono komunikatu w zadnym arkuszu" );
        }
    }

    /**
     * Error Code : MSGLOAD0005
     */
    public static class MessageCreateException extends ToolsException {

        public MessageCreateException( Exception ex ) {
            super( "MSGLOAD0005", "Błąd podczas tworzenia wiadomości", ex );
        }
    }

    /**
     * Error Code : MSGLOAD0006
     */
    public static class MessageLoadException extends ToolsException {

        public MessageLoadException( Throwable ex ) {
            super( "MSGLOAD0006", "Błąd podczas ładowania wiadomości", ex );
        }
    }

}
