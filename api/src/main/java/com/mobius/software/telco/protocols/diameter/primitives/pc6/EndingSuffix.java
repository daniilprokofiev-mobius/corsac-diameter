package com.mobius.software.telco.protocols.diameter.primitives.pc6;
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
	6.3.70	Ending-Suffix 
	The Ending-Suffix AVP is of type OctetString. This AVP shall contain the highest ProSe Restricted Code Suffix in a consecutive sequence of ProSe Restricted Code suffixes or the highest ProSe Application Code Suffix in a consecutive sequence of ProSe Application Code Suffixes. The format of ProSe Restricted Code Suffix or ProSe Application Code Suffix is defined in 3GPP TS 23.003 [4]. The size of this suffix shall align with octet boundary.
 */
@DiameterAvpDefinition(code = 3849L, vendorId = KnownVendorIDs.TGPP_ID, must = false, name = "Ending-Suffix")
public interface EndingSuffix extends DiameterOctetString
{
}