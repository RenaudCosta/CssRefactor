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

    @Override
    public boolean equals(Object p) {
        if (this == p)
            return true;
        if (!(p instanceof Property))
            return false;
        Property pp = (Property)p;
        return name.equals(pp.getName()) && value.equals(pp.getValue());
    }
}
