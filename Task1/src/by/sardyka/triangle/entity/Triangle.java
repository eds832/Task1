package by.sardyka.triangle.entity;

import java.util.ArrayList;

import java.util.Iterator;
import by.sardyka.triangle.observer.TriangleObserver;
import by.sardyka.triangle.observer.TrinangleEvent;

public class Triangle {
	private Point a;
	private Point b;
	private Point c;
	private ArrayList<TriangleObserver> observerList = new ArrayList<TriangleObserver>();

	public Triangle(Point a, Point b, Point c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public Point getA() {
		return a;
	}

	public void setA(Point a) {
		this.a = a;
		notifyObservers();
	}

	public Point getB() {
		return b;
	}

	public void setB(Point b) {
		this.b = b;
		notifyObservers();
	}

	public Point getC() {
		return c;
	}

	public void setC(Point c) {
		this.c = c;
		notifyObservers();
	}

	public void addObserver(TriangleObserver triangleObserver) {
		observerList.add(triangleObserver);
	}

	private void notifyObservers() {
		Iterator<TriangleObserver> it = observerList.iterator();
		while (it.hasNext()) {
			((TriangleObserver) it.next()).valueChanged(new TrinangleEvent(this));
		}
	}

	@Override
	public String toString() {
		String str = "Triangle [a=" + a + ", b=" + b + ", c=" + c + ",\n";
		Iterator<TriangleObserver> it = observerList.iterator();
		while (it.hasNext()) {
			str += ((TriangleObserver) it.next()).toString();
		}
		return  str + "]\n";
	}

}
