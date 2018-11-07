package com.mobile.fatah.learnrequestapi.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mobile.fatah.learnrequestapi.R;
import com.mobile.fatah.learnrequestapi.detail.DetailBerita;
import com.mobile.fatah.learnrequestapi.model.BeritaItem;
import com.mobile.fatah.learnrequestapi.network.ConfigRetrofit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterBerita extends RecyclerView.Adapter<AdapterBerita.ViewHolder> {

    //TODO deklarasi
    private Context ctx;
    private List<BeritaItem> dataBerita;

    //TODO create function / method constructor / wajib ada
    public AdapterBerita(Context ctx, List<BeritaItem> dataBerita) {
        this.ctx = ctx;
        this.dataBerita = dataBerita;
    }

    //TODO menyisipkan sebuah layout kedalam adapter dan recyclerview
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup vg, int i) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.item_berita, vg, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    //TODO eksekusi data yang mau ditampilkan
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

        //TODO tampung data nya ke variable
        final String judul = dataBerita.get(position).getJudulBerita();
        final String images = ConfigRetrofit.IMAGES + dataBerita.get(position).getFoto();
        final String posting = dataBerita.get(position).getTanggalPosting();
        final String penulis = dataBerita.get(position).getPenulis();
        final String content = dataBerita.get(position).getIsiBerita();

        viewHolder.tvJudulBerita.setText(judul);
        viewHolder.tvPenulis.setText(penulis);
        viewHolder.tvTglTerbit.setText(posting);
        Glide.with(ctx)
                .load(images)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(viewHolder.ivGambarBerita);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO buat object dari class yang mau kita kirim
                BeritaItem data = new BeritaItem();
                data.setJudulBerita(judul);
                data.setFoto(images);
                data.setPenulis(penulis);
                data.setTanggalPosting(posting);
                data.setIsiBerita(content);

                //TODO intent data nya
                Intent passdata = new Intent(ctx, DetailBerita.class);
                passdata.putExtra(DetailBerita.EXTRA_OBJECT, data);
                ctx.startActivity(passdata);

                /**passdata.putExtra("JUDUL", dataBerita.get(position).getJudulBerita());
                 passdata.putExtra("TANGGAL", dataBerita.get(position).getTanggalPosting());
                 passdata.putExtra("ISI", dataBerita.get(position).getIsiBerita());
                 passdata.putExtra("PENULIS", dataBerita.get(position).getPenulis());
                 passdata.putExtra("FOTO", dataBerita.get(position).getFoto());*/
            }
        });

    }


    //TODO return data yang ingin ditampilkan / proses looping
    @Override
    public int getItemCount() {
        if (dataBerita == null) return 0;
        return dataBerita.size();
    }

    static

    //TODO deklarasi widget atau component nya
    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_gambar_berita)
        ImageView ivGambarBerita;
        @BindView(R.id.tv_judul_berita)
        TextView tvJudulBerita;
        @BindView(R.id.tv_tgl_terbit)
        TextView tvTglTerbit;
        @BindView(R.id.tv_penulis)
        TextView tvPenulis;

        //TODO casting view / hub dgn id nya masing"
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
