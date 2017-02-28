package by.sardyka.triangle.observer;

import java.util.EventObject;
import by.sardyka.triangle.entity.Triangle;

public class TrinangleEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	public TrinangleEvent(Triangle source) {
		super(source);
	}

	@Override
	public Triangle getSource() {
		return (Triangle) super.getSource();
	}
}