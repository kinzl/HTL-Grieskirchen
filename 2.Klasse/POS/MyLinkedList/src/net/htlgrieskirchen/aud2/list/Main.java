/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.htlgrieskirchen.aud2.list;

/**
 *
 * @author pbogner18
 */
public class Main {

    public static void main(String[] args) {
        MyList<String> ml = new MyList();

        ml.add("a");
        System.out.println(ml.get(0));
    }
}
