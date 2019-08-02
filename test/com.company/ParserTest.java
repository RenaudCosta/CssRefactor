package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    String[] tests = {"spacing"};

    @org.junit.jupiter.api.Test
    void parse() {
        for (String test : tests) {
            try {
                Parser p = new Parser("test/resources/in/" + test + ".css", "test/resources/out/out_"+ test + ".css");
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
            File expected = new File("test/resources/in/" + test + ".css");
            File out = new File("test/resources/out/out_" + test + ".css");
            assertTrue(areFilesEqual(expected, out));
        }
    }

    private static boolean areFilesEqual(File in, File out) {
        try {
            FileInputStream fis1 = new FileInputStream(in);
            FileInputStream fis2 = new FileInputStream(out);
            int i1 = fis1.read();
            int i2 = fis2.read();
            while (i1 != -1) {
                if (i1 != i2) {
                    return false;
                }
                i1 = fis1.read();
                i2 = fis2.read();
            }
            fis1.close();
            fis2.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}