package com.example.project12.model;

import android.content.Context;
import android.database.Cursor;


import com.example.project12.entity.Bunga;
import com.example.project12.helper.DbHelper;

import java.util.ArrayList;

public class BungaModel {
    private Context context;
    private DbHelper db;

    public BungaModel(Context context)
    {
        this.context = context;
        this.db = new DbHelper(this.context);
    }

    public void tambahBunga(Bunga b)
    {
        String nama = b.getNama();
        String harga = b.getHarga();

        // Rangkai ke dalam SQL
        // Contoh: INSERT INTO kontak (nama, nomor_telepon) VALUES ('Yoppy', '085-123-456')
        String sql = "INSERT INTO bunga (nama, harga) VALUES ('" + nama + "', '" + harga + "')";

        // Langsung lempar ke dbHelper
        this.db.tulisData(sql);
    }

    public void hapusBunga(Bunga k)
    {
        // Buat SQL untuk hapus
        String sql = "DELETE FROM bunga WHERE id = '" + k.getId() + "'";

        // Panggil db.tulisData(sql)
        db.tulisData(sql);
    }

    public void perbaruiBunga(Bunga b)
    {
        int id = b.getId();
        String nama = b.getNama();
        String harga = b.getHarga();

        // Buat SQL untuk update
        String sql = "UPDATE bunga SET nama = '" + nama + "', harga = '" + harga + "' WHERE id = '" + id + "'";

        // Tulis ke DB
        db.tulisData(sql);
    }

    public ArrayList<Bunga> ambilSemuaBunga()
    {
        String sql = "SELECT * FROM bunga";

        Cursor c = this.db.bacaData(sql);

        // Ubah cursor menjadi ArrayList
        ArrayList<Bunga> hasil = new ArrayList<>();

        if(c.getCount() < 1)
            return hasil;

        c.moveToFirst(); // Pasang di baris ke-1

        do {
            // Mengambil data dari kolom-kolom di tabel sesuai URUTANNYA.
            // Urutannya --> Db Helper saat kita membuat tabel kontak
            int id = c.getInt(0); // Kolom ke-0 INTEGER
            String nama = c.getString(1); // Kolom ke-1 VARCHAR
            String harga = c.getString(2); // Kolom ke-2 VARCHAR

            Bunga b = new Bunga();
            b.setId(id);
            b.setNama(nama);
            b.setHarga(harga);

            // Setiap kontak yang didapat dimasukkan ke ArrayList hasil;
            hasil.add(b);
        }
        while(c.moveToNext());

        return hasil;
    }

    // untuk mencari kontak berdasarkan ID
    public Bunga cariBerdasarkanId(int id)
    {
        String sql = "SELECT * FROM bunga WHERE id = '" + id + "'";

        Cursor c = this.db.bacaData(sql);

        if(c.getCount() > 0)
        {
            c.moveToFirst();

            String nama = c.getString(1);
            String harga = c.getString(2);

            Bunga b = new Bunga();
            b.setId(id);
            b.setNama(nama);
            b.setHarga(harga);

            return b;
        }

        return null;
    }
}
