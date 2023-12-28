package com.example.kamusindonesiaminang.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kamusindonesiaminang.adapter.IndonesiaMinangAdapter;
import com.example.kamusindonesiaminang.database.DatabaseAccess;
import com.example.kamusindonesiaminang.model.ModelKamus;
import com.example.kamusindonesiaminang.R;

import java.util.ArrayList;

public class IndonesiaMinangActivity extends AppCompatActivity {

    ArrayList<ModelKamus> modelKamusArrayList = new ArrayList<>();
    IndonesiaMinangAdapter indonesiaMinangAdapter;
    DatabaseAccess databaseAccess;
    RecyclerView rvListData;
    Toolbar toolbar;
    EditText etSearchData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indonesia);

        toolbar = findViewById(R.id.toolbar);
        etSearchData = findViewById(R.id.etSearchData);
        rvListData = findViewById(R.id.rvListData);

        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        setDatabase();
        setRecyclerView();

        etSearchData.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String strKeyword = etSearchData.getText().toString();
                strKeyword = strKeyword.replaceAll("'","");
                getDataSearch(strKeyword);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void setDatabase() {
        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();

        modelKamusArrayList = databaseAccess.getKamusIndonesia();
        databaseAccess.close();
    }

    private void setRecyclerView() {
        rvListData.setLayoutManager(new LinearLayoutManager(this));
        indonesiaMinangAdapter = new IndonesiaMinangAdapter(this, modelKamusArrayList); 
        rvListData.setAdapter(indonesiaMinangAdapter);
        rvListData.setHasFixedSize(true);
    }

    public void getDataSearch(String keyword){
        databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        modelKamusArrayList = databaseAccess.getSearchIndonesia(keyword);
        databaseAccess.close();

        setRecyclerView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
