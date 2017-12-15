package edu.colorado.oit.crm.salesforce;

import com.sforce.soap.partner.sobject.SObject;

/**
 * Created by grbu2232 on 5/25/17.
 */
public class SObjectBuilder {

    private SObject sob;
    public SObjectBuilder(String type) {
        this.sob = new SObject();
        sob.setType(type);
    }

    public SObjectBuilder(SObject sob) {
        this.sob = sob;
    }

    public SObjectBuilder addField(String fieldName, Object value) {
        sob.setField(fieldName, value);
        return this;
    }

    public SObjectBuilder addField(String fieldName, SObjectBuilder builder) {
        sob.setField(fieldName, builder.build());
        return this;
    }

    public SObject build() {
        return sob;
    }
}
