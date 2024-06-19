package com.mobius.software.telco.protocols.diameter.commands.s6b;
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

import com.mobius.software.telco.protocols.diameter.ApplicationIDs;
import com.mobius.software.telco.protocols.diameter.CommandCodes;
import com.mobius.software.telco.protocols.diameter.annotations.DiameterCommandDefinition;

/**
*
* @author yulian oifa
*
*/

/*
 * 	9.2.2.4.2	Abort-Session-Answer (ASA) Command
	The Abort-Session-Answer (ASA) command, indicated by the Command-Code field set to 274 and the "R" bit cleared in the Command Flags field, is sent from a PDN GW to a 3GPP AAA Server/Proxy. The ABNF is based on the one in IETF RFC 4005 [4].
	
	< Abort-Session-Answer > ::=	< Diameter Header: 274, PXY, 16777272 >
				 < Session-Id >
				 [ DRMP ]
				 { Result-Code }
				 { Origin-Host }
				 { Origin-Realm }
				…
				*[ AVP ]
 */
@DiameterCommandDefinition(applicationId = ApplicationIDs.S6B, commandCode = CommandCodes.ABORT_SESSION, request = false, proxyable = true, name="Abort-Session-Answer")
public interface AbortSessionAnswer extends S6bAnswer,com.mobius.software.telco.protocols.diameter.commands.commons.AbortSessionAnswer
{	
}