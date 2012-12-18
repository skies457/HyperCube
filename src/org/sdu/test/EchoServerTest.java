package org.sdu.test;

import org.sdu.network.NetworkServer;
import org.sdu.network.Packet;
import org.sdu.network.Session;
import org.sdu.network.SessionHandler;
import org.sdu.util.DebugFramework;

import java.util.Observable;
import java.util.Observer;

/**
 * A echo server test.
 * 
 * @version 0.1 rev 8001 Dec. 17, 2012.
 * Copyright (c) HyperCube Dev Team.
 */
public class EchoServerTest implements Observer, SessionHandler
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
		server = new NetworkServer();
		server.start(port, 1, this);
	}
	
	@Override
	public boolean onServerStart()
	{
		return true;
	}

	@Override
	public void onServerClose()
	{
	}

	@Override
	public void update(Observable session, Object p) {
		debugger.print("Notification received");
		if(session instanceof Session && p instanceof Packet) {
			try {
				String s = new String(((Packet)p).getData(), "ISO-8859-1");
				debugger.print(s);
			} catch (Exception e) {
				debugger.print(e);
			}
		}
	}

	@Override
	public boolean onNewSession(Session s) {
		s.addObserver(this);
		return true;
	}
}