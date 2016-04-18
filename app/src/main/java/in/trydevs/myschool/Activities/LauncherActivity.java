package in.trydevs.myschool.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import in.trydevs.myschool.R;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000,0);
                    Intent intent = new Intent(LauncherActivity.this, SubscriptionActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();



    }


}
