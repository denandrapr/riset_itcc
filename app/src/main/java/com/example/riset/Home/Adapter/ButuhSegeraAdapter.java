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
import com.example.riset.Home.DonasiDetailActivity;
import com.example.riset.Model.ButuhSegeraModel;
import com.example.riset.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ButuhSegeraAdapter extends RecyclerView.Adapter<ButuhSegeraAdapter.ViewHolder>{

    private LayoutInflater mInflater;
    private List<ButuhSegeraModel> butuhSegeraModels;
    String id = null;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    double total;
    String dayDifference;

    public ButuhSegeraAdapter(Context context, List<ButuhSegeraModel> data) {
        this.mInflater = LayoutInflater.from(context);
        this.butuhSegeraModels = data;
//        butuhSegeraFiltered = data;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_butuh_segera, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        total = 0;

        ButuhSegeraModel result = butuhSegeraModels.get(position);

        if (result.getTipe() == 1){
            String sTargetNominal = result.getTargetNominalDonasi().replace(",","");
            id = result.getId();
            String infoTerkumpul = "Terkumpul dari Rp "+decimalFormat(Double.parseDouble(sTargetNominal));

            Glide
                .with(holder.imageSegera.getContext())
                .load(result.getLinkFotoUtama())
                .placeholder(R.drawable.dokumentasi_foto_temp)
                .into(holder.imageSegera);

            holder.textJudul.setText(result.getJudulKegiatan());
            holder.textInfo.setText(infoTerkumpul);
            holder.textSisa.setText(result.getBatasWaktu());
            holder.textId.setText(result.getId());
            holder.textSisa.setText(CurrentDate(result.getBatasWaktu()));
            holder.textInfoSisa.setText("hari tersisa");
            get_count_dana(id, holder);
        }else if(result.getTipe() == 2){
            Glide
                .with(holder.imageSegera.getContext())
                .load(result.getLinkFotoUtama())
                .placeholder(R.drawable.dokumentasi_foto_temp)
                .into(holder.imageSegera);

            holder.textJudul.setText(result.getJudulKegiatan());
            holder.textDuit.setText("5");
            holder.textInfo.setText("dari 10 orang telah berpartisipasi");
            holder.textSisa.setText(CurrentDate(result.getBatasWaktu()));
            holder.textId.setText(result.getId());
            holder.textInfoSisa.setText("hari tersisa");
        }
    }

    private void get_count_dana(String id, ViewHolder holder){
        db.collection("Posting")
                .document(id)
                .collection("berdonasi")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        total = 0;
                        for (QueryDocumentSnapshot doc : task.getResult()){
                            total = total + doc.getDouble("nominal");
                        }
                        holder.textDuit.setText(decimalFormat(total));
                    }
                });
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

    public String CurrentDate(String batasWaktu) {
        try {
            Date date1;
            Date date = java.util.Calendar.getInstance().getTime();
            SimpleDateFormat dates = new SimpleDateFormat("dd-MM-yyyy");

            date1 = dates.parse(batasWaktu);

            long difference = Math.abs(date1.getTime() - date.getTime());
            long differenceDate = difference / (24*60*60*1000) + 1;

            dayDifference = Long.toString(differenceDate);

//            Log.d("TAG", "Date => "+dayDifference);
        }catch (Exception e){

        }
        return dayDifference;
    }


    @Override
    public int getItemCount() {
        return butuhSegeraModels.size();
    }


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
        @BindView(R.id.txtInfoSisa)
        TextView textInfoSisa;
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