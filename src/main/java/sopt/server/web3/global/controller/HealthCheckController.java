package sopt.server.web3.global.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import sopt.server.web3.global.response.CommonApiResponse;
import sopt.server.web3.global.response.success.SuccessCode;

@RestController
public class HealthCheckController {

    @GetMapping("/health")
    public CommonApiResponse<Void> healthCheck() {
        return CommonApiResponse.success(SuccessCode.SUCCESS);
    }
}
