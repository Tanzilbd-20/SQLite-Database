package com.example.databasedemo.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.databasedemo.R;
import com.example.databasedemo.model.Contact;
import com.example.databasedemo.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME,null,Util.DATABASE_VERSION);
    }

    //Create our table.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //SQL - Structure Query Language.

        //Create a table name -> (id , name , phone_number);
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + Util.TABLE_NAME +"("
                + Util.KEY_ID + " INTEGER PRIMARY KEY,"+Util.KEY_NAME+" TEXT,"
                +Util.KEY_PHONE_NUMBER+" TEXT"+")";

        sqLiteDatabase.execSQL(CREATE_CONTACT_TABLE);//Creating our table.
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String DROP_TABLE = String.valueOf(R.string.dp_drop);
        sqLiteDatabase.execSQL(DROP_TABLE, new String[]{Util.DATABASE_NAME});

        //Create a table again.
        onCreate(sqLiteDatabase);

    }

    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Util.KEY_NAME,contact.getName());
        values.put(Util.KEY_PHONE_NUMBER,contact.getPhone_number());

        db.insert(Util.TABLE_NAME,null,values);
        Log.d("DBHandler", "Item Added");
        db.close();
    }

    public Contact getContact(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.TABLE_NAME,
                new String[]{Util.KEY_ID, Util.KEY_NAME, Util.KEY_PHONE_NUMBER},
                Util.KEY_ID +"=?",new String[]{String.valueOf(id)},
                null, null, null
                );

        if(cursor !=null)
            cursor.moveToFirst();

        Contact contact = new Contact();
        contact.setId(Integer.parseInt(cursor.getString(0)));
        contact.setName(cursor.getString(1));
        contact.setPhone_number(cursor.getString(2));

        return contact;


    }

    public List<Contact> getAllContact(){

        List<Contact> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = "SELECT * FROM "+Util.TABLE_NAME;

        Cursor cursor = db.rawQuery(selectAll,null);

        if(cursor.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhone_number(cursor.getString(2));

                contactList.add(contact);
            }while(cursor.moveToNext());

        }
        return contactList;
    }

    //Update Contact Method
    public int updateContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Util.KEY_NAME,contact.getName());
        values.put(Util.KEY_PHONE_NUMBER,contact.getPhone_number());

        //Update the row.
        //Update --> Table_Name, values, Which ID we wanna update, then set that Id.
        return db.update(Util.TABLE_NAME,values,Util.KEY_ID+"=?",
                new String[]{String.valueOf(contact.getId())});



    }

}
