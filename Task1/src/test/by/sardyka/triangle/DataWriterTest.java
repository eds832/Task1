package test.by.sardyka.triangle;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import by.sardyka.triangle.datawriter.DataWriter;
import by.sardyka.triangle.entity.Point;
import by.sardyka.triangle.entity.Triangle;
import by.sardyka.triangle.observer.IsRectagonal;
import by.sardyka.triangle.observer.IsTriangle;
import by.sardyka.triangle.observer.Perimeter;
import by.sardyka.triangle.observer.Square;

@RunWith(Parameterized.class)
public class DataWriterTest {

	private String input;
	private Triangle tr1;
	private Triangle tr2;
	private Point p;
	private File file;
	private ArrayList<Triangle> tr;

	public DataWriterTest(String input, Triangle tr1, Triangle tr2, Point p) {
		this.input = input;
		this.tr1 = tr1;
		this.tr2 = tr2;
		this.p = p;
	}

	@Parameters
	public static Collection<Object[]> DataWriterTestValue() {
		Object[][] obj = new Object[][] {
				{ "Input is correct", new Triangle(new Point(1, 0), new Point(0, 0), new Point(1, 1)),
						new Triangle(new Point(0, 1), new Point(0, -1), new Point(-1, 0)), new Point(1, 0) },
				{ "Input is incorrect",
						new Triangle(new Point(1000.99, 0.99), new Point(0.99, 9999.99), new Point(1000, 100)),
						new Triangle(new Point(0.0001, 1.7), new Point(0.1e3, -12e-7), new Point(-777.77, .77)),
						new Point(5, 0) } };
		return Arrays.asList(obj);
	}

	@Rule
	public final TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void initDataWriter() throws IOException {
		file = folder.newFile("out.txt");
		tr2.addObserver(new Square());
		tr2.addObserver(new Perimeter());
		tr2.addObserver(new IsRectagonal());
		tr2.addObserver(new IsTriangle());
		tr2.setA(p);
		tr = new ArrayList<>();
		tr.add(tr1);
		tr.add(tr2);
	}

	@After
	public void closeTemporaryFolder() {
		tr = null;
		file.delete();
		folder.delete();
	}

	@Test
	public void writeStringDataTest() throws FileNotFoundException, NoSuchElementException {
		boolean expected = true;
		DataWriter.writeData(input, file.getAbsolutePath());
		Scanner sc = new Scanner(file);
		String s = sc.nextLine();
		sc.close();
		boolean actual = s.equals(input);
		assertEquals("DataWriter writes a string incorrectly", expected, actual);
	}

	@Test
	public void writeEmptyDataTest() throws FileNotFoundException {
		boolean expected = true;
		DataWriter.writeData("", file.getAbsolutePath());
		Scanner sc = new Scanner(file);
		boolean actual = !sc.hasNextLine();
		sc.close();
		assertEquals("DataWriter writes empty data incorrectly", expected, actual);
	}

	@Test
	public void writeTriangleDataTest() throws FileNotFoundException, NoSuchElementException {
		boolean expected = true;
		DataWriter.writeData(tr, file.getAbsolutePath());
		Scanner sc = new Scanner(file);
		String s = sc.nextLine() + "\n" + sc.nextLine() + "\n" + sc.nextLine() + "\n" + sc.nextLine() + "\n"
				+ sc.nextLine() + "\n" + sc.nextLine() + "\n";
		sc.close();
		String str = tr.get(0).toString() + "\n" + tr.get(1).toString() + "\n";
		boolean actual = s.equals(str);
		assertEquals("DataWriter writes triangles data incorrectly", expected, actual);
	}

}
