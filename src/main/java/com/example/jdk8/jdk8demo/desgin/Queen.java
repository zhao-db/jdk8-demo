package com.example.jdk8.jdk8demo.desgin;

/**
 * @author Collin
 * @date 2021/5/27 11:43 下午
 */
public class Queen extends Character {

    @Override
    public void fight() {
        System.out.println("queen fight with ");
        getWeaponBeahvior().userWeapon();
    }
}
