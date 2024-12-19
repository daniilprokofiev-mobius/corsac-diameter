package com.mobius.software.telco.protocols.diameter.test.s6a;
/*
 * Mobius Software LTD
 * Copyright 2019 - 2023, Mobius Software LTD and individual contributors
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

import static org.junit.Assert.assertEquals;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.restcomm.cluster.ClusteredID;

import com.mobius.software.telco.protocols.diameter.ApplicationIDs;
import com.mobius.software.telco.protocols.diameter.AsyncCallback;
import com.mobius.software.telco.protocols.diameter.DiameterLink;
import com.mobius.software.telco.protocols.diameter.DiameterSession;
import com.mobius.software.telco.protocols.diameter.DiameterStack;
import com.mobius.software.telco.protocols.diameter.NetworkListener;
import com.mobius.software.telco.protocols.diameter.ResultCodes;
import com.mobius.software.telco.protocols.diameter.app.ClientAuthSessionStateless;
import com.mobius.software.telco.protocols.diameter.app.ServerAuthSessionStateless;
import com.mobius.software.telco.protocols.diameter.app.SessionStateEnum;
import com.mobius.software.telco.protocols.diameter.app.s6a.ClientListener;
import com.mobius.software.telco.protocols.diameter.app.s6a.S6aClientSession;
import com.mobius.software.telco.protocols.diameter.app.s6a.ServerListener;
import com.mobius.software.telco.protocols.diameter.commands.DiameterMessage;
import com.mobius.software.telco.protocols.diameter.commands.DiameterRequest;
import com.mobius.software.telco.protocols.diameter.commands.s6a.DeleteSubscriberDataAnswer;
import com.mobius.software.telco.protocols.diameter.commands.s6a.DeleteSubscriberDataRequest;
import com.mobius.software.telco.protocols.diameter.commands.s6a.S6aAnswer;
import com.mobius.software.telco.protocols.diameter.commands.s6a.S6aRequest;
import com.mobius.software.telco.protocols.diameter.exceptions.DiameterException;
import com.mobius.software.telco.protocols.diameter.impl.app.s6a.S6aProviderImpl;
import com.mobius.software.telco.protocols.diameter.primitives.s6a.DSRFlags;

/**
*
* @author yulian oifa
*
*/
public class DeleteSubscriberDataTest extends NetworkTestBase
{
	protected static final String localListenerID = "1";
	private static final Logger logger = LogManager.getLogger(DeleteSubscriberDataTest.class);
	
	@Test
	public void testAuthenticationInformation() throws Exception
	{
		super.setupRemote(0L,0L);
		super.setupLocal(0L,0L);
		
		final AtomicLong dsaReceived=new AtomicLong(0L);
		final AtomicLong dsaReceivedByListener=new AtomicLong(0L);
		final AtomicLong dsrReceived=new AtomicLong(0L);
		final AtomicLong dsrReceivedByListener=new AtomicLong(0L);
		final AtomicLong timeoutReceived=new AtomicLong(0L);
		
		DiameterStack localStack = this.localStack;
		DiameterStack serverStack = this.serverStack;
		
		localStack.getNetworkManager().addNetworkListener(localListenerID, new NetworkListener()
		{
			@Override
			public void onMessage(DiameterMessage message, String linkID, AsyncCallback callback)
			{
				if(message instanceof S6aAnswer)
					dsrReceived.incrementAndGet();				
			}
		});
		
		serverStack.getNetworkManager().addNetworkListener(localListenerID, new NetworkListener()
		{
			@Override
			public void onMessage(DiameterMessage message, String linkID, AsyncCallback callback)
			{
				if(message instanceof S6aRequest)
					dsaReceived.incrementAndGet();				
			}
		});
		
		try
		{
			Thread.sleep(reconnectTimeout * 2);
		}
		catch(InterruptedException ex)
		{
			
		}
		
		S6aProviderImpl provider = (S6aProviderImpl)localStack.getProvider(Long.valueOf(ApplicationIDs.S6A), Package.getPackage("com.mobius.software.telco.protocols.diameter.commands.s6a"));
		ClusteredID<?> listenerID=generator.generateID();
		provider.setClientListener(listenerID, new ClientListener()
		{
			@Override
			public void onTimeout(DiameterRequest request,DiameterSession session)
			{
				timeoutReceived.incrementAndGet();
			}
			
			@Override
			public void onIdleTimeout(DiameterSession session)
			{
				timeoutReceived.incrementAndGet();
			}

			@Override
			public void onInitialAnswer(S6aAnswer answer, ClientAuthSessionStateless<S6aRequest> session, String linkID,AsyncCallback callback)
			{
				dsaReceivedByListener.incrementAndGet();
			}
			
		});
		
		S6aProviderImpl serverProvider = (S6aProviderImpl)serverStack.getProvider(Long.valueOf(ApplicationIDs.S6A), Package.getPackage("com.mobius.software.telco.protocols.diameter.commands.s6a"));
		serverProvider.setServerListener(listenerID, new ServerListener()
		{
			@Override
			public void onTimeout(DiameterRequest request,DiameterSession session)
			{
				timeoutReceived.incrementAndGet();
			}
			
			@Override
			public void onIdleTimeout(DiameterSession session)
			{
				timeoutReceived.incrementAndGet();
			}

			@Override
			public void onInitialRequest(S6aRequest request, ServerAuthSessionStateless<S6aAnswer> session,String linkID, AsyncCallback callback)
			{
				dsrReceivedByListener.incrementAndGet();
				DeleteSubscriberDataRequest dsr=(DeleteSubscriberDataRequest)request;
				try
				{
					DeleteSubscriberDataAnswer dsa = serverProvider.getMessageFactory().createDeleteSubscriberDataAnswer(dsr, dsr.getHopByHopIdentifier(), dsr.getEndToEndIdentifier(), ResultCodes.DIAMETER_SUCCESS);
					session.sendInitialAnswer(dsa, new AsyncCallback()
					{
						@Override
						public void onSuccess()
						{
							
						}
						
						@Override
						public void onError(DiameterException ex)
						{
							logger.error("An error occured while sending Delete Subscriber Data Answer," + ex.getMessage(),ex);
						}
					});
				}
				catch(DiameterException ex)
				{
					logger.error("An error occured while sending Delete Subscriber Data Answer," + ex.getMessage(),ex);
				}
			}
			
		});
		
		//usually its not needed to get link, here we use it to read hosts/realms
		DiameterLink localLink = localStack.getNetworkManager().getLink(localLinkID);
		
		DSRFlags dsrFlags = provider.getAvpFactory().getDSRFlags();
		dsrFlags.setActiveTimeWithdrawalBit(true);;
		
		
		DeleteSubscriberDataRequest request = provider.getMessageFactory().createDeleteSubscriberDataRequest(localLink.getLocalHost(), localLink.getLocalRealm(), localLink.getDestinationHost(), localLink.getDestinationRealm(),dsrFlags);
		
		S6aClientSession clientSession = (S6aClientSession)provider.getSessionFactory().createClientSession(request);
		
		clientSession.sendInitialRequest(request, new AsyncCallback()
		{
			@Override
			public void onSuccess()
			{
			}
			
			@Override
			public void onError(DiameterException ex)
			{
			}
		});
		
		try
		{
			Thread.sleep(responseTimeout);
		}
		catch(InterruptedException ex)
		{
			
		}
		
		assertEquals(clientSession.getSessionState(),SessionStateEnum.IDLE);
		
		//make sure no timeout is processed
		try
		{
			Thread.sleep(idleTimeout * 2);
		}
		catch(InterruptedException ex)
		{
			
		}
		
		super.stopLocal();
		super.stopRemote();
		
		try
		{
			Thread.sleep(responseTimeout);
		}
		catch(InterruptedException ex)
		{
			
		}
		
		assertEquals(dsaReceived.get() , 1L);
		assertEquals(dsaReceivedByListener.get() , 1L);
		assertEquals(dsrReceived.get() , 1L);
		assertEquals(dsrReceivedByListener.get() , 1L);
		assertEquals(timeoutReceived.get() , 0L);
	}
	
}