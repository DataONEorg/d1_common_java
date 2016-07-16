package org.dataone.service.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.apache.commons.lang3.StringUtils;
import org.dataone.service.types.v1.AccessPolicy;
import org.dataone.service.types.v1.ReplicationPolicy;

public class ReplicationPolicyMarshallingAdapter extends XmlAdapter<ReplicationPolicy, ReplicationPolicy> {


    @Override
    public ReplicationPolicy unmarshal(ReplicationPolicy rp) throws Exception {
        return rp;
    }

    @Override
    public ReplicationPolicy marshal(ReplicationPolicy rp) throws Exception {
        if (rp != null 
                && rp.sizeBlockedMemberNodeList() == 0 
                && rp.sizePreferredMemberNodeList() == 0
                && rp.getReplicationAllowed() == null
                && rp.getNumberReplicas() == null)
            return null;
        return rp;
    }
}

