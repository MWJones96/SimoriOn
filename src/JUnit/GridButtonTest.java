package JUnit;


import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GridButtonTest {

public SimoriOn test;
	@Before
	public void setUp() throws Exception
	{
		test = SimoriOn.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testGdridButtonConstructor(int x, int y){
		GridButton gb = new GridButton(x,y);
		GridButton egb = new GridButton(x,y);
		
		assertEquals(gb,egb);
	}
	
	@Test
	public void testValidButton(){
		assertEquals((3,4),(3,4));
	}
	
}
