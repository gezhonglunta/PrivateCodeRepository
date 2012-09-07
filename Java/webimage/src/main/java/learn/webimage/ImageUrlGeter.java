package learn.webimage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class ImageUrlGeter implements MyRunnable {
	protected String forumSaveFilePath;
	protected boolean isStop = false;

	public void run() {
		try {
			if (FileUtil.fileNameStartsWith(forumSaveFilePath, Constant.FILE_NAME_PREFIX)) {
				return;
			}
			FileReader fReader = new FileReader(forumSaveFilePath);
			BufferedReader bufferedReader = new BufferedReader(fReader);
			List<String> pageUrlList = new ArrayList<String>();
			while (!isStop) {
				String tmp = bufferedReader.readLine();
				if (tmp == null) {
					bufferedReader.close();
					fReader.close();
					break;
				}
				if (StringUtils.isBlank(tmp)) {
					continue;
				}
				for (String urlString : pageUrlList) {
					if (StringUtils.equals(urlString, tmp)) {
						continue;
					}
				}
				pageUrlList.add(tmp);
			}
			if (pageUrlList.size() > 1) {
				System.out.println("pageUrlList.size() > 1");
			}
			for (String pageUrl : pageUrlList) {
				byte[] data = HttpClientUtil.getResStream(pageUrl);
				String string;
				string = new String(data, "gbk");
				List<String> imgUrls = getImgUrls(string);
				FileOutputStream instream = FileUtil.createFile(forumSaveFilePath);
				if (instream == null) {
					System.out.println("create save image url file failed");
				}
				instream.write(("--------------------" + Constant.NEWLINE).getBytes());
				for (String string2 : imgUrls) {
					if (isStop) {
						break;
					}
					try {
						instream.write(string2.getBytes());
						instream.write(Constant.NEWLINE.getBytes());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				try {
					instream.flush();
					instream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			FileUtil.renameFileWithPrefix(forumSaveFilePath, Constant.FILE_NAME_PREFIX);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<String> getImgUrls(String html) {
		List<String> result = new ArrayList<String>();
		int index = 0;
		String startWith = "<img src=\"http://";
		String endWith = "\"";
		while (true) {
			index = html.indexOf(startWith, index);
			if (index < 0) {
				break;
			}
			int end = html.indexOf(endWith, index + startWith.length());
			if (end <= index) {
				break;
			}
			String tmp = html.substring(index + 10, end);
			index = end;
			result.add(tmp);
		}
		return result;
	}

	public void setStop(boolean isStop) {
		this.isStop = isStop;
	}

	public void setForumSaveFilePath(String forumSaveFilePath) {
		this.forumSaveFilePath = forumSaveFilePath;
	}
}
