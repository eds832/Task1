package by.sardyka.triangle.observer;

import by.sardyka.triangle.observer.TrinangleEvent;
import by.sardyka.triangle.logic.Logic;

public class Square extends TriangleObserver {
	private double square;

	@Override
	public void valueChanged(TrinangleEvent event) {
		square = Logic.calculateSquare(event.getSource());
	}

	public double getSquare() {
		return square;
	}

	@Override
	public String toString() {
		return " [Square = " + square + "],";
	}

}
