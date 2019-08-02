package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String path;
        if (args.length == 0) {
            path = "css/in.css";
        } else {
            path = args[0];
        }
        try {
            Parser p = new Parser(path);
            p.parse();
            p.write();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
