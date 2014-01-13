import javax.swing.JComponent;
import java.util.Set;
import java.util.HashMap;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.FlowLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.Color;

public class GraphContainer extends JComponent implements VertexComponentDelegate
{
    private Digraph<String> graph;
    private HashMap<String, VertexComponent> vertexComponents;
    private static final int ORANGE = 0xF7DDC1;

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
        g.setColor(new Color(GraphContainer.ORANGE));
        g.fillRect(
            0,
            0,
            this.getWidth(),
            this.getHeight());
        super.paint(g);

        g.setColor(Color.BLACK);
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
        double trimFromSource = 35;
        double trimFromDestination = 35;

        // trim the source[xy] and destination [xy] so the lines don't go all the way to the center
        // of the label
        sourceX      = (int)((double)sourceX      + (dx * trimFromSource));
        sourceY      = (int)((double)sourceY      + (dy * trimFromSource));
        destinationX = (int)((double)destinationX - (dx * trimFromDestination));
        destinationY = (int)((double)destinationY - (dy * trimFromDestination));

        // draw the main line of the arrow
        g.drawLine(
            sourceX,
            sourceY,
            destinationX,
            destinationY);

        // get the vector -<dx, dy> and rotate it 30 deg (pi/6 rad) and -30 deg to create
        // the 'wings' or the arrow
        double wingSize = 5;
        double wingX = rotateX(Math.PI / 6, -dx, -dy);
        double wingY = rotateY(Math.PI / 6, -dx, -dy);

        g.drawLine(
            destinationX,
            destinationY,
            (int)(destinationX + wingSize * wingX),
            (int)(destinationY + wingSize * wingY));

        wingX = rotateX(-Math.PI / 6, -dx, -dy);
        wingY = rotateY(-Math.PI / 6, -dx, -dy);

        g.drawLine(
            destinationX,
            destinationY,
            (int)(destinationX + wingSize * wingX),
            (int)(destinationY + wingSize * wingY));
    }

    // rotates vector <x, y> by angle (radians) and returns the x component of the new vector
    double rotateX(double angle, double x, double y)
    {
        return x * Math.cos(angle) - y * Math.sin(angle);
    }

    double rotateY(double angle, double x, double y)
    {
        return x * Math.sin(angle) + y * Math.cos(angle);
    }
}
