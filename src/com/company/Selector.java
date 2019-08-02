package com.company;

import java.util.ArrayList;
import java.util.List;

public class Selector {

    private String name;
    private List<Property> properties;

    public Selector(String name) {
        this.name = name;
        this.properties = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void addProperty(Property p) {
        properties.removeIf(e -> p.getName().equals(e.getName()));
        properties.add(p);
    }

    public boolean equals(Selector s) {
        return name.equals(s.name) && properties.containsAll(s.properties) && s.properties.containsAll(properties);
    }

    public String toString() {
        StringBuilder out = new StringBuilder(this.name + " {\n");
        for (Property p : properties) {
            out.append(p.toString());
        }
        out.append("}\n\n");
        return out.toString();
    }
}
