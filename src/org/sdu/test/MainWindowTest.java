package org.sdu.test;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.sdu.client.ClientFrame;
import org.sdu.ui.AvatarBox;
import org.sdu.ui.PanelSwitcher;
import org.sdu.ui.PushMessage;
import org.sdu.ui.TextBox;
import org.sdu.ui.UIHelper;
import org.sdu.util.DebugFramework;

/**
 * Create a test-purposed main window.
 *
 * @version 0.1 rev 8001 Jan. 6, 2013.
 * Copyright (c) HyperCube Dev Team.
 */
public class MainWindowTest extends ClientFrame
{
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args)
	{
		DebugFramework.getFramework().setLogFileName("MainWindowTest.log");
		UIHelper.loadResource("art/resource.xml");
		new MainWindowTest();
	}
	
	public MainWindowTest()
	{
		super();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(UIHelper.loginFrameWidth, UIHelper.loginFrameHeight);
		setTitle("Arthas");
		titleOffsetX += 95;
		titleOffsetY += 5;
		
		AvatarBox avatar = new AvatarBox();
		avatar.setBounds(UIHelper.avatarBoxLoginOffsetX, 10,
				UIHelper.avatarBoxWidth, UIHelper.avatarBoxHeight);
		try {
			avatar.setAvatar(ImageIO.read(new File("art/testavatar.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		add(avatar);
		
//		TextBox signature = new TextBox("签名档...");
//		signature.setBounds(titleOffsetX, 70,
//				180, UIHelper.textBoxHeight);
//		signature.setEditable(false);
//		add(signature);
//		
//		PushMessage pushmsg = new PushMessage();
//		pushmsg.setBounds(18, 120, PushMessage.Width + 1, PushMessage.Height + 1);
//		add(pushmsg);
//		
//		pushmsg = new PushMessage();
//		pushmsg.setBounds(18, 200, PushMessage.Width + 1, PushMessage.Height + 1);
//		add(pushmsg);
		
		PanelSwitcher switcher = new PanelSwitcher(35);
		switcher.setBounds(5, 110, 300, 560);
		JPanel p1, p2, p3;
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		p1.setBackground(Color.GRAY);
		p2.setBackground(Color.BLUE);
		p3.setBackground(Color.GREEN);
		switcher.addPanel(p1);
		switcher.addPanel(p2);
		switcher.addPanel(p3);
		add(switcher);
		
		setVisible(true);
		
		startExpanding(680);
	}
}