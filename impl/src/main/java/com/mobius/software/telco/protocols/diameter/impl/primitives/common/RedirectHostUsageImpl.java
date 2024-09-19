package com.mobius.software.telco.protocols.diameter.impl.primitives.common;
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

import com.mobius.software.telco.protocols.diameter.impl.primitives.DiameterEnumeratedImpl;
import com.mobius.software.telco.protocols.diameter.primitives.common.RedirectHostUsage;
import com.mobius.software.telco.protocols.diameter.primitives.common.RedirectHostUsageEnum;

/**
*
* @author yulian oifa
*
*/
public class RedirectHostUsageImpl extends DiameterEnumeratedImpl<RedirectHostUsageEnum> implements RedirectHostUsage
{
	public RedirectHostUsageImpl() 
	{
		super();
	}
	
	protected RedirectHostUsageImpl(Integer minValue,Integer maxValue) 
	{
		super(minValue, maxValue);
	}
	
	public RedirectHostUsageImpl(RedirectHostUsageEnum value,Integer minValue,Integer maxValue) 
	{
		super(value, minValue, maxValue);
	}
}