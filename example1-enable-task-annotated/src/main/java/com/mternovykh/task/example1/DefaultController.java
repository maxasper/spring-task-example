package com.mternovykh.task.example1;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.task.repository.TaskExecution;
import org.springframework.cloud.task.repository.TaskExplorer;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class DefaultController {
    private final TaskExplorer taskExplorer;


    @GetMapping
    public ResponseEntity<List<TaskExecution>> getTasks() {
        return ResponseEntity.ok(taskExplorer.findAll(PageRequest.of(0, 100))
                .stream().collect(Collectors.toList()));
    }

}
