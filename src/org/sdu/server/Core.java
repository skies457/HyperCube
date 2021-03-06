/**
 * 
 */
package org.sdu.server;

import java.nio.channels.SocketChannel;
import java.util.Hashtable;
import java.util.Observer;

import org.sdu.server.DatabaseInterface;
import org.sdu.server.UI.ServerDataObserver;
import org.sdu.server.UI.TrackDataObserver;
import org.sdu.database.Database;
import org.sdu.net.Packet;
import org.sdu.net.Session;
import org.sdu.net.SessionHandler;
import org.sdu.net.NetworkServer;
import org.sdu.util.DebugFramework;

/**
 * @author Celr
 *
 */
public class Core extends SessionHandler {
	public static final int port = 21071;
	public static final DebugFramework debugger = DebugFramework.getFramework();
	private static Hashtable<String,Session> SessionMap;
	private static Hashtable<Session,String> UserMap;
	private NetworkServer server;
	private DatabaseInterface db;
	private Observer ServerMan;
	private ServerDataObserver d;
	private TrackDataObserver d1;
	
	/* (non-Javadoc)
	 * @see org.sdu.net.SessionHandler#onAcceptingSession(java.nio.channels.SocketChannel)
	 */
	@Override
	public boolean onAcceptingSession(SocketChannel c) {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see org.sdu.net.SessionHandler#onSessionAccepted(org.sdu.net.Session)
	 */
	@Override
	public void onSessionAccepted(Session s) {
//		try {
//			debugger.print("Session from " + s.getChannel().getRemoteAddress() + " accepted.");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

	/* (non-Javadoc)
	 * @see org.sdu.net.SessionHandler#onSessionClosed(org.sdu.net.Session)
	 */
	@Override
	public void onSessionClosed(Session s) {
//		try {
//			//debugger.print("Session from " + s.getChannel().getRemoteAddress() + " closed.");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

	/* (non-Javadoc)
	 * @see org.sdu.net.SessionHandler#onUnregisteredSession(java.nio.channels.SocketChannel)
	 */
	@Override
	public void onUnregisteredSession(SocketChannel c) {
//		try {
//			//debugger.print("Session from " + c.getRemoteAddress() + " failed to register.");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

	/* (non-Javadoc)
	 * @see org.sdu.net.SessionHandler#onPacketReceived(org.sdu.net.Session, org.sdu.net.Packet)
	 */
	@Override
	public void onPacketReceived(Session s, Packet p) {
			try {
				db = new Database();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			d = new ServerDataObserver();
			d1 = new TrackDataObserver();
			process Pro = new process(d,d1);
			Pro.Push(p.getData(),db,s,SessionMap,UserMap);// Process
//		try {
// 			for (int i = 0; i<Pro.GetData().getLength();i++)
//    		{System.out.print(Pro.GetData().getDataArray()[i]);
//    		System.out.print(' ');}
			try {
				s.post(Pro.GetData());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		} catch (Exception e) {
//			if (e.getMessage().equals("Unknow"))
//			e.printStackTrace();
//		}// Send back the Packet

	}

	/* (non-Javadoc)
	 * @see org.sdu.net.SessionHandler#onConnected(org.sdu.net.Session)
	 */
	@Override
	public void onConnected(Session s) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.sdu.net.SessionHandler#onConnectFailure(java.nio.channels.SocketChannel)
	 */
	@Override
	public void onConnectFailure(SocketChannel c) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public Core() throws Exception
	{		
		try {
				db = new Database();
			} catch (Exception e1) {
				throw new Exception("DB");
		}
		
		SessionMap = new Hashtable<String,Session>();
		UserMap = new Hashtable<Session,String>();
		//Init for more;
		server = new NetworkServer(this, port);
		server.start(false);
		
	}

	public void CloseServer() throws Exception
	{
		if (server.isRunning() == true){
		server.stop();}else
		{throw new Exception("Already Shutdown");}
	}
}
