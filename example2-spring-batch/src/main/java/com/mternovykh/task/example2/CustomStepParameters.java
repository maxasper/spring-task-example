package com.mternovykh.task.example2;

import lombok.Builder;

@Builder
public record CustomStepParameters(String name, String taskletType, CustomTaskletParameters customTaskletParameters
) {
}
