package com.example.project12;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project12.entity.Bunga;
import com.example.project12.model.BungaModel;


public class TambahBunga extends AppCompatActivity {

    private EditText edtNama, edtHarga;
    private Button btnHapus;

    private Bunga bungaTerpilih;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_bunga);

        this.edtNama = this.findViewById(R.id.edt_nama);
        this.edtHarga = this.findViewById(R.id.edt_harga);
        this.btnHapus = this.findViewById(R.id.btn_hapus);

        // Cek apakah ada Extra di intentnya.
        Intent intentTitipan = this.getIntent();
        int bungaId = intentTitipan.getIntExtra("bungaId", -1);

        // Apabila ada maka ambil data kontak dari DB sesuai dengan Id yang ada di Extra-nya Intent.
        if(bungaId != -1)
        {
            // Ambil dari database..
            this.muatData(bungaId);
        }

        this.sesuaikanTampilan();
    }

    private void sesuaikanTampilan()
    {
        if(this.bungaTerpilih == null)
            this.btnHapus.setVisibility(View.GONE);
        else
            this.btnHapus.setVisibility(View.VISIBLE);
    }

    private void muatData(int bungaId)
    {
        BungaModel mBunga = new BungaModel(this);

        Bunga b = mBunga.cariBerdasarkanId(bungaId);

        this.edtNama.setText(b.getNama());
        this.edtHarga.setText(b.getHarga());

        this.bungaTerpilih = b;
    }

    public void btnSimpan_onClick(View view)
    {
        // Ambil data dari komponen
        String nama = this.edtNama.getText().toString();
        String harga = this.edtHarga.getText().toString();

        // Panggil Modelnya
        BungaModel mBunga = new BungaModel(this);


        if(this.bungaTerpilih == null) // Jika tidak ada kontak terpilih berarti saat ini adalah window untuk menambah/mengurange data Pak..
        {
            // Masukkan ke Entity-nya
            Bunga b = new Bunga();
            b.setNama(nama);
            b.setHarga(harga);

            mBunga.tambahBunga(b);
        }
        else
        {
            this.bungaTerpilih.setNama(nama);
            this.bungaTerpilih.setHarga(harga);

            mBunga.perbaruiBunga(this.bungaTerpilih);
        }

        Toast.makeText(this, "Data telah disimpan..", Toast.LENGTH_SHORT).show();
    }

    public void btnKembali_onClick(View view)
    {
        this.finish();
    }

    public void btnHapus_onClick(View view)
    {
        BungaModel mBunga = new BungaModel(this);

        mBunga.hapusBunga(this.bungaTerpilih);

        this.edtNama.setText("");
        this.edtHarga.setText("");

        this.bungaTerpilih = null;

        this.sesuaikanTampilan();

        Toast.makeText(this, "Data telah dihapus..", Toast.LENGTH_SHORT).show();
    }

}