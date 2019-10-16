package com.example.riset.Home.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.riset.Berdonasi.Model.BerdonasiUangModel;
import com.example.riset.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DonaturDetailDonasiAdapter extends RecyclerView.Adapter<DonaturDetailDonasiAdapter.ViewHolder>{
    private Context context;
    private List<BerdonasiUangModel> data;

    public DonaturDetailDonasiAdapter(Context context, List<BerdonasiUangModel> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donatur_detail_donasi, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        BerdonasiUangModel results = data.get(position);
        if (results.isAnonim()){
            holder.txtNamaDonatur.setText("Anonim");
        }else{
            holder.txtNamaDonatur.setText(results.getNama());
        }
        holder.txtJumlahDonasi.setText(Integer.toString(results.getNominal()));
        holder.txtDonasiDate.setText(results.getTanggal());
        Glide.with(holder.foto_donatur.getContext())
                .load(R.drawable.placeholder)
                .circleCrop()
                .placeholder(R.drawable.placeholder)
                .into(holder.foto_donatur);
    }

    @Override
    public int getItemCount(){
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/{

        @BindView(R.id.fotoDonatur)
        ImageView foto_donatur;
        @BindView(R.id.nama_donatur)
        TextView txtNamaDonatur;
        @BindView(R.id.jumlahDonasi)
        TextView txtJumlahDonasi;
        @BindView(R.id.donasi_date)
        TextView txtDonasiDate;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
//            itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View view) {
//            String ii = isi.getText().toString();
//            Intent i = new Intent(view.getContext(), Detail.class);
//            i.putExtra("isi", ii);
//            view.getContext().startActivity(i);
////            Toast.makeText(context, ii, Toast.LENGTH_SHORT).show();
//        }
    }
}
