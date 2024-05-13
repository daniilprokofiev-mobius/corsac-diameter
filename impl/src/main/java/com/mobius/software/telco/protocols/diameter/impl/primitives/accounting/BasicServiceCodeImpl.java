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
import com.mobius.software.telco.protocols.diameter.primitives.accounting.BasicServiceCode;
import com.mobius.software.telco.protocols.diameter.primitives.accounting.BearerService;
import com.mobius.software.telco.protocols.diameter.primitives.accounting.Teleservice;

import io.netty.buffer.ByteBuf;

/**
*
* @author yulian oifa
*
*/
public class BasicServiceCodeImpl extends DiameterAvpImpl implements BasicServiceCode
{
	private BearerService bearerService;
	private Teleservice teleservice;
	
	public BasicServiceCodeImpl()
	{
		
	}
	
	public ByteBuf getBearerService()
	{
		if(bearerService==null)
			return null;
		
		return bearerService.getValue();
	}
	
	public void setBearerService(ByteBuf value)
	{
		if(value==null)
			this.bearerService = null;
		else
			this.bearerService = new BearerServiceImpl(value, null, null);			
	}
	
	public ByteBuf getTeleservice()
	{
		if(teleservice==null)
			return null;
		
		return teleservice.getValue();
	}
	
	public void setTeleservice(ByteBuf value)
	{
		if(value==null)
			this.teleservice = null;
		else
			this.teleservice = new TeleserviceImpl(value, null, null);			
	}
}