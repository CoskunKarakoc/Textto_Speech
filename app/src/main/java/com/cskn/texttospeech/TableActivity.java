package com.cskn.texttospeech;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cskn_ on 3.06.2018.
 */

public class TableActivity extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Ogrenci";

    // Contacts table name
    private static final String TABLE_CONTACTS = "ContactActivity";

    // Contacts Table Columns names
    private static final String numara = "numara";
    private static final String adSoyad = "adSoyad";
    private static final String vizenotu = "vizenotu";
    private static final String finalnotu = "finalnotu";
    private static final String ort = "ort";

    public TableActivity(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + numara + " INTEGER PRIMARY KEY," + adSoyad + " TEXT,"
                + vizenotu + " INTEGER," + finalnotu + " INTEGER," + ort
                + " INTEGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addContact(ContactActivity contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(numara, contact.getNumara());
        values.put(adSoyad, contact.getAdSoyad());
        values.put(vizenotu, contact.getVizenotu());
        values.put(finalnotu, contact.getFinalnotu());
        values.put(ort, contact.getOrt());

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    ContactActivity getContact(int numara) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{TableActivity.numara, adSoyad,
                        vizenotu, finalnotu, ort}, TableActivity.numara + "=?",
                new String[]{String.valueOf(numara)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ContactActivity contact = new ContactActivity(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),Integer.parseInt(cursor.getString(2)),Integer.parseInt(cursor.getString(3)),Integer.parseInt(cursor.getString(4)) );
        // return contact
        return contact;
    }

    // Getting All Contacts
    public List<ContactActivity> getAllContacts() {
        List<ContactActivity> contactList = new ArrayList<ContactActivity>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ContactActivity contact = new ContactActivity(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(4));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Updating single contact
    public int updateContact(ContactActivity contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(numara, contact.getNumara());
        values.put(adSoyad, contact.getAdSoyad());
        values.put(vizenotu, contact.getVizenotu());
        values.put(finalnotu, contact.getFinalnotu());
        values.put(ort, contact.getOrt());

        // updating row
        return db.update(TABLE_CONTACTS, values, numara + " = ?",
                new String[]{String.valueOf(contact.getNumara())});
    }

    // Deleting single contact
    public void deleteContact(ContactActivity contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, numara + " = ?",
                new String[]{String.valueOf(contact.getNumara())});
        db.close();
    }


    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}