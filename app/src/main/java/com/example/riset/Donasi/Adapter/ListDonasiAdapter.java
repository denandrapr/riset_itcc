package com.example.riset.Donasi.Adapter;

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
import com.example.riset.Home.Model.ButuhSegeraModel;
import com.example.riset.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListDonasiAdapter extends RecyclerView.Adapter<ListDonasiAdapter.ViewHolder> {

    private List<ButuhSegeraModel> butuhSegeraModels;
    private LayoutInflater mInflater;

    public ListDonasiAdapter(Context context, List<ButuhSegeraModel> butuhSegeraModels) {
        this.mInflater = LayoutInflater.from(context);
        this.butuhSegeraModels = butuhSegeraModels;
    }

    @Override
    @NonNull
    public ListDonasiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_terdekat_kamu, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListDonasiAdapter.ViewHolder holder, int position) {
        ButuhSegeraModel result = butuhSegeraModels.get(position);
        Glide
                .with(holder.myView.getContext())
                .load(result.getLinkFotoUtama())
                .placeholder(R.drawable.terdekat_kamu_temp1)
                .into(holder.myView);
        holder.judulKegiatan.setText(result.getJudulKegiatan());
//        holder.myTextView.setText(result);
    }

    @Override
    public int getItemCount() {
        return butuhSegeraModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.imgTerdekat)
        ImageView myView;
        @BindView(R.id.txtJudulKegiatan)
        TextView judulKegiatan;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
