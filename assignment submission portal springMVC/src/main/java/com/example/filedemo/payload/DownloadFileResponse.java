package com.example.filedemo.payload;

public class DownloadFileResponse {
    private String fileName;
    private String fileDownloadUrl;
    
    private String Id;

    public DownloadFileResponse(String fileName, String Id, String fileDownloadUrl) {
        this.fileName = fileName;
        this.Id = Id;
        this.fileDownloadUrl=fileDownloadUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownloadUrl() {
        return fileDownloadUrl;
    }

    public void setFileDownloadUrl(String fileDownloadUrl) {
        this.fileDownloadUrl = fileDownloadUrl;
    }
    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }
}

