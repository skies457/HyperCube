package org.sdu.test;

import org.sdu.net.NetworkServer;
import org.sdu.net.Packet;
import org.sdu.net.Session;
import org.sdu.net.SessionHandler;
import org.sdu.util.DebugFramework;

import java.nio.channels.SocketChannel;

/**
 * A echo server test.
 * 
 * @version 0.1 rev 8004 Dec. 31, 2012.
 * Copyright (c) HyperCube Dev Team.
 */
public class EchoServerTest extends SessionHandler
{
	public static final int port = 21071;
	public static final DebugFramework debugger = DebugFramework.getFramework();
	
	private NetworkServer server;
	
	public static void main(String[] args)
	{
		debugger.setLogFileName("EchoServerTest.log");
		new EchoServerTest();
	}

	/**
	 * Initialize echo server.
	 */
	public EchoServerTest()
	{
		server = new NetworkServer(this, port);
		server.start(true);
	}

	@Override
	public boolean onAcceptingSession(SocketChannel c)
	{
		return true;
	}

	@Override
	public void onSessionAccepted(Session s)
	{
		try {
			debugger.print("Session from " + s.getChannel().getRemoteAddress() + " accepted.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onSessionClosed(Session s)
	{
		try {
			debugger.print("Session from " + s.getChannel().getRemoteAddress() + " closed.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUnregisteredSession(SocketChannel c)
	{
		try {
			debugger.print("Session from " + c.getRemoteAddress() + " failed to register.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onPacketReceived(Session s, Packet p)
	{
		try {
			debugger.print("Received " + p.getLength() + " bytes from client " + s.getChannel().getRemoteAddress() + ".");
			s.post(p);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
