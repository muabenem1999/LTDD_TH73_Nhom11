package com.example.homeworkout.Model;
public class Bai {
    int id_mon;
    byte[] hinhBai;
    String tenBai;
    String HuongDan;

    public Bai(int id_mon, String tenBai, String huongDan, byte[] hinhBai) {
        this.id_mon = id_mon;
        this.hinhBai = hinhBai;
        this.tenBai = tenBai;
        HuongDan = huongDan;
    }

    public int getId_mon() {
        return id_mon;
    }

    public void setId_mon(int id_mon) {
        this.id_mon = id_mon;
    }

    public byte[] getHinhBai() {
        return hinhBai;
    }

    public void setHinhBai(byte[] hinhBai) {
        this.hinhBai = hinhBai;
    }

    public String getTenBai() {
        return tenBai;
    }

    public void setTenBai(String tenBai) {
        this.tenBai = tenBai;
    }

    public String getHuongDan() {
        return HuongDan;
    }

    public void setHuongDan(String huongDan) {
        HuongDan = huongDan;
    }
}

