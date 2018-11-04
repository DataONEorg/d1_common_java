package org.dataone.service.types.v1.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.dataone.exceptions.MarshallingException;
import org.dataone.service.types.v1.Group;
import org.dataone.service.types.v1.Permission;
import org.dataone.service.types.v1.Person;
import org.dataone.service.types.v1.Session;
import org.dataone.service.types.v1.Subject;
import org.dataone.service.types.v1.SubjectInfo;
import org.dataone.service.types.v1.SystemMetadata;
import org.dataone.service.types.v1.TypeFactory;
import org.dataone.service.util.Constants;
import org.dataone.service.util.TypeMarshaller;
import org.junit.Before;
import org.junit.Test;


public class AuthUtilsTestCase {

	private Session standardSession = null;
	
	private Subject publick = null; 
	private Subject authenticated = null; 
	private Subject verified = null; 
	

	private Subject buildSubject(String cn) {
		Subject subject = new Subject();
		subject.setValue("CN=" + cn + ",DC=something,DC=org");
		return subject;
	}
	
	private Person buildTestPerson(String subjectString) {
		Person p = new Person();
		p.setSubject(buildSubject(subjectString));
		return p;
	}
	
	private Group buildTestGroup(String subjectString) {
		Group g = new Group();
		g.setSubject(buildSubject(subjectString));
		return g;
	}
	
	
	@Before
	public void setupSymbolicSubjects() {
		publick = new Subject();
		publick.setValue(Constants.SUBJECT_PUBLIC);
		
		authenticated = new Subject();
		authenticated.setValue(Constants.SUBJECT_AUTHENTICATED_USER);
		
		verified = new Subject();
		verified.setValue(Constants.SUBJECT_VERIFIED_USER);
	}
	
	
	@Before
	public void createSession() {
		if (standardSession == null) {
			Session ses = new Session();
			Subject subjectX = buildSubject("x");
			ses.setSubject(subjectX);

			SubjectInfo si = new SubjectInfo();
			Person x = buildTestPerson("x");
			Person y = buildTestPerson("y");
			Person z = buildTestPerson("z"); 
			z.setVerified(true);
			x.addEquivalentIdentity(y.getSubject());
			x.addEquivalentIdentity(z.getSubject());
			si.addPerson(x);		
			si.addPerson(y);		
			si.addPerson(z);		

			ses.setSubjectInfo(si);
			standardSession = ses;
		}
	}
	
	
	@Test
	public void testAuthorizedClientSubjects_Public() {
				
		Set<Subject> subjectSet = AuthUtils.authorizedClientSubjects(standardSession);		
		assertTrue("public should always appear", subjectSet.contains(publick));
	}

	
	@Test
	public void testAuthorizedClientSubjects_Authenticated() {
		
		Set<Subject> subjectSet = AuthUtils.authorizedClientSubjects(standardSession);
		assertTrue("authenticated should be in the list", subjectSet.contains(authenticated));
	}

	
	@Test
	public void testAuthorizedClientSubjects_Verified() {
		
		Set<Subject> subjectSet = AuthUtils.authorizedClientSubjects(standardSession);	
		assertTrue("verified appears because an equiv identity is verified", subjectSet.contains(verified));
	}

	@Test
	public void testAuthorizedClientSubjects_EquivalentIDs() {
		
		
		Set<Subject> subjectSet = AuthUtils.authorizedClientSubjects(standardSession);
	
		assertTrue("subject list should contain x", subjectSet.contains(buildSubject("x")));
		assertTrue("subject list should contain y", subjectSet.contains(buildSubject("y")));
		assertTrue("subject list should contain z", subjectSet.contains(buildSubject("z")));
	
	}
	
	
	@Test
	public void testAuthorizedClientSubjects_DaisyChainEquivalentIDs() {
		
		Session ses = new Session();
		Subject subjectX = buildSubject("x");
		ses.setSubject(subjectX);

		SubjectInfo si = new SubjectInfo();
		Person x = buildTestPerson("x");
		Person y = buildTestPerson("y");
		Person z = buildTestPerson("z"); 
		z.setVerified(true);
		x.addEquivalentIdentity(y.getSubject());
		y.addEquivalentIdentity(z.getSubject());
		si.addPerson(x);		
		si.addPerson(y);		
		si.addPerson(z);		

		ses.setSubjectInfo(si);
		Set<Subject> subjectSet = AuthUtils.authorizedClientSubjects(standardSession);
	
		assertTrue("subject list contains x", subjectSet.contains(buildSubject("x")));
		assertTrue("subject list contains y", subjectSet.contains(buildSubject("y")));
		assertTrue("subject list contains z", subjectSet.contains(buildSubject("z")));
	
	}
	
	@Test
	public void testAuthorizedClientSubjects_DaisyChainVerified() {
		
		Session ses = new Session();
		Subject subjectX = buildSubject("x");
		ses.setSubject(subjectX);

		SubjectInfo si = new SubjectInfo();
		Person x = buildTestPerson("x");
		Person y = buildTestPerson("y");
		Person z = buildTestPerson("z"); 
		z.setVerified(true);
		x.addEquivalentIdentity(y.getSubject());
		y.addEquivalentIdentity(z.getSubject());
		si.addPerson(x);		
		si.addPerson(y);		
		si.addPerson(z);		

		ses.setSubjectInfo(si);
		Set<Subject> subjectSet = AuthUtils.authorizedClientSubjects(ses);
	
		assertTrue("subject list contains x", subjectSet.contains(verified));
	}

	
	@Test
	public void testAuthorizedClientSubjects_EmptySession_isPublic() {
		
		Session ses = new Session();
		
		Set<Subject> subjectSet = AuthUtils.authorizedClientSubjects(ses);
	
		assertTrue("empty session should revert to public",
				subjectSet.contains(publick));
		assertTrue("empty session should have only 1 subject (public)",
				subjectSet.size() == 1);
	}
	
	@Test
	public void testAuthorizedClientSubjects_Null_isPublic() {
		
		
		Set<Subject> subjectSet = AuthUtils.authorizedClientSubjects(null);
	
		assertTrue("null session should revert to public",
				subjectSet.contains(publick));
		assertTrue("null session should have only 1 subject (public)",
				subjectSet.size() == 1);
	}
	
	
	
	@Test
	public void testAuthorizedClientSubjects_PublicNotAuthenticated() {
		
		Session ses = new Session();
		ses.setSubject(publick);

		SubjectInfo si = new SubjectInfo();
//		Person x = buildTestPerson("x");
//		Person y = buildTestPerson("y");
//		Person z = buildTestPerson("z"); 

		ses.setSubjectInfo(si);
		Set<Subject> subjectSet = AuthUtils.authorizedClientSubjects(ses);
	
		assertFalse("public subject in session, should not have authenticated",
				subjectSet.contains(authenticated));
	}
	
	
	@Test
	public void testAuthorizedClientSubjects_GroupTransitive() {
		
		Session ses = new Session();
		ses.setSubject(buildSubject("x"));

		SubjectInfo si = new SubjectInfo();
		Person x = buildTestPerson("x");
		x.addIsMemberOf(buildSubject("groupA"));
		Person y = buildTestPerson("y");
		x.addEquivalentIdentity(buildSubject("y"));
		y.addEquivalentIdentity(buildSubject("x"));		
		y.addIsMemberOf(buildSubject("groupB"));
		
		Person z = buildTestPerson("z");
		x.addEquivalentIdentity(buildSubject("z"));
		z.addEquivalentIdentity(buildSubject("x"));	
		z.addIsMemberOf(buildSubject("groupC"));

		si.addPerson(x);
		si.addPerson(y); 
		si.addPerson(z);
		
		si.addGroup(buildTestGroup("groupA"));
		si.addGroup(buildTestGroup("groupB"));
		si.addGroup(buildTestGroup("groupC"));
		ses.setSubjectInfo(si);
		
		Set<Subject> subjectSet = AuthUtils.authorizedClientSubjects(ses);
	
		assertTrue("subject list should contain groupA", subjectSet.contains(buildSubject("groupA")));
		assertTrue("subject list should contain groupB", subjectSet.contains(buildSubject("groupB")));
		assertTrue("subject list should contain groupC", subjectSet.contains(buildSubject("groupC")));
	}
	
	
	
	
	
	@Test
	public void testAuthorizedClientSubjects_GroupTransitive_DaisyChainEquivID() {
		
		Session ses = new Session();
		ses.setSubject(buildSubject("x"));

		SubjectInfo si = new SubjectInfo();
		Person x = buildTestPerson("x");
		Group a = buildTestGroup("groupA");
		a.addHasMember(x.getSubject());
		x.addIsMemberOf(a.getSubject());
		
		Person y = buildTestPerson("y");
		Group b = buildTestGroup("groupB");
		b.addHasMember(y.getSubject());
		y.addIsMemberOf(b.getSubject());
		
		
		x.addEquivalentIdentity(buildSubject("y"));
		y.addEquivalentIdentity(buildSubject("x"));		
		
		
		Person z = buildTestPerson("z");
		Group c = buildTestGroup("groupC");
		c.addHasMember(z.getSubject());
		z.addIsMemberOf(c.getSubject());
		
		
		y.addEquivalentIdentity(buildSubject("z"));
		z.addEquivalentIdentity(buildSubject("y"));	
		

		si.addPerson(x);
		si.addPerson(y); 
		si.addPerson(z);	
		
		si.addGroup(a);
		si.addGroup(b);
		si.addGroup(c);
		
		ses.setSubjectInfo(si);
		
		Set<Subject> subjectSet = AuthUtils.authorizedClientSubjects(ses);
	
		assertTrue("subject list should contain groupA", subjectSet.contains(buildSubject("groupA")));
		assertTrue("subject list should contain groupB", subjectSet.contains(buildSubject("groupB")));
		assertTrue("subject list should contain groupC", subjectSet.contains(buildSubject("groupC")));
	}
	
	
	/**
	 * setting up a circular equivalent identity graph, to make sure we don't get stuck
	 */
	@Test
	public void testAuthorizedClientSubjects_RecursionInfiniteLoopTest() {
		
		Session ses = new Session();
		ses.setSubject(buildSubject("x"));

		SubjectInfo si = new SubjectInfo();
		Person x = buildTestPerson("x");
		Group a = buildTestGroup("groupA");
		a.addHasMember(x.getSubject());
		x.addIsMemberOf(a.getSubject());
		
		Person y = buildTestPerson("y");
		Group b = buildTestGroup("groupB");
		b.addHasMember(y.getSubject());
		y.addIsMemberOf(b.getSubject());
		
		
		x.addEquivalentIdentity(buildSubject("y"));
		y.addEquivalentIdentity(buildSubject("x"));		
		
		
		Person z = buildTestPerson("z");
		Group c = buildTestGroup("groupC");
		c.addHasMember(z.getSubject());
		z.addIsMemberOf(c.getSubject());
		
		
		y.addEquivalentIdentity(buildSubject("z"));
		z.addEquivalentIdentity(buildSubject("y"));	
		
		
		Person w = buildTestPerson("w");
		Group d = buildTestGroup("groupD");
		d.addHasMember(w.getSubject());
		w.addIsMemberOf(d.getSubject());
		
		
		z.addEquivalentIdentity(buildSubject("w"));
		w.addEquivalentIdentity(buildSubject("z"));	
		
		x.addEquivalentIdentity(buildSubject("w"));
		w.addEquivalentIdentity(buildSubject("x"));	

		si.addPerson(w);
		si.addPerson(x);
		si.addPerson(y); 
		si.addPerson(z);	
		
		si.addGroup(a);
		si.addGroup(b);
		si.addGroup(c);
		si.addGroup(d);
		
		ses.setSubjectInfo(si);
		
		Set<Subject> subjectSet = AuthUtils.authorizedClientSubjects(ses);
	
		assertTrue("subject list should contain groupA", subjectSet.contains(buildSubject("groupA")));
		assertTrue("subject list should contain groupB", subjectSet.contains(buildSubject("groupB")));
		assertTrue("subject list should contain groupC", subjectSet.contains(buildSubject("groupC")));
		assertTrue("subject list should contain groupC", subjectSet.contains(buildSubject("groupD")));
	}
	
	@Test
	public void testAuthorizedClientSubjects_RecursionInfiniteLoopTest_Live() 
	        throws InstantiationException, IllegalAccessException, IOException, MarshallingException {

	    Session ses = new Session();
	    InputStream is = this.getClass().getResourceAsStream("/org/dataone/service/samples/v1/loopedSubjectInfo.xml");
	    SubjectInfo si = TypeMarshaller.unmarshalTypeFromStream(SubjectInfo.class, is);

	    ses.setSubjectInfo(si);
	    ses.setSubject(si.getPerson(0).getSubject());
	    Set<Subject> subjectSet = AuthUtils.authorizedClientSubjects(ses);

	    for (Subject s : subjectSet) {
	        System.out.println(s.getValue());
	    }
	    assertTrue("subject list should contain New Group", subjectSet.contains(TypeFactory.buildSubject("CN=New Group,DC=dataone,DC=org")));
//	    assertTrue("subject list should contain groupB", subjectSet.contains(buildSubject("groupB")));
//	    assertTrue("subject list should contain groupC", subjectSet.contains(buildSubject("groupC")));
//	    assertTrue("subject list should contain groupC", subjectSet.contains(buildSubject("groupD")));
	}

	
	@Test
	public void testIsAuthorized_AccessPolicy() 
	{
		SystemMetadata sysmeta = new SystemMetadata();
		
		sysmeta.setAccessPolicy(
				
				AccessUtil.createSingleRuleAccessPolicy(
				new String[]{buildSubject("x").getValue(), buildSubject("y").getValue()},
				new Permission[]{ Permission.WRITE}));
		
		sysmeta.setRightsHolder(buildSubject("qq"));
		
		Set<Subject> subjectSet = new TreeSet<Subject>();
		subjectSet.add(buildSubject("z"));
		subjectSet.add(buildSubject("y"));
		subjectSet.add(buildSubject("x"));
		
		assertTrue("x should be able to read the object", AuthUtils.isAuthorized(subjectSet, Permission.READ, sysmeta));		
		assertTrue("x should be able to write the object", AuthUtils.isAuthorized(subjectSet, Permission.WRITE, sysmeta));		
		assertFalse("x should NOT be able to change the object", AuthUtils.isAuthorized(subjectSet, Permission.CHANGE_PERMISSION, sysmeta));	
		
		assertFalse("testRightsHolder should be able to change the object", AuthUtils.isAuthorized(subjectSet, Permission.CHANGE_PERMISSION, sysmeta));
	}
	
	
	@Test
	public void testIsAuthorized_RightsHolder() 
	{
		SystemMetadata sysmeta = new SystemMetadata();
		
		sysmeta.setAccessPolicy(
				
				AccessUtil.createSingleRuleAccessPolicy(
				new String[]{buildSubject("x").getValue(), buildSubject("y").getValue()},
				new Permission[]{ Permission.WRITE}));
		
		sysmeta.setRightsHolder(buildSubject("testRightsHolder"));
		
		Set<Subject> subjectSet = new TreeSet<Subject>();
		subjectSet.add(buildSubject("testRightsHolder"));
	
		assertTrue("testRightsHolder should be able to change the object", AuthUtils.isAuthorized(subjectSet, Permission.CHANGE_PERMISSION, sysmeta));
	}
	
	@Test
    public void testFindEquivalentSubjects() throws Exception {
        SubjectInfo subjectInfoPerson = TypeMarshaller.unmarshalTypeFromStream(SubjectInfo.class, getClass().getClassLoader().getResourceAsStream("test-files/subject-info-person.xml"));
        Set<Subject> returnSubjects = new HashSet<Subject>();
        Subject targetPerson = new Subject();
        targetPerson.setValue("CN=Matt Jones A729,O=Google,C=US,DC=cilogon,DC=org");
        returnSubjects= AuthUtils.findEquivalentSubjects(subjectInfoPerson, targetPerson);
        /*for (Subject subject : returnSubjects) {
            System.out.println("The subject is "+subject.getValue());
        }*/
        assertTrue("testFindPersonSubjects - the size of returned subjects should be 12 rather than "+returnSubjects.size(), returnSubjects.size()==12);
        
        
        SubjectInfo subjectInfoPerson_2 = TypeMarshaller.unmarshalTypeFromStream(SubjectInfo.class, getClass().getClassLoader().getResourceAsStream("test-files/subject-info-person-without-group.xml"));
        Set<Subject> returnSubjects_2 = new HashSet<Subject>();
        Subject targetPerson_2 = new Subject();
        targetPerson_2.setValue("CN=test-person-4,DC=dataone,DC=org");
        returnSubjects_2 = AuthUtils.findEquivalentSubjects(subjectInfoPerson_2, targetPerson_2);
        assertTrue("testFindPersonSubjects - the size of returned subjects should be 1 rather than "+returnSubjects_2.size(), returnSubjects_2.size()==1);
        assertTrue("The subject should be CN=test-person-4,DC=dataone,DC=org", returnSubjects_2.contains(targetPerson_2));
        
        //pass a group subject to this method. TODO now it only returns the group subject back. We need to think more about the groups.
        SubjectInfo subjectInfoGroup = TypeMarshaller.unmarshalTypeFromStream(SubjectInfo.class, getClass().getClassLoader().getResourceAsStream("test-files/subject-info-group.xml"));
        Set<Subject>returnSubjects2 = new HashSet<Subject>();
        Subject targetGroup = new Subject();
        targetGroup.setValue("CN=dataone-coredev,DC=dataone,DC=org");
        returnSubjects2 = AuthUtils.findEquivalentSubjects(subjectInfoGroup, targetGroup);
        /*for (Subject subject : returnSubjects2) {
            System.out.println("the subject equals?"+subject.equals(targetGroup));
            System.out.println("The subject is "+subject.getValue());
        }*/
        assertTrue("testFindPersonSubjects - the size of returned subjects should be 1 rather than "+returnSubjects2.size(), returnSubjects2.size()==1);
        
        
        SubjectInfo subjectInfoGroup3 = TypeMarshaller.unmarshalTypeFromStream(SubjectInfo.class, getClass().getClassLoader().getResourceAsStream("test-files/subject-info-circular-group-1.xml"));
        Set<Subject>returnSubjects3 = new HashSet<Subject>();
        Subject targetGroup3 = new Subject();
        targetGroup3.setValue("CN=test-group-2,DC=dataone,DC=org");
        returnSubjects3 = AuthUtils.findEquivalentSubjects(subjectInfoGroup3, targetGroup3);
        /*for (Subject subject : returnSubjects3) {
        System.out.println("the subject equals?"+subject.equals(targetGroup));
        System.out.println("The subject is "+subject.getValue());
        }*/
        assertTrue("testFindPersonSubjects - the size of returned subjects should be 2 rather than "+returnSubjects3.size(), returnSubjects3.size()==2);
        
        SubjectInfo subjectInfoGroup4 = TypeMarshaller.unmarshalTypeFromStream(SubjectInfo.class, getClass().getClassLoader().getResourceAsStream("test-files/subject-info-circular-group-2.xml"));
        Set<Subject>returnSubjects4 = new HashSet<Subject>();
        Subject targetGroup4 = new Subject();
        targetGroup4.setValue("CN=test-group-3,DC=dataone,DC=org");
        returnSubjects4 = AuthUtils.findEquivalentSubjects(subjectInfoGroup4, targetGroup4);
        assertTrue("testFindPersonSubjects - the size of returned subjects should be 1 rather than "+returnSubjects4.size(), returnSubjects4.size()==1);
        
        SubjectInfo subjectInfoGroup5 = TypeMarshaller.unmarshalTypeFromStream(SubjectInfo.class, getClass().getClassLoader().getResourceAsStream("test-files/subject-info-circular-group-3.xml"));
        Set<Subject>returnSubjects5 = new HashSet<Subject>();
        Subject targetGroup5 = new Subject();
        targetGroup5.setValue("CN=test-group-3,DC=dataone,DC=org");
        returnSubjects5 = AuthUtils.findEquivalentSubjects(subjectInfoGroup5, targetGroup5);
        assertTrue("testFindPersonSubjects - the size of returned subjects should be 1 rather than "+returnSubjects5.size(), returnSubjects5.size()==3);
        
        SubjectInfo subjectInfoGroup6 = TypeMarshaller.unmarshalTypeFromStream(SubjectInfo.class, getClass().getClassLoader().getResourceAsStream("test-files/subject-info-group-nested-2.xml"));
        Set<Subject>returnSubjects6 = new HashSet<Subject>();
        Subject targetGroup6 = new Subject();
        targetGroup6.setValue("CN=test-group-1,DC=dataone,DC=org");
        returnSubjects6 = AuthUtils.findEquivalentSubjects(subjectInfoGroup6, targetGroup6);
        assertTrue("testFindPersonSubjects - the size of returned subjects should be 1 rather than "+returnSubjects6.size(), returnSubjects6.size()==1);
        
        
        SubjectInfo subjectInfoGroup7 = TypeMarshaller.unmarshalTypeFromStream(SubjectInfo.class, getClass().getClassLoader().getResourceAsStream("test-files/subject-info-group-nested-2.xml"));
        Set<Subject>returnSubjects7 = new HashSet<Subject>();
        Subject targetGroup7 = new Subject();
        targetGroup7.setValue("CN=test-group-2,DC=dataone,DC=org");
        returnSubjects7 = AuthUtils.findEquivalentSubjects(subjectInfoGroup7, targetGroup7);
        assertTrue("testFindPersonSubjects - the size of returned subjects should be 2 rather than "+returnSubjects7.size(), returnSubjects7.size()==2);
        
        SubjectInfo subjectInfoGroup8 = TypeMarshaller.unmarshalTypeFromStream(SubjectInfo.class, getClass().getClassLoader().getResourceAsStream("test-files/subject-info-group-nested-2.xml"));
        Set<Subject>returnSubjects8 = new HashSet<Subject>();
        Subject targetGroup8 = new Subject();
        targetGroup8.setValue("CN=test-group-3,DC=dataone,DC=org");
        returnSubjects8 = AuthUtils.findEquivalentSubjects(subjectInfoGroup8, targetGroup8);
        assertTrue("testFindPersonSubjects - the size of returned subjects should be 3 rather than "+returnSubjects8.size(), returnSubjects8.size()==3);
        
    }
	
	@Test
    public void testFindPersonsSubjects() throws Exception {
        SubjectInfo subjectInfoPerson = TypeMarshaller.unmarshalTypeFromStream(SubjectInfo.class, getClass().getClassLoader().getResourceAsStream("test-files/subject-info-person.xml"));
        Set<Subject> returnSubjects = new HashSet<Subject>();
        Subject targetPerson = new Subject();
        targetPerson.setValue("CN=Matt Jones A729,O=Google,C=US,DC=cilogon,DC=org");
        AuthUtils.findPersonsSubjects(returnSubjects, subjectInfoPerson, targetPerson);
        /*for (Subject subject : returnSubjects) {
            System.out.println("The subject is "+subject.getValue());
        }*/
        assertTrue("testFindPersonSubjects - the size of returned subjects should be 12 rather than "+returnSubjects.size(), returnSubjects.size()==12);
        
        
        SubjectInfo subjectInfoPerson_2 = TypeMarshaller.unmarshalTypeFromStream(SubjectInfo.class, getClass().getClassLoader().getResourceAsStream("test-files/subject-info-person-without-group.xml"));
        Set<Subject> returnSubjects_2 = new HashSet<Subject>();
        Subject targetPerson_2 = new Subject();
        targetPerson_2.setValue("CN=test-person-4,DC=dataone,DC=org");
        AuthUtils.findPersonsSubjects(returnSubjects_2, subjectInfoPerson_2, targetPerson_2);
        assertTrue("testFindPersonSubjects - the size of returned subjects should be 1 rather than "+returnSubjects_2.size(), returnSubjects_2.size()==1);
        
        //pass a group subject to this method. TODO now it only returns the group subject back. We need to think more about the groups.
        SubjectInfo subjectInfoGroup = TypeMarshaller.unmarshalTypeFromStream(SubjectInfo.class, getClass().getClassLoader().getResourceAsStream("test-files/subject-info-group.xml"));
        Set<Subject>returnSubjects2 = new HashSet<Subject>();
        Subject targetGroup = new Subject();
        targetGroup.setValue("CN=dataone-coredev,DC=dataone,DC=org");
        AuthUtils.findPersonsSubjects(returnSubjects2, subjectInfoGroup, targetGroup);
        /*for (Subject subject : returnSubjects2) {
            System.out.println("the subject equals?"+subject.equals(targetGroup));
            System.out.println("The subject is "+subject.getValue());
        }*/
        assertTrue("testFindPersonSubjects - the size of returned subjects should be 1 rather than "+returnSubjects2.size(), returnSubjects2.size()==1);
        
        
        SubjectInfo subjectInfoGroup3 = TypeMarshaller.unmarshalTypeFromStream(SubjectInfo.class, getClass().getClassLoader().getResourceAsStream("test-files/subject-info-circular-group-1.xml"));
        Set<Subject>returnSubjects3 = new HashSet<Subject>();
        Subject targetGroup3 = new Subject();
        targetGroup3.setValue("CN=test-group-2,DC=dataone,DC=org");
        AuthUtils.findPersonsSubjects(returnSubjects3, subjectInfoGroup3, targetGroup3);
        assertTrue("testFindPersonSubjects - the size of returned subjects should be 1 rather than "+returnSubjects3.size(), returnSubjects3.size()==1);
        
        SubjectInfo subjectInfoGroup4 = TypeMarshaller.unmarshalTypeFromStream(SubjectInfo.class, getClass().getClassLoader().getResourceAsStream("test-files/subject-info-circular-group-2.xml"));
        Set<Subject>returnSubjects4 = new HashSet<Subject>();
        Subject targetGroup4 = new Subject();
        targetGroup4.setValue("CN=test-group-3,DC=dataone,DC=org");
        AuthUtils.findPersonsSubjects(returnSubjects4, subjectInfoGroup4, targetGroup4);
        assertTrue("testFindPersonSubjects - the size of returned subjects should be 1 rather than "+returnSubjects4.size(), returnSubjects4.size()==1);
        
        SubjectInfo subjectInfoGroup5 = TypeMarshaller.unmarshalTypeFromStream(SubjectInfo.class, getClass().getClassLoader().getResourceAsStream("test-files/subject-info-circular-group-3.xml"));
        Set<Subject>returnSubjects5 = new HashSet<Subject>();
        Subject targetGroup5 = new Subject();
        targetGroup5.setValue("CN=test-group-1,DC=dataone,DC=org");
        AuthUtils.findPersonsSubjects(returnSubjects5, subjectInfoGroup5, targetGroup5);
        assertTrue("testFindPersonSubjects - the size of returned subjects should be 1 rather than "+returnSubjects5.size(), returnSubjects5.size()==1);
        
        SubjectInfo subjectInfoGroup6 = TypeMarshaller.unmarshalTypeFromStream(SubjectInfo.class, getClass().getClassLoader().getResourceAsStream("test-files/subject-info-group-nested-2.xml"));
        Set<Subject>returnSubjects6 = new HashSet<Subject>();
        Subject targetGroup6 = new Subject();
        targetGroup6.setValue("CN=test-group-1,DC=dataone,DC=org");
        AuthUtils.findPersonsSubjects(returnSubjects6, subjectInfoGroup6, targetGroup6);
        assertTrue("testFindPersonSubjects - the size of returned subjects should be 1 rather than "+returnSubjects6.size(), returnSubjects6.size()==1);
        
    }
	
}