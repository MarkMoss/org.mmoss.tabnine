package org.mmoss.tabnine;

/**
 * Indicates that the operation failed, and that future operations are likely to fail, due to the
 * problem described.
 *
 */
public class FatalException extends TabNineException {
  
  /**
   * Generated serialVersionUID
   */
  private static final long serialVersionUID = -5412313506865229924L;

  public FatalException(String message, String request) {
    super(message,request);
  }
  
  /**
   * Constructor.  
   * 
   * @param message Exception message.  Passed unchanged to the RuntimeException's constructor.
   * 
   * @param request The request which failed.  This may be retrieved by calling the getRequest
   *                method.
   */
  public FatalException(String message) {
    super(message, null);
  }

}
