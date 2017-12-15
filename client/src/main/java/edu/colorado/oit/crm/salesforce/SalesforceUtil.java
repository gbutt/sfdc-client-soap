package edu.colorado.oit.crm.salesforce;

import com.sforce.soap.partner.IError;

import java.util.ArrayList;
import java.util.List;

public class SalesforceUtil {
    public static String formatErrors(IError[] errors) {
        List<String> messages = new ArrayList<>(errors.length);
        for (IError error : errors) {
            String message = String.format("%s: %s", error.getStatusCode(), error.getMessage());
            if (error.getFields().length > 0) {
                message += String.format(" [%s]", String.join(", ", error.getFields()));
            }
            messages.add(message);
        }
        return String.join("; ", messages);
    }

}
