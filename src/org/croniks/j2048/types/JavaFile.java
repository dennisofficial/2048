package org.croniks.j2048.types;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.croniks.j2048.core.Main;

@SuppressWarnings("serial")
public class JavaFile extends File {

	public JavaFile(String pathname) {
		super(getLocation() + pathname);
	}
	
	private static String getLocation() {
		String output = null;
		try {
			String temp = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).toString();
			List<String> list = new ArrayList<String>();
			String[] folders = temp.split("/");
			for (String folder : folders) {
				list.add(folder);
			}
			list.remove(list.size() - 1);
			output = "";
			for (String folder : list) {
				output = output + folder + "/";
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return output;
	}

}
