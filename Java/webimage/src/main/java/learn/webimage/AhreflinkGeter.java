package learn.webimage;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AhreflinkGeter implements MyRunnable {
	private String urlBase;
	private String urlFormat;
	private String urlExp;
	private String savePath;
	private int startIndex;
	private int endIndex;
	private boolean isStop = false;

	public void run() {
		// TODO Auto-generated method stub
		String filePath = this.savePath + this.urlFormat.substring(0, this.urlFormat.lastIndexOf("-")) + "\\";
		FileUtil.createDir(filePath);
		for (int i = this.startIndex; (!(this.isStop)) && (i <= this.endIndex); ++i) {
			String url = String.format(this.urlFormat, new Object[] { Integer.valueOf(i) });
			try {
				byte[] data = HttpClientUtil.getResStream(this.urlBase + url + this.urlExp);
				if (data == null) {
					continue;
				}
				String string = new String(data, "gbk");
				List<String> aHrefLinks = GetAhrefLinks(string);
				if (aHrefLinks.size() > 0) {
					FileOutputStream fs = FileUtil.createFile(filePath + url + "-" + DateUtil.getToday("yyyyMMdd")
							+ ".txt");
					if (fs != null) {
						PrintStream ps = new PrintStream(fs);
						System.out.println(url);
						for (String string2 : aHrefLinks) {
							String str = string2;
							ps.println(str);
						}
						ps.flush();
						ps.close();
						fs.close();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void setUrlBase(String urlBase) {
		this.urlBase = urlBase;
	}

	public void setUrlFormat(String urlFormat) {
		this.urlFormat = urlFormat;
	}

	public void setUrlExp(String urlExp) {
		this.urlExp = urlExp;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public void setStop(boolean isStop) {
		this.isStop = isStop;
	}

	private Vector<String> GetAhrefLinks(String html) {
		Pattern pattern = Pattern
				.compile("(<a\\s+([^>h]|h(?!ref\\s))*href[\\s+]?=[\\s+]?('|\"))([^(\\s+|'|\")]*)([^>]*>)(.*?)</a>");
		Matcher matcher = pattern.matcher(html);
		Vector<String> result = new Vector<String>();
		while (matcher.find()) {
			String groupString = matcher.group();
			Pattern pattern2 = Pattern.compile("\\[.*\\]");
			Matcher matcher2 = pattern2.matcher(groupString);
			if (matcher2.find()) {
				result.add(groupString);
			}
		}

		return result;
	}
}
