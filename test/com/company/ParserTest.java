package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private String[] tests = {"spacing", "duplicate_selector", "duplicate_property", "unknown_property"};

    private Map<String, Map<String, Selector>> expectedSelectors = new HashMap<>();

    ParserTest() {
        initSpacing();
        initDuplicateSelector();
        initDuplicateProperty();
        initUnknownProperty();
    }

    private void initSpacing() {
        Selector c1 = new Selector(".class", Arrays.asList(new Property[]{new Property("text-align", "center")}));
        Selector c2 = new Selector(".test", Arrays.asList(new Property[]{new Property("color", "white")}));
        Selector c3 = new Selector(".hey", Arrays.asList(new Property[]{new Property("display", "inline")}));
        Selector c4 = new Selector(".hello", Arrays.asList(new Property[]{new Property("display", "none")}));
        Map<String, Selector> selectors = new HashMap<>();
        selectors.put(".class", c1);
        selectors.put(".test", c2);
        selectors.put(".hey", c3);
        selectors.put(".hello", c4);
        expectedSelectors.put("spacing", selectors);
    }

    private void initDuplicateSelector() {
        Selector c1 = new Selector(".class", Arrays.asList(new Property[]{new Property("text-align", "center"),
                new Property("display", "inline"), new Property("color", "purple")}));
        Selector c2 = new Selector(".test", Arrays.asList(new Property[]{new Property("color", "white")}));
        Map<String, Selector> selectors = new HashMap<>();
        selectors.put(".class", c1);
        selectors.put(".test", c2);
        expectedSelectors.put("duplicate_selector", selectors);
    }

    private void initDuplicateProperty() {
        Selector c1 = new Selector(".class", Arrays.asList(new Property[]{new Property("text-align", "left"),
                new Property("display", "inline")}));
        Selector c2 = new Selector("#id", Arrays.asList(new Property[]{new Property("display", "none")}));
        Map<String, Selector> selectors = new HashMap<>();
        selectors.put(".class", c1);
        selectors.put("#id", c2);
        expectedSelectors.put("duplicate_property", selectors);
    }

    private void initUnknownProperty() {
        Selector c1 = new Selector(".class", Arrays.asList(new Property[]{new Property("text-align", "left"),
                new Property("display", "inline")}));
        Selector c2 = new Selector("#id", Arrays.asList(new Property[]{new Property("display", "none")}));
        Map<String, Selector> selectors = new HashMap<>();
        selectors.put(".class", c1);
        selectors.put("#id", c2);
        expectedSelectors.put("unknown_property", selectors);
    }

    @org.junit.jupiter.api.Test
    void parse() {
        for (String test : tests) {
            try {
                Parser p = new Parser("test/resources/in/" + test + ".css", "test/resources/out/" + test + ".css");
                p.parse();
                Map<String, Selector> selectors = expectedSelectors.get(test);
                System.out.println("Parsing "+test);
                assertEquals(selectors, p.getSelectors());

                // Generate output files for next test
                p.write();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    @org.junit.jupiter.api.Test
    void write() {
        for (String test : tests) {
            File expected = new File("test/resources/expected/" + test + ".css");
            File out = new File("test/resources/out/" + test + ".css");
            System.out.println("Writing "+test);
            assertTrue(areFilesEqual(expected, out));
        }
    }

    private static boolean areFilesEqual(File expected, File out) {
        try {
            BufferedReader br1 = new BufferedReader(new FileReader(expected));
            BufferedReader br2 = new BufferedReader(new FileReader(out));
            String line1, line2;
            while ((line1 = br1.readLine()) != null) {
                line2 = br2.readLine();
                if (!line1.equals(line2)) {
                    System.err.println(line1);
                    System.err.println(line2);
                    System.err.println();
                    return false;
                }
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}