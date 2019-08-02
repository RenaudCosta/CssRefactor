package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private String[] tests = {"spacing", "duplicate_selectors", "duplicate_property"};

    @org.junit.jupiter.api.Test
    void parse() {
        for (String test : tests) {
            try {
                Parser p = new Parser("test/resources/in/" + test + ".css", "test/resources/out/"+ test + ".css");
                p.parse();
                p.write();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @org.junit.jupiter.api.Test
    void write() {
        for (String test : tests) {
            File expected = new File("test/resources/expected/" + test + ".css");
            File out = new File("test/resources/out/" + test + ".css");
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
                if (!line1.equals(line2))
                {
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