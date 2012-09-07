package learn.webimage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;

import org.apache.commons.lang.StringUtils;

public class FileUtil {

	public static FileOutputStream createFile(String filePath) {
		File dir = new File(filePath);
		if (!(dir.exists())) {
			try {
				dir.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			return new FileOutputStream(filePath, true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean exists(String file) {
		File f = new File(file);
		return f.exists();
	}

	public static boolean createDir(String path) {
		File dir = new File(path);
		if (dir.exists()) {
			return true;
		} else {
			return dir.mkdir();
		}
	}

	public static boolean renameFile(String filePath, String destPath) {
		File file = new File(filePath);
		if (file == null || !file.exists()) {
			return false;
		}
		return file.renameTo(new File(destPath));
	}

	public static boolean fileNameStartsWith(String filePath, String prefix) {
		int lastIndex = filePath.lastIndexOf('\\') + 1;
		String name = filePath.substring(lastIndex, filePath.length());
		return StringUtils.startsWith(name, prefix);
	}

	public static boolean renameFileWithPrefix(String filePath, String prefix) {
		int lastIndex = filePath.lastIndexOf('\\') + 1;
		String path = filePath.substring(0, lastIndex);
		String name = filePath.substring(lastIndex, filePath.length());
		if (StringUtils.startsWith(name, prefix)) {
			return true;
		}
		name = prefix + name;
		return renameFile(filePath, path + name);
	}

	public static List<String> refreshFileList(String path, Boolean isRefreshFile, Boolean isGetChild, String filter) {
		File dir = new File(path);
		if (dir == null || !dir.exists()) {
			return null;
		}
		Vector<String> result = new Vector<String>();
		Queue<File> fileQueue = new LinkedList<File>();
		fileQueue.offer(dir);
		while (!fileQueue.isEmpty()) {
			File file = fileQueue.poll();
			if (file.isDirectory()) {
				File[] files = file.listFiles();
				for (File f : files) {
					if (f.isDirectory()) {
						if (!isRefreshFile) {
							result.add(f.getAbsolutePath());
						}
						if (isGetChild) {
							fileQueue.offer(f);
						}
					} else if (f.isFile() && isRefreshFile) {
						String tmp1 = f.getAbsolutePath();
						result.add(tmp1);
					}
				}
			} else if (file.isFile() && isRefreshFile) {
				String tmp1 = file.getAbsolutePath();
				result.add(tmp1);
			}
		}
		return result;
	}

	public static FileInputStream openFile(String filePath) {
		File dir = new File(filePath);
		if (dir.exists()) {
			try {
				FileInputStream fStream = new FileInputStream(dir);
				return fStream;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public static byte[] readFromInputStream(InputStream stream) {
		List<Byte> result = new ArrayList<Byte>();
		byte[] buff;
		while (true) {
			try {
				int size = stream.available();
				if (size < 1) {
					size = 8096;
				}
				buff = new byte[size];
				int len = stream.read(buff);
				if (len < 1) {
					break;
				}
				for (int i = 0; i < len; i++) {
					result.add(buff[i]);
				}
			} catch (IOException e) {
				System.out.println("readFromInputStream IOException");
			}
		}
		buff = new byte[result.size()];
		for (int i = 0; i < buff.length; i++) {
			buff[i] = result.get(i);
		}
		return buff;
	}
}
