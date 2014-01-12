import javax.swing.JComponent;
import java.util.Set;
import java.util.ArrayList;
import java.awt.Point;

public class GraphContainer extends JComponent
{
    private Digraph<String> graph;
    private Set<String> vertices;
    private ArrayList<VertexComponent> vertexComponents;

    public GraphContainer(Digraph<String> newGraph)
    {
        this.graph = newGraph;

        this.vertices = this.graph.vertices();
        this.vertexComponents = new ArrayList<VertexComponent>(this.vertices.size());

        VertexComponent component = null;

        this.setSize(500, 500);

        int i = 0;
        int size = this.vertices.size();
        for(String vertex : this.vertices)
        {
            component = new VertexComponent(vertex);
            this.vertexComponents.add(component);

            double theta = (((double) i) * 2 * Math.PI) / ((double)size);
            double x = 100 * Math.cos(theta) + 250;
            double y = 100 * Math.sin(theta) + 250;

            component.setLocation(new Point((int)x, (int)y));
            i ++;
        }

        // TODO: create arrow containers for each out-edge. Need another class for that.
        // TODO: helper method to draw them
    }
}
