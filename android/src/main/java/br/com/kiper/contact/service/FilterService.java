package br.com.kiper.contact.service;

import com.facebook.react.bridge.ReadableArray;

import java.util.ArrayList;
import java.util.List;

import br.com.kiper.contact.model.Filter;

/**
 * Created by david on 23/08/18.
 */

public class FilterService {

    private List<Filter> filtersList = new ArrayList();

    public FilterService(ReadableArray filters) {
        if (filters != null) {
            for (int i = 0; i < filters.size(); i++) {
                Filter filter = new Filter(filters.getMap(i));
                filtersList.add(filter);
            }
        }
    }

    public String buildSelection() {
        String result = "";
        if (filtersList != null) {
            int index = 0;
            for (Filter filter : filtersList) {
                result += " " + filter.getKey() + " " + filter.getType();
                if ((index + 1) < filtersList.size()) {
                    result += " AND ";
                }
                index++;
            }
        }
        return result;
    }

    public String[] buildSelectionArgs() {
        String[] args = new String[filtersList.size()];
        int index = 0;
        for (Filter filter : filtersList) {
            args[index] = filter.getValue();
            index++;
        }
        return args;
    }
}
