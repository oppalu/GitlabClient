package com.example.phoebegl.gitlabclient.ui.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.phoebegl.gitlabclient.R;
import com.example.phoebegl.gitlabclient.data.ApiService;
import com.example.phoebegl.gitlabclient.data.RetrofitWrapper;
import com.example.phoebegl.gitlabclient.model.Account;
import com.example.phoebegl.gitlabclient.model.UserInfo;

import java.io.IOException;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{
    Button btn;
    TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        btn = (Button)findViewById(R.id.btn_click);
        tv_result = (TextView)findViewById(R.id.tv_result);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService service = RetrofitWrapper.getInstance().create(ApiService.class);
                Call<UserInfo> call = service.login(new Account("liuqin","123"));
                new UserTask().execute(call);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    class UserTask extends AsyncTask<Call,Void,String> {
        @Override
        protected String doInBackground(Call... params) {
            Call<UserInfo> call = params[0];
            try {
                Response<UserInfo> response = call.execute();
                return response.body().getEmail();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            tv_result.setText(result);
        }
    }
}
