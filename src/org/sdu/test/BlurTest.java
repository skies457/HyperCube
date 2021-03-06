package org.sdu.test;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.sdu.ui.GaussianBlur;

/**
 * A unit test for GaussianBlur class.
 * 
 * @version 0.1 rev 8000 Dec. 21, 2012.
 * Copyright (c) HyperCube Dev Team.
 */
public class BlurTest
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Blur Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		
		BufferedImage image = new BufferedImage(61, 34, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(
		        RenderingHints.KEY_TEXT_ANTIALIASING,
		        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setBackground(new Color(0, true));
		g.setFont(new Font("微软雅黑", Font.BOLD, 18));
		g.setColor(Color.BLACK);
		g.drawString("登录", 5, 17);
		GaussianBlur.getDefaultBlur().blur(image);
		g.setColor(Color.WHITE);
		g.drawString("登录", 5, 17);
		g.dispose();
		
		JLabel label = new JLabel(new ImageIcon(image));
		frame.add(label);
		
		frame.pack();
		frame.setVisible(true);
	}
}
