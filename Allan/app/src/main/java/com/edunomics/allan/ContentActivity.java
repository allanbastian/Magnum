package com.edunomics.allan;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import java.net.InetAddress;

public class ContentActivity extends AppCompatActivity {

    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        frameLayout = findViewById(R.id.frame);
        MsgFragment msgFragment = new MsgFragment();
        final NoNetFragment noNetFragment = new NoNetFragment();
        final FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        if (isInternetAvailable()) {
            trans.replace(R.id.frame, msgFragment);
            trans.addToBackStack(null);
            trans.commit();
        } else {
            trans.replace(R.id.frame, noNetFragment);
            trans.addToBackStack(null);
            trans.commit();
        }
        /*frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isInternetAvailable()) {
                    trans.replace(R.id.frame, noNetFragment);
                    trans.addToBackStack(null);
                    trans.commit();
                } else {
                    //figure out what to do here
                }
            }
        });*/
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            return !ipAddr.equals("");
        } catch (Exception e) {
            return false;
        }
    }
}
