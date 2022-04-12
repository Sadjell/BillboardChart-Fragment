package com.example.billboardchart;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        //get the id, get the fragment from the fragment manager and set the id
        int id = (int)getIntent().getIntExtra("category_id", 0);
        DetailFragment frag = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.frag_detail);
        frag.setWorkId(id);
    }
}