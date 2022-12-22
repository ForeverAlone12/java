package org.example;

import java.util.TreeMap;

class M {
    double  temp;

    @Override
    public String toString() {
        return "," + temp;
    }
}

public class Response {
    String name;

    M main;

    @Override
    public String toString() {
        return name + main.toString();
    }
}
