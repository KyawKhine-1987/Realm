package com.freelance.android.androidrealmdatabase.realm;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;


/**
 * Created by Kyaw Khine on 03/13/2018.
 */

public class RealmHelper {

    private static final String LOG_TAG = RealmHelper.class.getName();

    Realm realm;

    public RealmHelper(Realm realm) {
        Log.i(LOG_TAG, "Test : RealmHelper() constructor called...");

        this.realm = realm;
    }

    /***
     * save data to Realm.
     * @param product
     */
    public void save(final Product product) {
        Log.i(LOG_TAG, "Test : save() called...");

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Log.i(LOG_TAG, "Test : execute() in save called...");

                if (realm != null) {
                    Log.i(LOG_TAG, "Realm Database was created.");

                    Number currentId = realm.where(Product.class).max("id");

                    int incrementId;
                    if (currentId == null) {
                        incrementId = 1;
                    } else {
                        incrementId = currentId.intValue() + 1;
                    }

                    product.setId(incrementId);
                    Product p = realm.copyToRealm(product);
                } else {
                    Log.e(LOG_TAG, "Realm Database wasn't existing.");
                }
            }
        });
    }

    /***
     * select data from Realm
     * @return productResults
     */
    public List<Product> getAllProduct() {
        Log.i(LOG_TAG, "Test : getAllProduct() called...");

        RealmResults<Product> productResults = realm.where(Product.class).findAll();
        return productResults;
    }

    public void update(final int id, final String title, final String imageUrl) {
        Log.i(LOG_TAG, "Test : update() called...");

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Log.i(LOG_TAG, "Test : execute() in update called...");

                Product p = realm.where(Product.class).equalTo("id", id).findFirst();

                p.setName(title);
                p.setImage(imageUrl);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.i(LOG_TAG, "Test : onSuccess() in update called...");

                Log.e(LOG_TAG, "Test : update successfully.");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.i(LOG_TAG, "Test : onError() in update called...");

                error.printStackTrace();
            }
        });
    }

    public void delete(int id) {
        Log.i(LOG_TAG, "Test : delete() called...");

        final Product results = realm.where(Product.class).equalTo("id", id).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Log.i(LOG_TAG, "TEST: execute() called...");

                results.deleteFromRealm(); // Delete and remove object directly.
            }
        });
    }
}
