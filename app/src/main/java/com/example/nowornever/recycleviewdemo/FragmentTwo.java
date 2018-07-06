package com.example.nowornever.recycleviewdemo;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FragmentTwo extends Fragment {

    Button btnCall;
    TextView text_job;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);

        btnCall = view.findViewById(R.id.btnCall);
        text_job = view.findViewById(R.id.text_job);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new JobAsyncTask().execute();



            }
        });

        return view;
    }

    // para1 : tham số nhận vào của onPreExecute() , truyền vào cho doInBackground
    // para2 : là giá trị biến update giao diện lúc thực thi , nhận vào onProgressUpdate
    // para3 : Biết return từ doInBackgroud sau khi thực hiện xog.
    public class JobAsyncTask extends AsyncTask<Void, String, String>{

        // Khởi tạo , chuẩn bị các tiến trình, tự gọi thi tiến trình đc kích hoạt.
        @Override
        protected void onPreExecute() {
            text_job.setText("Start ! \n ");
            super.onPreExecute();
        }

        // Bắt buộc phải có , hàm chính của AsynTask
        // Xử lý ngầm , chỉ thực hiện , không thể tác động đến giao diện
        @Override
        protected String doInBackground(Void... voids) {
            for (int i = 0; i < 5; i ++){
                publishProgress("\nFinish :" + i);
            }
            return "Hoan thanh full";
        }

        // Nhận return từ doInBackgroud
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s); // nhan ve
            text_job.append(s); // addText
        }

        // Cập các tiến trình thực hiện trong doInBackgroud để đưa ra GUI
        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            text_job.append(values[0]);

        }
    }


}
