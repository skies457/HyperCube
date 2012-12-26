package org.sdu.client;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import org.sdu.ui.AvatarBox;
import org.sdu.ui.BasicFrame;
import org.sdu.ui.HyperLink;
import org.sdu.ui.PasswordBox;
import org.sdu.ui.RectBorder;
import org.sdu.ui.TextBox;
import org.sdu.ui.UIHelper;

/**
 * ClientUI class implements a user interface of student user.
 * 
 * @version 0.1 rev 8002 Dec. 26, 2012.
 * Copyright (c) HyperCube Dev Team.
 */
public class ClientUI
{
	private BasicFrame frame;
	private AvatarBox avatarBox;
	private TextBox	userBox;
	private PasswordBox passBox;
	private HyperLink registerLink;
	
	private Action actionCloseOnEscape = new Action() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(frame != null) frame.dispose();
		}

		@Override
		public void addPropertyChangeListener(PropertyChangeListener arg0) {}

		@Override
		public Object getValue(String arg0) {
			return null;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}

		@Override
		public void putValue(String arg0, Object arg1) {}

		@Override
		public void removePropertyChangeListener(PropertyChangeListener arg0) {}

		@Override
		public void setEnabled(boolean arg0) {}
	};
	
	/**
	 * Initialize ClientUI object.
	 */
	public ClientUI()
	{
		frame = new BasicFrame((String)UIHelper.getResource("ui.string.login.title"),
								(String)UIHelper.getResource("ui.string.login.subtitle"));
		
		frame.setSize(UIHelper.loginFrameWidth, UIHelper.loginFrameHeight);
		frame.setIconImage((Image)UIHelper.getResource("ui.common.icon"));
		frame.getRootPane().getActionMap().put("on_escape", actionCloseOnEscape);
		frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke("ESCAPE"), "on_escape");
		
		avatarBox = new AvatarBox();
		avatarBox.setBounds(UIHelper.avatarBoxLoginOffsetX, UIHelper.avatarBoxLoginOffsetY,
				UIHelper.avatarBoxWidth, UIHelper.avatarBoxHeight);
		frame.add(avatarBox);
		
		userBox = new TextBox((String)UIHelper.getResource("ui.string.login.username"));
		userBox.setBorder(new RectBorder(UIHelper.darkColor));
		userBox.setBounds(UIHelper.usernameBoxOffsetX, UIHelper.usernameBoxOffsetY,
				UIHelper.textBoxWidth, UIHelper.textBoxHeight);
		userBox.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) passBox.requestFocus();
			}

			@Override
			public void keyReleased(KeyEvent arg0) {}

			@Override
			public void keyTyped(KeyEvent arg0) {}
		});
		frame.add(userBox);
		
		passBox = new PasswordBox((String)UIHelper.getResource("ui.string.login.password"));
		passBox.setBorder(new RectBorder(UIHelper.darkColor));
		passBox.setBounds(UIHelper.passwordBoxOffsetX, UIHelper.passwordBoxOffsetY,
				UIHelper.textBoxWidth, UIHelper.textBoxHeight);
		frame.add(passBox);
		
		registerLink = new HyperLink(
				(String)UIHelper.getResource("ui.string.login.register.desc"),
				(String)UIHelper.getResource("ui.string.login.register.url"));
		registerLink.setBounds(UIHelper.registerLinkOffsetX, UIHelper.registerLinkOffsetY,
				UIHelper.registerLinkWidth, UIHelper.registerLinkHeight);
		frame.add(registerLink);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/**
	 * @return the avatarBox
	 */
	public AvatarBox getAvatarBox() {
		return avatarBox;
	}

	/**
	 * @param avatarBox the avatarBox to set
	 */
	public void setAvatarBox(AvatarBox avatarBox) {
		this.avatarBox = avatarBox;
	}
}
