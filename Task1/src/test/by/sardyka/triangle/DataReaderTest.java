package test.by.sardyka.triangle;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import by.sardyka.triangle.datareader.DataReader;

@RunWith(Parameterized.class)
public class DataReaderTest {
	private String input;
	private static File file1;
	private static File file2;

	public DataReaderTest(String input) {
		this.input = input;
	}

	@Parameters
	public static Collection<Object[]> DataReaderTestValue() {
		Object[][] obj = new Object[][] { { "1 2 3 4   5.0\n  6  ab c\n  .1 -.5 0.7" },
				{ "111.111 99999.99\nthe htl e\n4E-24 777e3" } };
		return Arrays.asList(obj);
	}

	@Rule
	public final TemporaryFolder folder = new TemporaryFolder();

	@Before
	public void initDataReader() throws IOException {
		file1 = folder.newFile("inp.txt");
		FileWriter fw = new FileWriter(file1);
		fw.write(input);
		fw.flush();
		fw.close();
		file2 = folder.newFile("empty.txt");
	}

	@After
	public void closeTemporaryFolder() {
		file1.delete();
		file2.delete();
		folder.delete();
	}

	@Test
	public void readDataTest()  throws IndexOutOfBoundsException, NullPointerException{
		ArrayList<String> list = DataReader.readData(file1.getAbsolutePath());
		String s = list.get(0) + "\n" + list.get(1) + "\n" + list.get(2);
		boolean actual = s.equals(input);
		assertTrue("readData reads incorrectly", actual);
	}

	@Test
	public void readEmptyDataTest() throws NullPointerException{
		ArrayList<String> list = DataReader.readData(file2.getAbsolutePath());
		boolean actual = list.size() == 0;
		assertTrue("readData reads empty file incorrectly", actual);
	}

}
