package com.mobius.software.telco.protocols.diameter.app.sta;

import com.mobius.software.telco.protocols.diameter.app.ClientAuthListener;
import com.mobius.software.telco.protocols.diameter.commands.sta.AAAnswer;
import com.mobius.software.telco.protocols.diameter.commands.sta.AbortSessionRequest;
import com.mobius.software.telco.protocols.diameter.commands.sta.EAPAnswer;
import com.mobius.software.telco.protocols.diameter.commands.sta.ReAuthRequest;
import com.mobius.software.telco.protocols.diameter.commands.sta.SessionTerminationAnswer;
import com.mobius.software.telco.protocols.diameter.exceptions.DiameterException;
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
public interface ClientListener extends ClientAuthListener<AAAnswer, ReAuthRequest, AbortSessionRequest, SessionTerminationAnswer>
{
	void onInitialAnswer(EAPAnswer answer) throws DiameterException;
}