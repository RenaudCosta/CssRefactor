package com.company;

public class Property {
    private String name;
    private String value;

    public Property(String n, String v) {
        name = n;
        value = v;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return "\t"+name+": "+value+";\n";
    }

    public boolean equals(Property p) {
        return name.equals(p.getName()) && value.equals(p.getValue());
    }
}
