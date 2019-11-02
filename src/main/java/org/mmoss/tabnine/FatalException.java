/* 
 * Copyright (c) 2019 Mark Moss
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, 
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, 
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or 
 * substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT 
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

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
