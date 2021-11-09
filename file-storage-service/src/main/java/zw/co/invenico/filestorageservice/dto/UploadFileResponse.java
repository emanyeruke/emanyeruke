package zw.co.invenico.filestorageservice.dto;

public class UploadFileResponse {

    private final String fileName;

    public UploadFileResponse(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

}
