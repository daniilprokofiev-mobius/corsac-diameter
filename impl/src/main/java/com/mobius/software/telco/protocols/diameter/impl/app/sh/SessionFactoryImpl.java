package com.mobius.software.telco.protocols.diameter.impl.app.sh;
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

import com.mobius.software.telco.protocols.diameter.DiameterProvider;
import com.mobius.software.telco.protocols.diameter.app.sh.ClientListener;
import com.mobius.software.telco.protocols.diameter.app.sh.ShClientSession;
import com.mobius.software.telco.protocols.diameter.app.sh.ShServerSession;
import com.mobius.software.telco.protocols.diameter.app.sh.ServerListener;
import com.mobius.software.telco.protocols.diameter.app.sh.SessionFactory;
import com.mobius.software.telco.protocols.diameter.commands.sh.ProfileUpdateRequest;
import com.mobius.software.telco.protocols.diameter.commands.sh.PushNotificationRequest;
import com.mobius.software.telco.protocols.diameter.commands.sh.SubscribeNotificationsRequest;
import com.mobius.software.telco.protocols.diameter.commands.sh.UserDataRequest;
import com.mobius.software.telco.protocols.diameter.exceptions.AvpNotSupportedException;
/**
*
* @author yulian oifa
*
*/
public class SessionFactoryImpl implements SessionFactory
{
	private DiameterProvider<ClientListener,ServerListener,?, ?, ?> provider;
	
	public SessionFactoryImpl(DiameterProvider<ClientListener,ServerListener,?, ?, ?> provider)
	{
		this.provider = provider;
	}

	@Override
	public ShClientSession createClientSession(ProfileUpdateRequest request) throws AvpNotSupportedException
	{
		return new ShClientSessionImpl(request.getSessionId(), request.getDestinationHost(), request.getDestinationRealm(), provider);
	}

	@Override
	public ShServerSession createServerSession(ProfileUpdateRequest request) throws AvpNotSupportedException
	{
		return new ShServerSessionImpl(request.getSessionId(), request.getOriginHost(), request.getOriginRealm(), provider);
	}
	
	@Override
	public ShClientSession createClientSession(PushNotificationRequest request) throws AvpNotSupportedException
	{
		return new ShClientSessionImpl(request.getSessionId(), request.getDestinationHost(), request.getDestinationRealm(), provider);
	}

	@Override
	public ShServerSession createServerSession(PushNotificationRequest request) throws AvpNotSupportedException
	{
		return new ShServerSessionImpl(request.getSessionId(), request.getOriginHost(), request.getOriginRealm(), provider);
	}
	
	@Override
	public ShClientSession createClientSession(SubscribeNotificationsRequest request) throws AvpNotSupportedException
	{
		return new ShClientSessionImpl(request.getSessionId(), request.getDestinationHost(), request.getDestinationRealm(), provider);
	}

	@Override
	public ShServerSession createServerSession(SubscribeNotificationsRequest request) throws AvpNotSupportedException
	{
		return new ShServerSessionImpl(request.getSessionId(), request.getOriginHost(), request.getOriginRealm(), provider);
	}
	
	@Override
	public ShClientSession createClientSession(UserDataRequest request) throws AvpNotSupportedException
	{
		return new ShClientSessionImpl(request.getSessionId(), request.getDestinationHost(), request.getDestinationRealm(), provider);
	}

	@Override
	public ShServerSession createServerSession(UserDataRequest request) throws AvpNotSupportedException
	{
		return new ShServerSessionImpl(request.getSessionId(), request.getOriginHost(), request.getOriginRealm(), provider);
	}
	

}