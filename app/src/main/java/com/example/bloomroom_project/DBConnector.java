package com.example.bloomroom_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

public class DBConnector extends SQLiteOpenHelper {

    //creating database for Bloom Room project
    private static final String DB_NAME = "BloomRoom";
    private static final int DB_VERSION = 1;

    //tables that created for the database

    private static final String Register_Table = "reg_table";
    private static final String Product_Table = "pro_table";
    private static final String Category_Table = "cate_table";
    private static final String Order_Table = "order_table";
    private static final String Invoice_Table = "invo_table";

    //variables for Register table
    private static final String Register_Name = "R_Id";
    //private static final String Register_Id = "R_Name";
    private static final String Register_Password = "R_Password";
    private static final String Register_User = "R_User";

    //variables for product table
    private static final String Product_Id = "P_Id";
    private static final String Product_Name = "P_Name";
    private static final String Product_Price = "P_Price";
    private static final String Product_Qty = "P_Qty";
    private static final String Category_Name = "P_Cate_Name";

    //varibales for category table
    private static final String Category_Id = "C_Id";
    private static final String category_Name = "C_Name";

    //variables for order table
    private static final String User_Id = "User_Id";
    private static final String product_id = "Product_Id";
    private static final String product_Qty = "Product_Qty";

    //variables for Invoice table
    private static final String I_User_Id = "User_Id";
    //private static final String I_Invoice_Id = "Invo_Id";
    private static final String I_Product_Id = "Product_Id";
    private static final String I_Product_Qty = "Product_Qty";
    private static final String I_Product_Qty_Total = "Pro_Qty_Total";

    private Context con;

    public DBConnector(Context context)
    {
        super(context,"BloomRoom",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //creating database query with datatypes

        String query = "CREATE TABLE " + Register_Table + " (" + Register_Name + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Register_Password + " TEXT, " + Register_User + " TEXT)";

        String query01 = "CREATE TABLE " + Product_Table + " (" + Product_Id + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Product_Name + " TEXT, " + Product_Price + " TEXT, "
                + Product_Qty + " INTEGER, " + Category_Name + " TEXT)";

        String query02 = " CREATE TABLE " + Category_Table + " (" + Category_Id + " INTEGER PRIMARY KEY, "
                + category_Name + " TEXT)";

        String query03 = "CREATE TABLE " + Order_Table + " (" + User_Id + " INTEGER PRIMARY KEY, "
                + product_id + " TEXT, " + product_Qty + " TEXT)" ;

        String query04 = "CREATE TABLE " + Invoice_Table + " (" + I_User_Id + " INTEGER PRIMARY KEY, "
                + I_Product_Id + " TEXT, " + I_Product_Qty + " TEXT, "
                + I_Product_Qty_Total + " TEXT)";

        //Queries

        db.execSQL(query);
        db.execSQL(query01);
        db.execSQL(query02);
        db.execSQL(query03);
        db.execSQL(query04);

    }

    public void addnewcate(String id, String name){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Category_Id, id);
        values.put(category_Name, name);

        db.insert(Category_Table, null, values);

    }

    public String getCategory_Id(String CategoryName){

        SQLiteDatabase db = this.getReadableDatabase();
        String CategoryId = null;
        try
        {
            Cursor cursor = db.rawQuery("Select C_Name from cate_table where C_Name='" + CategoryName + "' ", null);
            if (cursor.moveToFirst())
            {
                CategoryId=cursor.getString(0);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return CategoryId;
    }


    public Vector<String> getCategory_Name() {

        SQLiteDatabase db = this.getReadableDatabase();

        Vector<String> vecCategory = new Vector<>();
        try {
            Cursor cursor = db.rawQuery("select C_Name from cate_table", null);
            if (cursor.moveToFirst())
            {
                do {
                    vecCategory.add(cursor.getString(0));
                }
                while (cursor.moveToNext());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return vecCategory;
    }


    public void addnewUser(String userId, String user_password, String userType){

        //variable creating for SQLite database and calling as writable

        SQLiteDatabase db = this.getWritableDatabase();

        //variable creating for content value

        ContentValues values = new ContentValues();

        //all values for variables

        values.put(Register_Name, userId);
        //values.put(Register_Id, username);
        values.put(Register_Password, user_password);
        values.put(Register_User, userType);

        //all values for database

        db.insert(Register_Table, null, values);
        db.close();

    }

    public ArrayList<User_Class> ValidationLogin(String UserName, String Password){

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<User_Class> userList=new ArrayList<>();
        try
        {
            Cursor cursor = db.rawQuery("select * from reg_table where R_Id='" + UserName + "' and R_Password='" + Password + "'", null);
            if (cursor.moveToFirst())
            {
                do {
                    User_Class user=new User_Class();
                    user.setUserId(cursor.getString(0));
                    user.setPassword(cursor.getString(1));
                    user.setUserType(cursor.getString(2));
                    userList.add(user);
                }
                while (cursor.moveToNext());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return userList;

    }


    public void addNewOrder(String u_id, String p_id, String p_qty){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(User_Id, u_id);
        values.put(product_id, p_id);
        values.put(product_Qty, p_qty);

        db.insert(Order_Table, null, values);
    }

    public ArrayList<OrderModel> readOrder() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorOrders = db.rawQuery("SELECT * FROM " + Order_Table, null);

        ArrayList<OrderModel> orderModelArrayList = new ArrayList<>();

        if (cursorOrders.moveToFirst()){
            do {
                orderModelArrayList.add(new OrderModel(cursorOrders.getString(0),
                        cursorOrders.getString(1),
                        cursorOrders.getString(2)));
            }
            while (cursorOrders.moveToNext());
        }
        cursorOrders.close();
        return orderModelArrayList;
    }

    public void updateOrder(String original_user_id, String User_id, String Product_id, String Product_qty) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(User_Id, User_id);
        values.put(product_id, Product_id);
        values.put(product_Qty, Product_qty);

        db.update(Order_Table, values, "User_Id=?",new String[]{original_user_id});
        db.close();
    }


    public void deleteOrder(String Original_user_id){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Order_Table, "User_Id=?", new String[]{Original_user_id});
        db.close();
    }

    public ArrayList<Order_Class> searchOrder(String O_id) {

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Order_Class> OrderList = new ArrayList<Order_Class>();
        try
        {
            Cursor cursor = db.rawQuery("select * from Order_Table where product_id='" + O_id + "' ", null);
            if (cursor.moveToFirst()) {
                do {
                    Order_Class order_class = new Order_Class();
                    order_class.setOrderProduct_id(cursor.getString(1));
                    order_class.setOrderuserId(cursor.getString(0));
                    order_class.setOrderProduct_qty(cursor.getString(2));
                    OrderList.add(order_class);
                }
                while (cursor.moveToNext());
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return OrderList;
    }


    public void addNewProduct(String p_id, String p_name, String p_price, String p_qty, String cate_name) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        //values.put(Product_Id, p_id);
        values.put(Product_Name, p_name);
        values.put(Product_Price, p_price);
        values.put(Product_Qty, p_qty);
        values.put(Category_Name, cate_name);

        db.insert(Product_Table, null, values);
    }


    public ArrayList<ProductModel> readProduct() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorProducts = db.rawQuery("SELECT * FROM " + Product_Table, null);

        ArrayList<ProductModel> ProductModalArrayList = new ArrayList<>();

        if (cursorProducts.moveToFirst()) {
            do {
                ProductModalArrayList.add(new ProductModel(cursorProducts.getString(0),
                        cursorProducts.getString(1),
                        cursorProducts.getString(4),
                        cursorProducts.getString(2),
                        cursorProducts.getString(3)));
            }
            while (cursorProducts.moveToNext());
        }
        cursorProducts.close();
        return ProductModalArrayList;
    }


    public void updateProduct(String org_product_id, String u_product_id, String u_product_name, String u_product_price, String u_product_qty, String u_product_cate_id) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Product_Id, u_product_id);
        values.put(Product_Name, u_product_name);
        values.put(Product_Price, u_product_price);
        values.put(Product_Qty, u_product_qty);
        values.put(Category_Name, u_product_cate_id);

        db.update(Product_Table, values, "P_Id=?", new String[]{org_product_id});
        db.close();
    }


    public void deleteProduct(String org_Product_Id) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Product_Table, "P_Id=?", new String[]{org_Product_Id});
        db.close();
    }


    public ArrayList<Product_Class> searchProduct(String product_ID) {

        SQLiteDatabase db = this.getReadableDatabase();

        ArrayList<Product_Class> productList = new ArrayList<>();
        try {
            Cursor cursor = db.rawQuery("select * from pro_table where P_Id='" + product_ID + "' ", null);
            if (cursor.moveToFirst()) {
                do {
                    Product_Class product_class = new Product_Class();
                    product_class.setProductId(cursor.getString(0));
                    product_class.setProductName(cursor.getString(1));
                    product_class.setPrice(cursor.getInt(2));
                    product_class.setQuantity(cursor.getInt(3));
                    product_class.setCategoryId(cursor.getString(4));
                    productList.add(product_class);
                }
                while (cursor.moveToNext());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();

        }
        return productList;
    }

    public void buyProduct(String Product_Id, int Qty) {

        SQLiteDatabase db = this.getWritableDatabase();

        try {
            db.execSQL("update pro_table set P_Qty=P_Qty-" + Qty + " where P_Id= '" + Product_Id + "' ");

            Toast.makeText(con, "Purchase Successfull...", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex) {

        }
    }

    public boolean insertInvoice(Invoice_Class invoice_class) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(I_User_Id, invoice_class.getUserid());
            //values.put(I_Invoice_Id, invoice_class.getInvoiceId());
            values.put(I_Product_Id, invoice_class.getProductId());
            values.put(I_Product_Qty, invoice_class.getQty());
            values.put(I_Product_Qty_Total, invoice_class.getTotal());

            db.insert(Invoice_Table, null, values);
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Register_Table);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + Product_Table);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + Category_Table);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + Order_Table);
        onCreate(db);
        /*db.execSQL("DROP TABLE IF EXISTS " + Invoice_Table);
        onCreate(db);*/

    }


}
