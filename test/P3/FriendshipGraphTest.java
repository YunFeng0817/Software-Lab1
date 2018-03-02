package test.P3;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import P3.FriendshipGraph;
import P3.Person;

/**
 * FriendshipGraph Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>ÈýÔÂ 2, 2018</pre>
 */
public class FriendshipGraphTest {

    private FriendshipGraph graphTest = new FriendshipGraph(),graphTest1 = new FriendshipGraph();
    private Person[] persons = new Person[1000],persons1 = new Person[20];

    @Before
    public void before() throws Exception {
        /*
         * test case one
         */
        for (int i = 0; i < 1000; i++) {
            persons[i] = new Person(String.valueOf(i));
            graphTest.addVertex(persons[i]);
        }
        for (int i = 0; i < 999; i++) {
            graphTest.addEdge(persons[i], persons[i + 1]);
            graphTest.addEdge(persons[i + 1], persons[i]);
        }
        /*
         * end test case one
         */
        
        /*
         * test case two
         */
        for(int i=0;i<14;i++){
            persons1[i] = new Person(String.valueOf((int)'a'+i));
            graphTest1 .addVertex(persons1[i]);
        }
        graphTest1.addEdge(persons1[0], persons1[1]);
        graphTest1.addEdge(persons1[1], persons1[0]);

        graphTest1.addEdge(persons1[1], persons1[2]);
        graphTest1.addEdge(persons1[2], persons1[1]);

        graphTest1.addEdge(persons1[2], persons1[3]);
        graphTest1.addEdge(persons1[3], persons1[2]);

        graphTest1.addEdge(persons1[0], persons1[4]);
        graphTest1.addEdge(persons1[4], persons1[0]);

        graphTest1.addEdge(persons1[0], persons1[7]);
        graphTest1.addEdge(persons1[7], persons1[0]);

        graphTest1.addEdge(persons1[7], persons1[1]);
        graphTest1.addEdge(persons1[1], persons1[7]);

        graphTest1.addEdge(persons1[3], persons1[7]);
        graphTest1.addEdge(persons1[7], persons1[3]);

        graphTest1.addEdge(persons1[4], persons1[5]);
        graphTest1.addEdge(persons1[5], persons1[4]);

        graphTest1.addEdge(persons1[2], persons1[5]);
        graphTest1.addEdge(persons1[5], persons1[2]);

        graphTest1.addEdge(persons1[2], persons1[6]);
        graphTest1.addEdge(persons1[6], persons1[2]);

        graphTest1.addEdge(persons1[3], persons1[6]);
        graphTest1.addEdge(persons1[6], persons1[3]);

        graphTest1.addEdge(persons1[2], persons1[8]);
        graphTest1.addEdge(persons1[8], persons1[2]);

        graphTest1.addEdge(persons1[9], persons1[10]);
        graphTest1.addEdge(persons1[10], persons1[9]);

        graphTest1.addEdge(persons1[10], persons1[11]);
        graphTest1.addEdge(persons1[11], persons1[10]);

        graphTest1.addEdge(persons1[11], persons1[12]);
        graphTest1.addEdge(persons1[12], persons1[11]);

        graphTest1.addEdge(persons1[12], persons1[13]);
        graphTest1.addEdge(persons1[13], persons1[12]);

        graphTest1.addEdge(persons1[9], persons1[12]);
        graphTest1.addEdge(persons1[12], persons1[9]);

        /*
         * end test case two
         */
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: addVertex(Person newPerson)
     */
    @Test
    public void testAddVertex() throws Exception {
//TODO: Test goes here...
        for (int i = 0; i < 1000; i++) {
            persons[i] = new Person(String.valueOf(i));
            graphTest.addVertex(persons[i]);
        }
    }

    /**
     * Method: addEdge(Person personA, Person personB)
     */
    @Test
    public void testAddEdge() throws Exception {
//TODO: Test goes here...
        for (int i = 0; i < 999; i++) {
            graphTest.addEdge(persons[i], persons[i + 1]);
            graphTest.addEdge(persons[i + 1], persons[i]);
        }
    }

    /**
     * Method: getDistance(Person personA, Person personB)
     */
    @Test
    public void testGetDistance() throws Exception {
//TODO: Test goes here...
        /*
         * test case one
         */
        for (int i = 0; i < 1000; i++) {
            Assert.assertEquals(i, graphTest.getDistance(persons[0], persons[i]));
        }
        /*
         * end test case one
         */

        /*
         * test case two
         */
        Assert.assertEquals(2,graphTest1.getDistance(persons1[0],persons1[5]));
        Assert.assertEquals(3,graphTest1.getDistance(persons1[0],persons1[6]));
        Assert.assertEquals(2,graphTest1.getDistance(persons1[0],persons1[3]));
        Assert.assertEquals(-1,graphTest1.getDistance(persons1[0],persons1[9]));
        Assert.assertEquals(2,graphTest1.getDistance(persons1[9],persons1[13]));
        Assert.assertEquals(2,graphTest1.getDistance(persons1[2],persons1[7]));
        /*
         * end test case two
         */
    }
} 
