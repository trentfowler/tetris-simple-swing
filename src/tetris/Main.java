package tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

/**
 * Container class for main method which creates a Tetris object and 
 * starts the control flow. 
 * 
 * @author Trent Fowler
 *
 */
public class Main {
	public static void main(String[] args) {
		final Tetris game = new Tetris();
		new Thread() {
			@Override public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
						game.move(Tetris.Move.DOWN);
					} catch (Exception e) { }
				}
			}
		}.start();
		JFrame frame = new JFrame(); 
		frame.setContentPane(game);
		frame.setSize(407, 830);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.addKeyListener(new KeyListener() {
			@Override public void keyTyped(KeyEvent e) { }
			@Override public void keyReleased(KeyEvent e) { }
			@Override public void keyPressed(KeyEvent e) { 
				switch (e.getKeyCode()) {
				case KeyEvent.VK_DOWN:
					game.move(Tetris.Move.DOWN);
					break;
				case KeyEvent.VK_LEFT:
					game.move(Tetris.Move.LEFT);
					break;
				case KeyEvent.VK_RIGHT:
					game.move(Tetris.Move.RIGHT);
					break;
				case KeyEvent.VK_UP:
					game.move(Tetris.Move.ROTATE);
					break;
				case KeyEvent.VK_SPACE:
					game.move(Tetris.Move.DROP);
					break;
				}
			}
		});
	}

}
