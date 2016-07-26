package org.dataone.service.util;

import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;


public class DateMarshallingAdapter extends XmlAdapter<String, Date> {


    @Override
    public Date unmarshal(String dateString) throws Exception {
        return DateTimeMarshaller.deserializeDateToUTC(dateString);
    }

    @Override
    public String marshal(Date date) throws Exception {
       return DateTimeMarshaller.serializeDateToUTC(date); 
    }
}

