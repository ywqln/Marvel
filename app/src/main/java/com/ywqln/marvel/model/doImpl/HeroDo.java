package com.ywqln.marvel.model.doImpl;

import com.ywqln.marvel.model.Hero;

/**
 * 描述：英雄
 * <p>
 *
 * @author yanwenqiang
 * @date 2019/1/13
 */
public class HeroDo implements Hero {
    private String name;
    private String gender;
    private int age;
    private String power;

    public String getName() {
        return name;
    }

    public HeroDo setName(String name) {
        this.name = name;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public HeroDo setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public int getAge() {
        return age;
    }

    public HeroDo setAge(int age) {
        this.age = age;
        return this;
    }

    public String getPower() {
        return power;
    }

    public HeroDo setPower(String power) {
        this.power = power;
        return this;
    }

    @Override
    public String toString() {
        return "HeroDo{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", power='" + power + '\'' +
                '}';
    }
}
