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
import com.mobius.software.telco.protocols.diameter.primitives.accounting.APNRateControl;
import com.mobius.software.telco.protocols.diameter.primitives.accounting.APNRateControlDownlink;
import com.mobius.software.telco.protocols.diameter.primitives.accounting.APNRateControlUplink;

/**
*
* @author yulian oifa
*
*/
public class APNRateControlImpl extends DiameterAvpImpl implements APNRateControl
{
	private APNRateControlUplink apnRateControlUplink;
	private APNRateControlDownlink apnRateControlDownlink;
			
	public APNRateControlImpl()
	{
		
	}
	
	public APNRateControlUplink getAPNRateControlUplink()
	{
		return apnRateControlUplink;
	}
	
	public void setAPNRateControlUplink(APNRateControlUplink value)
	{
		this.apnRateControlUplink = value;
	}
	
	public APNRateControlDownlink getAPNRateControlDownlink()
	{
		return apnRateControlDownlink;
	}
	
	public void setAPNRateControlDownlink(APNRateControlDownlink value)
	{
		this.apnRateControlDownlink = value;
	}
}