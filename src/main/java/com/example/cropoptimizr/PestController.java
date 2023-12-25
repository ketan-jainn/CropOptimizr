package com.example.cropoptimizr;
import com.example.cropoptimizr.model.CropData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import static com.example.cropoptimizr.CropRecommend.getRecommendation;

public class PestController {
    public TextField area;
    private HashMap<String, ArrayList<String>> pestControlDictionary;
    @FXML
    private ChoiceBox<String> pestBox;
    @FXML
    private Button resultButton;
    @FXML
    private TextArea resultArea;
    @FXML
    private TextField n, p, k, ph, humid, temp,rain;
    public void initialize() {
        pestControlDictionary = new HashMap<>();
        pestControlDictionary.put("Sawfly", new ArrayList<>(List.of("Parasitic Wasps such as Braconid and Ichneumonid wasps", "bifenthrin", "carbaryl")));
        pestControlDictionary.put("Aphids", new ArrayList<>(List.of("Neonicotinoids", "Pyrethroids", "Insecticidal soaps and oils")));
        pestControlDictionary.put("Wireworm", new ArrayList<>(List.of("imidacloprid", "chlorpyrifos", "fipronil")));
        pestControlDictionary.put("Cutworm", new ArrayList<>(List.of("carbaryl", "bifenthrin", "cypermethrin")));
        pestControlDictionary.put("Grasshopper", new ArrayList<>(List.of("crop rotation", "removing grassy vegetation near fields", "reducing bare ground")));

        pestBox.getItems().addAll(pestControlDictionary.keySet());
        pestBox.setValue("None");
    }

    double calcYield(double area,String cropT) {

        // Create a HashMap to store the crop data
        Map<String, CropData> cropData = new HashMap<>();

        // Add the crop data to the HashMap
        cropData.put("rice", new CropData(250.0, 150.0, 2.5));
        cropData.put("maize", new CropData(180.0, 300.0, 4.2));
        cropData.put("chickpeas", new CropData(200.0, 15.0, 1.8));
        cropData.put("kidneybeans", new CropData(220.0, 20.0, 2.2));
        cropData.put("pigeonpeas", new CropData(150.0, 10.0, 1.5));
        cropData.put("mothbeans", new CropData(180.0, 12.0, 2.0));
        cropData.put("mungbean", new CropData(200.0, 18.0, 2.4));
        cropData.put("blackgram", new CropData(190.0, 16.0, 2.1));
        cropData.put("lentil", new CropData(210.0, 14.0, 1.9));
        cropData.put("pomegranate", new CropData(1.0, 200.0, 300.0));
        cropData.put("banana", new CropData(1.0, 400.0, 1000.0));
        cropData.put("mango", new CropData(1.0, 100.0, 500.0));
        cropData.put("grapes", new CropData(10.0, 1000.0, 2000.0));
        cropData.put("watermelon", new CropData(1.0, 10000.0, 4000.0));
        cropData.put("muskmelon", new CropData(1.0, 20.0, 2000.0));
        cropData.put("apple", new CropData(1.0, 100.0, 150.0));
        cropData.put("orange", new CropData(1.0, 150.0, 200.0));
        cropData.put("papaya", new CropData(1.0, 30.0, 1500.0));
        cropData.put("coconut", new CropData(1.0, 50.0, 1200.0));
        cropData.put("cotton", new CropData(1.0, 50.0, 5000.0));
        cropData.put("jute", new CropData(1.0, 100.0, 150.0));
        cropData.put("coffee", new CropData(1.0, 500.0, 0.1));

        // Get user input for crop type and farm area
        double farmArea = area;

        // Retrieve crop data from the crop data HashMap
        CropData crop = cropData.get(cropT);
        double yield = 0;
        if (crop != null) {
            double headsPer = crop.getHeadsPer();
            double grainCount = crop.getGrainCount();
            double grainWeight = crop.getGrainWeight();
            // Calculate the crop yield
            yield = (headsPer * grainCount * grainWeight) / 10000;
            System.out.println("--------Crop Yield:--------\n\t" + yield + "  t/ha");
            System.out.println("\t" + yield * farmArea + "  tons");
        } else {
            System.out.println("Invalid crop type!");
        }
        return yield * farmArea;
    }


    public void getResult(ActionEvent e) throws InterruptedException {
        String result;
        // TO GET PREDICTION
        int n1 = Integer.parseInt(n.getText());
        int p1 = Integer.parseInt(p.getText());
        int k1 = Integer.parseInt(k.getText());
        int ph1 = Integer.parseInt(ph.getText());
        int humid1 = Integer.parseInt(humid.getText());
        int temp1 = Integer.parseInt(temp.getText());
        int rain1 = Integer.parseInt(rain.getText());
        double area1=Double.parseDouble(area.getText());
        String resultantCrop= getRecommendation((double) n1, (double) p1, (double) k1, (double) temp1, (double) humid1, (double) ph1, (double) rain1);
        Thread.sleep(500);
        double y=calcYield(area1,resultantCrop);
        result = "Prediction: "+ resultantCrop+"\n";

        // TO GET PESTICIDE
        String selectedKey = pestBox.getValue();
        if (selectedKey != null) {
            List<String> values = pestControlDictionary.get(selectedKey);
            if (values != null) {
                int random  = (int)(Math.random()*(values.toArray().length)+0);
                result+="Predicted Yield: " + y + " Tonnes\n";
                result += "Method to Control the Pest: \n"+ values.get(random)+"\n";
                resultArea.setText(result);
            }
        }
    }
}


