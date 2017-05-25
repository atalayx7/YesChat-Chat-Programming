package login_client;

public class Cipher {

	private String text1 = "57abcdejklmUVWno01234pq' )>r+-#?!/_:&,;@swxy<.zABfghiCDE89FGHIJKtuv(LMNOP6QRSTXYZ";
	private String text2;
	private char[] text = text1.toCharArray();
	private char[] ctext = text1.toCharArray();
	private String output;

	public Cipher() {
		ctext = text;

	}

	public Cipher(String message1, String key1) {
		int val = 0;
		char[] key = key1.toCharArray();
		for (int i = 0; i < key.length; i++) {
			val += text1.indexOf(key[i]);
		}
		while (val >= text.length) {
			val -= text.length;
		}
		for (int i = val; i < text.length; i++) {
			ctext[i - val] = text[i];
		}

		for (int i = 0; i < val; i++) {
			ctext[i + (text.length - val)] = text[i];
		}
		text2 = new String(ctext);

		int position;
		char[] message = message1.toCharArray();
		for (int i = 0; i < message.length; i++) {
			position = text1.indexOf(message[i]);
			message[i] = ctext[position];
		}
		output = new String(message);
	}

	public String getText() {
		return this.text1;
	}

	public String getCtext() {
		return this.text2;
	}

	public String getOutput() {
		return this.output;
	}

	public String translate(String message1, String key1) {

		Cipher c = new Cipher(message1, key1);
		int position;
		String ciphertext = c.getCtext();
		char[] message = message1.toCharArray();
		for (int i = 0; i < message.length; i++) {
			position = ciphertext.indexOf(message[i]);
			message[i] = text[position];
		}
		output = new String(message);
		return output;
	}
}