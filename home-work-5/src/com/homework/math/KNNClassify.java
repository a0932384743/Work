package com.homework.math;

import com.homework.entity.Abalone;
import com.homework.entity.DistanceComparator;
import com.homework.entity.Result;

import java.util.*;

public class KNNClassify {

    private int k; // k-nearest neighbor to consider
    private List<Abalone> trainingSet;

    public KNNClassify(int k) {
        this.k = k;
    }

    public void setTrainingSet(List<Abalone> trainingSet) {
        this.trainingSet = trainingSet;
    }

    public String predict(Abalone testInstance) {
        List<Result> result = new ArrayList<>();
        for (Abalone entry : trainingSet) {
            result.add(new Result(calculDistance(entry , testInstance), entry.getSex()));
        }
        Collections.sort(result, new DistanceComparator());

        List<String> neighborsToConsider = new ArrayList<>();
        for (int i = 0; i <= k; i++) {
            if (i >= result.size()) { // Avoid too big value of K
                break;
            }
            neighborsToConsider.add(result.get(i).getAbaloneClass());
        }

        return ListUtils.mostCommonItem(neighborsToConsider);
    }

    // ====================
    // DISTANCE CALCULATION
    // ====================

    private double calculDistance(Abalone from, Abalone to) {
        return computeEuclidianDistance(from, to);
    }

    /**
     * Calcul de la distance Euclidienne entre les deux entitiées
     * Formule de https://fr.wikipedia.org/wiki/Distance_(math%C3%A9matiques)
     */
    private double computeEuclidianDistance(Abalone from, Abalone to) {
        double distance = 0.0;
        distance += Math.pow(from.getAgeRing() - to.getAgeRing(), 2);
        distance += Math.pow(from.getWeight() - to.getWeight(), 2);
        distance += Math.pow(from.getHeight() - to.getHeight(), 2);
        distance += Math.pow(from.getDiameter() - to.getDiameter(), 2);
        distance += Math.pow(from.getLength() - to.getLength(), 2);
        distance += Math.pow(from.getShellWeight() - to.getShellWeight(), 2);
        distance += Math.pow(from.getVisceralWeight() - to.getVisceralWeight(), 2);
        distance += Math.pow(from.getTotalWeight() - to.getTotalWeight(), 2);
        return Math.sqrt(distance);
    }
}
