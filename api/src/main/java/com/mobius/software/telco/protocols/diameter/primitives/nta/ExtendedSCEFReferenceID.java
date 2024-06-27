package com.mobius.software.telco.protocols.diameter.primitives.nta;

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

import com.mobius.software.telco.protocols.diameter.TgppAvpCodes;
import com.mobius.software.telco.protocols.diameter.VendorIDs;
import com.mobius.software.telco.protocols.diameter.annotations.DiameterAvpDefinition;
import com.mobius.software.telco.protocols.diameter.primitives.DiameterUnsigned64;

/* 
 * A.6.3.8	Extended-SCEF-Reference-ID
 * The Extended-SCEF-Reference-ID AVP (AVP code 4216) is of type Unsigned64 
 * and it shall contain the identifier provided by the SCEF.
 */

@DiameterAvpDefinition(code = TgppAvpCodes.EXTENDED_SCEF_REFERENCE_ID, vendorId = VendorIDs.TGPP_ID, name = "Extended-SCEF-Reference-ID")
public interface ExtendedSCEFReferenceID extends DiameterUnsigned64
{

}