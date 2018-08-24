package br.com.kiper.contact.model;

import android.provider.ContactsContract.CommonDataKinds.Phone;

import com.facebook.react.bridge.ReadableMap;

/**
 * Created by david on 23/08/18.
 */

public class Filter {

    private String key;
    private String value;
    private String type;

    public Filter(ReadableMap filter) {
        this.key = filter.hasKey("key") ? filter.getString("key") : null;
        this.value = filter.hasKey("value") ? filter.getString("value") : null;
        this.type = filter.hasKey("type") ? filter.getString("type") : null;
    }

    public String getKey() {
        if (key != null) {
            if (key.toLowerCase().contains("phone") || key.toLowerCase().contains("number")) {
                return Phone.NUMBER;
            } else {
                return Phone.DISPLAY_NAME;
            }
        }
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        if (type != null) {
            if (type.toLowerCase().equals("like right")) {
                return value + "%";
            } else if (type.toLowerCase().equals("like left")) {
                return "%" + value;
            } else if (type.toLowerCase().equals("like")) {
                return "%" + value + "%";
            }
        }
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        if (type != null) {
            if (type.toLowerCase().contains("like")) {
                return " LIKE ? ";
            }
        } else {
            return " = ?";
        }
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
