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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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
    String id = null;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    double total;
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
        total = 0;
        ButuhSegeraModel result = butuhSegeraModels.get(position);
        Glide
            .with(holder.imageSegera.getContext())
            .load(result.getLinkFotoUtama())
            .placeholder(R.drawable.dokumentasi_foto_temp)
            .into(holder.imageSegera);
        holder.textJudul.setText(result.getJudulKegiatan());
        String sTargetNominal = result.getTargetNominalDonasi().replace(",","");
        holder.textInfo.setText("Terkumpul dari Rp "+decimalFormat(Double.parseDouble(sTargetNominal)));
        holder.textInfo.setText("Terkumpul dari Rp "+result.getTargetNominalDonasi());
        holder.textSisa.setText(result.getBatasWaktu());

        String getDate = result.getBatasWaktu();
        CurrentDateTimeExample1();
        holder.textId.setText(result.getId());
        id = result.getId();
        holder.textDuit.setText("0");
//        get_count_dana(result.getId());
    }

    private void get_count_dana(String ide){
        db.collection("Posting")
                .document(ide)
                .collection("berdonasi")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot doc : task.getResult()){
                            total = total + doc.getDouble("nominal");
//                            Log.d("TAG", "count => "+id+" "+doc.getDouble("nominal"));
                        }
                    }
                });
        Log.d("TAG", "totale => "+total);
    }

    private String decimalFormat(Double total){
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp. ");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);

        return kursIndonesia.format(total);
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