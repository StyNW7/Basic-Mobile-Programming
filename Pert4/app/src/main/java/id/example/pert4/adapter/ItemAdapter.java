package id.example.pert4.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.example.pert4.ProductDetailActivity;
import id.example.pert4.R;
import id.example.pert4.model.Product;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

//    1. siapin data, update si getItemCount -- done
    private ArrayList<Product> arrData;

    public ItemAdapter(ArrayList<Product> arrData) {
        this.arrData = arrData;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //    2. inflate -- done
        View view = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_layout, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        //    3. manage UI -- done
        Product data = arrData.get(position);
        holder.txtTitle.setText(data.getName());
        holder.txtPrice.setText("IDR "+data.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("ItemAdapter", "onClick: "+data.getName());
                Intent intent = new Intent(holder.itemView.getContext(), ProductDetailActivity.class);
                intent.putExtra("data", data);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrData.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        //init ui component
        public TextView txtTitle, txtPrice;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtPrice = itemView.findViewById(R.id.txtPrice);
        }
    }

}
