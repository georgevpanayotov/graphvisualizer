import javax.swing.JComponent;

public class GraphContainer extends JComponent
{
    private Digraph<String> graph;

    public GraphContainer(Digraph<String> graph)
    {
        this.graph = graph;
        // TODO: create vertexComponents for each vertex
        // TODO: create arrow containers for each out-edge. Need another class for that.
        // TODO: helper method to draw them
    }
}
