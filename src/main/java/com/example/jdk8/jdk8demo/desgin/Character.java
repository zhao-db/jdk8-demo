package com.example.jdk8.jdk8demo.desgin;

/**
 * @author Collin
 * @date 2021/5/27 11:42 下午
 */
public abstract class Character {

    WeaponBeahvior weaponBeahvior;

    public WeaponBeahvior getWeaponBeahvior() {
        return weaponBeahvior;
    }

    public void setWeaponBeahvior(WeaponBeahvior weaponBeahvior) {
        this.weaponBeahvior = weaponBeahvior;
    }

    public abstract void fight();

}
