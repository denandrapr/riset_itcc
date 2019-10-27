package com.example.riset.Home.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.riset.Model.ButuhSegeraModel;
import com.example.riset.Model.UserModel;
import com.example.riset.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
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

public class TerdekatKamuAdapter extends RecyclerView.Adapter<TerdekatKamuAdapter.ViewHolder> {

    private List<ButuhSegeraModel> butuhSegeraModels;
    private LayoutInflater mInflater;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    double total;
    float total2;
    String dayDifference;
    String namaPembuat;
    Double nominalDonasi;
    float sisa;

    public TerdekatKamuAdapter(Context context, List<ButuhSegeraModel> butuhSegeraModels) {
        this.mInflater = LayoutInflater.from(context);
        this.butuhSegeraModels = butuhSegeraModels;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_terdekat_kamu, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TerdekatKamuAdapter.ViewHolder holder, int position) {
        ButuhSegeraModel result = butuhSegeraModels.get(position);

        if (result.getTipe() == 1){
            Glide
                    .with(holder.myView.getContext())
                    .load(result.getLinkFotoUtama())
                    .placeholder(R.drawable.terdekat_kamu_temp1)
                    .into(holder.myView);
            holder.judulKegiatan.setText(result.getJudulKegiatan());
            holder.sisaHari.setText(CurrentDate(result.getBatasWaktu()));
            get_total_terkumpul(result.getId(), holder);
            get_created_by(result.getCreated_by(), holder);

            //setting progressbar
            db.collection("Posting")
                    .document(result.getId())
                    .collection("berdonasi")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            total = 0;
                            nominalDonasi = 0.0;
                            sisa = 0;
                            for (QueryDocumentSnapshot doc : task.getResult()){
                                total = total + doc.getDouble("nominal");
                            }
                            nominalDonasi = Double.parseDouble(result.getTargetNominalDonasi().replace(",",""));

                            LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) holder.barNominalTerkumpul.getLayoutParams();
                            LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) holder.barNominalTersisa.getLayoutParams();

                            sisa = (float)(total/nominalDonasi);

                            params1.weight = sisa;
                            holder.barNominalTerkumpul.setLayoutParams(params1);
                        }
                    });
        }
    }

    private void get_created_by(String email_created_by, ViewHolder holder){
        db.collection("User")
                .document(email_created_by)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        namaPembuat = "";
                        UserModel user = null;
                        user = documentSnapshot.toObject(UserModel.class);
                        holder.pengepost.setText(user.getNama());
                    }
                });
    }

    private void get_total_terkumpul(String id, ViewHolder holder){
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
                        holder.txtTotalTerkumpul.setText(decimalFormat(total));
                    }
                });
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
    public int getItemCount() {
        return butuhSegeraModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.imgTerdekat)
        ImageView myView;
        @BindView(R.id.txtJudulKegiatan)
        TextView judulKegiatan;
        @BindView(R.id.txtTotalTerkumpul)
        TextView txtTotalTerkumpul;
        @BindView(R.id.txtSisaHari)
        TextView sisaHari;
        @BindView(R.id.pengepost)
        TextView pengepost;
        @BindView(R.id.barNominalTerkumpul)
        LinearLayout barNominalTerkumpul;
        @BindView(R.id.barNominalTersisa)
        LinearLayout barNominalTersisa;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
