import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.unit.Charakter;

public class DataBeanFightTest {
	
	static ArrayList<Charakter> charakterPool = new ArrayList<Charakter>();
	static ArrayList<Charakter> choosenCharakter;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		for (int i=0;i<10;i++) {
			Charakter charakter = new Charakter();
			charakter.setName("Test"+i);
			charakterPool.add(charakter);
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void selectChoosenUnitTest() {
		
	}
}
