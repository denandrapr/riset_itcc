package com.example.riset.Home.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.riset.Home.RelawanDetail;
import com.example.riset.Model.ButuhSegeraModel;
import com.example.riset.Model.RuanganModel;
import com.example.riset.R;

import java.util.List;

public class JadiTutorMerekaAdapter extends RecyclerView.Adapter<JadiTutorMerekaAdapter.ViewHolder> {

    private List<RuanganModel> ruanganModels;
    private LayoutInflater mInflater;
    Context context;

    // data is passed into the constructor
    public JadiTutorMerekaAdapter(Context context, List<RuanganModel> ruanganModels) {
        this.mInflater = LayoutInflater.from(context);
        this.ruanganModels = ruanganModels;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public JadiTutorMerekaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_tutor_mereka, parent, false);
        return new JadiTutorMerekaAdapter.ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull JadiTutorMerekaAdapter.ViewHolder holder, int position) {
        RuanganModel result = ruanganModels.get(position);

        Glide
            .with(holder.myView.getContext())
            .load(result.getLinkFotoUtama())
            .placeholder(R.drawable.dokumentasi_foto_temp)
            .into(holder.myView);
        holder.txtJudul.setText(result.getJudulKegiatan());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return ruanganModels.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView myView;
        TextView txtJudul;

        ViewHolder(View itemView) {
            super(itemView);
            myView = itemView.findViewById(R.id.imgTutor);
            txtJudul = itemView.findViewById(R.id.textJudul);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent i = new Intent(view.getContext(), RelawanDetail.class);
            view.getContext().startActivity(i);
        }
    }
}