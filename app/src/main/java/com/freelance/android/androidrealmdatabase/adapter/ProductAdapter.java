package com.freelance.android.androidrealmdatabase.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.freelance.android.androidrealmdatabase.R;
import com.freelance.android.androidrealmdatabase.activity.UpdateActivity;
import com.freelance.android.androidrealmdatabase.realm.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Kyaw Khine on 03/13/2018.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private static final String LOG_TAG = ProductAdapter.class.getName();

    Context mContext;
    private List<Product> mProductList;

    public ProductAdapter(Context context, List<Product> productList) {
        Log.i(LOG_TAG, "Test: ProductAdapter() constructor called...");

        this.mContext = context;
        this.mProductList = productList;
    }

    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(LOG_TAG, "Test: onCreateViewHolder() called...");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_product, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductAdapter.MyViewHolder holder, int position) {
        Log.i(LOG_TAG, "Test: onBindViewHolder() called...");

        Product p = mProductList.get(position);
        holder.name.setText(p.getName());
        Picasso.with(mContext)
                .load(p.getImage())
                /*.resize(50, 50)
                .centerCrop()*/
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        Log.i(LOG_TAG, "Test: getItemCount() called...");

        return mProductList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final String LOG_TAG = MyViewHolder .class.getName();

        TextView name;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);

            Log.i(LOG_TAG, "Test: MyViewHolder() called...");

            name = (TextView)itemView.findViewById(R.id.txtProductName);
            image = (ImageView) itemView.findViewById(R.id.ivProductPhoto);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.i(LOG_TAG, "Test: onClick() in MyViewHolder() called...");

            Intent i = new Intent(mContext, UpdateActivity.class);
            i.putExtra("key_id", mProductList.get(getAdapterPosition()).getId() + "");
            i.putExtra("key_name", mProductList.get(getAdapterPosition()).getName());
            i.putExtra("key_image", mProductList.get(getAdapterPosition()).getImage());

            mContext.startActivity(i);
        }
    }
}
