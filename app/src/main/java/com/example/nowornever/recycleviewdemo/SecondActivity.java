package com.example.nowornever.recycleviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    EditText edit_show_data;
    Button btn_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        edit_show_data = findViewById(R.id.edit_text_show_data);
        btn_confirm = findViewById(R.id.button_confirm);

        final Intent intent = getIntent();

        //String stringData = intent.getStringExtra("Data");
        //int intData = intent.getIntExtra("Data", 100);
        //String[] arrayData = intent.getStringArrayExtra("Data");
        Herro herro = (Herro) intent.getSerializableExtra("Data");


        //edit_show_data.setText(herro.getHerroName());

        /*for (String animal : arrayData) {
            edit_show_data.append(animal +" \t");
        }*/



       //Bundle
        Bundle bundle = intent.getBundleExtra("Data");
        if (bundle != null) {
            Herro herro1 = (Herro)bundle.getSerializable("object");
            edit_show_data.setText(bundle.getString("string") + "  "
                + bundle.getInt("int")  + "  "
                + herro1.getHerroName()  + "  "
                + bundle.getStringArray("arr").length );
        }





        /// startActivityForResult
        /*btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newString = edit_show_data.getText().toString();
                Intent intentForResult = new Intent();
                intentForResult.putExtra("newString" , newString);
                setResult(RESULT_OK, intentForResult);
                finish();
            }
        });*/
    }


}
