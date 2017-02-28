package by.sardyka.triangle.parser;

import java.util.ArrayList;
import by.sardyka.triangle.exception.WrongDataException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

public class DataParser {
	private final static double MAX_VALUE = 10000.0;
	private final static String REG = "\\s+";
	private static final Logger LOG = LogManager.getLogger(DataParser.class);

	public static ArrayList<double[]> parsData(ArrayList<String> strList) throws WrongDataException {
		ArrayList<double[]> masList = new ArrayList<double[]>();
		if ((strList == null) || (strList.isEmpty())) {
			throw new WrongDataException("This datafile is empty or doesn't exist");
		}
		for (String s : strList) {
			String strMas[] = s.split(REG);
			double[] doubleMas = new double[strMas.length];
			boolean b = true;

			for (int i = 0; i < doubleMas.length; i++) {
				Double d = null;
				try {
					d = Double.parseDouble(strMas[i]);
				} catch (NumberFormatException e) {
					b = false;
					LOG.log(Level.DEBUG, "This datafile contain the wrong String = " + strMas[i]);
				}
				if (d != null) {
					if (Math.abs(d) > MAX_VALUE) {
						b = false;
						LOG.log(Level.DEBUG, "This datafile contain the wrong double d = " + d);
					}
					doubleMas[i] = d;
				}
			}

			if (b) {
				masList.add(doubleMas);
			}

		}
		LOG.log(Level.INFO, "There are " + masList.size() + " arrays of doubles");
		return masList;
	}

}
