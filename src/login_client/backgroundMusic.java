package login_client;

import java.io.*;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.*;

public class backgroundMusic {
	private static Thread listenMusic;

	public static void runMusic() {
		listen();
	}

	public static void listen() {

		listenMusic = new Thread("Listen") {
			public void run() {

				while (true) {
					try {
						File file = new File(
								"C:\\Users\\AtalaySamet\\Documents\\Javax\\ChatProgramming\\src\\login_client\\intro.mp3");

						FileInputStream fis = new FileInputStream(file);

						BufferedInputStream bis = new BufferedInputStream(fis);

						try {

							Player player = new Player(bis);
							player.play();

						} catch (JavaLayerException ex) {
						}
					} catch (IOException e) {
					}

				}
			}
		};
		listenMusic.start();

	}

	@SuppressWarnings("deprecation")
	public static void notListen() {
		listenMusic.stop();
	}
}
