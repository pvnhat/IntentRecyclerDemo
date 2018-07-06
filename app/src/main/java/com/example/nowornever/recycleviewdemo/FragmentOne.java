package com.example.nowornever.recycleviewdemo;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class FragmentOne extends Fragment implements OnItemClickListener {

    private static final int  MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1000;

    ArrayList<Herro> herros;
    HeroAdapter mHeroAdapter;

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


        mHeroAdapter = new HeroAdapter(herros, getActivity());
        recyler_listHerro.setAdapter(mHeroAdapter);
        mHeroAdapter.setOnItemClickListener(this);



    }




    @Override
    public void onCallClick(int positsion) {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) ==
                PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getContext(), "You have already granted this permission !", Toast.LENGTH_SHORT).show();

            // do something
            makeCall(herros.get(positsion).getHerroId());

        } else {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            //requestCallPermission();
        }

    }

    @Override
    public void onItemClick(int position) {

        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/"));
        if (browserIntent.resolveActivity(getActivity().getPackageManager()) != null)
            startActivity(browserIntent);
    }

    @Override
    public void onSendMess(int positsion) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SENDTO);
        intent.putExtra("sms_body" , "Welcome to ....");
        intent.setData(Uri.parse("sms:" + herros.get(positsion).getHerroId()));
        if (intent.resolveActivity(getActivity().getPackageManager()) != null)
            startActivity(intent);
    }

    private void requestCallPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                getActivity(), Manifest.permission.CALL_PHONE)) {
            new AlertDialog.Builder(getContext())
                    .setTitle("Permission need")
                    .setMessage("This permission need becase make a call")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(getActivity(),
                                    new String[]{Manifest.permission.CALL_PHONE},
                                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
            .create().show();
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CALL_PHONE},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_CONTACTS && grantResults.length > 0
                && grantResults[0] > PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getContext(), "Granted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Denied", Toast.LENGTH_SHORT).show();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    private void makeCall(String number) {
        Intent callIntent = new Intent();
        callIntent.setAction(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+number));
        startActivity(callIntent);
    }




}
