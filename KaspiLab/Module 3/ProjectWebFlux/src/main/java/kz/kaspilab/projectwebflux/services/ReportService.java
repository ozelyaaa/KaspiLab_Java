package kz.kaspilab.projectwebflux.services;

import kz.kaspilab.projectwebflux.models.ReportDTO;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ReportService {
    Flux<ReportDTO> getReports(List<Integer> product_ids);
}
