package com.example.quiz;

public class Ques {
    int id;
    String Q;
    String O1;
    String O2;
    String O3;
    String O4;

    public String getO1() {
        return O1;
    }

    public String getO2() {
        return O2;
    }

    public String getO3() {
        return O3;
    }

    public String getO4() {
        return O4;
    }

    public Ques(int id, String q, String o1, String o2, String o3, String o4) {
        this.id = id;
        Q = q;
        O1 = o1;
        O2 = o2;
        O3 = o3;
        O4 = o4;
    }

    public String getQ() {
        return Q;
    }
}
