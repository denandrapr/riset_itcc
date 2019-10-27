package com.example.riset.Home.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.riset.Model.BerdonasiUangModel;
import com.example.riset.Model.UserModel;
import com.example.riset.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DonaturDetailDonasiAdapter extends RecyclerView.Adapter<DonaturDetailDonasiAdapter.ViewHolder>{
    private Context context;
    private List<BerdonasiUangModel> data;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

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
            Glide.with(holder.foto_donatur.getContext())
                    .load(R.drawable.placeholder)
                    .circleCrop()
                    .placeholder(R.drawable.placeholder)
                    .into(holder.foto_donatur);
        }else{
            db.collection("User")
                    .document(results.getEmail_donatur())
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            UserModel userModel = null;
                            userModel = documentSnapshot.toObject(UserModel.class);
                            holder.txtNamaDonatur.setText(userModel.getNama());
                            Glide
                                    .with(holder.foto_donatur.getContext())
                                    .load(userModel.getUrlProfileImage())
                                    .placeholder(R.drawable.dokumentasi_foto_temp)
                                    .circleCrop()
                                    .into(holder.foto_donatur);
                        }
                    });
        }

        Double d = (double)results.getNominal();
        holder.txtJumlahDonasi.setText(decimalFormat(d));
        holder.txtDonasiDate.setText(results.getTanggal());
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
