/**
 * 
 */
package org.dataone.exceptions;

/**
 * A class to represent any exception during  marshalling or unmarshalling
 * data objects, typically org.dataone.service.types, but not exclusively.
 * 
 * @author rnahf
 *
 */
public class MarshallingException extends Exception {

    /**
     * 
     */
    public MarshallingException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public MarshallingException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public MarshallingException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public MarshallingException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public MarshallingException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

}
