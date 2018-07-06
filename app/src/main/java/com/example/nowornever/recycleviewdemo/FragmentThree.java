package com.example.nowornever.recycleviewdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class FragmentThree extends Fragment implements View.OnClickListener {

    private static final int MESSS_COWN_DOWN = 69;

    TextView text_showNum;
    Button btn_start;

    Handler mHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);

        text_showNum = view.findViewById(R.id.text_showNum);
        btn_start = view.findViewById(R.id.btn_start);

        initHandler();
        btn_start.setOnClickListener(this);

        return view;
    }

    // giao tiep giua thread phu va main thread
    private void initHandler() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MESSS_COWN_DOWN:
                        text_showNum.setText(String.valueOf(msg.arg1));
                        Toast.makeText(getContext(), "cc", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        System.out.println("Nothing");
                        break;
                }
            }
        };
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_start:
                countDown();
                break;
            default:
                break;
        }

    }
    public void countDown() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int time = 10;
                do {
                    time--;
                    Message message = new Message();
                    message.what = MESSS_COWN_DOWN; // duong dan
                    message.arg1 = time; // gui gia tri
                    mHandler.sendMessage(message); // gui agrument

                    try {

                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }while (time > 0);

            }
        });
        thread.start();
    }
}
