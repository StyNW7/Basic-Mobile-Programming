package id.example.pert4.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Objects;

import id.example.pert4.model.Product;

public class WishlistDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "wishlist.db";
    private static final int DB_VER = 1;
    private static final String TABLE_WISHLIST = "wishlist";

    private static final String COLUMN_ID = "id", COLUMN_NAME = "name", COLUMN_PRICE = "price", COLUMN_DESCRIPTION = "description";

    public WishlistDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VER);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO: buat table, execSQL


    }

    public void addToWishlist(Product product){
        //TODO: insert ke db pakai contentvalue.

    }

    public ArrayList<Product> getAllWishList(){
        //TODO: get semua data dari db, pakai cursor.
        ArrayList<Product> arrProduct = new ArrayList<>();
        

        return arrProduct;
    }


    public void removeFromWishlist(int productId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_WISHLIST + " WHERE " + COLUMN_ID + "=?";
        String args[] = {String.valueOf(productId)};
        db.execSQL(query, args);
        db.close();
    }



    public boolean isInWishlist(int productId) {
        // cek apakah produk ada di wishlist, kalau cursor lebih dari 1 berarti id nya ada.
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_WISHLIST + " WHERE " + COLUMN_ID + "=?";
        String args[] = {String.valueOf(productId)};
        Cursor cursor = db.rawQuery(query, args);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //jika version naik, maka ini akan jalan.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WISHLIST);
        onCreate(db);

    }
}
