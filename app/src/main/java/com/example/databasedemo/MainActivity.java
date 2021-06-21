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

        Log.d("CountContact", "onCreate: "+db.getCountContact());

        Contact tanzil = new Contact();
        tanzil.setName("Tanzil");
        tanzil.setPhone_number("37467823");

        Contact shipan = new Contact("Shipan","4637845375");
        Contact shaheen = new Contact("Shaheen","463743756");
        Contact imran = new Contact("Imran","436545365");
        Contact topu = new Contact("Topu","37436");



     /* db.addContact(tanzil);
        db.addContact(shipan);
        db.addContact(shaheen);
        db.addContact(imran);
        db.addContact(topu);*/



      /*Contact deleteContact = db.getContact(3);
      db.deleteContact(deleteContact);*/
      /* Contact update = db.getContact(1);

       update.setName("Tanzil Ahmed");
       update.setPhone_number("01127273755");

       db.updateContact(update);*/







        List<Contact> contactList = db.getAllContact();
        for(Contact contact : contactList){
            Log.d("Information", "ID : "+contact.getId());
            Log.d("Information", "Name : "+contact.getName());
            Log.d("Information", "Phone Number : "+contact.getPhone_number());
        }

    }
}