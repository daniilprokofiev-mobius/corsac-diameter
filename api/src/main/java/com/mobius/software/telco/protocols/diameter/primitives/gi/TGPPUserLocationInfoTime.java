package com.mobius.software.telco.protocols.diameter.primitives.gi;
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

import com.mobius.software.telco.protocols.diameter.annotations.DiameterAvpDefinition;
import com.mobius.software.telco.protocols.diameter.primitives.DiameterOctetString;
import com.mobius.software.telco.protocols.diameter.primitives.KnownVendorIDs;

/**
*
* @author yulian oifa
*
*/

/*
	30 – 3GPP-User-Location-Info-Time
	3GPP Type: 30
	Length=6
	User Location Info time field is Unsigned32 type, it indicates the NTP time at which the UE was last known to be in the location which is reported during bearer deactivation or UE detach procedure.
*/
@DiameterAvpDefinition(code = 30L, vendorId = KnownVendorIDs.TGPP_ID, must = false, name = "3GPP-User-Location-Info-Time")
public interface TGPPUserLocationInfoTime extends DiameterOctetString
{
}