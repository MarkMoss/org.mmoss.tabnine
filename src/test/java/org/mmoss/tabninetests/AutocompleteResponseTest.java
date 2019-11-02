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

package org.mmoss.tabninetests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.mmoss.tabnine.AutocompleteResponse;

public class AutocompleteResponseTest {
   
  @Test
  public void testDecodeStatic() throws IOException {
    String json = "{\"old_prefix\":\"w\",\"results\":[{\"new_prefix\":\"while\",\"old_suffix\":\"\",\"new_suffix\":\"\"},{\"new_prefix\":\"warning\",\"old_suffix\":\"\",\"new_suffix\":\"\"},{\"new_prefix\":\"switch\",\"old_suffix\":\"\",\"new_suffix\":\"\"},{\"new_prefix\":\"new\",\"old_suffix\":\"\",\"new_suffix\":\"\"}],\"user_message\":[\"Establishing connection to TabNine Cloud...\"],\"docs\":[]}";
    AutocompleteResponse r = AutocompleteResponse.fromJson(json);
    assertEquals(r.old_prefix, "w");
    assertEquals(r.results.length, 4);
    assertEquals(r.results[0].new_prefix, "while");
    assertEquals(r.results[0].old_suffix, "");
    assertEquals(r.results[0].new_suffix, "");
    assertEquals(r.results[1].new_prefix, "warning");
    assertEquals(r.results[1].old_suffix, "");
    assertEquals(r.results[1].new_suffix, "");
    assertEquals(r.results[2].new_prefix, "switch");
    assertEquals(r.results[2].old_suffix, "");
    assertEquals(r.results[2].new_suffix, "");
    assertEquals(r.results[3].new_prefix, "new");
    assertEquals(r.results[3].old_suffix, "");
    assertEquals(r.results[3].new_suffix, "");
    assertEquals(r.user_message.length, 1);
    assertEquals(r.user_message[0], "Establishing connection to TabNine Cloud...");
    
    
  }
}
