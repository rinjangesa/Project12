package com.example.project12;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.project12.entity.Bunga;
import com.example.project12.model.BungaModel;

import java.util.ArrayList;

public class LihatBunga extends AppCompatActivity implements AdapterView.OnItemClickListener {

    // Jadikan properti kelas
    private ListView lstDaftarBunga;

    // Adapter untuk ListView
    private ArrayAdapter<String> adapterDaftarBunga;

    // Daftar nama dalam bentuk ArrayList<String>
    private ArrayList<String> daftarNama; // Contoh: ['Adi', 'Budi', 'Cici']

    // Daftar kontak dari database
    private ArrayList<Bunga> daftarBunga;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_bunga);

        this.lstDaftarBunga = this.findViewById(R.id.lst_daftar_bunga);

        this.daftarNama = new ArrayList<>(); // [], Array yang belum punya isi, tetapi TIDAK null.

        this.isiDaftarNama();

        this.adapterDaftarBunga = new ArrayAdapter<>(
                this, // Konteks/Activity yang memanggil
                android.R.layout.simple_list_item_1, // Template bawaan Android
                this.daftarNama // Dari mana datanya diambil
        );

        // Pasangkan adapter ke ListView
        this.lstDaftarBunga.setAdapter(this.adapterDaftarBunga);

        // Ketika list item diklik muncul toast.
        // Event Listener
        // Tentang: objek yang "mendengarkan" apa yang dialami oleh objek yang didengarkan
        this.lstDaftarBunga.setOnItemClickListener(this);
    }

    private void isiDaftarNama()
    {
        /*
        this.daftarNama.add("Adi");
        this.daftarNama.add("Budi");
        this.daftarNama.add("Cici");
         */

        BungaModel mBunga = new BungaModel(this);

        ArrayList<Bunga> daftarBunga = mBunga.ambilSemuaBunga();

        for (Bunga b : daftarBunga)
        {
            this.daftarNama.add(b.getNama());
        }

        // Kita simpan daftarKontaknya ke property class
        this.daftarBunga = daftarBunga;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        // Lakukan apa saja yang Anda inginkan ketika itemnya ListView diklik.
        // Ambil kontak dari array sesuai dengan posisi klik oleh user
        Bunga bungaDiKlik = this.daftarBunga.get(position);

        int bungaId = bungaDiKlik.getId();

        Intent i = new Intent(LihatBunga.this, TambahBunga.class);
        i.putExtra("bungaId", bungaId);
        this.startActivity(i);
    }

    public void btnKembali_onClick(View view)
    {
        this.finish();
    }

    public void btnRefresh_onClick(View view)
    {
        this.daftarBunga.clear();

        this.daftarNama.clear();

        this.adapterDaftarBunga.clear();

        this.isiDaftarNama();

        this.adapterDaftarBunga.notifyDataSetChanged();

        Toast.makeText(this, "Data telah di-refresh..", Toast.LENGTH_SHORT).show();
    }
}