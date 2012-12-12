import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionHelper {
	private static final String KEY = "8qdgjgtd";
	// 算法名称
	public static final String KEY_ALGORITHM = "DES";
	// 算法名称/加密模式/填充方式
	public static final String CIPHER_ALGORITHM = "DES/CBC/PKCS5Padding";
	private static final byte[] iv = new byte[] { 8, 7, 6, 5, 4, 3, 2, 1 };

	public static String getLXZKimageKey(String strMing) {
		String tmp = encryptStr(strMing);
		String key1 = "";
		String key2 = "";
		for (int i = 0; i < tmp.length(); i++) {
			if (Character.isDigit(tmp.charAt(i))) {
				key1 += tmp.charAt(i);
			} else {
				key2 += tmp.charAt(i);
			}
		}
		if (key2.length() + key1.length() <= strMing.length()) {
			return key1 + key2;
		}
		if (key2.length() >= strMing.length()) {
			return key2.substring(0, strMing.length());
		} else {
			return key2 + key1.substring(0, strMing.length() - key2.length());
		}
	}

	/**
	 * 加密 String 明文输入 ,String 密文输出
	 */
	private static String encryptStr(String strMing) {
		byte[] byteMi = null;
		byte[] byteMing = null;
		String strMi = "";
		try {
			byteMing = strMing.getBytes("UTF8");
			byteMi = encryptByte(byteMing);
			strMi = byte2HexStr(byteMi);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			byteMing = null;
			byteMi = null;
		}
		return strMi;
	}

	/**
	 * 加密以 byte[] 明文输入 ,byte[] 密文输出
	 * 
	 * @param byteS
	 * @return
	 */
	private static byte[] encryptByte(byte[] byteS) {
		byte[] byteFina = null;
		Cipher cipher;
		try {
			IvParameterSpec zeroIv = new IvParameterSpec(iv);
			SecretKeySpec key = new SecretKeySpec(KEY.getBytes(), KEY_ALGORITHM);
			cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
			return cipher.doFinal(byteS);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * bytes转换成十六进制字符串
	 * 
	 * @param byte[] b byte数组
	 * @return String 每个Byte值之间空格分隔
	 */
	public static String byte2HexStr(byte[] b) {
		String stmp = "";
		StringBuilder sb = new StringBuilder("");
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0xFF);
			sb.append((stmp.length() == 1) ? "0" + stmp : stmp);
		}
		return sb.toString().toUpperCase().trim();
	}
}
