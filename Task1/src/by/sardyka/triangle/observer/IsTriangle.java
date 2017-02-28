package by.sardyka.triangle.observer;

import by.sardyka.triangle.logic.Logic;

public class IsTriangle extends TriangleObserver {
	private boolean isTriangle;

	@Override
	public void valueChanged(TrinangleEvent event) {
		isTriangle = Logic.isTriangle(event.getSource());
	}

	public boolean getTriangle() {
		return isTriangle;
	}

	@Override
	public String toString() {
		return " [isTriangle = " + isTriangle + "]";
	}

}
