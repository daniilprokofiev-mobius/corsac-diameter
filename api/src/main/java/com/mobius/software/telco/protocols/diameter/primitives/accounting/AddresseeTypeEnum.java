package com.mobius.software.telco.protocols.diameter.primitives.accounting;

import java.util.HashMap;
import java.util.Map;

import com.mobius.software.telco.protocols.diameter.primitives.IntegerEnum;

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
/*
 * 	7.2.10 Addressee-Type AVP
	The Addressee-Type AVP (AVP code 1208) is of type Enumerated and identifies the how the recipient is addressed in
	the header of an MM. The following values are defined:

	0 TO
	1 CC
	2 BCC
*/
public enum AddresseeTypeEnum implements IntegerEnum
{
	TO(0),CC(1),BCC(2);

	private static final Map<Integer, AddresseeTypeEnum> intToTypeMap = new HashMap<Integer, AddresseeTypeEnum>();
	static
	{
	    for (AddresseeTypeEnum type : AddresseeTypeEnum.values()) 
	    {
	    	intToTypeMap.put(type.value, type);
	    }
	}

	public static AddresseeTypeEnum fromInt(Integer value) 
	{
		AddresseeTypeEnum type = intToTypeMap.get(value);
	    return type;
	}
	
	private int value;
	
	private AddresseeTypeEnum(int value)
	{
		this.value=value;
	}
	
	public int getValue()
	{
		return value;
	}
}
