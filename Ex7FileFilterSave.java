package ex7;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class Ex7FileFilterSave extends FileFilter {

	@Override
	public boolean accept(File f) {
		String fileName = f.getName().toLowerCase();
		return f.isDirectory() || fileName.endsWith(".ex7")
				|| fileName.endsWith(".bmp") || fileName.endsWith(".png");
	}

	@Override
	public String getDescription() {
		return ".ex7, .bmp or .png files";
	}

}
