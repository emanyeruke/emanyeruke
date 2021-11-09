package zw.co.mynhaka.polad.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "file-storage-service")
public interface FileStorageServiceClient {
    @GetMapping(value = "/files/download/{filename:.+}")
    Resource downloadFile(@PathVariable String filename);
}
