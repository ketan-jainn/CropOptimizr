package com.example.cropoptimizr;

import org.apache.commons.math3.util.Pair;
import org.pmml4s.model.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.IntStream;

import static org.pmml4s.model.Model.fromFile;

//import static org.pmml4s.model.Model.fromFile;

public class CropRecommend {

    private Model model;

    public CropRecommend(String pmmlFilePath) throws Exception {
        model = fromFile(pmmlFilePath);
    }


    public int findMaxValueIndex(Object[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array is null or empty");
        }

        int maxIndex = 0;
        Comparable<Object> maxComparable = (Comparable<Object>) array[0];

        for (int i = 1; i < array.length; i++) {
            Comparable<Object> currentComparable = (Comparable<Object>) array[i];
            if (currentComparable.compareTo(maxComparable) > 0) {
                maxComparable = currentComparable;
                maxIndex = i;
            }
        }

        return maxIndex;
    }


    public String classifyCrop(Map<String, Double> values) {
        Object[] valuesMap = Arrays.stream(model.inputNames())
                .map(values::get)
                .toArray();

        Object[] result = model.predict(valuesMap);
        int resIndex =  findMaxValueIndex(result);
        String[] crops = {
                "rice", "maize", "chickpeas", "kidneybeans", "pigeonpeas",
                "mothbeans", "mungbean", "blackgram", "lentil", "pomegranate",
                "banana", "mango", "grapes", "watermelon", "muskmelon",
                "apple", "orange", "papaya", "coconut", "cotton",
                "jute", "coffee"
        };
        return crops[resIndex];
    }



    public static String chaloKaamKaro(Double n, Double P, Double K, Double temp, Double humidity, Double ph, Double rainfall) {
        List<Pair<String, String>> predictedValues = new ArrayList<>();
        CropRecommend classifier;

        try {
            classifier = new CropRecommend("C:\\Users\\DIPTI\\IdeaProjects\\CropOptimizr\\src\\main\\java\\com\\example\\cropoptimizr\\crop_modelfn.pmml");

            // Perform prediction using user input
            Map<String, Double> userInput = new HashMap<>();
            userInput.put("N",n);
            userInput.put("P", P);
            userInput.put("K", K);
            userInput.put("temperature", temp);
            userInput.put("humidity", humidity);
            userInput.put("ph", ph);
            userInput.put("rainfall", rainfall);

            String predictedCrop = classifier.classifyCrop(userInput);
            return predictedCrop;
        } catch (Exception ex) {
            System.out.println("Error: "+ex);
        }
        return null;
    }
}