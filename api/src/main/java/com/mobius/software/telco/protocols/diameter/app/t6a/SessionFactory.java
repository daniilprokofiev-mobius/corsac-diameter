package com.mobius.software.telco.protocols.diameter.app.t6a;
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

import com.mobius.software.telco.protocols.diameter.commands.t6a.ConfigurationInformationRequest;
import com.mobius.software.telco.protocols.diameter.commands.t6a.ConnectionManagementRequest;
import com.mobius.software.telco.protocols.diameter.commands.t6a.MODataRequest;
import com.mobius.software.telco.protocols.diameter.commands.t6a.MTDataRequest;
import com.mobius.software.telco.protocols.diameter.commands.t6a.ReportingInformationRequest;
import com.mobius.software.telco.protocols.diameter.exceptions.AvpNotSupportedException;

public interface SessionFactory
{
	public T6aClientSession createClientSession(ConfigurationInformationRequest request) throws AvpNotSupportedException;	
	
	public T6aServerSession createServerSession(ConfigurationInformationRequest request) throws AvpNotSupportedException;		

	public T6aClientSession createClientSession(ConnectionManagementRequest request) throws AvpNotSupportedException;	
	
	public T6aServerSession createServerSession(ConnectionManagementRequest request) throws AvpNotSupportedException;	
	
	public T6aClientSession createClientSession(MODataRequest request) throws AvpNotSupportedException;	
	
	public T6aServerSession createServerSession(MODataRequest request) throws AvpNotSupportedException;		

	public T6aClientSession createClientSession(MTDataRequest request) throws AvpNotSupportedException;	
	
	public T6aServerSession createServerSession(MTDataRequest request) throws AvpNotSupportedException;	
	
	public T6aClientSession createClientSession(ReportingInformationRequest request) throws AvpNotSupportedException;	
	
	public T6aServerSession createServerSession(ReportingInformationRequest request) throws AvpNotSupportedException;	
		
}