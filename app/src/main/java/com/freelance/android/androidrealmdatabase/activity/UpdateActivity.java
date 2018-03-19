package com.freelance.android.androidrealmdatabase.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.freelance.android.androidrealmdatabase.R;
import com.freelance.android.androidrealmdatabase.realm.RealmHelper;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/*public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {*/
public class UpdateActivity extends AppCompatActivity {

    private static final String LOG_TAG = UpdateActivity.class.getName();

    RealmHelper helper;
    Realm realm;
    Button update, delete;
    EditText ed_title, ed_image;

    int id;
    String name, image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOG_TAG, "Test : onCreate() called...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        ed_title = (EditText) this.findViewById(R.id.et_title);
        ed_image = (EditText) this.findViewById(R.id.et_image);
        update = (Button) this.findViewById(R.id.btnUpdate);
        delete = (Button)this.findViewById(R.id.btnDelete);

        //Get data from DetailActivity.java.
        id = Integer.parseInt(getIntent().getStringExtra("key_id"));
        name = getIntent().getStringExtra("key_name");
        image = getIntent().getStringExtra("key_image");

        ed_title.setText(name);
        ed_image.setText(image);

        //Setup Realm
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(realmConfiguration);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(LOG_TAG, "Test : onClick() called...");

                helper = new RealmHelper(realm);
                helper.update(id, ed_title.getText().toString(), ed_image.getText().toString());

                Toast.makeText(UpdateActivity.this, "Success Data Updated!", Toast.LENGTH_SHORT).show();

                ed_title.setText("");
                ed_image.setText("");
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(LOG_TAG, "Test : onClick() called...");

                helper = new RealmHelper(realm);
                helper.delete(id);

                Toast.makeText(UpdateActivity.this, "Success Data Deleted!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

   /* @Override
    public void onClick(View view) {
        Log.i(LOG_TAG, "Test : onClick() called...");

        helper = new RealmHelper(realm);
        helper.update(id, ed_title.getText().toString(), ed_image.getText().toString());

        Toast.makeText(this, "Success Data Updated!", Toast.LENGTH_SHORT).show();

        ed_title.setText("");
        ed_image.setText("");
        finish();
    }*/
}
