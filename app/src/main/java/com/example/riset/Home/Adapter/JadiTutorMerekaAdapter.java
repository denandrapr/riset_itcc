package com.example.riset.Home.Adapter;

import android.content.Context;
import android.media.Image;
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

public class JadiTutorMerekaAdapter extends RecyclerView.Adapter<JadiTutorMerekaAdapter.ViewHolder> {

    private List<String> mAnimals;
    private LayoutInflater mInflater;
    Context context;

    // data is passed into the constructor
    public JadiTutorMerekaAdapter(Context context, List<String> animals) {
        this.mInflater = LayoutInflater.from(context);
        this.mAnimals = animals;
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
        String animal = mAnimals.get(position);
        if (position == 0){
            Glide
                .with(holder.myView.getContext())
                .load(R.drawable.foto_tutor_1)
                .placeholder(R.drawable.dokumentasi_foto_temp)
                .into(holder.myView);
        }else if(position == 1){
            Glide
                .with(holder.myView.getContext())
                .load(R.drawable.foto_tutor_2)
                .placeholder(R.drawable.dokumentasi_foto_temp1)
                .into(holder.myView);
        }else if(position == 2){
            Glide
                .with(holder.myView.getContext())
                .load(R.drawable.foto_tutor_1)
                .placeholder(R.drawable.dokumentasi_foto_temp3)
                .into(holder.myView);
        }else if(position == 3){
            Glide
                .with(holder.myView.getContext())
                .load(R.drawable.foto_tutor_2)
                .placeholder(R.drawable.dokumentasi_foto_temp)
                .into(holder.myView);
        }else{
            Glide
                .with(holder.myView.getContext())
                .load(R.drawable.foto_tutor_1)
                .placeholder(R.drawable.dokumentasi_foto_temp)
                .into(holder.myView);
        }
        holder.myTextView.setText(animal);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mAnimals.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView myView;
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myView = itemView.findViewById(R.id.imgTutor);
            myTextView = itemView.findViewById(R.id.tvAnimalName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
//            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
}