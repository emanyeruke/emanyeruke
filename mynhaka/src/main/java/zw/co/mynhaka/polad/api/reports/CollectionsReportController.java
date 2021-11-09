package zw.co.mynhaka.polad.api.reports;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.mynhaka.polad.service.iface.reports.ICollectionsReportService;
import zw.co.mynhaka.polad.service.iface.reports.dtos.CollectionResultDto;


@RestController
@Slf4j
@RequestMapping(value = "/api/v1/report/collections", produces = MediaType.APPLICATION_JSON_VALUE)
public class CollectionsReportController {


    private final zw.co.mynhaka.polad.service.iface.reports.ICollectionsReportService ICollectionsReportService;

    public CollectionsReportController(ICollectionsReportService ICollectionsReportService) {
        this.ICollectionsReportService = ICollectionsReportService;
    }

    @GetMapping("invoice-payment")
    private ResponseEntity<CollectionResultDto> getCollectionsReport() {
        return ResponseEntity.ok(ICollectionsReportService.getCollections());
    }
}
