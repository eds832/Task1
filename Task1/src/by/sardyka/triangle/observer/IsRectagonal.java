package by.sardyka.triangle.observer;

import by.sardyka.triangle.logic.Logic;

public class IsRectagonal extends TriangleObserver {
	private boolean isRectagonal;

	@Override
	public void valueChanged(TrinangleEvent event) {
		isRectagonal = Logic.isRectagonal(event.getSource());
	}

	public boolean getRectagonal() {
		return isRectagonal;
	}

	@Override
	public String toString() {
		return " [isRectagonal = " + isRectagonal + "], ";
	}

}
