package edu.bluejack25_1.fix_uts.model;

public class Product {

    private int image;
    private String nama;
    private String harga;

    public Product(){};

    public Product(int image, String nama, String harga) {
        this.image = image;
        this.nama = nama;
        this.harga = harga;
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
