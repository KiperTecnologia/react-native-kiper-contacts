
package br.com.kiper.contact;

import android.content.ContentResolver;
import android.content.Context;
import android.os.AsyncTask;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;

import br.com.kiper.contact.service.ContactService;

public class KiperContactsModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public KiperContactsModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "KiperContacts";
    }

    @ReactMethod
    public void getAll(Integer limit, Integer offset, Promise promise) {
        getContacts(null, limit, offset, promise);
    }

    @ReactMethod
    public void searchContacts(ReadableArray filters, Integer limit, Integer offset, Promise promise) {
        getContacts(filters, limit, offset, promise);
    }

    private void getContacts(final ReadableArray filters, final Integer limit, final Integer offset, final Promise promise) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Context context = reactContext;
                    ContentResolver cr = context.getContentResolver();

                    ContactService contactService = new ContactService(cr, filters, limit, offset);
                    WritableArray contacts = contactService.getContacts();
                    promise.resolve(contacts);
                } catch (Exception ex) {
                    promise.reject("ERR_UNEXPECTED_EXCEPTION", ex);
                }
            }
        });
    }
}