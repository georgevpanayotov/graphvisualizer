import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;

public class Driver
{
    public static void main(String[] args)
    {
        if(args.length > 0)
        {
            try
            {
                FileInputStream fs = new FileInputStream(args[0]);
                GraphParser parser = new GraphParser(fs);
                Digraph<String> graph = parser.read();

                // create the graph container to visualize this graph
                GraphContainer graphContainer = new GraphContainer(graph);

                // Frame will be the main window
                JFrame frame =  new JFrame("Graph Visualizer");
                frame.setSize(500, 500);
                frame.getContentPane().add(graphContainer);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
            catch(IOException iox)
            {
                System.out.println("" + iox);
            }
        }
        else
        {
            System.out.println("usage:\n Driver <FILE_TO_GRAPH>");
        }
    }
}
