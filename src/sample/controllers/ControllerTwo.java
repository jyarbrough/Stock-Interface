package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import sample.models.stockFactories.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ControllerTwo implements Initializable {
    @FXML
    LineChart lineChart;


    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        final StockHistoryFactory stockHistoryFactory = new StockHistoryFactory();
        AllStockFactory allStockFactory = new AllStockFactory();
        PopulateStockHistory populateStockHistory = new PopulateStockHistory();
        HashMap<String, Stock> tickersMap = allStockFactory.build();
        populateStockHistory.populateStockHistory(stockHistoryFactory, tickersMap);


        Stock QQQ = tickersMap.get("QQQ");
        lineChart.setData(getChartData(QQQ.getStockHistoryRecords()));
    }

    private ObservableList<XYChart.Series<String, Double>> getChartData(ArrayList<StockHistoryRecord> stockHistoryRecords) {
        ObservableList<XYChart.Series<String, Double>> dataToPlot = FXCollections.observableArrayList();

        XYChart.Series<String, Double> highLine = new XYChart.Series<String, Double>();
        highLine.setName("High");

        XYChart.Series<String, Double> lowLine = new XYChart.Series<String, Double>();
        lowLine.setName("Low");


        for (StockHistoryRecord historyRecord : stockHistoryRecords) {
            highLine.getData().add(new XYChart.Data(historyRecord.getDate(), historyRecord.getHigh()));
            lowLine.getData().add(new XYChart.Data(historyRecord.getDate(), historyRecord.getLow()));
        }

        dataToPlot.addAll(highLine, lowLine);

        return dataToPlot;
    }

}
