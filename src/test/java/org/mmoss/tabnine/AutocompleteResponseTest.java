package org.mmoss.tabnine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.Test;

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
