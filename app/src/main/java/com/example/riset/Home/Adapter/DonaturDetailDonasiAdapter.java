package com.example.riset.Home.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.riset.R;

import java.util.List;

public class DonaturDetailDonasiAdapter extends RecyclerView.Adapter<DonaturDetailDonasiAdapter.ViewHolder> {

    private List<String> mAnimals;
    private LayoutInflater mInflater;

    public DonaturDetailDonasiAdapter(Context context, List<String> animals) {
        this.mInflater = LayoutInflater.from(context);
        this.mAnimals = animals;
    }

    @Override
    @NonNull
    public DonaturDetailDonasiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donatur_detail_donasi, parent, false);
        DonaturDetailDonasiAdapter.ViewHolder holder = new DonaturDetailDonasiAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DonaturDetailDonasiAdapter.ViewHolder holder, int position) {
        String result = mAnimals.get(position);
//        Glide
//                .with(holder.myView.getContext())
//                .load(R.drawable.item_terdekat_kamu)
//                .placeholder(R.drawable.item_terdekat_kamu)
//                .into(holder.myView);
//        holder.myTextView.setText(result);
    }

    @Override
    public int getItemCount() {
        return mAnimals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView myView;
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
//            myView = itemView.findViewById(R.id.imgTerdekat);
//            myTextView = itemView.findViewById(R.id.tvAnimalName);
        }
    }
}

