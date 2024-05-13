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
import com.mobius.software.telco.protocols.diameter.primitives.accounting.ChangeTime;
import com.mobius.software.telco.protocols.diameter.primitives.accounting.RadioParameterSetInfo;
import com.mobius.software.telco.protocols.diameter.primitives.accounting.RadioParameterSetValues;

import io.netty.buffer.ByteBuf;

/**
*
* @author yulian oifa
*
*/
public class RadioParameterSetInfoImpl extends DiameterAvpImpl implements RadioParameterSetInfo
{
	private RadioParameterSetValues radioParameterSetValues;
	private ChangeTime changeTime;
			
	public RadioParameterSetInfoImpl()
	{
		
	}
	
	public ByteBuf getRadioParameterSetValues()
	{
		if(radioParameterSetValues==null)
			return null;
		
		return radioParameterSetValues.getValue();
	}
	
	public void setRadioParameterSetValues(ByteBuf value)
	{
		if(value==null)
			this.radioParameterSetValues = null;
		else
			this.radioParameterSetValues = new RadioParameterSetValuesImpl(value, null, null);			
	}
	
	public Date getChangeTime()
	{
		if(changeTime==null)
			return null;
		
		return changeTime.getDateTime();
	}
	
	public void setChangeTime(Date value)
	{
		if(value==null)
			this.changeTime = null;
		else
			this.changeTime = new ChangeTimeImpl(value, null, null);			
	}
}