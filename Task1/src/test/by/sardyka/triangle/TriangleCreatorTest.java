package test.by.sardyka.triangle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import by.sardyka.triangle.creator.TriangleCreator;
import by.sardyka.triangle.entity.Point;
import by.sardyka.triangle.entity.Triangle;
import by.sardyka.triangle.exception.WrongDataException;
import by.sardyka.triangle.performer.Performer;

@RunWith(Parameterized.class)
public class TriangleCreatorTest {
	private double[] m1;
	private double[] m2;
	private double[] m3;
	private Triangle tr1;
	private Triangle tr2;
	private ArrayList<double[]> parsDate;
	private ArrayList<Triangle> tr;

	public TriangleCreatorTest(double[] m1, double[] m2, double[] m3, Triangle tr1, Triangle tr2) {
		this.m1 = m1;
		this.m2 = m2;
		this.m3 = m3;
		this.tr1 = tr1;
		this.tr2 = tr2;
	}

	@Parameters
	public static Collection<Object[]> TriangleCreatorTestValue() {
		Object[][] obj = new Object[][] {
				{ new double[] { 1, 2, 3, 4, 5 }, new double[] { 6, .1, -.5e-22, 0.7E4, 9999.99, -9999.99 },
						new double[] { 0, 0, 1, 1, -1, 1 },
						new Triangle(new Point(6, 0.1), new Point(-0.5e-22, 0.7e4), new Point(9999.99, -9999.99)),
						new Triangle(new Point(0, 0), new Point(1, 1), new Point(-1, 1)) },
				{ new double[] { 7.574, 424.5, 0.56, 0.32, 4.0, 5.0 }, new double[] { 1, 1, 2, 2, 3, 3 },
						new double[] { 0.77, 0.77, 777.77, 777.77, 777.77, 0.77 },
						new Triangle(new Point(7.574, 424.5), new Point(0.56, 0.32), new Point(4.0, 5.0)),
						new Triangle(new Point(0.77, 0.77), new Point(777.77, 777.77), new Point(777.77, 0.77)) } };
		return Arrays.asList(obj);
	}

	@Before
	public void initTriangleCreator() {
		parsDate = new ArrayList<>();
		parsDate.add(m1);
		parsDate.add(m2);
		parsDate.add(m3);
		Performer instance = Performer.getInstance();
		tr = instance.getArrayTriangle();
		Performer.getInstance().getArrayTriangle();
		tr.clear();
	}

	@After
	public void closeTriangleCreator() {
		m1 = null;
		m2 = null;
		m3 = null;
		tr1 = null;
		tr2 = null;
		parsDate = null;
		tr.clear();
		tr = null;
	}

	@Test
	public void creatTriangleTest() throws WrongDataException, IndexOutOfBoundsException, NullPointerException {
		boolean expected = true;
		TriangleCreator.creatTriangle(parsDate);
		boolean actual = tr.get(0).toString().equals(tr1.toString())
				&& tr.get(1).toString().equals(tr2.toString());
		assertEquals("TriangleCreator works incorrectly", expected, actual);
	}

	@Test
	public void parsTriangleCreatorExeption1Test() {
		ArrayList<double[]> pars = null;
		try {
			TriangleCreator.creatTriangle(pars);
			fail("parsTriangleCreatorExeption1Test for pars should have thrown a WrongDataException");
		} catch (WrongDataException e) {
			assertEquals("This datafile doesn't contain doubles", e.getMessage());
		}
	}

	@Test
	public void parsTriangleCreatorExeption2Test() {
		ArrayList<double[]> pars = new ArrayList<>();
		try {
			TriangleCreator.creatTriangle(pars);
			fail("parsTriangleCreatorExeption2Test for pars should have thrown a WrongDataException");
		} catch (WrongDataException e) {
			assertEquals("This datafile doesn't contain doubles", e.getMessage());
		}
	}

}
