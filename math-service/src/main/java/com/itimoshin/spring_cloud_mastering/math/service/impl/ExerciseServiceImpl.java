package com.itimoshin.spring_cloud_mastering.math.service.impl;

import com.itimoshin.spring_cloud_mastering.math.model.Exercise;
import com.itimoshin.spring_cloud_mastering.math.service.ExerciseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Service
@Transactional
public class ExerciseServiceImpl implements ExerciseService {

    @Override
    public List<Exercise> random(int count) {
        final Random rnd = new Random();
        return IntStream.range(0, count)
                .mapToObj((i) -> {
                    int firstNum = rnd.nextInt() & Integer.MAX_VALUE;
                    int secondNum = rnd.nextInt() & Integer.MAX_VALUE;
                    String question = String.format("%s + %s = ?", firstNum, secondNum);
                    String answer = Integer.toString(firstNum + secondNum);
                    return new Exercise(question, answer);
                }).collect(Collectors.toList());
    }
}
