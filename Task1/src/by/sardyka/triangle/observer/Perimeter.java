package by.sardyka.triangle.observer;

import by.sardyka.triangle.logic.Logic;

public class Perimeter extends TriangleObserver {
	private double perimeter;

	@Override
	public void valueChanged(TrinangleEvent event) {
		perimeter = Logic.calculatePerimeter(event.getSource());
	}

	public double getPerimeter() {
		return perimeter;
	}

	@Override
	public String toString() {
		return " [Perimeter = " + perimeter + "],";
	}

}
