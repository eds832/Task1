package by.sardyka.triangle.logic;

import java.math.BigDecimal;

import by.sardyka.triangle.entity.Point;
import by.sardyka.triangle.entity.Triangle;

public class Logic {

	public static double calculatePerimeter(Triangle triangle) {
		BigDecimal ab = calculateSqSide(triangle.getA(), triangle.getB());
		BigDecimal bc = calculateSqSide(triangle.getB(), triangle.getC());
		BigDecimal ac = calculateSqSide(triangle.getA(), triangle.getC());
		Double abD = Math.sqrt(new Double(ab.toString()));
		Double bcD = Math.sqrt(new Double(bc.toString()));
		Double acD = Math.sqrt(new Double(ac.toString()));
		double perimeter = abD + bcD + acD;
		return perimeter;
	}

	public static double calculateSquare(Triangle triangle) {
		BigDecimal bx = new BigDecimal(triangle.getB().getX());
		BigDecimal ax = new BigDecimal(- triangle.getA().getX());
		BigDecimal cy = new BigDecimal(triangle.getC().getY());
		BigDecimal ay = new BigDecimal(- triangle.getA().getY());
		BigDecimal cx = new BigDecimal(triangle.getC().getX());
		BigDecimal by = new BigDecimal(triangle.getB().getY());
		BigDecimal square1 = bx.add(ax).multiply(cy.add(ay));
		BigDecimal square2 = cx.add(ax).multiply(by.add(ay));
		BigDecimal square = square1.add(square2.negate());
		square = (square.divide(new BigDecimal(2))).abs();
		return new Double(square.toString());
	}

	public static boolean isRectagonal(Triangle triangle) {
		BigDecimal ab = calculateSqSide(triangle.getA(), triangle.getB());
		BigDecimal bc = calculateSqSide(triangle.getB(), triangle.getC());
		BigDecimal ac = calculateSqSide(triangle.getA(), triangle.getC());
		boolean b1 = ab.compareTo(bc.add(ac)) == 0;
		boolean b2 = bc.compareTo(ab.add(ac)) == 0;
		boolean b3 = ac.compareTo(bc.add(ab)) == 0;
		boolean result = b1 || b2 || b3;
		return result;
	}

	public static boolean isTriangle(Triangle triangle) {
		double square = calculateSquare(triangle);
		return square != 0.0;
	}

	private static BigDecimal calculateSqSide(Point p1, Point p2) {
		BigDecimal x = new BigDecimal(p1.getX()).add(new BigDecimal(-p2.getX()));
		BigDecimal y = new BigDecimal(p1.getY()).add(new BigDecimal(-p2.getY()));
		BigDecimal sqSide = x.pow(2).add(y.pow(2));
		return sqSide;
	}
}
