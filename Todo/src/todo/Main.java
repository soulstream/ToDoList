package todo;

import java.awt.EventQueue;

import ui.FrameUi;

public class Main {
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameUi frame = new FrameUi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
