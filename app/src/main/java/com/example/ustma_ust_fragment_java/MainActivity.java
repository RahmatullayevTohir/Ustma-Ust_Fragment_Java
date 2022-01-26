package com.example.ustma_ust_fragment_java;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ustma_ust_fragment_java.fragment.PageOneFragment;
import com.example.ustma_ust_fragment_java.fragment.PageThreeFragment;
import com.example.ustma_ust_fragment_java.fragment.PageTwoFragment;

public class MainActivity extends AppCompatActivity {

    static final String TAG = MainActivity.class.toString();
    Button button_first, button_second,button_thrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_stack);
        initViews();

        //StaticFragment fragment = StaticFragment.newInstance("PDP");
    }

    @SuppressLint("WrongViewCast")
    private void initViews() {
        button_first = findViewById(R.id.b_first);
        button_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new PageOneFragment());
            }
        });
        button_second = findViewById(R.id.b_second);
        button_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new PageTwoFragment());
            }
        });

        button_thrid = findViewById(R.id.b_thrid);
        button_thrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new PageThreeFragment());
            }
        });
    }


    private void replaceFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();

        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped) { //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.frameLayout, fragment);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount()==1){
            finish();
        }else{
            super.onBackPressed();
        }
    }
}