package tafat.natives.userinterface;

public class Values {

	public static String values(tafat.UserInterface $) {
		return $.heatmap() != null ? $.heatmap().values() : null;
	}
}