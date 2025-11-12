package edu.bluejack25_1.pakaian_sepatu.model;

public class Product {

    private int image;
    private String nama;
    private String harga;

    public Product(){}

    public Product(String harga, String nama, int image) {
        this.harga = harga;
        this.nama = nama;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
