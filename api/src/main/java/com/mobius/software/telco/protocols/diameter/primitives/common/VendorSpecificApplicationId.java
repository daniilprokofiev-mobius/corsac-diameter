package com.mobius.software.telco.protocols.diameter.primitives.common;
/*
 * Mobius Software LTD
 * Copyright 2023, Mobius Software LTD and individual contributors
 * by the @authors tag.
 *
 * This program is free software: you can redistribute it and/or modify
 * under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

import com.mobius.software.telco.protocols.diameter.AvpCodes;
import com.mobius.software.telco.protocols.diameter.annotations.DiameterAvpDefinition;
import com.mobius.software.telco.protocols.diameter.exceptions.MissingAvpException;
import com.mobius.software.telco.protocols.diameter.primitives.DiameterAvp;

/**
*
* @author yulian oifa
*
*/

/*
 * 6.11.  Vendor-Specific-Application-Id AVP
   
   The Vendor-Specific-Application-Id AVP (AVP Code 260) is of type
   Grouped and is used to advertise support of a vendor-specific
   Diameter application.  Exactly one instance of either Auth-
   Application-Id or Acct-Application-Id AVP MUST be present.  The
   Application Id carried by either Auth-Application-Id or Acct-
   Application-Id AVP MUST comply with vendor-specific Application Id
   assignment described in Section 11.3.  It MUST also match the
   Application Id present in the Diameter header except when used in a
   CER or CEA message.
   
   The Vendor-Specific-Application-Id AVP SHOULD be placed as close to
   the Diameter header as possible.

      AVP Format

      <Vendor-Specific-Application-Id> ::= < AVP Header: 260 >
                                           { Vendor-Id }
                                           [ Auth-Application-Id ]
                                           [ Acct-Application-Id ]
   
   A Vendor-Specific-Application-Id AVP MUST contain exactly one of
   either Auth-Application-Id or Acct-Application-Id.  If a Vendor-
   Specific-Application-Id is received without one of these two AVPs,
   then the recipient SHOULD issue an answer with a Result-Code set to
   DIAMETER_MISSING_AVP.  The answer SHOULD also include a Failed-AVP,
   which MUST contain an example of an Auth-Application-Id AVP and an
   Acct-Application-Id AVP.
 */
@DiameterAvpDefinition(code = AvpCodes.VENDOR_SPECIFIC_APPLICATION_ID, vendorId = -1L, name = "Vendor-Specific-Application-Id")
public interface VendorSpecificApplicationId extends DiameterAvp 
{
	Long getVendorId();
	
	void setVendorId(Long value);
	
	Long getAuthApplicationId();
	
	void setAuthApplicationId(Long value) throws MissingAvpException;
	
	Long getAcctApplicationId();
	
	void setAcctApplicationId(Long value) throws MissingAvpException;
}