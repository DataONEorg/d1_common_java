package org.dataone.service.types.v2.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.dataone.service.exceptions.InvalidRequest;
import org.dataone.service.exceptions.InvalidSystemMetadata;
import org.dataone.service.types.v1.NodeReference;
import org.dataone.service.types.v1.Service;
import org.dataone.service.types.v1.Services;
import org.dataone.service.types.v1.TypeFactory;
import org.dataone.service.types.v2.Node;
import org.dataone.service.types.v2.NodeList;
import org.dataone.service.types.v2.SystemMetadata;
import org.junit.Test;


public class AuthUtilsV2TestCase {

	@Test
	public void testIsCNAuthorityForSystemMetadataUpdates_Yes() throws InvalidSystemMetadata, InvalidRequest {
	    NodeReference nodeId = TypeFactory.buildNodeReference("fooNode");
	    NodeList nl = new NodeList();
	    nl.addNode(new Node());
	    nl.getNode(0).setIdentifier(nodeId);
	    nl.getNode(0).setServices(new Services());

	    nl.getNode(0).getServices().addService(new Service());
        nl.getNode(0).getServices().getService(0).setName("MNCore");
        nl.getNode(0).getServices().getService(0).setVersion("v1");
        nl.getNode(0).getServices().getService(0).setAvailable(true);

        nl.getNode(0).getServices().addService(new Service());
        nl.getNode(0).getServices().getService(1).setName("MNStorage");
        nl.getNode(0).getServices().getService(1).setVersion("v1");
        nl.getNode(0).getServices().getService(1).setAvailable(true);
	        
	    SystemMetadata sysmeta = new SystemMetadata();
	    sysmeta.setAuthoritativeMemberNode(nodeId);
	    
	    assertTrue("The CN should be the authority",AuthUtils.isCNAuthorityForSystemMetadataUpdate(nl, sysmeta));
	    
	}
	
	
	@Test
	public void testIsCNAuthorityForSystemMetadataUpdates_No_HigherVersionServices() throws InvalidSystemMetadata, InvalidRequest {
	    NodeReference nodeId = TypeFactory.buildNodeReference("fooNode");
	    NodeList nl = new NodeList();
	    nl.addNode(new Node());
	    nl.getNode(0).setIdentifier(nodeId);
	    nl.getNode(0).setServices(new Services());

	    nl.getNode(0).getServices().addService(new Service());
	    nl.getNode(0).getServices().getService(0).setName("MNCore");
	    nl.getNode(0).getServices().getService(0).setVersion("v3");
	    nl.getNode(0).getServices().getService(0).setAvailable(true);

	    nl.getNode(0).getServices().addService(new Service());
	    nl.getNode(0).getServices().getService(1).setName("MNStorage");
	    nl.getNode(0).getServices().getService(1).setVersion("v1");
	    nl.getNode(0).getServices().getService(1).setAvailable(true);

	    SystemMetadata sysmeta = new SystemMetadata();
	    sysmeta.setAuthoritativeMemberNode(nodeId);

	    assertFalse("The CN should not be the authority",AuthUtils.isCNAuthorityForSystemMetadataUpdate(nl, sysmeta));

	}
	
	   @Test
	    public void testIsCNAuthorityForSystemMetadataUpdates_No_v1ReadOnly() throws InvalidSystemMetadata, InvalidRequest {
	        NodeReference nodeId = TypeFactory.buildNodeReference("fooNode");
	        NodeList nl = new NodeList();
	        nl.addNode(new Node());
	        nl.getNode(0).setIdentifier(nodeId);
	        nl.getNode(0).setServices(new Services());

	        nl.getNode(0).getServices().addService(new Service());
	        nl.getNode(0).getServices().getService(0).setName("MNCore");
	        nl.getNode(0).getServices().getService(0).setVersion("v1");
	        nl.getNode(0).getServices().getService(0).setAvailable(true);

	        nl.getNode(0).getServices().addService(new Service());
	        nl.getNode(0).getServices().getService(1).setName("MNRead");
	        nl.getNode(0).getServices().getService(1).setVersion("v1");
	        nl.getNode(0).getServices().getService(1).setAvailable(true);
	        
	        nl.getNode(0).getServices().addService(new Service());
            nl.getNode(0).getServices().getService(2).setName("MNStorage");
            nl.getNode(0).getServices().getService(2).setVersion("v1");
            nl.getNode(0).getServices().getService(2).setAvailable(false);


	        SystemMetadata sysmeta = new SystemMetadata();
	        sysmeta.setAuthoritativeMemberNode(nodeId);

	        assertFalse("The CN should not be the authority",AuthUtils.isCNAuthorityForSystemMetadataUpdate(nl, sysmeta));

	    }
	   
       @Test
       public void testIsCNAuthorityForSystemMetadataUpdates_No_BadVersionString() throws InvalidSystemMetadata, InvalidRequest {
           NodeReference nodeId = TypeFactory.buildNodeReference("fooNode");
           NodeList nl = new NodeList();
           nl.addNode(new Node());
           nl.getNode(0).setIdentifier(nodeId);
           nl.getNode(0).setServices(new Services());

           nl.getNode(0).getServices().addService(new Service());
           nl.getNode(0).getServices().getService(0).setName("MNCore");
           nl.getNode(0).getServices().getService(0).setVersion("v1");
           nl.getNode(0).getServices().getService(0).setAvailable(true);

           nl.getNode(0).getServices().addService(new Service());
           nl.getNode(0).getServices().getService(1).setName("MNRead");
           nl.getNode(0).getServices().getService(1).setVersion("vsdf2");
           nl.getNode(0).getServices().getService(1).setAvailable(true);
           
           nl.getNode(0).getServices().addService(new Service());
           nl.getNode(0).getServices().getService(2).setName("MNStorage");
           nl.getNode(0).getServices().getService(2).setVersion("v1");
           nl.getNode(0).getServices().getService(2).setAvailable(true);


           SystemMetadata sysmeta = new SystemMetadata();
           sysmeta.setAuthoritativeMemberNode(nodeId);

           assertTrue("The CN should be the authority",AuthUtils.isCNAuthorityForSystemMetadataUpdate(nl, sysmeta));

       }
       
       @Test
       public void testIsCNAuthorityForSystemMetadataUpdates_NodeNotFound() throws InvalidRequest {
           NodeReference nodeId = TypeFactory.buildNodeReference("fooNode");
           NodeList nl = new NodeList();
           nl.addNode(new Node());
           nl.getNode(0).setIdentifier(nodeId);
           nl.getNode(0).setServices(new Services());

           nl.getNode(0).getServices().addService(new Service());
           nl.getNode(0).getServices().getService(0).setName("MNCore");
           nl.getNode(0).getServices().getService(0).setVersion("v1");
           nl.getNode(0).getServices().getService(0).setAvailable(true);

           nl.getNode(0).getServices().addService(new Service());
           nl.getNode(0).getServices().getService(1).setName("MNRead");
           nl.getNode(0).getServices().getService(1).setVersion("vsdf2");
           nl.getNode(0).getServices().getService(1).setAvailable(true);
           
           nl.getNode(0).getServices().addService(new Service());
           nl.getNode(0).getServices().getService(2).setName("MNStorage");
           nl.getNode(0).getServices().getService(2).setVersion("v1");
           nl.getNode(0).getServices().getService(2).setAvailable(true);


           SystemMetadata sysmeta = new SystemMetadata();
           sysmeta.setAuthoritativeMemberNode(TypeFactory.buildNodeReference("bar"));
           try {
               AuthUtils.isCNAuthorityForSystemMetadataUpdate(nl, sysmeta);
               fail("Should not get this far - should throw an exception.");
           } catch (InvalidSystemMetadata e) {
               ; // that's good
           }
       }
}