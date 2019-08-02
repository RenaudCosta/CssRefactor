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

    public Selector(String name, List<Property> properties) {
        this.name = name;
        this.properties = properties;
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

    @Override
    public boolean equals(Object s) {
        if (this == s)
            return true;
        if (!(s instanceof Selector))
            return false;
        Selector ss = (Selector)s;
        return name.equals(ss.name) && properties.containsAll(ss.properties) && ss.properties.containsAll(properties);
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
