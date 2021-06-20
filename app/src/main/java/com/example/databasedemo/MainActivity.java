package com.example.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.databasedemo.data.DatabaseHandler;
import com.example.databasedemo.model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);

        Contact tanzil = new Contact();
        tanzil.setName("Tanzil");
        tanzil.setPhone_number("37467823");

        Contact shipan = new Contact("Shipan","4637845375");
        Contact shaheen = new Contact("Shaheen","463743756");
        Contact imran = new Contact("Imran","436545365");
        Contact topu = new Contact("Imran","436545365");
        Contact tanvir = new Contact("Imran","436545365");


    /*    db.addContact(tanzil);
        db.addContact(shipan);
        db.addContact(shaheen);
        db.addContact(imran);
        db.addContact(topu);
        db.addContact(tanvir);*/

        Contact c = db.getContact(10);
      c.setName("Tanzil Ahmed");
      c.setPhone_number("01127273755");
      db.updateContact(c);




        List<Contact> contactList = db.getAllContact();
        for(Contact contact : contactList){
            Log.d("MainActivity", "ID : "+contact.getId());
            Log.d("MainActivity", "Name : "+contact.getName());
            Log.d("MainActivity", "Phone Number : "+contact.getPhone_number());
        }

    }
}