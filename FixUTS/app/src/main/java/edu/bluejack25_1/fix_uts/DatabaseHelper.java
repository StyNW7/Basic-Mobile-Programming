package edu.bluejack25_1.fix_uts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import edu.bluejack25_1.fix_uts.model.Order;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "toko", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = "CREATE TABLE Orders ("
                + "Name TEXT PRIMARY KEY,"
                + "OrderName TEXT,"
                + "Quantity TEXT)";
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Orders");
        onCreate(db);
    }

    public boolean insertOrder(String name, String orderName, String quantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("OrderName", orderName);
        contentValues.put("Quantity", quantity);
        long result = db.insert("Orders", null, contentValues);
        db.close();
        if (result != -1){
            return true;
        }
        return false;
    }

    public ArrayList<Order> getAllOrders(){

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM Orders";
        ArrayList<Order> orderList = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                Order order = new Order();
                order.setName(cursor.getString(cursor.getColumnIndexOrThrow("Name")));
                order.setOrderName(cursor.getString(cursor.getColumnIndexOrThrow("OrderName")));
                order.setQuantity(cursor.getString(cursor.getColumnIndexOrThrow("Quantity")));
                orderList.add(order);
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return orderList;

    }

}
