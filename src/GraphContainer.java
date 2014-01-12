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
        g.drawLine(
            source.x + (source.width / 2),
            source.y + (source.height / 2),
            destination.x + (destination.width / 2),
            destination.y + (destination.height / 2));

    }
}
