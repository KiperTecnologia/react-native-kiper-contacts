package br.com.kiper.contact.model;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;

/**
 * Created by david on 23/08/18.
 */

public class Contact {

    private String contactId;
    private String name;
    private WritableArray phones;

    public WritableMap toMap(){
        WritableMap map = Arguments.createMap();
        map.putString("contactId", this.contactId);
        map.putString("name", this.name);
        map.putArray("phones", this.phones);
        return map;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WritableArray getPhones() {
        if(phones == null){
            phones = Arguments.createArray();
        }
        return phones;
    }

    public void setPhones(WritableArray phones) {
        this.phones = phones;
    }
}
