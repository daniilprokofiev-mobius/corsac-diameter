package com.mobius.software.telco.protocols.diameter.impl.primitives.accounting;
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

import com.mobius.software.telco.protocols.diameter.impl.primitives.DiameterAvpImpl;
import com.mobius.software.telco.protocols.diameter.primitives.accounting.CallingPartyAddress;
import com.mobius.software.telco.protocols.diameter.primitives.accounting.VoLTEInformation;
import com.mobius.software.telco.protocols.diameter.primitives.rx.CalleeInformation;

/**
*
* @author yulian oifa
*
*/
public class VoLTEInformationImpl extends DiameterAvpImpl implements VoLTEInformation
{
	private CallingPartyAddress callingPartyAddress;
	private CalleeInformation calleeInformation;
			
	public VoLTEInformationImpl()
	{
		
	}
	
	public String getCallingPartyAddress()
	{
		if(callingPartyAddress==null)
			return null;
		
		return callingPartyAddress.getString();
	}
	
	public void setCallingPartyAddress(String value)
	{
		if(value==null)
			this.callingPartyAddress = null;
		else
			this.callingPartyAddress = new CallingPartyAddressImpl(value, null, null);			
	}
	
	public CalleeInformation getCalleeInformation()
	{
		return this.calleeInformation;
	}
	
	public void setCalleeInformation(CalleeInformation value)
	{
		this.calleeInformation = value;
	}
}