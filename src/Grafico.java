/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Willian
 */
public class Grafico {
    
    ChartPanel chartPanel;

    public Grafico(List<Double> listaValoresGrafico) {
        
        XYSeries series = new XYSeries("");

        for (int i = 0; i < listaValoresGrafico.size(); i++) {
            series.add(i + 1, listaValoresGrafico.get(i));
        }

        XYSeriesCollection data = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                null,
                "Época",
                "Fitness Média",
                data,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        
        chart.removeLegend();
        
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(300, 340));
    }
    
    public ChartPanel getChartPanel() {
        return chartPanel;
    }

}
