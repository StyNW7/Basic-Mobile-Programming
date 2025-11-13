package edu.bluejack25_1.fix_uts;

import android.animation.LayoutTransition;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import edu.bluejack25_1.fix_uts.model.Product;

public class InputAdapter extends ArrayAdapter<Product> {

    private Context context;
    private int resource;
    private ArrayList<Product> items;
    public InputAdapter(Context context, int resource, ArrayList<Product> items) {
        super(context, resource, items);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Product product = getItem(position);
        convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        TextView txtName = convertView.findViewById(R.id.txtName);
        TextView txtPrice = convertView.findViewById(R.id.txtPrice);
        ImageView img = convertView.findViewById(R.id.imgItem);

        txtName.setText(product.getNama());
        txtPrice.setText(product.getHarga());
        img.setImageResource(product.getImage());
        return convertView;
    }

}
