package com.mobile.fatah.learnrequestapi.detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mobile.fatah.learnrequestapi.R;
import com.mobile.fatah.learnrequestapi.model.BeritaItem;
import com.mobile.fatah.learnrequestapi.network.ConfigRetrofit;

import retrofit2.http.GET;


public class DetailBerita extends AppCompatActivity {

    TextView judulBerita, tglPosting, isiBerita, penulis;
    ImageView foto;

    //TODO buat variable secara const sebagai key nya atau penampung
    public static final String EXTRA_OBJECT = "OBJECT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);

        foto = findViewById(R.id.img_berita);
        judulBerita = findViewById(R.id.judul_berita);
        tglPosting = findViewById(R.id.tanggal_posting);
        isiBerita = findViewById(R.id.isi_berita);
        penulis = findViewById(R.id.penulis);


        //TODO cara get nya pake parcelable
        BeritaItem get = getIntent().getParcelableExtra(EXTRA_OBJECT);
        judulBerita.setText(get.getJudulBerita());
        tglPosting.setText(get.getTanggalPosting());
        isiBerita.setText(get.getIsiBerita());
        penulis.setText(get.getPenulis());

        Glide.with(DetailBerita.this)
                .load(get.getFoto())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(foto);




        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(judulBerita.getText().toString());

    }
}
/** getFoto = getIntent().getStringExtra("FOTO");
 judulBerita.setText(ambil.getStringExtra("JUDUL"));
 tglPosting.setText(ambil.getStringExtra("TANGGAL"));
 isiBerita.setText(ambil.getStringExtra("ISI"));
 penulis.setText(ambil.getStringExtra("PENULIS"));*/

/**Glide.with(DetailBerita.this)
 .load(url_image + getFoto)
 .placeholder(R.mipmap.ic_launcher)
 .error(R.mipmap.ic_launcher)
 .into(foto);*/