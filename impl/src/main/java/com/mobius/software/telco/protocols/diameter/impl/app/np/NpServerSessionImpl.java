package com.mobius.software.telco.protocols.diameter.impl.app.np;
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

import com.mobius.software.telco.protocols.diameter.ApplicationIDs;
import com.mobius.software.telco.protocols.diameter.DiameterProvider;
import com.mobius.software.telco.protocols.diameter.app.ServerAuthStatelessListener;
import com.mobius.software.telco.protocols.diameter.app.np.NpServerSession;
import com.mobius.software.telco.protocols.diameter.commands.np.NpAnswer;
import com.mobius.software.telco.protocols.diameter.commands.np.NpRequest;
import com.mobius.software.telco.protocols.diameter.impl.app.ServerAuthSessionStatelessImpl;

public class NpServerSessionImpl extends ServerAuthSessionStatelessImpl<NpRequest, NpAnswer> implements NpServerSession
{
	public NpServerSessionImpl()
	{
		super(Long.valueOf(ApplicationIDs.NP));
	}
	
	public NpServerSessionImpl(String sessionID, String remoteHost, String remoteRealm, DiameterProvider<?, ? extends ServerAuthStatelessListener<NpAnswer>, ?, ?, ?> provider)
	{
		super(sessionID,Long.valueOf(ApplicationIDs.NP), remoteHost, remoteRealm, provider);
	}
}