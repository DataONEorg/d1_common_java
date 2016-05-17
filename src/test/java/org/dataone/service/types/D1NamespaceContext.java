/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.dataone.service.types;

import java.util.Iterator;
import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import org.apache.log4j.Logger;

/**
 *
 * @author waltz
 */
public class D1NamespaceContext implements NamespaceContext {
private static Logger log = Logger.getLogger(D1NamespaceContext.class);
        @Override
        public String getNamespaceURI(String prefix) {
            String uri = null;
            switch (prefix) {
                case "d1" : 
                    uri = "http://ns.dataone.org/service/types/v1";
                    break;
                case "d1_v1.1" :
                    uri = "http://ns.dataone.org/service/types/v1.1";
                    break;
                case "d1_v2.0" :
                    uri = "http://ns.dataone.org/service/types/v2.0";
                    break;
                case "xml":
                    uri = XMLConstants.XML_NS_URI;
                    break;
                default:
                    uri = XMLConstants.NULL_NS_URI;
            }
            return uri;
        }

        @Override
        public String getPrefix(String namespaceURI) {
            return null;
        }

        @Override
        public Iterator getPrefixes(String namespaceURI) {
            return null;
        }
}
