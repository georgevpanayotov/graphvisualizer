import java.io.InputStream;
import java.util.Scanner;

class GraphParser
{
    private InputStream stream;

    public GraphParser(InputStream is)
    {
        this.stream = is;
    }

    // Parses a graph file of the form:
    // <VERTEX> -> <VERTEX>
    // <VERTEX> -> <VERTEX>
    // <VERTEX> -> <VERTEX>
    // ...
    public Digraph<String> read()
    {
        Scanner sc = new Scanner(this.stream);
        Digraph<String> readGraph = new Digraph<String>();
        while(sc.hasNext())
        {
            String vertex = sc.next();
            String arrow = sc.next();
            String vertex2 = sc.next();
            if(!arrow.equals("->"))
            {
                return null;
            }
            else
            {
                readGraph.add(vertex, vertex2);
            }
        }

        return readGraph;
    }
}
