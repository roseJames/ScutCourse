package com.rosejames.picdemo.engine;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.rosejames.picdemo.config.PicDemoApplication;
import com.rosejames.picdemo.model.Contact;

import java.util.ArrayList;

/**
 * Created by rosejames on 16/6/8.
 */
public class ContactInfoCenter {

    public ArrayList<Contact> getContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();
        ContentResolver cr = PicDemoApplication.getApplication().getContentResolver();

        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if (!cursor.moveToFirst()) {
            return contacts;
        }
        while (cursor.moveToNext()) {
            Contact contact = new Contact();
            int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            String name = cursor.getString(nameIndex);

            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

            Cursor phoneNumbers = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = "
                    + contactId, null, null);
            String phoneNumber = "";
            if (phoneNumbers.moveToFirst()) {
                phoneNumber = phoneNumbers.getString(phoneNumbers.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            phoneNumbers.close();
            contacts.add(contact.setId(contactId).setName(name).setPhoneNumber(phoneNumber));
        }
        cursor.close();
        return contacts;
    }
}
