package test.by.sardyka.triangle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
import by.sardyka.triangle.exception.WrongDataException;
import by.sardyka.triangle.parser.DataParser;

@RunWith(Parameterized.class)
public class DataParserTest {
	private String s1;
	private String s2;
	private String s3;
	private String s4;
	private double[] m1;
	private double[] m2;
	private ArrayList<String> ar1;
	private ArrayList<double[]> ar2;

	public DataParserTest(String s1, String s2, String s3, String s4, double[] m1, double[] m2) {
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
		this.s4 = s4;
		this.m1 = m1;
		this.m2 = m2;
	}

	@Parameters
	public static Collection<Object[]> DataParserTestValue() {
		Object[][] obj = new Object[][] {
				{ "1 2 3 4   5.0   6", "6 .1 -.5e-22 0.7E4 9999.99   -9999.99", "2 10000.0001 3 4 5.0", "ab 2 3 4 5.0",
						new double[] { 1, 2, 3, 4, 5, 6 },
						new double[] { 6, 0.1, -0.5e-22, 0.7e4, 9999.99, -9999.99 } },
				{ "w ro ng str ing", "2e-12 4E-12 3e-12 6E-12 4e-12 -4e-12", "10000.001",
						"-.000001 .000001 0 0 -0.000001 0", new double[] { 2e-12, 4e-12, 3e-12, 6e-12, 4e-12, -4e-12 },
						new double[] { -0.000001, 0.000001, 0, 0, -0.000001, 0 } } };
		return Arrays.asList(obj);
	}

	@Before
	public void initDataParser() {
		ar1 = new ArrayList<>();
		ar2 = new ArrayList<>();
		ar1.add(s1);
		ar1.add(s2);
		ar1.add(s3);
		ar1.add(s4);
		ar2.add(m1);
		ar2.add(m2);
	}

	@After
	public void closeTemporaryFolder() {
		m1 = null;
		m2 = null;
		ar1 = null;
		ar2 = null;
	}

	@Test
	public void parseDataTest() throws WrongDataException, IndexOutOfBoundsException, NullPointerException {
		ArrayList<double[]> masList = DataParser.parseData(ar1);
		boolean b1 = ar2.size() == masList.size();
		boolean b2 = masList.get(0).length == ar2.get(0).length;
		boolean b3 = masList.get(0)[0] == ar2.get(0)[0];
		boolean b4 = masList.get(0)[1] == ar2.get(0)[1];
		boolean b5 = masList.get(0)[2] == ar2.get(0)[2];
		boolean b6 = masList.get(0)[3] == ar2.get(0)[3];
		boolean b7 = masList.get(0)[4] == ar2.get(0)[4];
		boolean b8 = masList.get(0)[5] == ar2.get(0)[5];
		boolean b9 = masList.get(1).length == ar2.get(1).length;
		boolean b10 = masList.get(1)[0] == ar2.get(1)[0];
		boolean b11 = masList.get(1)[1] == ar2.get(1)[1];
		boolean b12 = masList.get(1)[2] == ar2.get(1)[2];
		boolean b13 = masList.get(1)[3] == ar2.get(1)[3];
		boolean b14 = masList.get(1)[4] == ar2.get(1)[4];
		boolean b15 = masList.get(1)[5] == ar2.get(1)[5];
		boolean actual = b1 && b2 && b3 && b4 && b5 && b6 && b7 && b8 && b9 && b10 && b11 && b12 && b13 && b14 && b15;
		assertTrue("parseData works incorrectly", actual);
	}

	@Test
	public void parseDataException1Test() {
		ArrayList<String> strList = null;
		try {
			DataParser.parseData(strList);
			fail("parseDataExeptionTest for strList should have thrown a WrongDataException");
		} catch (WrongDataException e) {
			assertEquals("This data file is empty or doesn't exist", e.getMessage());
		}
	}

	@Test
	public void parseDataException2Test() {
		ArrayList<String> strList = new ArrayList<>();
		try {
			DataParser.parseData(strList);
			fail("parseDataExeptionTest for strList should have thrown a WrongDataException");
		} catch (WrongDataException e) {
			assertEquals("This data file is empty or doesn't exist", e.getMessage());
		}
	}
}
