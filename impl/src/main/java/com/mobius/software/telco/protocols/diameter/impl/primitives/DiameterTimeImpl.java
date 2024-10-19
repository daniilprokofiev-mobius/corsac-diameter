package com.mobius.software.telco.protocols.diameter.impl.primitives;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.mobius.software.telco.protocols.diameter.annotations.DiameterDecode;
import com.mobius.software.telco.protocols.diameter.annotations.DiameterPrint;
import com.mobius.software.telco.protocols.diameter.exceptions.DiameterException;
import com.mobius.software.telco.protocols.diameter.parser.DiameterParser;
import com.mobius.software.telco.protocols.diameter.primitives.DiameterTime;

import io.netty.buffer.ByteBuf;

/*
 * Mobius Software LTD, Open Source Cloud Communications
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
/**
*
* @author yulian oifa
*
*/
public class DiameterTimeImpl extends DiameterUnsigned32Impl implements DiameterTime 
{
	private static final long EPOCH_SHIFT = 2208988800L;
	private static final long MAX_ERA_SECONDS = 3520800000L;
	private static final long SECOND_ERA_START = 2085978496L;
	
	private Date date;
	
	private static SimpleDateFormat sf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	//required for parser
	public DiameterTimeImpl() 
	{
		super();
	}
	
	protected DiameterTimeImpl(Long minValue,Long maxValue) 
	{
		super(minValue, maxValue);
	}
	
	public DiameterTimeImpl(Date value,Long minValue,Long maxValue) 
	{
		super(fromDate(value), minValue, maxValue);
	}

	public Date getDateTime() 
	{
		return date;
	}
	
	@DiameterPrint
	public void print(StringBuilder sb) 
	{
		if(date==null)
			DiameterParser.printMesage(sb, "");
		else			
			DiameterParser.printMesage(sb, sf.format(date));		 		
	}

	@DiameterDecode
	public DiameterException decode(ByteBuf buffer,Integer length) 
	{
		super.decode(buffer, length);
		Long value = getUnsigned();
		if(value!=null)
		{
			value = value & 0x0FFFFFFFFL;
			// Era 0
	        // - NTP Timestamp 3520800000 to 4294967295
	        // - Jul 28 00:00:00 UTC 2011 to Feb 7 06:28:15 UTC 2036
	        // - UNIX Timestamp 1311811200 to 2085978495
			// Era 1
	        // - NTP Timestamp 0 to 3520799999
	        // - Feb 7 06:28:16 UTC 2036 to Sep 3 06:28:15 UTC 2147
	        // - Unix Timestamp 2085978496 to 5606778495
	        
			if( value >= MAX_ERA_SECONDS )
				value = value - EPOCH_SHIFT;
	        else
	        	value = value + SECOND_ERA_START;
	        
			this.date = new Date(value * 1000L);
		}
		
		return null;
	}
	
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		DiameterTimeImpl other = (DiameterTimeImpl) obj;
		if (getUnsigned() == null) 
		{
			if (other.getUnsigned() != null)
				return false;
		} 
		else if (!getUnsigned().equals(other.getUnsigned()))
			return false;
		
		return true;
	}
	
	public static Long fromDate(Date date)
	{
		if(date==null)
			return null;
		
		Long value = (date.getTime() / 1000L) + EPOCH_SHIFT;
		return value & 0x0FFFFFFFFL;
	}
}