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

import java.nio.file.Path;

public class AutocompleteRequest extends Request {
  /**
   * Constructs a new AutocompleteRequest.
   * 
   * @param before The text prior to the current cursor.
   * @param after The text after the current cursor.
   * @param region_includes_beginning Whether or not the 'before' text extends to the beginning of 
   *                                  the file.
   * @param region_includes_end Whether or not the 'after' text extends to the end of the file.
   * @param filename The name of the file containing the 'before' and 'after' text.
   * @param max_num_results The maximum number of results to return.
   */
  public AutocompleteRequest(String before,
                             String after,
                             boolean region_includes_beginning,
                             boolean region_includes_end,
                             Path filename,
                             int max_num_results) {
    setRequestName("Autocomplete");
    setRequestParameter("before", before);
    setRequestParameter("after", after);
    setRequestParameter("region_includes_beginning", region_includes_beginning);
    setRequestParameter("region_includes_end", region_includes_end);
    setRequestParameter("filename", filename.toString());
    setRequestParameter("max_num_results", max_num_results);
  }
}
