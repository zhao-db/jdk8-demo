package com.example.jdk8.jdk8demo.desgin;

/**
 * @author Collin
 * @date 2021/5/27 11:50 下午
 */
public class Test {

    public static void main(String[] args) {

        Character c = new Queen();
        c.setWeaponBeahvior(new SwordBehavoir());
        c.fight();


    }

}
