package learn.webimage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

public class App {
	private static final String URL_BASE = "http://f1.avzcf.info/bbs/";
	private static final String URL = "forum-13-%s";
	private static final String URL_EXP = ".html";
	private static final String SAVE_PATH = "D:\\Downloads\\imdl\\work\\";

	public static void main(String[] args) {
		List<KeyValuePair<Thread, MyRunnable>> threads = new ArrayList<KeyValuePair<Thread, MyRunnable>>();
		//		String[] forums = new String[] { "forum-13-%s", "forum-10-%s", "forum-9-%s" };
		//		int[] pages = new int[] { 200, 107, 242 };
		//
		//		for (int i = 0; i < forums.length; i++) {
		//			AhreflinkGeter aGeter = new AhreflinkGeter();
		//			aGeter.setEndIndex(pages[i]);
		//			aGeter.setSavePath(SAVE_PATH);
		//			aGeter.setStartIndex(0);
		//			aGeter.setUrlBase(URL_BASE);
		//			aGeter.setUrlExp(URL_EXP);
		//			aGeter.setUrlFormat(forums[i]);
		//			Thread thread = new Thread(aGeter);
		//			threads.add(new KeyValuePair<Thread, MyRunnable>(thread, aGeter));
		//			thread.start();
		//		}
		try {

			List<String> forumDirList = FileUtil.refreshFileList(SAVE_PATH, false, false, "");
			for (String forumDir : forumDirList) {
				//				UrlGeter urlGeter = new UrlGeter();
				//				urlGeter.setAhreflinkBase(URL_BASE);
				//				urlGeter.setAhreflinkFilePath(forumDir);
				//				Thread thread = new Thread(urlGeter);
				//				threads.add(new KeyValuePair<Thread, MyRunnable>(thread, urlGeter));
				//				thread.start();

				//				List<String> urlDirList = FileUtil.refreshFileList(forumDir + UrlGeter.URL_DIR, false, "");
				//				for (String urlDir : urlDirList) {
				//					String forumFile = urlDir + "\\" + UrlGeter.URL_FILE;
				//					ImageUrlGeter imageUrlGeter = new ImageUrlGeter();
				//					imageUrlGeter.setForumSaveFilePath(forumFile);
				//					imageUrlGeter.run();
				//					System.out.println(forumFile);
				//				}
				//				System.out.println(forumDir);
				//				App2 app2 = new App2();
				//				app2.setForumSaveFilePath(forumDir + UrlGeter.URL_DIR);
				//				Thread thread = new Thread(app2);
				//				threads.add(new KeyValuePair<Thread, MyRunnable>(thread, app2));
				//				thread.start();
			}
			String[] imgPath = new String[] { "D:\\Downloads\\imdl\\work\\forum-9\\imageUrl\\",
					"D:\\Downloads\\imdl\\work\\forum-10\\imageUrl\\",
					"D:\\Downloads\\imdl\\work\\forum-13\\imageUrl\\" };
			for (int i = 0; i < imgPath.length; i++) {
				ImageGeter imageGeter = new ImageGeter();
				imageGeter.setImgUrlPath(imgPath[i]);
				Thread thread = new Thread(imageGeter);
				threads.add(new KeyValuePair<Thread, MyRunnable>(thread, imageGeter));
				thread.start();
			}

			String readString = "";
			do {
				byte[] readBuff = new byte[10];
				int len = System.in.read(readBuff);
				readString = new String(readBuff, 0, len);
			} while (!(readString.equalsIgnoreCase("exit\r\n")));
			System.out.println("begin exit...");
			for (KeyValuePair<Thread, MyRunnable> thread : threads) {
				thread.getValue().setStop(true);
				try {
					thread.getKey().join();
					System.out.println("one thread exited");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class App2 extends ImageUrlGeter {

	@Override
	public void run() {
		if (StringUtils.isBlank(forumSaveFilePath)) {
			return;
		}
		List<String> urlDirList = FileUtil.refreshFileList(forumSaveFilePath, false, false, "");
		System.out.println("urlDirList:" + urlDirList.size());
		for (String urlDir : urlDirList) {
			if (isStop) {
				break;
			}
			List<String> urlDirList2 = FileUtil.refreshFileList(urlDir + "\\", true, false, "");
			if (urlDirList2 == null || urlDirList2.size() == 0) {
				continue;
			}
			String forumFile = urlDirList2.get(0);
			ImageUrlGeter imageUrlGeter = new ImageUrlGeter();
			imageUrlGeter.setForumSaveFilePath(forumFile);
			imageUrlGeter.run();
			System.out.println(forumFile);
		}
		System.out.println("work done");
	}

}