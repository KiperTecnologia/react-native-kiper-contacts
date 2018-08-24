package br.com.kiper.contact.service;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.Phone;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;

import java.util.LinkedHashMap;
import java.util.Map;

import br.com.kiper.contact.model.Contact;
import br.com.kiper.contact.projection.ContactProjection;

/**
 * Created by david on 23/08/18.
 */

public class ContactService {

    private final ContentResolver contentResolver;
    private final ReadableArray filters;
    private final Integer limit;
    private final Integer offset;
    private Map<String, WritableArray> contactsWithNumbers;

    public ContactService(ContentResolver contentResolver, ReadableArray filters, Integer limit, Integer offset) {
        this.contentResolver = contentResolver;
        this.filters = filters;
        this.limit = limit;
        this.offset = offset;
    }

    public WritableArray getContacts() {
        FilterService filterService = new FilterService(filters);

        Cursor cursorWithNumber = loadContactsWithNumbers(filterService);
        contactsWithNumbers = getContactsWithNumbers(cursorWithNumber);

        Cursor cursorWithoutNumber = loadContactsWithoutNumbers(filterService);
        WritableArray contacts = buildContacts(cursorWithoutNumber);
        return contacts;
    }

    private Cursor loadContactsWithNumbers(FilterService filterService){
        Cursor cursor = contentResolver.query(
                Phone.CONTENT_URI,
                ContactProjection.CONTACT_WITH_NUMBER,
                filterService.buildSelection(),
                filterService.buildSelectionArgs(),
                null
        );
        return cursor;
    }

    private Cursor loadContactsWithoutNumbers(FilterService filterService){
        Cursor cursor = contentResolver.query(
                Phone.CONTENT_URI,
                ContactProjection.CONTACT_WITHOUT_NUMBER,
                filterService.buildSelection(),
                filterService.buildSelectionArgs(),
                Phone.DISPLAY_NAME + " ASC " + buildLimitOffset()
        );
        return cursor;
    }

    private Map<String, WritableArray> getContactsWithNumbers(Cursor cursor) {
        Map<String, WritableArray> contacts = new LinkedHashMap();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(Phone.CONTACT_ID));
                String phone = cursor.getString(cursor.getColumnIndex(Phone.NUMBER));
                WritableArray phones = Arguments.createArray();
                phones.pushString(phone);

                if (!contacts.containsKey(id)) {
                    contacts.put(id, phones);
                } else {
                    WritableArray numbers = contacts.get(id);
                    numbers.pushString(phone);
                    contacts.put(id, numbers);
                }

            }
            cursor.close();
        }
        return contacts;
    }

    private WritableArray buildContacts(Cursor cursor) {
        WritableArray contacts = Arguments.createArray();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(Phone.CONTACT_ID));
                String name = cursor.getString(cursor.getColumnIndex(Phone.DISPLAY_NAME));
                Contact contact = new Contact();
                contact.setContactId(id);
                contact.setName(name);
                contact.setPhones(contactsWithNumbers.get(id));
                contacts.pushMap(contact.toMap());
            }
            cursor.close();
        }
        return contacts;
    }

    private String buildLimitOffset() {
        String result = "";
        if (limit != null && limit > 0) {
            result += " LIMIT " + limit;
        }
        if (offset != null && offset > 0) {
            result += " OFFSET " + offset;
        }
        return result;
    }


}
