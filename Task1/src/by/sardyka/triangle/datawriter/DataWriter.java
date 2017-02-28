package by.sardyka.triangle.datawriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import by.sardyka.triangle.entity.Triangle;

public class DataWriter {
	private static final Logger LOG = LogManager.getLogger(DataWriter.class);

	public static void writeData(ArrayList<Triangle> arrayTriangle, String fileName) {
		String str = "";
		for (int i = 0; i < arrayTriangle.size(); i++) {
			str += arrayTriangle.get(i).toString() + "\n";
		}
		writeData(str, fileName);
	}

	public static void writeData(String stringData, String fileName) {
		File output = new File(fileName);
		FileWriter fw = null;
		try {
			fw = new FileWriter(output);
			fw.write(stringData);
		} catch (IOException e) {
			LOG.log(Level.FATAL, "There is a input-output problem");
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					LOG.log(Level.DEBUG, "There is a input-output problem");
				}
			}
		}
	}
}
