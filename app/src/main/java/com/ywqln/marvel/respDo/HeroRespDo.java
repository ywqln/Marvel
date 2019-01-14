package com.ywqln.marvel.respDo;

/**
 * 描述:英雄任务.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/14
 */
public class HeroRespDo {
    private String name;
    private String gender;
    private int age;
    private String power;

    public String getName() {
        return name;
    }

    public HeroRespDo setName(String name) {
        this.name = name;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public HeroRespDo setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public int getAge() {
        return age;
    }

    public HeroRespDo setAge(int age) {
        this.age = age;
        return this;
    }

    public String getPower() {
        return power;
    }

    public HeroRespDo setPower(String power) {
        this.power = power;
        return this;
    }

    @Override
    public String toString() {
        return "HeroRespDo{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", power='" + power + '\'' +
                '}';
    }
}
