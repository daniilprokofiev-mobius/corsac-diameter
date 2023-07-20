package com.mobius.software.telco.protocols.diameter.primitives.creditcontrol;
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

import com.mobius.software.telco.protocols.diameter.annotations.DiameterAvpDefinition;
import com.mobius.software.telco.protocols.diameter.primitives.DiameterAvp;

import io.netty.buffer.ByteBuf;

/**
*
* @author yulian oifa
*
*/

/*
 * The User-Equipment-Info AVP is defined as follows (per
   grouped-avp-def as defined in [RFC6733]):

         User-Equipment-Info ::= < AVP Header: 458 >
                                 { User-Equipment-Info-Type }
                                 { User-Equipment-Info-Value }
 */

@DiameterAvpDefinition(code = 458L, vendorId = -1, must = false, name = "User-Equipment-Info")
public interface UserEquipmentInfo extends DiameterAvp 
{
	UserEquipmentInfoTypeEnum getUserEquipmentInfoType();
	
	void setUserEquipmentInfoType(UserEquipmentInfoTypeEnum userEquipmentInfoType);
	
	ByteBuf getUserEquipmentInfoValue();
	
	void setUserEquipmentInfoValue(ByteBuf userEquipmentInfoValue);
}