package learn.webimage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class ImageGeter implements MyRunnable {
	private String imgUrlPath = "D:\\Downloads\\imdl\\work\\forum-9\\imageUrl\\";
	private boolean isStop = false;

	public void run() {
		// TODO Auto-generated method stub
		List<String> imgList = FileUtil.refreshFileList(imgUrlPath, false, false, null);
		for (int i = 0; !isStop && i < imgList.size(); i++) {
			try {
				String path = imgList.get(i);
				String file = path + "\\" + Constant.FILE_NAME_PREFIX + UrlGeter.URL_FILE;
				if (!FileUtil.exists(file)) {
					continue;
				}
				FileReader fReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fReader);
				String line = null;
				String url = null;
				while (!isStop) {
					line = bufferedReader.readLine();
					if (line == null) {
						bufferedReader.close();
						fReader.close();
						break;
					}
					if (StringUtils.isBlank(line)) {
						continue;
					}
					if (StringUtils.startsWith(line, "---")) {
						url = "";
						continue;
					}
					if (url == null) {
						continue;

					} else {
						url = line;
					}
					url = StringUtils.lowerCase(url);
					int lastIndex = url.lastIndexOf('?');
					if (lastIndex > 0) {
						url = url.substring(0, lastIndex);
					}
					lastIndex = url.lastIndexOf('/');
					String picName = url.substring(lastIndex + 1, url.length());
					if (!StringUtils.contains(picName, '.')) {
						continue;
					}
					String picFullPath = path + "\\" + picName;
					if (FileUtil.exists(picFullPath)) {
						continue;
					}
					byte[] inStream = HttpClientUtil.getResStream(url);
					if (inStream == null) {
						continue;
					}
					FileOutputStream outStream = FileUtil.createFile(picFullPath);
					if (outStream != null) {
						try {
							outStream.write(inStream);
							outStream.flush();
							outStream.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				ProcessShower.Show(imgUrlPath, i, imgList.size());
				FileUtil.renameFileWithPrefix(file, Constant.FILE_NAME_PREFIX);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void setStop(boolean isStop) {
		this.isStop = isStop;
	}

	public void setImgUrlPath(String imgUrlPath) {
		this.imgUrlPath = imgUrlPath;
	}
}
