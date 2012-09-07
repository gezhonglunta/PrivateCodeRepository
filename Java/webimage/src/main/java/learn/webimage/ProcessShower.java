package learn.webimage;

public class ProcessShower {
	public static void Show(String tile, float proc) {
		System.out.println(tile + " pricess:" + proc * 100 + "%");
	}

	public static void Show(String tile, int current, int total) {
		Show(tile, (float) current / (float) total);
	}
}
