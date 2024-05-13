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

import java.util.Date;

import com.mobius.software.telco.protocols.diameter.impl.primitives.DiameterAvpImpl;
import com.mobius.software.telco.protocols.diameter.primitives.accounting.SDPAnswerTimestamp;
import com.mobius.software.telco.protocols.diameter.primitives.accounting.SDPOfferTimestamp;
import com.mobius.software.telco.protocols.diameter.primitives.accounting.SDPTimeStamps;

/**
*
* @author yulian oifa
*
*/
public class SDPTimeStampsImpl extends DiameterAvpImpl implements SDPTimeStamps
{
	private SDPOfferTimestamp sdpOfferTimestamp;
	private SDPAnswerTimestamp sdpAnswerTimestamp;
		
	public SDPTimeStampsImpl()
	{
		
	}
	
	public Date getSDPOfferTimestamp()
	{
		if(sdpOfferTimestamp==null)
			return null;
		
		return sdpOfferTimestamp.getDateTime();
	}
	
	public void setSDPOfferTimestamp(Date value)
	{
		if(value==null)
			this.sdpOfferTimestamp = null;
		else
			this.sdpOfferTimestamp = new SDPOfferTimestampImpl(value, null, null);			
	}
	
	public Date getSDPAnswerTimestamp()
	{
		if(sdpAnswerTimestamp==null)
			return null;
		
		return sdpAnswerTimestamp.getDateTime();
	}
	
	public void setSDPAnswerTimestamp(Date value)
	{
		if(value==null)
			this.sdpAnswerTimestamp = null;
		else
			this.sdpAnswerTimestamp = new SDPAnswerTimestampImpl(value, null, null);			
	}
}