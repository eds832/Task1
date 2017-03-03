package test.by.sardyka.triangle;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.Collection;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import by.sardyka.triangle.entity.Point;
import by.sardyka.triangle.entity.Triangle;
import by.sardyka.triangle.logic.Logic;

@RunWith(Parameterized.class)
public class LogicTest {
	private Triangle tr;
	private double perimeter;
	private double square;
	private boolean isRectagonal;
	private boolean isTriangle;

	public LogicTest(Triangle tr, double perimeter, double square, boolean isRectagonal, boolean isTriangle) {
		this.tr = tr;
		this.perimeter = perimeter;
		this.square = square;
		this.isRectagonal = isRectagonal;
		this.isTriangle = isTriangle;
	}

	@Parameters
	public static Collection<Object[]> TriangleCreatorTestValue() {
		Object[][] obj = new Object[][] {
				{ new Triangle(new Point(0.1, 0.1), new Point(1.1, 1.1), new Point(-5.5, -5.5)), 18.667, 0, false,
						false },
				{ new Triangle(new Point(0, 0), new Point(1, 1), new Point(-1, 1)), 4.828, 1, true, true },
				{ new Triangle(new Point(7.574, 424.5), new Point(0.56, 0.32), new Point(4.0, 5.0)), 849.561, 713.176,
						false, true },
				{ new Triangle(new Point(0.77, 0.77), new Point(777.77, 777.77), new Point(777.77, 0.77)), 2652.843,
						301864.5, true, true } };
		return Arrays.asList(obj);
	}

	@After
	public void closeLogic() {
		tr = null;
	}

	@Test
	public void logicPerimeterTest() {
		double expected = perimeter;
		double actual = Logic.calculatePerimeter(tr);
		assertEquals("Logic.calculatePerimeter works incorrectly", expected, actual, 0.01);
	}

	@Test
	public void logicSquareTest() {
		double expected = square;
		double actual = Logic.calculateSquare(tr);
		assertEquals("Logic.calculateSquare works incorrectly", expected, actual, 0.01);
	}

	@Test
	public void logicIsRectagonalTest() {
		boolean expected = isRectagonal;
		boolean actual = Logic.isRectagonal(tr);
		assertEquals("Logic.isRectagonal works incorrectly", expected, actual);
	}

	@Test
	public void logicIsTriangleTest() {
		boolean expected = isTriangle;
		boolean actual = Logic.isTriangle(tr);
		assertEquals("Logic.isTriangle works incorrectly", expected, actual);
	}

}
