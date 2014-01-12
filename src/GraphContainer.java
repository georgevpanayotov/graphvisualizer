import javax.swing.JComponent;
import java.util.Set;
import java.util.HashMap;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;

public class GraphContainer extends JComponent implements VertexComponentDelegate
{
    private Digraph<String> graph;
    private HashMap<String, VertexComponent> vertexComponents;

    public GraphContainer(Digraph<String> newGraph)
    {
        this.setLayout(null);

        this.graph = newGraph;

        this.vertexComponents = new HashMap<String, VertexComponent>(this.graph.vertices().size());

        VertexComponent component = null;

        this.setSize(500, 500);

        int i = 0;
        int size = this.graph.vertices().size();
        for(String vertex : this.graph.vertices())
        {
            component = new VertexComponent(vertex);
            this.vertexComponents.put(vertex, component);
            component.setDelegate(this);

            double theta = (((double) i) * 2 * Math.PI) / ((double)size);
            double x = 200 * Math.cos(theta) + 250;
            double y = 200 * Math.sin(theta) + 250;

            this.add(component);

            Dimension componentDim = component.getPreferredSize();
            component.setBounds(
            (int)x,
            (int)y,
            componentDim.width,
            componentDim.height);

            i++;
        }

        this.setVisible(true);
        this.revalidate();
        this.repaint();
    }

//
//  @category subclassing
//
    public void paint(Graphics g)
    {
        super.paint(g);
        for (String vertex : this.graph.vertices())
        {
            for(String destVertex : this.graph.outEdges(vertex))
            {
                this.drawArrow(g,
                    this.vertexComponents.get(vertex).getBounds(),
                    this.vertexComponents.get(destVertex).getBounds());
            }
        }
    }

//
// @category VertexComponentDelegate methods
//

    public void vertexMoved(VertexComponent v)
    {
        this.repaint();
    }

//
//  @category helpers
//

    private void drawArrow(Graphics g, Rectangle source, Rectangle destination)
    {
        int sourceX      = source.x        + (source.width / 2);
        int sourceY      = source.y        + (source.height / 2);
        int destinationX = destination.x   + (destination.width / 2);
        int destinationY = destination.y   + (destination.height / 2);

        double dx = destinationX - sourceX;
        double dy = destinationY - sourceY;
        double magnitude = Math.sqrt( dx * dx + dy * dy);

        // <dx, dy> is now a unit vector
        dx /= magnitude;
        dy /= magnitude;

        // the fraction of the line to trim from the source and destination respectively
        double trimFromSource = 0.05;
        double trimFromDestination = 0.1;

        sourceX      = (int)((double)sourceX      + (dx * trimFromSource * magnitude));
        sourceY      = (int)((double)sourceY      + (dy * trimFromSource * magnitude));
        destinationX = (int)((double)destinationX - (dx * trimFromDestination * magnitude));
        destinationY = (int)((double)destinationY - (dy * trimFromDestination * magnitude));

        g.drawLine(
        sourceX,
        sourceY,
        destinationX,
        destinationY);
    }
}
