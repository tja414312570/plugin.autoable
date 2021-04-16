package plugin.boot;

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

	public static void output(int[][] image) {
		Window window = new Window();
		window.setSize(1920, 1080);
		window.setTitle("测试窗口");
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = window.getContentPane();
		window.setVisible(true);
		Graphics graphics = pane.getGraphics();
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < image.length; i++) {
					for (int j = 0; j < image[i].length; j++) {
						try {
							if (j > 1000)
								Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						graphics.setColor(new Color(image[i][j]));
						graphics.drawOval(i, j, 1, 1);
					}
				}

			}
		});
		thread.start();
	}
}
