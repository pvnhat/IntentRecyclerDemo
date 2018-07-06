package com.example.nowornever.recycleviewdemo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.ViewHolder> {

    ArrayList<Herro> heros;
    Context context;

    public HeroAdapter(ArrayList<Herro> heros, Context context) {
        this.heros = heros;
        this.context = context;
    }

    /**
     * Inflat ve
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.herro_row, parent, false);
        return new ViewHolder(itemView);
    }

    // Gan du lieu vao textView
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.text_name.setText(heros.get(position).getHerroName());
        holder.image_herro.setImageResource(heros.get(position).getHeroImage());

        holder.image_herro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall(heros.get(position).getHerroId());
            }
        });
    }

    // return all item
    @Override
    public int getItemCount() {
        return heros.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView text_name;
        ImageButton image_herro;
        ImageButton imgbtn_call;
        public ViewHolder(final View itemView) {
            super(itemView);
            text_name = itemView.findViewById(R.id.text_name);
            image_herro = itemView.findViewById(R.id.image_herro);
            imgbtn_call = itemView.findViewById(R.id.imgbtn_call);



        }
    }

    public void removeItem(int index) {
        heros.remove(index);
        notifyItemRemoved(index);
    }


    public void makeCall(String phoneNumber) {
        Intent intentCall = new Intent();
        intentCall.setAction(Intent.ACTION_CALL);
        intentCall.setData(Uri.parse("tel:" + phoneNumber));
        context.startActivity(intentCall);
    }
}
