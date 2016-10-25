package com.asseco.pl.ssr.tools.messages.utils;

import com.asseco.pl.ssr.tools.messages.exceptions.MessageExceptions;
import com.google.common.base.CaseFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Implementation of excel message.
 *
 * @author Pawel.Kowalski
 * @version 1.0.0
 *
 * @see MessagesLoader
 * @see Message.Field
 * @see Message.FieldType
 */
public class Message {

    // variables
    private final String message;
    private final String description;
    private Message outMessage;

    private final List<Field> fields;
    private final Map<String, List<Field>> tables;

    /**
     * Create message object
     *
     * @param message     message id in cell A column
     * @param description message description in cell F column
     */
    public Message( String message, String description ) {

        this.message = message;                 // column A
        this.description = description;         // column F
        this.outMessage = null;                 // column G
        this.fields = new LinkedList<>();       // see Message.Field create by column C, D, F, L
        this.tables = new HashMap<>();          // see Message.Field create by column J and K
    }

    /**
     * @return cobol message id
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * @return cobol description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return list of fields
     *
     * @see Field
     */
    public List<Field> getFields() {
        return this.fields;
    }

    /**
     * @return map of message table with fields
     */
    public Map<String, List<Field>> getTables() {
        return this.tables;
    }

    /**
     * @return output message if message doesn't have output message it will be null
     */
    public Message getOutMessage() {
        return this.outMessage;
    }

    /**
     * Set output message to message in
     *
     * @param message instance of output message
     */
    public void setOutMessage( Message message ) {
        this.outMessage = message;
    }

    /**
     * Add field to message
     *
     * @param cobolName   value of cobol name from column C
     * @param coboleType  value of cobol type from column D
     * @param description value from column F
     * @param required    value from column L
     *
     * @throws com.asseco.pl.ssr.tools.messages.exceptions.MessageExceptions.UknownCobolFieldFormatException       can not format cobol field
     * @throws com.asseco.pl.ssr.tools.messages.exceptions.MessageExceptions.UnableToParseFieldSizeExeception      can not parse field size
     * @throws com.asseco.pl.ssr.tools.messages.exceptions.MessageExceptions.UnableToParseFieldPresisionExeception can not parse presision of field
     */
    public void addMessageField( String cobolName, String coboleType, String description, Boolean required )
            throws MessageExceptions.UknownCobolFieldFormatException,
                   MessageExceptions.UnableToParseFieldSizeExeception,
                   MessageExceptions.UnableToParseFieldPresisionExeception {
        this.fields.add( createField( cobolName, coboleType, description, required ) );
    }

    /**
     * Add table field to tables in message. If table is not add to table map its automatically adds in this map
     *
     * @param tableName   value from column K
     * @param cobolName   value of cobol name from column C
     * @param coboleType  value of cobol type from column D
     * @param description value from column F
     * @param required    value from column L
     *
     * @throws com.asseco.pl.ssr.tools.messages.exceptions.MessageExceptions.UknownCobolFieldFormatException       can not format cobol field
     * @throws com.asseco.pl.ssr.tools.messages.exceptions.MessageExceptions.UnableToParseFieldSizeExeception      can not parse field size
     * @throws com.asseco.pl.ssr.tools.messages.exceptions.MessageExceptions.UnableToParseFieldPresisionExeception can not parse presision of field
     */
    public void addTableField( String tableName, String cobolName, String coboleType, String description, Boolean required )

            throws MessageExceptions.UknownCobolFieldFormatException,
                   MessageExceptions.UnableToParseFieldSizeExeception,
                   MessageExceptions.UnableToParseFieldPresisionExeception {

        // check if table is in map if not add it.
        if ( !this.tables.containsKey( tableName ) ) {
            this.tables.put( tableName, new LinkedList<>() );
        }

        // add field
        this.tables.get( tableName ).add( createField( cobolName, coboleType, description, required ) );
    }

    private Field createField( String cobolName, String cobolType, String description, Boolean required )
            throws MessageExceptions.UknownCobolFieldFormatException,
                   MessageExceptions.UnableToParseFieldSizeExeception,
                   MessageExceptions.UnableToParseFieldPresisionExeception {
        return new Field( cobolName, cobolType, description, required );
    }

    /**
     * Representation of single row in excel message
     */
    public class Field {

        private final String cobolName;             // column C
        private final String cobolType;             // column D
        private String description;                 // column F
        private final Boolean required;             // column L

        private final String variableName;          // variable for WscFields
        private String javaVariable;                // variable for ejb interface and implementation
        private FieldType javaType;                 // variable type
        private final Integer fieldSize;            // size for ejb
        private final Integer fieldPrecision;       // precision for ejb, ONLY for BigDecimal

        /**
         * Create field representation
         *
         * @param cobolName   value of cobol name from column C
         * @param cobolType   value of cobol type from column D
         * @param description value from column F
         * @param required    value from column L
         *
         * @throws com.asseco.pl.ssr.tools.messages.exceptions.MessageExceptions.UknownCobolFieldFormatException       can not format cobol field
         * @throws com.asseco.pl.ssr.tools.messages.exceptions.MessageExceptions.UnableToParseFieldSizeExeception      can not parse field size
         * @throws com.asseco.pl.ssr.tools.messages.exceptions.MessageExceptions.UnableToParseFieldPresisionExeception can not parse presision of field
         */
        public Field( String cobolName, String cobolType, String description, Boolean required )
                throws MessageExceptions.UknownCobolFieldFormatException,
                       MessageExceptions.UnableToParseFieldSizeExeception,
                       MessageExceptions.UnableToParseFieldPresisionExeception {

            this.cobolName = cobolName;
            this.cobolType = cobolType;
            this.description = description;
            this.required = required;

            this.variableName = cobolName.replaceAll( "-", "_" );
            this.javaVariable = CaseFormat.UPPER_UNDERSCORE.to( CaseFormat.LOWER_CAMEL, this.variableName );
            this.javaType = FieldType.getType( cobolType.substring( 0, 1 ) );
            this.fieldSize = getSize( cobolType );
            this.fieldPrecision = getPrecision( cobolType, this.javaType );

        }

        /**
         * @return field description from column F
         */
        public String getDescription() {
            return description;
        }

        /**
         * Set custom description of field
         *
         * @param description custom field description
         */
        public void setDescription( String description ) {
            this.description = description;
        }

        /**
         * @return created java camelCase variable name
         */
        public String getJavaVariable() {
            return javaVariable;
        }

        /**
         * Set custom java variable
         *
         * @param javaVariable custom value java variable name
         */
        public void setJavaVariable( String javaVariable ) {
            this.javaVariable = javaVariable;
        }

        /**
         * @return parse to {@link FieldType} enum representation of java type
         *
         * @see FieldType
         */
        public FieldType getJavaType() {
            return javaType;
        }

        /**
         * Set custom java type
         *
         * @param javaType custom {@link FieldType} value
         */
        public void setJavaType( FieldType javaType ) {
            this.javaType = javaType;
        }

        /**
         * @return cobol name value form column C
         */
        public String getCobolName() {
            return cobolName;
        }

        /**
         * Get precision from cobol format. <br>
         * When type is NOT DECIMAL or MONEY it will be return 0 as presision of field
         *
         * @param cobolFormat String representation of cobol format as for example: S9(2)P9(10)
         * @param type        FieldType of cobol format
         *
         * @return precision of cobolFormat as Integer
         *
         * @see Message.FieldType
         *
         * @throws com.asseco.pl.ssr.tools.messages.exceptions.MessageExceptions.UnableToParseFieldPresisionExeception can not parse to Integer or can not find numeric pattern
         */
        public final Integer getPrecision( String cobolFormat, FieldType type )
                throws MessageExceptions.UnableToParseFieldPresisionExeception {

            Pattern _pattern = Pattern.compile( "\\([0-9]+\\)" );
            Matcher _matcher = _pattern.matcher( cobolFormat );
            if ( type == FieldType.DECIMAL || type == FieldType.MONEY ) {
                if ( _matcher.find( 1 ) ) {
                    try {
                        return Integer.parseInt( cobolFormat.substring( _matcher.start() + 1, _matcher.end() - 1 ) );
                    } catch (NumberFormatException ex) {
                        throw new MessageExceptions.UnableToParseFieldPresisionExeception( cobolFormat, ex );
                    }
                }
            } else {
                return 0;
            }

            throw new MessageExceptions.UnableToParseFieldPresisionExeception( cobolFormat );
        }

        /**
         *
         * Get field size from cobol format
         *
         * @param cobolFormat String representation of cobol format as for example: X(1)
         *
         * @return size of cobolFormat as Integer
         *
         * @throws com.asseco.pl.ssr.tools.messages.exceptions.MessageExceptions.UnableToParseFieldSizeExeception can not be parse into Integer or can not find numeric pattern
         */
        public final Integer getSize( String cobolFormat )
                throws MessageExceptions.UnableToParseFieldSizeExeception {

            Pattern _pattern = Pattern.compile( "\\([0-9]+\\)" );
            Matcher _matcher = _pattern.matcher( cobolFormat );

            if ( _matcher.find() ) {
                try {
                    return Integer.parseInt( cobolFormat.substring( _matcher.start() + 1, _matcher.end() - 1 ) );
                } catch (NumberFormatException ex) {
                    throw new MessageExceptions.UnableToParseFieldSizeExeception( cobolFormat, ex );
                }
            }

            throw new MessageExceptions.UnableToParseFieldSizeExeception( cobolFormat );
        }
    }

    public enum FieldType {
        STRING( "String", "X", "D" ),
        LONG( "Long", "9" ),
        DATE( "Date", "Y" ),
        TIME( "Date", "C" ),
        DECIMAL( "BigDecimal", "S" ),
        MONEY( "BigDecimal" );

        private final List<String> cobolPrefix;
        private final String javaType;

        private FieldType( String type, String... prefix ) {
            this.javaType = type;
            this.cobolPrefix = Arrays.asList( prefix != null ? prefix : new String[]{} );
        }

        /**
         * Get field type by searching in known prefix in specified java format
         *
         * @param cobolPrefix single character prefix as ex.: X or D
         *
         * @return enumerated filed type
         *
         * @throws com.asseco.pl.ssr.tools.messages.exceptions.MessageExceptions.UknownCobolFieldFormatException none of known prefix matches with cobolPrefix arg
         */
        public static FieldType getType( String cobolPrefix )
                throws MessageExceptions.UknownCobolFieldFormatException {

            for ( FieldType type : values() ) {
                if ( type.cobolPrefix.contains( cobolPrefix ) ) {
                    return type;
                }
            }

            throw new MessageExceptions.UknownCobolFieldFormatException( cobolPrefix );
        }
    }

}
