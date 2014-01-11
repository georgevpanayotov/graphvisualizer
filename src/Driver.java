import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

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

                for(String vertex : graph.vertices())
                {
                    System.out.print(vertex);
                    System.out.print(" <- ");

                    List<String> edges = graph.inEdges(vertex);

                    for(String vertex2 : edges)
                    {
                        System.out.print(vertex2);
                        System.out.print(" ");
                    }

                    System.out.println("");
                }
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
