package com.company;

import java.io.*;
import java.util.*;

public class Parser {

    List<String> lines = new ArrayList<>();
    Map<String, Selector> selectors = new HashMap<>();
    String outputPath = "css/out.css";

    public Parser(String path) throws IOException {
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
    }

    public Parser(String path, String out) throws IOException {
        this(path);
        this.outputPath = out;
    }

    private String purify() {
        for (int i = 0; i < lines.size(); i++) {
            lines.set(i, lines.get(i).trim());
        }
        this.lines.removeIf(e->e.equals(""));
        StringBuilder out = new StringBuilder();
        for (String line : lines) {
            out.append(line);
        }
        return out.toString();
    }

    public void parse() {

        String css = this.purify();

        String[] split = css.split("[}{]");

        String curSelector = "";

        for (int i = 0; i < split.length; i++) {
            split[i] = split[i].trim();
            if (i % 2 == 0) { // Selector
                if (!selectors.containsKey(split[i])) {
                    selectors.put(split[i], new Selector(split[i]));
                }
                curSelector = split[i];
            }
            else { // Properties
                if (!curSelector.equals("")) {
                    for (Property p : parseProperties(split[i]))
                        selectors.get(curSelector).addProperty(p);
                }
            }
        }
    }

    public void write() throws IOException {
        FileWriter fw = new FileWriter(this.outputPath);
        for (Selector s : selectors.values()) {
            fw.write(s.toString());
        }
        fw.close();
    }

    private static List<Property> parseProperties(String strProp) {
        List<Property> props = new ArrayList<>();
        String[] split = strProp.split(";");
        for (String s : split) {
            props.add(new Property(s.split(":")[0].trim(), s.split(":")[1].trim()));
        }
        return props;
    }

}
