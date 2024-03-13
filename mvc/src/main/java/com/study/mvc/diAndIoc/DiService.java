package com.study.mvc.diAndIoc;

import java.util.List;

public class DiService {
    private DiRepository diRepository;

    public DiService(DiRepository diRepository) {
        this.diRepository = diRepository;
    }

    public int getTotal() {
        int result = 0;
        DiRepository diRepository = new DiRepository();
        List<Integer> scoreList = diRepository.getScoreList();

        for(Integer score : scoreList) {
            int total = 0;
            total += score;
            result = total;
        }

        return result;
    }

    public double getAverage() {
        double avg = 0;
        int total = 0;
        DiRepository diRepository = new DiRepository();
        List<Integer> scoreList = diRepository.getScoreList();
        for(Integer score : scoreList) {
            total += score;
        }
        avg = total / scoreList.size();

        return avg;
    }

}
