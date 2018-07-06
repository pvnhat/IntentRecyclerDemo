package com.example.nowornever.recycleviewdemo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.ViewHolder> {



    private Context mContext;
    ArrayList<Herro> heros;
    private OnItemClickListener mOnItemClickListener;

    public HeroAdapter(ArrayList<Herro> heros, Context context) {
        this.heros = heros;
        mContext = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.herro_row, parent, false);
        return new ViewHolder(itemView, mOnItemClickListener);
    }

    // Gan du lieu vao textView
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.getViewHolder(holder, position);


    }

    // return all item
    @Override
    public int getItemCount() {
        return heros.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView text_name;
        ImageButton image_herro;
        ImageButton imgbtn_call;
        ImageButton imgbtn_send_mess;
        private OnItemClickListener mOnItemClickListener;

        ViewHolder(final View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            text_name = itemView.findViewById(R.id.text_name);
            image_herro = itemView.findViewById(R.id.image_herro);
            imgbtn_call = itemView.findViewById(R.id.imgbtn_call);
            imgbtn_send_mess = itemView.findViewById(R.id.imgbtn_send_mess);
            mOnItemClickListener = onItemClickListener;
            image_herro.setOnClickListener(this);
            imgbtn_call.setOnClickListener(this);
            imgbtn_send_mess.setOnClickListener(this);
        }

        public void getViewHolder(ViewHolder holder, int position) {
            holder.text_name.setText(heros.get(position).getHerroName());
            //holder.image_herro.setImageResource(heros.get(position).getHeroImage());
            Glide.with(mContext)
                    .load(heros.get(position).getHeroImage())
                    .into(holder.image_herro);
        }


        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.image_herro :
                    mOnItemClickListener.onItemClick(getAdapterPosition());
                    break;
                case R.id.imgbtn_call:
                    mOnItemClickListener.onCallClick(getAdapterPosition());
                    break;
                case R.id.imgbtn_send_mess:
                    mOnItemClickListener.onSendMess(getAdapterPosition());
                    break;

            }


        }



    }


}
