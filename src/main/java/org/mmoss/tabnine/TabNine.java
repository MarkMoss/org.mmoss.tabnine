package org.mmoss.tabnine;

import java.nio.file.Path;

public class TabNine {
  
  private final Manager manager;
  
  public TabNine(Path tabNinePath){
    this.manager = new Manager(tabNinePath); 
  }
  
  public TabNine(Manager manager){
    this.manager = manager;
  }
  
  public void close() {
    manager.close();
  }
  
  public AutocompleteResponse Autocomplete(AutocompleteRequest req) throws Exception { 
    return AutocompleteResponse.fromJson(manager.request(req.Serialize()));
  }
  
}
