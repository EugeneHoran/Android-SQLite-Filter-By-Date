package com.eugene.sqlitetesting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.eugene.sqlitetesting.Util.DateUtilities;

import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String STATE_DATE = "retain_date"; // save date on screen rotation
    private DatabaseHandler db;
    private ContactAdapter contactAdapter;
    private Date mDate;
    private EditText editText;
    private ListView listView;
    private TextView txtDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Retain Instance State
        if (savedInstanceState != null) {
            mDate = (Date) savedInstanceState.getSerializable(STATE_DATE);
        } else {
            mDate = new Date();
        }

        // Widgets
        editText = (EditText) findViewById(R.id.editText);
        listView = (ListView) findViewById(R.id.listView);
        txtDate = (TextView) findViewById(R.id.mDate);
        Button btnAddItem = (Button) findViewById(R.id.button);
        Button prevD = (Button) findViewById(R.id.prevD);
        Button nexD = (Button) findViewById(R.id.nexD);

        // Set the adapter based on date
        setAdapter(mDate);

        // Listeners
        prevD.setOnClickListener(new View.OnClickListener() { // previous date
            @Override
            public void onClick(View v) {
                mDate = DateUtilities.previousDate();
                setAdapter(mDate);
            }
        });
        nexD.setOnClickListener(new View.OnClickListener() { // next date
            @Override
            public void onClick(View v) {
                mDate = DateUtilities.nextDate();
                setAdapter(mDate);
            }
        });
        btnAddItem.setOnClickListener(new View.OnClickListener() { // add item
            @Override
            public void onClick(View v) {
                Contact newContact;
                if (editText.getText().toString().trim().length() > 0) {
                    db.addContact(newContact = new Contact(editText.getText().toString(), "", DateUtilities.dateToString(mDate)));
                    contactAdapter.add(newContact);
                    contactAdapter.notifyDataSetChanged();
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // item click
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact c = contactAdapter.getItem(position);
                Toast.makeText(MainActivity.this, c.getName() + " Pressed", Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // item long click
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Contact c = contactAdapter.getItem(position);
                Toast.makeText(MainActivity.this, c.getName() + " Deleted", Toast.LENGTH_SHORT).show();
                db.deleteContact(c);
                contactAdapter.remove(c);
                contactAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    // Filter data by date
    private void setAdapter(Date newDate) {
        String date = DateUtilities.dateToString(newDate); // Convert date to string
        db = new DatabaseHandler(this);
        List<Contact> contacts = db.getContactsByDate(date); // filter by string
        contactAdapter = new ContactAdapter(this, 0, contacts);
        listView.setAdapter(contactAdapter);
        txtDate.setText(DateFormat.format("MMM dd", newDate));
    }

    //Save date on screen rotation
    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putSerializable(STATE_DATE, mDate);
    }
}
