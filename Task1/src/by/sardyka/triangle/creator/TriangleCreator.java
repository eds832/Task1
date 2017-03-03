package by.sardyka.triangle.creator;

import java.util.ArrayList;

import by.sardyka.triangle.entity.Point;
import by.sardyka.triangle.entity.Triangle;
import by.sardyka.triangle.exception.WrongDataException;
import by.sardyka.triangle.logic.Logic;
import by.sardyka.triangle.performer.Performer;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

public class TriangleCreator {

	private static final int SIZE = 6;
	private static final Logger LOG = LogManager.getLogger(TriangleCreator.class);

	public static void creatTriangle(ArrayList<double[]> parsDate) throws WrongDataException {
		Performer performer = Performer.getInstance();
		ArrayList<Triangle> arrayTriangle = performer.getArrayTriangle();
		if ((parsDate == null) || (parsDate.isEmpty())) {
			throw new WrongDataException("This datafile doesn't contain doubles");
		}
		for (double[] d : parsDate) {
			if (d.length != SIZE) {
				LOG.log(Level.DEBUG, "The number of doubles is wrong n = " + d.length);
				continue;
			}
			Point p1 = new Point(d[0], d[1]);
			Point p2 = new Point(d[2], d[3]);
			Point p3 = new Point(d[4], d[5]);
			Triangle triangle = new Triangle(p1, p2, p3);
			boolean b = Logic.isTriangle(triangle);
			if (!b) {
				LOG.log(Level.DEBUG, "It isn't a triangle:\n\t" + p1 + " "+ p2 + " "+ p3);
			} else {
				arrayTriangle.add(triangle);
			}
		}
		LOG.log(Level.INFO, "There are " + arrayTriangle.size() + " triangles");
	}

}
