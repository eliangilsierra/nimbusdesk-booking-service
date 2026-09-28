package com.nimbusdesk.booking.infraestructure.output.feignClients;

import com.nimbusdesk.booking.infraestructure.output.feignClients.dto.PartnerAvailabilityResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "nimbusdesk-partner-bridge-service")
public interface PartnerBridgeFeignClient {

    @GetMapping("/availability")
    PartnerAvailabilityResponseDto checkAvailability(
            @RequestParam("roomId") Long roomId,
            @RequestParam("start") String start,
            @RequestParam("end") String end);
}
