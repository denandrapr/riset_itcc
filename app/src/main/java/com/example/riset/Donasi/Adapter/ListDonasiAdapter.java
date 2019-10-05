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
import com.example.riset.R;

import java.util.List;

public class ListDonasiAdapter  extends RecyclerView.Adapter<ListDonasiAdapter.ViewHolder> {

    private List<String> mAnimals;
    private LayoutInflater mInflater;

    public ListDonasiAdapter(Context context, List<String> animals) {
        this.mInflater = LayoutInflater.from(context);
        this.mAnimals = animals;
    }

    @Override
    @NonNull
    public ListDonasiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dokumentasi, parent, false);
        ListDonasiAdapter.ViewHolder holder = new ListDonasiAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListDonasiAdapter.ViewHolder holder, int position) {
        String result = mAnimals.get(position);
        if (position == 0){
            Glide
                    .with(holder.myView.getContext())
                    .load(R.drawable.dokumentasi_foto_temp)
                    .placeholder(R.drawable.dokumentasi_foto_temp)
                    .into(holder.myView);
        }else if(position == 1){
            Glide
                    .with(holder.myView.getContext())
                    .load(R.drawable.dokumentasi_foto_temp1)
                    .placeholder(R.drawable.dokumentasi_foto_temp1)
                    .into(holder.myView);
        }else if(position == 2){
            Glide
                    .with(holder.myView.getContext())
                    .load(R.drawable.dokumentasi_foto_temp3)
                    .placeholder(R.drawable.dokumentasi_foto_temp3)
                    .into(holder.myView);
        }else{
            Glide
                    .with(holder.myView.getContext())
                    .load(R.drawable.dokumentasi_foto_temp)
                    .placeholder(R.drawable.dokumentasi_foto_temp)
                    .into(holder.myView);
        }
        holder.myTextView.setText(result);
    }

    @Override
    public int getItemCount() {
        return mAnimals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView myView;
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myView = itemView.findViewById(R.id.imgDoc);
            myTextView = itemView.findViewById(R.id.txtJudul);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            Intent i = new Intent(view.getContext(), DokumentasiDetailActivity.class);
//            view.getContext().startActivity(i);
        }
    }
}
