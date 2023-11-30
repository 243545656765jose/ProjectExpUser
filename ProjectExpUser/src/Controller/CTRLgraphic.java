package Controller;

import Model.candidateDAO;
import Model.candidates;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

//Controller class for managing graphical representation of candidate votes.
 
public class CTRLgraphic {
    candidateDAO dao = new candidateDAO();
    
    //Creates a dataset with candidate votes for generating a bar chart.
     
     //return DefaultCategoryDataset containing candidate votes data.
     
     public DefaultCategoryDataset addnSources1Reports2() {
         DefaultCategoryDataset data = new DefaultCategoryDataset();
        List<candidates> candidate1 = this.dao.readCandidates();
        for (candidates candidate : candidate1) {
            int vote = (int)candidate.getVotes();
            String name = candidate.getName();
            String party = candidate.getParty();
            data.setValue(vote, name, party);
        }
        return data;
    }

     //Adds a bar chart to a JPanel using the provided dataset
    public void addGraph(JPanel jPanel5, DefaultCategoryDataset data) {
        // Create a bar chart with specified labels and data
        JFreeChart graph = ChartFactory.createBarChart("--Grafico Votos-", "Nombres ", "Votos", data, PlotOrientation.VERTICAL, true, true, false);
        // Create a ChartPanel to display the chart
        ChartPanel panel = new ChartPanel(graph);
        panel.setMouseWheelEnabled(true);
        panel.setPreferredSize(new Dimension(1158, 494));
        // Set layout and add the chart panel to the specified JPanel
        jPanel5.setLayout(new BorderLayout());
        jPanel5.add(panel, BorderLayout.NORTH);
    }

}
