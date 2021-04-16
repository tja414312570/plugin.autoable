package com.yanan.autoable;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;

import javax.swing.JFrame;

public class ImageOutput {
	static class Window extends JFrame {
		/**
		 * 
		 */
		private static final long serialVersionUID = -9007438353060180394L;
	}
	private Window window;
	private Container pane;
	public ImageOutput() {
		window = new Window();
		window.setSize(1920, 1080);
		window.setTitle("测试窗口");
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane = window.getContentPane();
		window.setVisible(true);
	}
	public void output(int[][] image) {
		Graphics graphics = pane.getGraphics();
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				
				for (int i = 0; i < image.length; i++) {
					int[] items = image[i];
					int x = items[0];
					int y = items[1];
					int color = items[2];
//					System.out.println(x+","+y+","+color);
					Color colors = new Color(color);
					graphics.setColor(colors);
					graphics.drawOval(x, y, 1, 1);
				}

			}
		});
		thread.start();
	}
}
