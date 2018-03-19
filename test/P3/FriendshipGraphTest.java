package P3;

import static org.junit.Assert.*;

import org.junit.Test;

public class FriendshipGraphTest {
	@Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }

	@Test
	  public void addVertexTest() throws RuntimeException{
   	 FriendshipGraph graph=new FriendshipGraph();
   	 Person tom=new Person("Tom");
   	 Person black=new Person("Black");
   	 Person tony=new Person("Tony");
   	 Person rachol=new Person("Rachol");
   	 Person ross=new Person("Ross");
   	 
   	 graph.addVertex(tom);
   	 graph.addVertex(black);
   	 graph.addVertex(tony);
   	 graph.addVertex(rachol);
   	 graph.addVertex(ross);
   
   	 
   	 try
        {
            graph.addVertex(ross);
            fail("Name repetition! ");
        }catch(Exception e1)
        {
            assertTrue(e1 instanceof Exception);
        }

    }
	@Test
	  public void addEdgeTest() throws RuntimeException{
 	 FriendshipGraph graph=new FriendshipGraph();
   	 Person tom=new Person("Tom");
   	 Person black=new Person("Black");
   	 Person tony=new Person("Tony");
   	 Person rachol=new Person("Rachol");
   	Person ross=new Person("Ross");
 	 
   	 graph.addVertex(tom);
   	 graph.addVertex(black);
   	 graph.addVertex(tony);
   	 graph.addVertex(rachol);
 	 graph.begin();
 	 graph.addEdge(tom, black);
 	 graph.addEdge(tom, tony);
 	 
 	 try
      {
          graph.addEdge(ross,tom);
          fail("Person don't exist.");
      }catch(Exception e1)
      {
          assertTrue(e1 instanceof Exception);
      }

  }
	@Test
	  public void getDistanceTest() throws RuntimeException{
	 FriendshipGraph graph=new FriendshipGraph();
   	 Person tom=new Person("Tom");
   	 Person black=new Person("Black");
   	 Person tony=new Person("Tony");
   	 Person rachol=new Person("Rachol");
	 
   	 graph.addVertex(tom);
   	 graph.addVertex(black);
   	 graph.addVertex(tony);
   	 graph.addVertex(rachol);
	 graph.begin();
	 
	 graph.addEdge(tom, black);
	 graph.addEdge(tom, tony);
	 graph.addEdge(black, rachol);
	 graph.Floyd();     
	 assertEquals(2,graph.getDistance(tom,rachol));
	 assertEquals(1,graph.getDistance(tom,black));
	 assertEquals(-1,graph.getDistance(tony,rachol));

 

}
}

