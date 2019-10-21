package org.mmoss.tabnine;

public class TabNineException extends RuntimeException {
  /**
   * Generated serialVersionUID
   */
  private static final long serialVersionUID = -8166150413749025319L;
  private final String request;
  
  /**
   * Constructor.  
   * 
   * @param message Exception message.  Passed unchanged to the RuntimeException's constructor.
   * 
   * @param request The request which timed out.  This may be retrieved by calling the getRequest
   *                method.
   */
  public TabNineException(String message, String request) {
    super(message);
    this.request = request;
  }
  
  /**
   * Returns the request which timed out.
   * 
   * @return the request which timed out.
   */
  public String getRequest() {
    return this.request;
  }
}
