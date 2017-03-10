package by.sardyka.triangle.datareader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

public class DataReader {
	private static final Logger LOG = LogManager.getLogger(DataReader.class);

	public static ArrayList<String> readData(String source) {
		ArrayList<String> list = new ArrayList<String>();
		FileInputStream stream = null;
		Scanner sc = null;
		try {
			stream = new FileInputStream(source);
			sc = new Scanner(stream);
			while (sc.hasNextLine()) {
				list.add(sc.nextLine());
			}
		} catch (FileNotFoundException e) {
			LOG.log(Level.FATAL, "There isn't the file " + source);
			throw new RuntimeException();

		} catch (IOException e) {
			LOG.log(Level.FATAL, "There is a input-output problem with " + source);
			throw new RuntimeException();

		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					LOG.log(Level.INFO, "There is a input-output problem with " + source);
				}
			}
		}
		LOG.log(Level.INFO, "There are " + list.size() + " strings in this file");
		return list;
	}

}
