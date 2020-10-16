package com.example.project12.entity;

public class Bunga // Nama tabel: kontak
{
    private int id; // Nama kolom id
    private String nama; // Nama kolom nama
    private String harga; // Nama kolom nomor_telepon

    public Bunga()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
