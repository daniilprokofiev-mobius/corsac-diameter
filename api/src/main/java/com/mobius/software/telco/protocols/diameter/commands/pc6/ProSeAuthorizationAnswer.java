package com.mobius.software.telco.protocols.diameter.commands.pc6;
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

import com.mobius.software.telco.protocols.diameter.annotations.DiameterCommandDefinition;

/**
*
* @author yulian oifa
*
*/

/*
 * 	6.2.4	ProSe-Authorization-Answer (PAA) Command
	The ProSe-Authorization-Answer (PAA) Command, indicated by the Command-Code field set to 8388668 and the "R" bit cleared in the Command Flags field, is sent from the ProSe Function in Local PLMN/VPLMN to the ProSe Function in the HPLMN. 
	Message Format

	< ProSe-Authorization-Answer> ::=	< Diameter Header: 8388668, PXY, 16777340 >
			 < Session-Id >
			 [ DRMP ] 
			 [ Vendor-Specific-Application-Id ]
			 [ Result-Code ]
			 [ Experimental-Result ] 
			 { Auth-Session-State }
			 { Origin-Host }
			 { Origin-Realm }
			*[ Supported-Features ]
			 [ OC-Supported-Features ]
			 [ OC-OLR ]
			*[ Load ]
			 [ ProSe-Direct-Allowed]
			 [ Validity-Time-Announce ]
			 [ Validity-Time-Monitor ]
			 [ Validity-Time-Communication ]
			 [ Authorized-Discovery-Range ]
			*[ AVP ]
			 [ Failed-AVP ]
			*[ Proxy-Info ]
			*[ Route-Record ]
 */
@DiameterCommandDefinition(applicationId = 16777340, commandCode = 8388668, request = false, proxyable = true, name="ProSe-Authorization-Answer")
public interface ProSeAuthorizationAnswer extends Pc6Answer
{
	Long getProSeDirectAllowed();
	 
	void setProSeDirectAllowed(Long value);
	 		
	Long getValidityTimeAnnounce();
	 
	void setValidityTimeAnnounce(Long value);
	
	Long getValidityTimeMonitor();
	 
	void setValidityTimeMonitor(Long value);
	 		
	Long getValidityTimeCommunication();
	 
	void setValidityTimeCommunication(Long value);
	
	Long getAuthorizedDiscoveryRange();
	 
	void setAuthorizedDiscoveryRange(Long value);
}