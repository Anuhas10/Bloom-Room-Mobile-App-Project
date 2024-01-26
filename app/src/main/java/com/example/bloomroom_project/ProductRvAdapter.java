package com.example.bloomroom_project;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductRvAdapter extends RecyclerView.Adapter<ProductRvAdapter.ViewHolder> {

    private ArrayList<ProductModel> ProductModalArrayList;
    private Context context;

    public ProductRvAdapter(ArrayList<ProductModel> ProductModalArrayList, Context context) {
        this.ProductModalArrayList = ProductModalArrayList;
        this.context = context;

    }

    @NonNull
    @Override
    public ProductRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_rv_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRvAdapter.ViewHolder holder, int position) {

        ProductModel model = ProductModalArrayList.get(position);
        holder.pro_id.setText("product id : " +model.getProductId());
        holder.pro_name.setText("product name : " +model.getProductName());
        holder.pro_price.setText("product price : " +model.getProductPrice());
        holder.pro_qty.setText("product quantity : " +model.getProductQty());
        holder.pro_cat_id.setText("product category id : " +model.getCategoryId());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, UpdateProduct_Activity.class);

                i.putExtra("P_Id", model.getProductId());
                i.putExtra("P_Name", model.getProductName());
                i.putExtra("P_Price", model.getProductPrice());
                i.putExtra("P_Qty", model.getProductQty());
                i.putExtra("P_Cate_Name", model.getCategoryId());

                context.startActivity(i);

            }
        });


    }

    @Override
    public int getItemCount() {
        return ProductModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView pro_id, pro_name, pro_price, pro_qty, pro_cat_id;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pro_id = itemView.findViewById(R.id.Update_Pro_Id);
            pro_name = itemView.findViewById(R.id.Update_Pro_Name);
            pro_price = itemView.findViewById(R.id.Update_Pro_Price);
            pro_qty = itemView.findViewById(R.id.Update_Pro_Qty);
            pro_cat_id = itemView.findViewById(R.id.Update_Pro_Cate_Id);
        }

    }
}
