package com.mternovykh.task.example2;

import lombok.Builder;

import java.util.Map;

@Builder
public record CustomTaskletParameters(Map<String, String> params) {
}
