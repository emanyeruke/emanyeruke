package zw.co.mynhaka.polad.reports;

import lombok.Getter;


@Getter
public enum ReportFormat {

    PDF(".pdf"),
    XLSX(".xlsx"),
    DOCX(".docx"),
    CSV(".csv");

    private String fileExtension;

    private ReportFormat(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFileExtension() {
        return fileExtension;
    }
}
