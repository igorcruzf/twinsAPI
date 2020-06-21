package org.twins.model;

import javax.validation.constraints.NotNull;

public class Data {
    @NotNull
    private String name;
    private String city;
    private int age;

    public Data(){};

    public Data(String name, String city, int age) {
        this.name = name;
        this.city = city;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
