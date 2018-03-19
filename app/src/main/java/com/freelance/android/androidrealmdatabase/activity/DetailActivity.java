package com.freelance.android.androidrealmdatabase.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.freelance.android.androidrealmdatabase.R;
import com.freelance.android.androidrealmdatabase.adapter.ProductAdapter;
import com.freelance.android.androidrealmdatabase.realm.Product;
import com.freelance.android.androidrealmdatabase.realm.RealmHelper;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class DetailActivity extends AppCompatActivity {

    private static final String LOG_TAG = DetailActivity.class.getName();

    Realm realm;
    RealmHelper helper;
    RecyclerView recyclerView;
    ProductAdapter adapter;
    List<Product> mProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "Test : onCreate() called...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        recyclerView = (RecyclerView) this.findViewById(R.id.rvDetail);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Setup Realm
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(realmConfiguration);

        helper = new RealmHelper(realm);
        mProductList = new ArrayList<>();

        mProductList = helper.getAllProduct();
        adapter = new ProductAdapter(this, mProductList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onRestart() {
        Log.i(LOG_TAG, "Test : onRestart() called...");

        super.onRestart();
        adapter.notifyDataSetChanged();
    }
}
