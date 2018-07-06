package com.example.nowornever.recycleviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class FragIntentDemo extends Fragment implements View.OnClickListener{

    private final static int INTENT_REQUEST_CODE = 69;

    Button btn_send_string,btn_send_num,btn_send_array,btn_send_object,btn_send_bundle,btn_edit;
    EditText edit_data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_intent_demo, container, false);
        Declare(view);


        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btn_send_string:
                intent = new Intent(getContext(), SecondActivity.class);
                intent.putExtra("Data", "Send a string use Intent");
                startActivity(intent);
                break;
            case R.id.btn_send_num:
                intent = new Intent(getContext(), SecondActivity.class);
                intent.putExtra("Data", 69);
                startActivity(intent);
                break;
            case R.id.btn_send_array:
                String[] animals = {"dog", "cat", "frog" , "grab", "bird"} ;
                intent = new Intent(getContext(), SecondActivity.class);
                intent.putExtra("Data",animals);
                startActivity(intent);
                break;
            case R.id.btn_send_object:
                Herro superman = new Herro("1","superman", "aa" , R.drawable.superman) ;
                intent = new Intent(getContext(), SecondActivity.class);
                intent.putExtra("Data",superman);
                startActivity(intent);
                break;
            case R.id.btn_send_bundle:
                Bundle bundle = new Bundle();
                bundle.putString("string", "This is string that was tranfered throught Bundle");
                bundle.putInt("int", 96);
                bundle.putStringArray("arr", new String[] {"dog", "cat", "frog" , "grab", "bird"});
                bundle.putSerializable("object", new Herro("1",
                        "superman", "aa" , R.drawable.superman));

                intent = new Intent(getContext(), SecondActivity.class);
                intent.putExtra("Data",bundle);
                startActivity(intent);
                break;
            case R.id.btn_edit:
                intent = new Intent(getContext(), SecondActivity.class);
                startActivityForResult(intent, INTENT_REQUEST_CODE);
                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == INTENT_REQUEST_CODE && resultCode == getActivity().RESULT_OK && data != null ) {
            String newString = data.getStringExtra("newString");
            System.out.println(newString);
            edit_data.setText(newString);
        }
    }

    void Declare(View view ) {
        btn_send_string = view.findViewById(R.id.btn_send_string);
        btn_send_num = view.findViewById(R.id.btn_send_num);
        btn_send_array = view.findViewById(R.id.btn_send_array);
        btn_send_object = view.findViewById(R.id.btn_send_object);
        btn_send_bundle = view.findViewById(R.id.btn_send_bundle);
        btn_edit = view.findViewById(R.id.btn_edit);
        edit_data = view.findViewById(R.id.edit_data);

        btn_send_string.setOnClickListener(this);
        btn_send_num.setOnClickListener(this);
        btn_send_array.setOnClickListener(this);
        btn_send_object.setOnClickListener(this);
        btn_send_bundle.setOnClickListener(this);
        btn_edit.setOnClickListener(this);
    }
}
