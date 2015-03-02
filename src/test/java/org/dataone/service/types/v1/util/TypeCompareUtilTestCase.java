package org.dataone.service.types.v1.util;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.dataone.service.types.v1.AccessPolicy;
import org.dataone.service.types.v1.Identifier;
import org.dataone.service.types.v1.NodeReference;
import org.dataone.service.types.v1.ObjectFormatIdentifier;
import org.dataone.service.types.v1.Permission;
import org.dataone.service.types.v1.Replica;
import org.dataone.service.types.v1.ReplicationPolicy;
import org.dataone.service.types.v1.ReplicationStatus;
import org.dataone.service.types.v1.Subject;
import org.dataone.service.types.v1.SystemMetadata;
import org.dataone.service.types.v1.util.AccessUtil;
import org.dataone.service.types.v1.util.TypeCompareUtil;
import org.junit.Before;
import org.junit.Test;

public class TypeCompareUtilTestCase {

	@Before
	public void setUp() throws Exception {
	}

//	@Test
	public final void testCompareD1ServiceType() {
		fail("Not yet implemented");
	}


	@Test
	public void testGetD1SubtypesListing() {
		SystemMetadata smd = new SystemMetadata();
		smd.setIdentifier(buildIdentifier("myID"));
		smd.setFormatId(buildFormatIdentifier("myFormatID"));
		smd.setDateUploaded(new Date());
		smd.setAuthoritativeMemberNode(buildNodeReference("urn:node:myNode"));
		smd.setDateSysMetadataModified(new Date());
		smd.setRightsHolder(buildSubject("me"));
		smd.setSerialVersion(new BigInteger("12345"));
		Replica r = new Replica();
		r.setReplicaMemberNode(buildNodeReference("urn:node:Xanother"));
		r.setReplicationStatus(ReplicationStatus.REQUESTED);
		r.setReplicaVerified(new Date());
		smd.addReplica(r);
		r = new Replica();
		r.setReplicationStatus(ReplicationStatus.COMPLETED);
		r.setReplicaVerified(new Date());
		r.setReplicaMemberNode(buildNodeReference("urn:node:ONEmore"));
		smd.addReplica(r);
		
		
		AccessPolicy ap = new AccessPolicy();
		ap.addAllow(AccessUtil.createAccessRule(
				new String[]{"you","me"},
				new Permission[]{Permission.READ, Permission.WRITE, Permission.CHANGE_PERMISSION})
				);
		ap.addAllow(AccessUtil.createAccessRule(
				new String[]{"him","her"},
				new Permission[]{Permission.WRITE, Permission.READ})
				);
		smd.setAccessPolicy(ap);
		
		ReplicationPolicy rp = new ReplicationPolicy();
		rp.setNumberReplicas(5);
		rp.setReplicationAllowed(true);
		rp.addBlockedMemberNode(buildNodeReference("urn:node:crazyUncleMN"));
		rp.addPreferredMemberNode(buildNodeReference("urn:node:cousinMN"));
		rp.addPreferredMemberNode(buildNodeReference("urn:node:brotherMN"));
		smd.setReplicationPolicy(rp);
		
		
//		List<String> report = getSubtypes("",smd);
//		for (String s: report) {
//			System.out.println(s);
//		}
		Map<String,String> report = TypeCompareUtil.getD1SubtypesListing("",smd,true);
		for (String s: report.keySet()) {
			System.out.println(s + "\t" + report.get(s)); 
		}
	}
	
	
	@Test
	public void testCompareSysMeta() {
		SystemMetadata sysMeta1 = createStandardSysmeta();
		SystemMetadata sysMeta2 = createStandardSysmeta();
		
		List<String> report = TypeCompareUtil.compareSystemMetadata(sysMeta1, sysMeta2);
		
		assertEquals("If they are the same, then it should be a one-line report",1, report.size());
		assertEquals("If they are the same, then should get 'OK'","OK",report.get(0));
		
		AccessPolicy ap = sysMeta2.getAccessPolicy();
		AccessPolicy apNew = new AccessPolicy();
		apNew.addAllow(ap.getAllow(1));
		apNew.addAllow(ap.getAllow(0));
		sysMeta2.setAccessPolicy(apNew);
		
		report = TypeCompareUtil.compareSystemMetadata(sysMeta1, sysMeta2);
		for (String line : report) {
			System.out.println(line);
		}
		System.out.println("");
		assertEquals("If they are the same, then it should be a one-line report",report.size(),1);
		assertEquals("If they are the same, then should get 'OK'",report.get(0),"OK");
		
		
		sysMeta2.getReplicationPolicy().setNumberReplicas(2);
		report = TypeCompareUtil.compareSystemMetadata(sysMeta1, sysMeta2);
		for (String line : report) {
			System.out.println(line);
		}
		assertTrue("Should have at least 2 rows", report.size() >= 2);
		assertTrue("Should have a difference in the number of replicas",
				report.get(1).contains("NumberReplicas"));	
	}

	
	
	private SystemMetadata createStandardSysmeta() {
		SystemMetadata smd = new SystemMetadata();
		smd.setIdentifier(buildIdentifier("myID"));
		smd.setFormatId(buildFormatIdentifier("myFormatID"));
		smd.setDateUploaded(new Date());
		smd.setAuthoritativeMemberNode(buildNodeReference("urn:node:myNode"));
		smd.setDateSysMetadataModified(new Date());
		smd.setRightsHolder(buildSubject("me"));
		smd.setSerialVersion(new BigInteger("12345"));
		Replica r = new Replica();
		r.setReplicaMemberNode(buildNodeReference("urn:node:Xanother"));
		r.setReplicationStatus(ReplicationStatus.REQUESTED);
		r.setReplicaVerified(new Date());
		smd.addReplica(r);
		r = new Replica();
		r.setReplicationStatus(ReplicationStatus.COMPLETED);
		r.setReplicaVerified(new Date());
		r.setReplicaMemberNode(buildNodeReference("urn:node:ONEmore"));
		smd.addReplica(r);
		
		
		AccessPolicy ap = new AccessPolicy();
		ap.addAllow(AccessUtil.createAccessRule(
				new String[]{"you","me"},
				new Permission[]{Permission.READ, Permission.WRITE, Permission.CHANGE_PERMISSION})
				);
		ap.addAllow(AccessUtil.createAccessRule(
				new String[]{"him","her"},
				new Permission[]{Permission.WRITE, Permission.READ})
				);
		smd.setAccessPolicy(ap);
		
		ReplicationPolicy rp = new ReplicationPolicy();
		rp.setNumberReplicas(5);
		rp.setReplicationAllowed(true);
		rp.addBlockedMemberNode(buildNodeReference("urn:node:crazyUncleMN"));
		rp.addPreferredMemberNode(buildNodeReference("urn:node:cousinMN"));
		rp.addPreferredMemberNode(buildNodeReference("urn:node:brotherMN"));
		smd.setReplicationPolicy(rp);
		
		return smd;
	}
	
	private static Identifier buildIdentifier(String value) {
		Identifier id = new Identifier();
		id.setValue(value);
		return id;
	}
	
	private static Subject buildSubject(String value) {
		Subject subject = new Subject();
		subject.setValue(value);
		return subject;
	}
	
	private static ObjectFormatIdentifier buildFormatIdentifier(String value) {
		ObjectFormatIdentifier id = new ObjectFormatIdentifier();
		id.setValue(value);
		return id;
	}
	
	private static NodeReference buildNodeReference(String value) {
		NodeReference nodeRef = new NodeReference();
		nodeRef.setValue(value);
		return nodeRef;
	}
	
	
}
