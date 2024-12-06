package com.mulmeong.contest.presentation;

import com.mulmeong.contest.application.ValidationServiceImpl;
import com.mulmeong.contest.common.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/v1")
public class ValidationController {

    private final ValidationServiceImpl fishValidationService;

    @GetMapping("/contests/validate")
    public BaseResponse<Boolean> validateFish(@RequestParam String imgUrl) throws IOException {

        return new BaseResponse<>(fishValidationService.isFish(imgUrl));

    }
}