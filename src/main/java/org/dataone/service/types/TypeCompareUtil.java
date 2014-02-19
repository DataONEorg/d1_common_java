/**
 * This work was created by participants in the DataONE project, and is
 * jointly copyrighted by participating institutions in DataONE. For 
 * more information on DataONE, see our web site at http://dataone.org.
 *
 *   Copyright ${year}
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 * 
 * $Id$
 */

package org.dataone.service.types;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.dataone.service.types.v1.AccessRule;
import org.dataone.service.types.v1.Replica;
import org.dataone.service.types.v1.Subject;
import org.dataone.service.types.v1.SystemMetadata;
import org.dataone.service.util.DateTimeMarshaller;

/**
 * This utility class provides an efficient way to test bean-style data objects
 * (of the same type) for differences in property values.  It uses reflection,
 * so there's a categorical performance hit compared to non-general-case solutions
 * you might write yourself.
 * 
 * The reason for use of reflection is to decouple it from datatype definitions
 * that may change over the long term.
 * 
 * @author rnahf
 *
 */
public class TypeCompareUtil {

    static Logger logger = Logger.getLogger(TypeCompareUtil.class.getName());

    // TODO: compare with https://github.com/SQiShER/java-object-diff/ wrt/ long-term implementation

    /**
     * Reports the properties of a systemMetadata object, one per line in xpath 
     * fashion.  the values of each property follow in subsequent columns per line.
     * 
     * @param sysmeta1
     * @param sysmeta2
     * @return - the report as a List<String> of differences
     */
    public static List<String> compareSystemMetadata(SystemMetadata sysmeta1,
            SystemMetadata sysmeta2) {

        LinkedHashMap<String, Object> labeledSysmeta = new LinkedHashMap<String, Object>();

        labeledSysmeta.put("sysmeta 1", sysmeta1);
        labeledSysmeta.put("sysmeta 2", sysmeta2);

        return compareD1ServiceType(labeledSysmeta, true);
    }

    /**
     * This method will compare any number of dataone service-type objects of 
     * the same type to each other and return a report of every field where 
     * there is a difference. Multi-valued fields are sorted by default to minimize 
     * reporting of meaningless differences, but with the possible side-effect 
     * of shifting elements out of alignment and reporting more rows than 
     * differences when there are differences.
     * 
     * @param d1ServiceTypeMap - a map of dataone service-type objects
     * @param sortArrays - Boolean: if set to false, will not sort the arrays prior
     *        to comparison.
     * @return - a table of fields where there are differences
     */
    public static List<String> compareD1ServiceType(Map<String, Object> d1ServiceTypeMap,
            Boolean sortArrays) {

        if (sortArrays == null) {
            sortArrays = true;
        }

        List<String> report = new LinkedList<String>();
        Map<String, Object> d1TypePropMap = new HashMap<String, Object>();
        Set<String> propKeys = new TreeSet<String>();

        for (String label : d1ServiceTypeMap.keySet()) {
            Map<String, String> smdProps = TypeCompareUtil.getD1SubtypesListing("",
                    d1ServiceTypeMap.get(label), sortArrays);
            propKeys.addAll(smdProps.keySet());
            d1TypePropMap.put(label, smdProps);
        }

        Set<String> nodeKeys = d1TypePropMap.keySet();
        StringBuffer sb = new StringBuffer();
        sb.append("property field");
        for (String key : nodeKeys) {
            sb.append("\t" + key);
        }
        report.add(sb.toString());

        for (String prop : propKeys) {
            sb = new StringBuffer();
            sb.append(prop);
            boolean difference = false;
            List values = new LinkedList<String>();
            for (String nodeKey : nodeKeys) {
                sb.append("\t");
                // map value can legitimately be either a LinkedHashMap or null
                String value = null;
                if (d1TypePropMap.get(nodeKey) instanceof LinkedHashMap) {
                    value = ((LinkedHashMap<String, String>) d1TypePropMap.get(nodeKey)).get(prop);
                } else {
                    value = (String) d1TypePropMap.get(nodeKey);
                }
                sb.append(value);

                // collect unique values
                if (!values.contains(value)) {
                    values.add(value);
                }
            }
            if (values.size() > 1) {
                // there's a difference, so report the line
                report.add(sb.toString());
            }
        }
        if (report.size() == 1) {
            report.clear();
            report.add("OK");
        }
        return report;
    }

    /**
     * A recursive method that returns a map of key value pairs that correspond 
     * to the property paths (keys) and values of the object passed in.
     * It depends on bean-style getter methods to find the properties of the 
     * object.
     * It will sort subtypes that are arrays when order doesn't matter.  For a strict
     * non-sorting listing, use getD1SubtypesListing(String path, Object o, boolean sortArrays)
     * and set sortArrays to false.
     * @param o - the dataone object
     * @return using LinkedHashMap to ensure order read equals order listed
     */
    public static LinkedHashMap<String, String> getD1SubtypesListing(Object o) {
        return getD1SubtypesListing(o.getClass().getSimpleName(), o, true);

    }

    /**
     * A recursive method that returns a map of key value pairs that correspond 
     * to the property paths (keys) and values of the object passed in.
     * It depends on bean-style getter methods to find the properties of the 
     * object.
     * Both empty lists and null values yield the value 'null' 
     * @param path
     * @param o - the dataone object
     * @param sortArrays - true will sort array subtypes so they can be logically compared
     * @return using LinkedHashMap to ensure order read equals order listed
     */
    public static LinkedHashMap<String, String> getD1SubtypesListing(String path, Object o,
            boolean sortArrays) {

        LinkedHashMap<String, String> results = new LinkedHashMap<String, String>();
        if (o == null) {
            results.put(path, "null");
        } else {

            Class cl = o.getClass();
            logger.debug("listing for: " + path + ": " + cl.getName());
            // only dataone service types are dealt with here, because we know 
            // they follow the bean convention of set/get methods to access properties
            if (cl.getPackage().getName().startsWith("org.dataone.service.types")) {
                Method[] methods = o.getClass().getMethods();
                for (Method m : methods) {
                    // if a get method that isn't "getClass", and doesn't take
                    // a parameter (avoids list-associated methods) OR is an
                    // enumeration (that uses xmlValue to get the value...
                    if ((m.getName().startsWith("get") && !m.getName().equals("getClass") && m
                            .getParameterTypes().length == 0) || m.getName().equals("xmlValue")) {
                        try {
                            Object p = m.invoke(o, (Object[]) null);
                            Map<String, String> subresults = getD1SubtypesListing(path + "/"
                                    + m.getName().replace("get", ""), p, sortArrays);
                            results.putAll(subresults);
                        } catch (IllegalArgumentException e) {
                            logger.error(m.getName(), e);
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            logger.error(m.getName(), e);
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            logger.error(m.getName(), e);
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }
            // otherwise, we are at a more basic datatype (String, etc)
            else {
                if (o instanceof String) {
                    results.put(path, (String) o);
                } else if (o instanceof Integer) {
                    results.put(path, ((Integer) o).toString());
                } else if (o instanceof Date) {
                    results.put(path, DateTimeMarshaller.serializeDateToUTC((Date) o));
                } else if (o instanceof BigInteger) {
                    results.put(path, ((BigInteger) o).toString());
                } else if (o instanceof Boolean) {
                    results.put(path, ((Boolean) o).toString());
                } else if (o instanceof List) {
                    List<?> list = ((List<?>) o);
                    if (!list.isEmpty()) {
                        if (sortArrays) {
                            logger.debug("arrayList size: " + list.size());
                            // many of the dataone simpletypes are sortable (implement Comparable)
                            if (list.get(0) instanceof Comparable) {
                                // permissions, Subjects, and Identifiers are Comparable, so sort and go
                                Collections.sort((List<Comparable>) list);
                            }
                            // ADD COMPARATORS HERE for subtype properties that are
                            // lists of non-Comparable dataone service-types
                            // if those need to be sorted (where list order doesn't 
                            // affect behavior)

	
							// While Replicas are not Comparable, the replica#replicaMemberNode
							// property is, and should be used to sort the replicas
							else if (list.get(0) instanceof Replica) {
								Collections.sort((List<Replica>)list, 
								    new Comparator<Replica>() {
									    public int compare(Replica r1, Replica r2) {
									    	
									    	// only using replicaMemberNodefor sorting
									    	// NodeReferences are Comparable
									    	return r1.getReplicaMemberNode().compareTo(r2.getReplicaMemberNode());
									    }
								    }
								);
							}
							
                            
                            
                            // The order of AccessRules in an AccessPolicy doesn't
                            // affect behavior, so should be made Comparable so 
                            // equivalence can be tested
                            else if (list.get(0) instanceof AccessRule) {
                                Collections.sort((List<AccessRule>) list,
                                        new Comparator<AccessRule>() {
                                            public int compare(AccessRule ar1, AccessRule ar2) {

                                                // only using subjectlist for sorting
                                                // because it's highly unlikely that 
                                                // that the same subject list will be
                                                // listed twice under separate AccessRules
                                                StringBuffer ar1SB = new StringBuffer();
                                                Collections.sort(ar1.getSubjectList());
                                                for (Subject s : ar1.getSubjectList()) {
                                                    ar1SB.append(s.getValue());
                                                }
                                                String ar1Subjects = ar1SB.toString();

                                                StringBuffer ar2SB = new StringBuffer();
                                                Collections.sort(ar2.getSubjectList());
                                                for (Subject s : ar2.getSubjectList()) {
                                                    ar2SB.append(s.getValue());
                                                }
                                                String ar2Subjects = ar2SB.toString();

                                                return ar1Subjects.compareTo(ar2Subjects);
                                            }
                                        });
                            } else {
                                ; // don't sort...
                            }
                        }
                        for (int j = 0; j < list.size(); j++) {
                            results.putAll(getD1SubtypesListing((path + "/" + j), list.get(j),
                                    sortArrays));
                        }
                    } else {
                        results.put(path, "{empty}");
                    }
                }
            }
        }
        logger.debug("(return)");
        return results;
    }

    /**
     * Generates a tabular listing of the field names and values, suitable for
     * display.  
     * @param o
     * @return
     */
    public static List<String> reportSubtypesListing(Object o, Boolean sort) {
        if (sort == null) {
            sort = Boolean.TRUE;
        }
        Map<String, String> map = getD1SubtypesListing("", o, sort);
        List<String> report = new ArrayList<String>();
        report.add("field_name\tfield_value");
        for (String key : map.keySet()) {
            report.add(String.format("%s\t%s", key, map.get(key)));
        }
        return report;
    }
}
