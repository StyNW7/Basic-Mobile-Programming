package edu.bluejack25_1.fix_uts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

import edu.bluejack25_1.fix_uts.model.Order;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "toko", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_table = "CREATE TABLE Orders ("
                + "Name TEXT PRIMARY KEY,"
                + "OrderName TEXT,"
                + "Quantity TEXT)";
        sqLiteDatabase.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Orders");
        onCreate(sqLiteDatabase);
    }

    public boolean insertOrder(String name, String orderName, String quantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("OrderName", orderName);
        contentValues.put("Quantity", quantity);
        long result = db.insert("Orders", null, contentValues);
        if (result != -1){
            return true;
        }
        return false;
    }

    public ArrayList<Order> getAllOrders(){

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Order> orderList = new ArrayList<>();

        String query = "SELECT * FROM Orders";
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

    // =========================
    // GET SINGLE ORDER BY NAME
    // =========================
    public Order getOrder(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Order order = null;

        Cursor cursor = db.rawQuery("SELECT * FROM Orders Where Name=?", new String[]{name});

        if (cursor.moveToFirst()) {
            order = new Order();
            order.setName(cursor.getString(cursor.getColumnIndexOrThrow("Name")));
            order.setOrderName(cursor.getString(cursor.getColumnIndexOrThrow("OrderName")));
            order.setQuantity(cursor.getString(cursor.getColumnIndexOrThrow("Quantity")));
        }

        cursor.close();
        db.close();
        return order;
    }

    // =========================
    // UPDATE ORDER
    // =========================
    public boolean updateOrder(String name, String newOrderName, String newQuantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("OrderName", newOrderName);
        values.put("Quantity", newQuantity);

        int result = db.update("Orders", values, "Name = ?", new String[]{name});
        db.close();
        return result > 0; // true jika ada baris yang diupdate
    }

    // =========================
    // DELETE ORDER
    // =========================
    public boolean deleteOrder(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("Orders", "Name = ?", new String[]{name});
        db.close();
        return result > 0; // true jika ada baris yang dihapus
    }

}
