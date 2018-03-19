package com.freelance.android.androidrealmdatabase.application;

import android.app.Application;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Kyaw Khine on 03/13/2018.
 */

public class RealmApplication extends Application {

    private static final String LOG_TAG = RealmApplication.class.getName();

    @Override
    public void onCreate() {
        Log.i(LOG_TAG, "Test : onCreate() in RealmApplication called...");

        super.onCreate();

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("product.realm")
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
    /****
     * Create realm database with customize name
     *
    // The RealmConfiguration is created using the builder pattern.
    // The Realm file will be located in Context.getFilesDir() with name "myrealm.realm"
    RealmConfiguration config = new RealmConfiguration.Builder()
            .name("myrealm.realm")
            .encryptionKey(getKey())
            .schemaVersion(42)
            .modules(new MySchemaModule())
            .migration(new MyMigration())
            .build();
    // Use the config
    Realm realm = Realm.getInstance(config);

     +++++======+++++

     RealmConfiguration myConfig = new RealmConfiguration.Builder()
     .name("myrealm.realm")
     .schemaVersion(2)
     .modules(new MyCustomSchema())
     .build();

     RealmConfiguration otherConfig = new RealmConfiguration.Builder()
     .name("otherrealm.realm")
     .schemaVersion(5)
     .modules(new MyOtherSchema())
     .build();

     Realm myRealm = Realm.getInstance(myConfig);
     Realm otherRealm = Realm.getInstance(otherConfig);
     */