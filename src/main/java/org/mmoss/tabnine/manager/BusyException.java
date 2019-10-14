package org.mmoss.tabnine.manager;

public class BusyException extends TabNineException {
  
  /**
   * Generated serialVersionUID
   */
  private static final long serialVersionUID = -7301117525829780168L;
    
  /**
   * Constructor.  
   * 
   * @param message Exception message.  Passed unchanged to the RuntimeException's constructor.
   * 
   * @param request The request which timed out.  This may be retrieved by calling the getRequest
   *                method.
   */
  public BusyException(String message, String request) {
    super(message, request);
  }
}
