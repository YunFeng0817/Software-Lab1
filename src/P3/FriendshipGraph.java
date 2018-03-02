package P3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashSet;

public class FriendshipGraph {

    private final int NOT_IN_GRAPH = -1;
    private ArrayList<ArrayList> Graph = new ArrayList<>();
    private int personNum = 0;
    private HashSet nameSet = new HashSet();

    public static void main(String[] argv) {
        FriendshipGraph graph = new FriendshipGraph();
        Person rachel = new Person("Rachel");
        Person ross = new Person("Ross");
        Person ben = new Person("Ben");
        Person kramer = new Person("Kramer");
        graph.addVertex(rachel);
        graph.addVertex(ross);
        graph.addVertex(ben);
        graph.addVertex(kramer);
        graph.addEdge(rachel, ross);
        graph.addEdge(ross, rachel);
        graph.addEdge(ross, ben);
        graph.addEdge(ben, ross);
        System.out.println(graph.getDistance(rachel, ross));
        System.out.println(graph.getDistance(rachel, ben));
        System.out.println(graph.getDistance(rachel, rachel));
        System.out.println(graph.getDistance(rachel, kramer));
    }

    /**
     * add new person to the FriendshipGraph
     *
     * @param newPerson the person of the new one
     */
    public void addVertex(Person newPerson) {
        if (newPerson.getId() == NOT_IN_GRAPH) {
            if (this.nameSet.contains(newPerson.getName())) {
                System.out.println("The new person's name '" + newPerson.getName() + "' had been in the friendship graph");
                System.exit(0);
            }
            ArrayList<Boolean> add = new ArrayList<Boolean>();
            for (int i = 0; i < this.personNum; i++) {
                add.add(false);
                Graph.get(i).add(false);
            }
            add.add(true);
            Graph.add(add);
            this.personNum++;
            this.nameSet.add(newPerson.getName());
            newPerson.setId(this.personNum - 1);
        } else {
            System.out.println("this person has been in the friendship Graph");
        }
    }

    /**
     * add directive direction between the two person
     *
     * @param personA the first person
     * @param personB the second person
     */
    public void addEdge(Person personA, Person personB) {
        Graph.get(personA.getId()).set(personB.getId(), true);
    }

    /**
     * get distance between the two person
     *
     * @param personA the first person
     * @param personB the second person
     * @return the value of the distance
     */
    public int getDistance(Person personA, Person personB) {
        if (personA == personB)
            return 0;
        Queue<Integer> BSQueue = new LinkedList<>();  // record the persons' id to be visit
        boolean[] visited = new boolean[this.personNum]; // record every person if has been visited
        for (int i = 1; i < this.personNum; i++) {
            visited[i] = false;
        }
        BSQueue.offer(personA.getId());
        visited[0] = true;
        /* record the begin(front) id and the end(rear) id of each BS floor */
        int count = 1, front = BSQueue.element(), rear = BSQueue.element();
        while (!BSQueue.isEmpty()) {
            for (int i = 0; i < this.personNum; i++) {
                if (Graph.get(BSQueue.element()).get(i).equals(true)) {
                    if (i == personB.getId()) {
                        return count;
                    }
                    if (!visited[i]) {
                        front = i;
                        visited[i] = true;
                        BSQueue.offer(i);
                    }
                }
            }

            /* judge if one BS floor has been visited */
            if (BSQueue.poll() == rear) {
                count++;
                rear = front;
            }
        }
        return -1;
    }
}
