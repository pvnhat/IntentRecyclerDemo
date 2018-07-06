package com.example.nowornever.recycleviewdemo;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FragmentOne extends Fragment {

    ArrayList<Herro> herros;
    HeroAdapter heroAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        initHeroList(view);

        return view;
    }

    public void initHeroList(View view){
        RecyclerView recyler_listHerro = view.findViewById(R.id.recyler_listHerro);
        // neu cac item co cung chieu cao va do rong thi toi uu de muot hon
        recyler_listHerro.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(),2); // set orientation
        recyler_listHerro.setLayoutManager(linearLayoutManager);

        herros = new ArrayList<>();
        herros.add(new Herro("06663262326","Captain American", "nope", R.drawable.captain));
        herros.add(new Herro("06663262326","iron man", "nope", R.drawable.iron_man));
        herros.add(new Herro("06663262326","spiderman", "nope", R.drawable.spider_man));
        herros.add(new Herro("06663262326","superman", "nope", R.drawable.superman));
        herros.add(new Herro("06663262326","wonder woman", "nope", R.drawable.wonder_woman));
        herros.add(new Herro("06663262326","hulk", "nope", R.drawable.hulk));


        // make color
        // tao border
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyler_listHerro.getContext(),
                DividerItemDecoration.VERTICAL); // o tren huong nao thi o duoi cung vay
        Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.custom_divider);
        dividerItemDecoration.setDrawable(drawable);
        recyler_listHerro.addItemDecoration(dividerItemDecoration); // add hieu ung
        // xoa item, tao hieu ung
        recyler_listHerro.setItemAnimator(new DefaultItemAnimator()); // add hieu ung mac dinh

        System.out.println(herros.get(1).getHerroName());
        heroAdapter = new HeroAdapter(herros, getActivity());
        recyler_listHerro.setAdapter(heroAdapter);



    }


}
