package com.freelance.android.androidrealmdatabase.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.freelance.android.androidrealmdatabase.R;
import com.freelance.android.androidrealmdatabase.realm.Product;
import com.freelance.android.androidrealmdatabase.realm.RealmHelper;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getName();
    Button insert, show;
    EditText name, image;
    String mName, mImage;

    Realm realm;
    RealmHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "Test : onCreate() called...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) this.findViewById(R.id.et_name);
        image = (EditText) this.findViewById(R.id.et_image_url);
        insert = (Button) this.findViewById(R.id.btnInsert);
        show = (Button) this.findViewById(R.id.btnShow);

        //Setup Realm
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(realmConfiguration);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(LOG_TAG, "Test : onClick() in insert called...");

                mName = name.getText().toString();
                mImage = image.getText().toString();

                if (!mName.isEmpty() && !mImage.isEmpty() ) {

                    Product p = new Product();
                    p.setName(mName);
                    p.setImage(mImage);

                    helper = new RealmHelper(realm);
                    helper.save(p);

                    Toast.makeText(MainActivity.this, "Success Data Inserted!", Toast.LENGTH_SHORT).show();

                    clear();
                } else{
                    Toast.makeText(MainActivity.this, "You must need to fill up all EditText!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(LOG_TAG, "Test : onClick() in show called...");

                startActivity(new Intent(MainActivity.this, DetailActivity.class));
            }
        });
    }

    private void clear() {
        Log.i(LOG_TAG, "Test : clear() called...");

        name.setText("");
        image.setText("");
        name.requestFocus();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close(); // Remember to close Realm when done.
    }
}
