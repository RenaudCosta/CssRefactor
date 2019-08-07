package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PropertyChecker {

  private Map<String, String> properties;

  public PropertyChecker() throws IOException {
    this.properties = new HashMap<>();

    File file = new File("src/resources/properties.txt");
    BufferedReader br = new BufferedReader(new FileReader(file));

    String line;
    while ((line = br.readLine()) != null) {
      String[] split = line.split(":");
      this.properties.put(split[0], split[1]);
    }
  }

  public boolean propertyIsValid(Property p) {
    return properties.containsKey(p.getName()) && p.getValue().matches(properties.get(p.getName()));
  }

}
