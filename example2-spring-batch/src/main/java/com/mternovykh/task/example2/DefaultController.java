package com.mternovykh.task.example2;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.ExitStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class DefaultController {
    private final CustomJob customJob;

    @PostMapping("run-custom-job-1")
    public ResponseEntity<ExitStatus> runCustomJob1() {
        return ResponseEntity.ok(customJob.runJob1());
    }

    @PostMapping("run-custom-job-2")
    public ResponseEntity<ExitStatus> runCustomJob2() {
        return ResponseEntity.ok(customJob.runJob2());
    }

    @PostMapping("run-custom-job-3")
    public ResponseEntity<ExitStatus> runCustomJob3() {
        return ResponseEntity.ok(customJob.runJob3());
    }

    @PostMapping("run-custom-job-4")
    public ResponseEntity<ExitStatus> runCustomJob4() {
        return ResponseEntity.ok(customJob.runJob4());
    }

    @PostMapping("run-custom-job-5")
    public ResponseEntity<ExitStatus> runCustomJob5() {
        return ResponseEntity.ok(customJob.runJob5());
    }
}
