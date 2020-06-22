package com.example.homeworkout.Model;

public class Lich {
    private int Id ,weight,height;
    private double bmi;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public Lich(int id, int weight, int height, double bmi) {
        Id = id;
        this.weight = weight;
        this.height = height;
        this.bmi = bmi;
    }
}
