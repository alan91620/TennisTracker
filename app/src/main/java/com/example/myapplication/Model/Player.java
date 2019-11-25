package com.example.myapplication.Model;

public class Player {

    private String first_name;
    private String last_name;
    private int age;
    private int size;
    private int wheight;
    private String nationality;

    public Player(String first_name, String last_name, int age, int size, int wheight, String nationality) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.size = size;
        this.wheight = wheight;
        this.nationality = nationality;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getWheight() {
        return wheight;
    }

    public void setWheight(int wheight) {
        this.wheight = wheight;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return first_name + " " + last_name;
    }
}
