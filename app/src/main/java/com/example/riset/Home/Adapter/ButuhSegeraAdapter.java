package com.example.riset.Home.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
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
import com.example.riset.Home.Model.ButuhSegeraModel;
import com.example.riset.R;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ButuhSegeraAdapter extends RecyclerView.Adapter<ButuhSegeraAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<ButuhSegeraModel> butuhSegeraModels;
    Context context;
    String id = null;

    // data is passed into the constructor
    public ButuhSegeraAdapter(Context context, List<ButuhSegeraModel> data) {
        this.mInflater = LayoutInflater.from(context);
        this.butuhSegeraModels = data;
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
        ButuhSegeraModel result = butuhSegeraModels.get(position);
        Glide
            .with(holder.imageSegera.getContext())
            .load(result.getImg())
            .placeholder(R.drawable.dokumentasi_foto_temp)
            .into(holder.imageSegera);
        holder.textJudul.setText(result.getJudul());
        holder.textInfo.setText("Terkumpul dari Rp "+result.getTarget());
        holder.textDuit.setText("0");
        holder.textSisa.setText(result.getTargetTanggal());

        String getDate = result.getTargetTanggal();
        CurrentDateTimeExample1();
        holder.textId.setText(result.getId());
    }


    public void CurrentDateTimeExample1() {

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());

        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date("25-November-2019");

        Log.d("date", "CurrentDateTimeExample1: " + formatter.format(date));
        Log.d("date", formatter1.format(date1));

    }


    // total number of rows
    @Override
    public int getItemCount() {
        return butuhSegeraModels.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.txtJudul)
        TextView textJudul;
        @BindView(R.id.txtDuit)
        TextView textDuit;
        @BindView(R.id.imgSegera)
        ImageView imageSegera;
        @BindView(R.id.txtInfo)
        TextView textInfo;
        @BindView(R.id.txtSisa)
        TextView textSisa;
        @BindView(R.id.txtId)
        TextView textId;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent i = new Intent(view.getContext(), DonasiDetailActivity.class);
            String idnya = textId.getText().toString();
            i.putExtra("id", idnya);
            view.getContext().startActivity(i);
        }
    }
}