package com.mobius.software.telco.protocols.diameter.impl.primitives.gx;
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

import com.mobius.software.telco.protocols.diameter.impl.primitives.DiameterGroupedAvpImpl;
import com.mobius.software.telco.protocols.diameter.primitives.gx.AllocationRetentionPriority;
import com.mobius.software.telco.protocols.diameter.primitives.gx.DefaultEPSBearerQoS;
import com.mobius.software.telco.protocols.diameter.primitives.gx.QoSClassIdentifier;
import com.mobius.software.telco.protocols.diameter.primitives.gx.QoSClassIdentifierEnum;

/**
*
* @author yulian oifa
*
*/
public class DefaultEPSBearerQoSImpl extends DiameterGroupedAvpImpl implements DefaultEPSBearerQoS
{
	private QoSClassIdentifier qosClassIdentifier;
	private AllocationRetentionPriority allocationRetentionPriority;
		
	public DefaultEPSBearerQoSImpl()
	{
		
	}
	
	public QoSClassIdentifierEnum getQoSClassIdentifier()
	{
		if(qosClassIdentifier==null)
			return null;
		
		return qosClassIdentifier.getEnumerated(QoSClassIdentifierEnum.class);
	}
	
	public void setQoSClassIdentifier(QoSClassIdentifierEnum value)
	{
		if(value==null)
			this.qosClassIdentifier = null;
		else
			this.qosClassIdentifier = new QoSClassIdentifierImpl(value, null, null);			
	}
	
	public AllocationRetentionPriority getAllocationRetentionPriority()
	{
		return allocationRetentionPriority;
	}
	
	public void setAllocationRetentionPriority(AllocationRetentionPriority value)
	{
		this.allocationRetentionPriority = value;
	}	
}