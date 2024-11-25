package com.mobius.software.telco.protocols.diameter.primitives.accounting;
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
import com.mobius.software.telco.protocols.diameter.primitives.DiameterEnumerated;

/**
*
* @author yulian oifa
*
*/
/*
 * 	7.2.111A MMTel-SService-Type AVP
	The MMTel-SService-Type AVP (AVP Code 2031) is of type Unsigned32 and identifies the type of MMTel
	supplementary service. The following values are defined:
	0 Originating Identification Presentation (OIP)
	1 Originating Identification Restriction (OIR)
	2 Terminating Identification Presentation (TIP)
	3 Terminating Identification Restriction (TIR)
	4 Communication HOLD (HOLD)
	5 Communications Barring (CB )
	6 Communication Diversion (CDIV)
	8 Communication Waiting (CW)
	9 Message Waiting Indication (MWI)
	10 Conference (CONF)
	11 Flexible Alerting (FA)
	12 Completion of Communication to Busy Subscriber (CCBS)
	13 Completion of Communications on No Reply (CCNR)
	14 Malicious Communication Identification (MCID)
	15 Customized Alerting Tone (CAT)
	16 Closed User Group (CUG)
	17 Personal Network management (PNM)
	18 Customized Ringing Signal (CRS)
	19 Advice of Charge (AoC)
	20 Explicit Communication Transfer (ECT) 
 */
@DiameterAvpDefinition(code = TgppAvpCodes.MMTEL_SERVICE_TYPE, vendorId = VendorIDs.TGPP_ID, name = "MMTel-SService-Type")
public interface MMTelSServiceType extends DiameterEnumerated<MMTelSServiceTypeEnum>
{
}