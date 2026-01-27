package kz.kaspilab.projectwebflux.controllers;

import kz.kaspilab.projectwebflux.models.ReportDTO;
import kz.kaspilab.projectwebflux.services.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public Flux<ReportDTO> getReports(@RequestParam List<Integer> ids) {
        return reportService.getReports(ids);
    }
}
