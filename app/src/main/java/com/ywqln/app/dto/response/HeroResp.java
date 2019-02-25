package com.ywqln.app.dto.response;

/**
 * 描述:英雄任务.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/14
 */
public class HeroResp {
    private String name;
    private String gender;
    private int age;
    private String power;

    public String getName() {
        return name;
    }

    public HeroResp setName(String name) {
        this.name = name;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public HeroResp setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public int getAge() {
        return age;
    }

    public HeroResp setAge(int age) {
        this.age = age;
        return this;
    }

    public String getPower() {
        return power;
    }

    public HeroResp setPower(String power) {
        this.power = power;
        return this;
    }

    @Override
    public String toString() {
        return "HeroResp{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", power='" + power + '\'' +
                '}';
    }
}
