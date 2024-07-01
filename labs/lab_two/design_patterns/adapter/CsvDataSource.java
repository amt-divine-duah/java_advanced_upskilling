package labs.lab_two.design_patterns.adapter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

interface IDataSource {
    List<String[]> readData();
}


public class CsvDataSource {
    private String filename;

    public CsvDataSource(String filename) {
        this.filename = filename;
    }

    public List<String[]> getCsvData() {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] dataRow = line.split(",");
                data.add(dataRow);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {
        CsvDataSource csvDataSource = new CsvDataSource("C:\\Santana\\Java Tutorials\\Amalitech Upskilling\\java_advanced_upskilling\\labs\\lab_two\\design_patterns\\adapter\\data.csv");

        IDataSource dataSource = new CsvDataSourceAdapter(csvDataSource);

        List<String[]> csvData = dataSource.readData();
        for (String[] row : csvData) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}

class CsvDataSourceAdapter implements IDataSource {

    private final CsvDataSource csvDataSource;

    public CsvDataSourceAdapter(CsvDataSource csvDataSource) {
        this.csvDataSource = csvDataSource;
    }

    @Override
    public List<String[]> readData() {
        return csvDataSource.getCsvData();
    }
}