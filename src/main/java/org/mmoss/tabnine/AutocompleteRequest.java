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
