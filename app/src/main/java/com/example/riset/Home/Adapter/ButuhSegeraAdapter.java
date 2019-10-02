package com.example.riset.Home.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.riset.Home.DonasiDetailActivity;
import com.example.riset.R;

import org.w3c.dom.Text;

import java.util.List;

public class ButuhSegeraAdapter extends RecyclerView.Adapter<ButuhSegeraAdapter.ViewHolder> {

    private List<String> mAnimals;
    private List<String> mDuit;
    private List<String> mInfo;
    private LayoutInflater mInflater;
    Context context;

    // data is passed into the constructor
    public ButuhSegeraAdapter(Context context, List<String> animals, List<String> mDuit, List<String> mInfo) {
        this.mInflater = LayoutInflater.from(context);
        this.mAnimals = animals;
        this.mDuit = mDuit;
        this.mInfo = mInfo;
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_butuh_segera, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String animal = mAnimals.get(position);
        String info = mInfo.get(position);
        String duit = mDuit.get(position);
            Glide
                .with(holder.myView.getContext())
                .load(R.drawable.img_butuh_segera)
                .placeholder(R.drawable.img_butuh_segera)
                .into(holder.myView);
        holder.myTextView.setText(animal);
        holder.txtInfo.setText(info);
        holder.txtDuit.setText(duit);

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
        TextView txtInfo;
        TextView txtDuit;

        ViewHolder(View itemView) {
            super(itemView);
            myView = itemView.findViewById(R.id.imgSegera);
            myTextView = itemView.findViewById(R.id.tvAnimalName);
            txtInfo = itemView.findViewById(R.id.txtInfo);
            txtDuit = itemView.findViewById(R.id.txtDuit);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent i = new Intent(view.getContext(), DonasiDetailActivity.class);
            view.getContext().startActivity(i);
        }
    }
}