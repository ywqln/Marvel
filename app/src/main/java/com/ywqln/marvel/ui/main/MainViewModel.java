package com.ywqln.marvel.ui.main;

/**
 * 描述：主界面的ViewModel实现，内部可包含数据请求
 * <p>
 *
 * @author yanwenqiang
 * @date 2019/1/13
 */
public class MainViewModel implements IMainViewModel {
    private String name;
    private String gender;
    private int age;
    private String power;

    public String getName() {
        return name;
    }

    public MainViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public MainViewModel setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public int getAge() {
        return age;
    }

    public MainViewModel setAge(int age) {
        this.age = age;
        return this;
    }

    public String getPower() {
        return power;
    }

    public MainViewModel setPower(String power) {
        this.power = power;
        return this;
    }

    @Override
    public String toString() {
        return "MainViewModel{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", power='" + power + '\'' +
                '}';
    }
}
