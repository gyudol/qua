package com.mulmeong.member.common.healthcheck;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "health-check", description = "헬스 체크")
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class HealthCheckController {

    private final Environment env;

    @Operation(summary = "Health check API", description = "Health check를 위한 API")
    @ApiResponse(responseCode = "200", description = "SUCCESS(성공)")
    @GetMapping("/health-check")
    public ResponseEntity<Void> healthCheck() {
        return ResponseEntity.ok().build();
    }
}
