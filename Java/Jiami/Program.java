import java.io.IOException;

public class Program {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		byte[] readBuff = new byte[1024];
		while (true) {
			try {
				System.out.println("输入：");
				int len = System.in.read(readBuff);
				String line = new String(readBuff, 0, len);
				line.replace("\n", "");
				line.replace("\r", "");
				String miwen = EncryptionHelper.getLXZKimageKey(line.replace(
						"-", ""));
				System.out.println("密文:" + miwen);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
