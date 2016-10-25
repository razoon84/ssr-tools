package com.asseco.pl.ssr.tools.exceptions;

/**
 * Generic exception for all tools
 * 
 * @author Pawel.Kowalski
 * @version 1.0.0
 */
public abstract class ToolsException extends Exception {

    private final String code;
    private final String message;
    private final String description;

    public ToolsException( String code, String message ) {
        super( String.format( "%s : %s", code, message ) );
        this.code = code;
        this.message = message;
        this.description = "";
    }

    public ToolsException( String code, String message, Throwable cause ) {
        super( String.format( "%s : %s \n %s", code, message, cause != null ? cause.getMessage() : "" ), cause );
        this.code = code;
        this.message = message;
        this.description = cause != null ? cause.getMessage() : null;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }

    public String getMessageWithDesc() {
        return super.getMessage();
    }
}
