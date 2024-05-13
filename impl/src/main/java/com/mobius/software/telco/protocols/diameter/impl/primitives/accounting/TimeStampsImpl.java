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
import com.mobius.software.telco.protocols.diameter.primitives.accounting.SIPRequestTimestamp;
import com.mobius.software.telco.protocols.diameter.primitives.accounting.SIPRequestTimestampFraction;
import com.mobius.software.telco.protocols.diameter.primitives.accounting.SIPResponseTimestamp;
import com.mobius.software.telco.protocols.diameter.primitives.accounting.SIPResponseTimestampFraction;
import com.mobius.software.telco.protocols.diameter.primitives.accounting.TimeStamps;

/**
*
* @author yulian oifa
*
*/
public class TimeStampsImpl extends DiameterAvpImpl implements TimeStamps
{
	private SIPRequestTimestamp sipRequestTimestamp;
	private SIPResponseTimestamp sipResponseTimestamp;
	private SIPRequestTimestampFraction sipRequestTimestampFraction;
	private SIPResponseTimestampFraction sipResponseTimestampFraction;
		
	public TimeStampsImpl()
	{		
	}
	
	public Date getSIPRequestTimestamp()
	{
		if(sipRequestTimestamp==null)
			return null;
		
		return sipRequestTimestamp.getDateTime();
	}
	
	public void setSIPRequestTimestamp(Date value)
	{
		if(value==null)
			this.sipRequestTimestamp = null;
		else
			this.sipRequestTimestamp = new SIPRequestTimestampImpl(value, null, null);			
	}
	
	public Date getSIPResponseTimestamp()
	{
		if(sipResponseTimestamp==null)
			return null;
		
		return sipResponseTimestamp.getDateTime();
	}
	
	public void setSIPResponseTimestamp(Date value)
	{
		if(value==null)
			this.sipResponseTimestamp = null;
		else
			this.sipResponseTimestamp = new SIPResponseTimestampImpl(value, null, null);			
	}
	
	public Long getSIPRequestTimestampFraction()
	{
		if(sipRequestTimestampFraction==null)
			return null;
		
		return sipRequestTimestampFraction.getUnsigned();
	}
	
	public void setSIPRequestTimestampFraction(Long value)
	{
		if(value==null)
			this.sipRequestTimestampFraction = null;
		else
			this.sipRequestTimestampFraction = new SIPRequestTimestampFractionImpl(value, null, null);			
	}
	
	public Long getSIPResponseTimestampFraction()
	{
		if(sipResponseTimestampFraction==null)
			return null;
		
		return sipResponseTimestampFraction.getUnsigned();
	}
	
	public void setSIPResponseTimestampFraction(Long value)
	{
		if(value==null)
			this.sipResponseTimestampFraction = null;
		else
			this.sipResponseTimestampFraction = new SIPResponseTimestampFractionImpl(value, null, null);			
	}
}