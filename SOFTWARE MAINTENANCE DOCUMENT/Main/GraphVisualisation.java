import java.io.File;

import org.graphstream.graph.Graph;

/**
 * Stores the dataset and displays the graph
 */
public class GraphVisualisation {

    private Graph devices;

    public DataSet dataset;

    /**
     * create new GraphVisualisation with a new dataset and graphBuilder
     */
    public GraphVisualisation(){
        
        dataset = new DataSet();
    }

    /**
     * Read the current DataSet, build the graph and display it
     */
    public void displayGraph(){

        GraphBuilder graphBuilder = new GraphBuilder();

        if (dataset.checkFormat()) {
            graphBuilder.readDataset(dataset.getDataSet());
            devices = graphBuilder.getGraph();
            devices.display();
        }


    }
}