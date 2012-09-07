package learn.webimage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class UrlGeter implements MyRunnable {
	private String ahreflinkFilePath;
	private String ahreflinkBase;
	private boolean isStop = false;
	public final static String URL_DIR = "\\imageUrl";
	public final static String URL_FILE = "topicUrl.txt";

	public void run() {
		List<String> filelList = FileUtil.refreshFileList(ahreflinkFilePath, true, false, null);
		if (filelList == null || filelList.size() == 0) {
			return;
		}
		String imageSavePath = ahreflinkFilePath + URL_DIR;
		if (!FileUtil.createDir(imageSavePath)) {
			System.out.println("create save image dir failed");
			assert true;
			return;
		}
		for (String file : filelList) {
			if (isStop) {
				break;
			}
			FileReader fReader;
			try {
				fReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fReader);
				String line;
				while (!isStop) {
					try {
						line = bufferedReader.readLine();
						if (line == null) {
							bufferedReader.close();
							fReader.close();
							break;
						}
						if (StringUtils.isBlank(line)) {
							continue;
						}
						KeyValuePair<String, String> result = GetImageUrl(line);
						String key = result.getKey();
						String value = result.getValue();
						if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
							continue;
						}
						String imageUrlSavePath = imageSavePath + "\\" + value;
						if (!FileUtil.createDir(imageUrlSavePath)) {
							System.out.println("create " + imageUrlSavePath + " url dir failed");
							continue;
						}
						FileOutputStream fStream = FileUtil.createFile(imageUrlSavePath + "\\" + URL_FILE);
						String tmpString = ahreflinkBase + key + Constant.NEWLINE;
						fStream.write(tmpString.getBytes());
						fStream.flush();
						fStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		System.out.println("work done");
	}

	private KeyValuePair<String, String> GetImageUrl(String text) {
		KeyValuePair<String, String> result = new KeyValuePair<String, String>("", "");
		text = text.replace(" ", "");
		text = text.replace('【', '[');
		text = text.replace('】', ']');
		text = text.replace('(', '[');
		text = text.replace(')', ']');
		text = text.replace('（', '[');
		text = text.replace('）', ']');
		text = text.replace('p', 'P');
		text = replaceDigit(text);
		String tmp1 = substring(text, "\">", "</");
		if (tmp1 == null) {
			return result;
		}
		StringBuffer sbBuffer = new StringBuffer();
		for (int i = 0; i < tmp1.length(); i++) {
			char c = tmp1.charAt(i);
			if (isCharacter(c)) {
				sbBuffer.append(c);
			} else {
				if ((c == '[') && i > 0 && isCharacter(tmp1.charAt(i - 1))) {
					sbBuffer.append(c);
					i++;
					for (; i < tmp1.length(); i++) {
						c = tmp1.charAt(i);
						if (c == ']' || c == '<') {
							break;
						}
						if (isCharacter(c) || Character.isDigit(c)) {
							sbBuffer.append(c);
						}
					}
					sbBuffer.append(']');
					break;
				}
			}
		}
		String tmp2 = substring(text, "=\"", "\">");
		if (tmp2 == null) {
			return result;
		}
		int len = tmp2.indexOf('"');
		if (len > 0) {
			tmp2 = tmp2.substring(0, len);
		}
		result.setKey(tmp2);
		result.setValue(sbBuffer.toString());
		return result;
	}

	private String substring(String sourceString, String startWith, String endWith) {
		int startIndex = sourceString.indexOf(startWith);
		int endIndex = sourceString.lastIndexOf(endWith);
		if (startIndex < 0 || endIndex < 0 || startIndex > endIndex) {
			return null;
		}
		return sourceString.substring(startIndex + startWith.length(), endIndex);
	}

	private boolean isChineseCharacter(char c) {
		char c1 = '\u4e00';
		char c2 = '\u9fa5';
		if (c >= c1 && c <= c2) {
			return true;
		}
		return false;
	}

	private boolean isCharacter(char c) {
		char c1 = 'a';
		char c2 = 'z';
		char c3 = 'A';
		char c4 = 'Z';
		if (isChineseCharacter(c)) {
			return true;
		} else {
			if ((c >= c1 && c <= c2) || (c >= c3 && c <= c4)) {
				return true;
			}
		}

		return false;
	}

	private String replaceDigit(String text) {
		char[] upDigit = new char[] { '０', '１', '２', '３', '４', '５', '６', '７', '８', '９' };
		char[] lowDigit = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		for (int i = 0; i < upDigit.length; i++) {
			text = text.replace(upDigit[i], lowDigit[i]);
		}
		return text;
	}

	public void setAhreflinkFilePath(String ahreflinkFilePath) {
		this.ahreflinkFilePath = ahreflinkFilePath;
	}

	public void setAhreflinkBase(String ahreflinkBase) {
		this.ahreflinkBase = ahreflinkBase;
	}

	public void setStop(boolean isStop) {
		this.isStop = isStop;
	}
}
