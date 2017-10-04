package ru.asemenov;

import org.junit.Test;

public class HibTest {

    @Test
    public void testik() {
        String a = "qwe";
        String a1 = "qwe";
        String a2 = new String("qwe");
        String b = "asd";
        System.out.println(a + b);

        String c = "123";
        String d = "456";
        System.out.println(c + d);

        int e = 1;
        int f = 2;
        System.out.println(e+f);

        System.out.println(a == a1);
        System.out.println(a == a2);
    }
}
