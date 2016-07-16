package org.dataone.service.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.dataone.service.types.v1.AccessPolicy;
import org.dataone.service.types.v1.AccessRule;

public class AccessRuleMarshallingAdapter extends XmlAdapter<AccessRule, AccessRule> {


    @Override
    public AccessRule unmarshal(AccessRule ar) throws Exception {
//        if (ap == null || ap.sizeAllowList() == 0)
//            return null;
        
        return ar;
    }

    /**
     * a valid accessRule has at least one Permission and at least one Subject 
     */
    @Override
    public AccessRule marshal(AccessRule ar) throws Exception {
        if (ar != null) 
            if (ar.sizePermissionList() == 0 || ar.sizeSubjectList() == 0)
                throw new IllegalStateException("AccessRule needs at least one Permission and Subject!");
        return ar;
    }
}

