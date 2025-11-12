package edu.bluejack25_1.pakaian_sepatu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import edu.bluejack25_1.pakaian_sepatu.model.Order;

public class DatabaseHelper extends SQLiteOpenHelper {

//    private static final String TABLE_NAME = "order";

    public DatabaseHelper(Context context) {
        super(context, "toko", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = "CREATE TABLE Orders ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name STRING,"
                + "quantity INTEGER )";
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Orders");
        onCreate(db);
    }

    public boolean addOrder(String name, String quantity){
        boolean success = false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("quantity", quantity);
        long result = db.insert("Order", null, contentValues);
        if (result != -1) {
            success = true;
        }
        return success;
    }

    public ArrayList<Order> getAllOrders(){

        ArrayList<Order> orderList = new ArrayList<>();
        String selectQuery = "SELECT * FROM Orders";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                Order order = new Order();
                order.setId(cursor.getLong(cursor.getColumnIndexOrThrow("id")));
                order.setName(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                order.setQuantity(cursor.getLong(cursor.getColumnIndexOrThrow("quantity")));
                orderList.add(order);
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return orderList;

    }

}
