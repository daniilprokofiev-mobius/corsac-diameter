package com.mobius.software.telco.protocols.diameter.primitives.creditcontrol;
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

/**
*
* @author yulian oifa
*
*/

/*
 * 8.19.  Used-Service-Unit AVP

   The Used-Service-Unit AVP is of type Grouped (AVP Code 446) and
   contains the amount of used units measured from the point when the
   service became active or, if interim interrogations are used during
   the session, from the point when the previous measurement ended.
   Note: The value reported in a Used-Service-Unit AVP is not
   necessarily related to the grant provided in a Granted-Service-Unit
   AVP, e.g., the value in this AVP may exceed the value in the grant.

   The Used-Service-Unit AVP is defined as follows (per grouped-avp-def
   as defined in [RFC6733]):

         Used-Service-Unit ::= < AVP Header: 446 >
                               [ Tariff-Change-Usage ]
                               [ CC-Time ]
                               [ CC-Money ]
                               [ CC-Total-Octets ]
                               [ CC-Input-Octets ]
                               [ CC-Output-Octets ]
                               [ CC-Service-Specific-Units ]
                              *[ AVP ]
 */
@DiameterAvpDefinition(code = AvpCodes.USED_SERVICE_UNIT, vendorId = -1, name = "Used-Service-Unit")
public interface UsedServiceUnit extends RequestedServiceUnit 
{
	TariffChangeUsageEnum getTariffChangeUsage();
	
	void setTariffChangeUsage(TariffChangeUsageEnum value);			
}