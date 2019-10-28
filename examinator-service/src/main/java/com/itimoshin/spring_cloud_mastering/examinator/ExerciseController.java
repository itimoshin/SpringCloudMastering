package com.itimoshin.spring_cloud_mastering.examinator;

import com.itimoshin.spring_cloud_mastering.examinator.client.ExamServiceClient;
import com.itimoshin.spring_cloud_mastering.examinator.client.MathServiceClient;
import com.itimoshin.spring_cloud_mastering.examinator.client.TheologyServiceClient;
import com.itimoshin.spring_cloud_mastering.examinator.model.Exercise;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/exercise")
@RefreshScope
public class ExerciseController {

    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;
    private final String examinatorMessage;
    private final Map<ExamType, ExamServiceClient> examClientMap;


    public ExerciseController(DiscoveryClient discoveryClient, RestTemplate restTemplate,
                              @Value("${examinator.message}") String examinatorMessage,
                              TheologyServiceClient theologyServiceClient,
                              MathServiceClient mathServiceClient) {
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
        this.examinatorMessage = examinatorMessage;

        Map<ExamType, ExamServiceClient> examServiceClientMap = new HashMap<>();
        examServiceClientMap.put(ExamType.THEOLOGY, theologyServiceClient);
        examServiceClientMap.put(ExamType.MATH, mathServiceClient);
        this.examClientMap = Collections.unmodifiableMap(examServiceClientMap);
    }

    @GetMapping("/all-subjects")
    public Map<String, List<Exercise>> allSubjectsExercises() {
        System.out.println(examinatorMessage);
        Map<String, List<Exercise>> result = new HashMap<>();
        for (ExamType value : ExamType.values()) {
            result.put(value.name(), examClientMap.get(value).getExercises(5));
        }
        return result;
    }
}
