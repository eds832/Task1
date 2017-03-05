package by.sardyka.triangle.performer;

import java.util.ArrayList;
import java.util.Iterator;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import by.sardyka.triangle.creator.TriangleCreator;
import by.sardyka.triangle.datareader.DataReader;
import by.sardyka.triangle.datawriter.DataWriter;
import by.sardyka.triangle.exception.WrongDataException;
import by.sardyka.triangle.observer.IsRectagonal;
import by.sardyka.triangle.observer.IsTriangle;
import by.sardyka.triangle.observer.Perimeter;
import by.sardyka.triangle.observer.Square;
import by.sardyka.triangle.parser.DataParser;
import by.sardyka.triangle.entity.Point;
import by.sardyka.triangle.entity.Triangle;

public class Performer {
	private ArrayList<Triangle> arrayTriangle = new ArrayList<Triangle>();
	private static final Performer INSTANCE = new Performer();
	private static final String USER_DIR = "user.dir";
	private static final String INPUT = "\\data\\input1.txt";
	private static final String OUTPUT = "\\data\\output.txt";
	private static final String OUTPUT_CHANGED = "\\data\\output_changed.txt";
	private static final Logger LOG = LogManager.getLogger(Performer.class);

	private Performer() {
	}

	public ArrayList<Triangle> getArrayTriangle() {
		return arrayTriangle;
	}

	public static Performer getInstance() {
		return INSTANCE;
	}

	public void doAction() {
		LOG.log(Level.INFO, "Start");
		String sys = System.getProperty(USER_DIR);
		ArrayList<String> strList = DataReader.readData(sys + INPUT);
		ArrayList<double[]> parsedDate = null;
		try {
			parsedDate = DataParser.parseData(strList);
		} catch (WrongDataException e) {
			LOG.log(Level.WARN, e);
		}
		boolean b1 = parsedDate == null;
		boolean b2 = true;
		boolean b3 = true;
		if (!b1) {
			try {
				TriangleCreator.createTriangle(parsedDate);
			} catch (WrongDataException e) {
				LOG.log(Level.WARN, e);
			}
			b2 = arrayTriangle == null;
			if (!b2) {
				b3 = arrayTriangle.isEmpty();
			}
			if (!b2 && !b3) {
				DataWriter.writeData(arrayTriangle, sys + OUTPUT);
			}
		}
		if (b1 || b2 || b3) {
			DataWriter.writeData("Input is incorrect", sys + OUTPUT);
			DataWriter.writeData("Input is incorrect", sys + OUTPUT_CHANGED);
		}

		if (!b2 && !b3) {
			Point p1 = new Point(0, 1);
			Point p2 = new Point(1, 0);
			Iterator<Triangle> it = arrayTriangle.iterator();
			while (it.hasNext()) {
				Triangle t = it.next();
				t.addObserver(new Perimeter());
				t.addObserver(new Square());
				t.addObserver(new IsRectagonal());
				t.addObserver(new IsTriangle());
				t.setA(p1);
				t.setC(p2);
			}
			DataWriter.writeData(arrayTriangle, sys + OUTPUT_CHANGED);
		}
		LOG.log(Level.INFO, "Stop");
	}

}
