package org.dataone.service.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.dataone.service.types.v1.AccessPolicy;

public class AccessPolicyMarshallingAdapter extends XmlAdapter<AccessPolicy, AccessPolicy> {


    @Override
    public AccessPolicy unmarshal(AccessPolicy ap) throws Exception {
//        if (ap == null || ap.sizeAllowList() == 0)
//            return null;
        
        return ap;
    }

    @Override
    public AccessPolicy marshal(AccessPolicy ap) throws Exception {
        if (ap == null || ap.sizeAllowList() == 0)
            return null;
        return ap;
    }
}

