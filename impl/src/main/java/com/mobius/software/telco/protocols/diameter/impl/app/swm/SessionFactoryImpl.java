package com.mobius.software.telco.protocols.diameter.impl.app.swm;
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

import org.restcomm.cluster.IDGenerator;

import com.mobius.software.telco.protocols.diameter.ApplicationIDs;
import com.mobius.software.telco.protocols.diameter.app.swm.SessionFactory;
import com.mobius.software.telco.protocols.diameter.commands.swm.AARequest;
import com.mobius.software.telco.protocols.diameter.commands.swm.EAPRequest;
import com.mobius.software.telco.protocols.diameter.exceptions.AvpNotSupportedException;
import com.mobius.software.telco.protocols.diameter.exceptions.MissingAvpException;
import com.mobius.software.telco.protocols.diameter.impl.commands.swm.AARequestImpl;
import com.mobius.software.telco.protocols.diameter.impl.commands.swm.EAPRequestImpl;
import com.mobius.software.telco.protocols.diameter.primitives.common.AuthRequestTypeEnum;

import io.netty.buffer.ByteBuf;

public class SessionFactoryImpl implements SessionFactory
{
	public static final long APPLICATION_ID=ApplicationIDs.SWM;
	
	private IDGenerator<?> idGenerator;
	
	private Long applicationId = APPLICATION_ID;
	
	public SessionFactoryImpl(IDGenerator<?> idGenerator)
	{
		this.idGenerator = idGenerator;
	}
	
	public SessionFactoryImpl(IDGenerator<?> idGenerator, long applicationId)
	{
		this.idGenerator = idGenerator;
		this.applicationId = applicationId;
	}
	
	public AARequest createAARequest(String originHost,String originRealm,String destinationHost,String destinationRealm,AuthRequestTypeEnum authRequestType) throws MissingAvpException, AvpNotSupportedException
	{
		return new AARequestImpl(originHost, originRealm, destinationHost, destinationRealm, false, idGenerator.generateID().toString(), applicationId, authRequestType);
	}			
	
	public EAPRequest createEAPRequest(String originHost,String originRealm,String destinationHost, String destinationRealm,AuthRequestTypeEnum authRequestType, ByteBuf eapPayload) throws MissingAvpException, AvpNotSupportedException
	{
		return new EAPRequestImpl(originHost, originRealm, destinationHost, destinationRealm, false, idGenerator.generateID().toString(), applicationId, authRequestType, eapPayload);
	}
}