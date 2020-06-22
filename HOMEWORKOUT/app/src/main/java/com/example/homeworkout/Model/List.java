package com.example.homeworkout.Model;


public class List {
    int Id;
    private byte[] Hinh;
    private String ten;
    private String noidung;
    public List(){

    }
    public List(String ten, byte[] hinh){
        this.Hinh = Hinh;
        this.ten = ten;
    }
    public List(int Id,String ten,String noidung, byte[] Hinh){
        this.Id =Id;
        this.Hinh = Hinh;
        this.ten = ten;
        this.noidung = noidung;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTen(){
        return ten;
    }
    public void setTen(String ten){
        this.ten = ten;
    }
    public String getNoidung(){
        return noidung;
    }
    public void setNoidung(String noidung){
        this.noidung = noidung;
    }
    public byte[] getHinh(){
        return Hinh;
    }
    public void setHinh(byte[] Hinh){
        this.Hinh = Hinh;
    }


}




