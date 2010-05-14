/**
 * Copyright 2010 Regents of the University of California and the
 *                National Center for Ecological Analysis and Synthesis
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package org.dataone.service.exceptions;

import java.util.TreeMap;

/**
 * The DataONE InvalidRequest exception, raised when a request 
 * is issued for which one or more parameters provided in the call were invalid.
 * 
 * @author Matthew Jones
 */
public class InvalidRequest extends BaseException {

    /** Fix the errorCode in this exception. */
    private static final int errorCode=400;
    
    public InvalidRequest(String detailCode, String description) {
        super(errorCode, detailCode, description);
    }

    public InvalidRequest(String detailCode, String description, 
            TreeMap<String, String> trace_information) {
        super(errorCode, detailCode, description);
    }
}
