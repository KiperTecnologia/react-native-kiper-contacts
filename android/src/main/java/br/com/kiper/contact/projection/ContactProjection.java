package br.com.kiper.contact.projection;

import android.provider.ContactsContract.CommonDataKinds.Phone;

/**
 * Created by david on 23/08/18.
 */

public class ContactProjection {

    public final static String[] CONTACT_WITHOUT_NUMBER = new String[]{
            Phone.CONTACT_ID, Phone.DISPLAY_NAME};

    public final static String[] CONTACT_WITH_NUMBER = new String[]{
            Phone.CONTACT_ID, Phone.NUMBER};
}
