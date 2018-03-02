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

    /* add new person to the FriendshipGraph */
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

    /* add directive direction between the two person : personA and personB */
    public void addEdge(Person personA, Person personB) {
        Graph.get(personA.getId()).set(personB.getId(), true);
    }

    /* get distance between the two person : personA and personB */
    public int getDistance(Person personA, Person personB) {
        if (personA == personB)
            return 0;
        Queue<Integer> BSQueue = new LinkedList<>();
        boolean[] visited = new boolean[this.personNum];
        for (int i = 1; i < this.personNum; i++) {
            visited[i] = false;
        }
        BSQueue.offer(personA.getId());
        visited[0] = true;
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

            if (BSQueue.poll() == rear) {
                count++;
                rear = front;
            }
        }
        return -1;
    }
}
