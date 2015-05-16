package com.eugene.sqlitetesting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {
    // List context
    private final Context context;
    // List values
    private final List<Contact> contact;

    public ContactAdapter(Context contextx, int textViewResourceId, List<Contact> logs) {
        super(contextx, textViewResourceId);
        context = contextx;
        contact = logs;
    }

    // add item to adapter
    public void add(Contact log) {
        contact.add(log);
    }

    // remove from adapter
    public void remove(Contact log) {
        contact.remove(log);
    }

    // get adapter count
    public int getCount() {
        return contact.size();
    }

    // get contact by position
    public Contact getItem(int position) {
        return contact.get(position);
    }

    // inflate view
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Contact mLog = contact.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.list_contact, parent, false);
        TextView personContact = (TextView) v.findViewById(R.id.contact);
        personContact.setText(mLog.getName());
        return v;
    }
}
