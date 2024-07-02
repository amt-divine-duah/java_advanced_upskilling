package labs.lab_two.design_patterns.proxy;

interface FileDownloadService {
    void downloadFile(String url);
}

class RealFileDownloadService implements FileDownloadService {

    @Override
    public void downloadFile(String url) {
        System.out.println("Starting file download from: " + url);

        // Simulate file download process
        try {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Downloading... " + (i * 10) + "%");
                Thread.sleep(500); // Simulate time delay for downloading
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("File download complete from: " + url);
    }
}

class ProxyFileDownloadService implements FileDownloadService {

    private final RealFileDownloadService realFileDownloadService;
    private final String authorizedUser;

    public ProxyFileDownloadService(String authorizedUser) {
        this.realFileDownloadService = new RealFileDownloadService();
        this.authorizedUser = authorizedUser;
    }

    private boolean checkAuthorization() {
        // Simulate authorization check
        if ("authorizedUser".equals(authorizedUser)) {
            System.out.println("User authorized for download.");
            return true;
        } else {
            System.out.println("User not authorized for download.");
            return false;
        }
    }

    @Override
    public void downloadFile(String url) {
        if (checkAuthorization()) {
            realFileDownloadService.downloadFile(url);
        } else {
            System.out.println("Download failed due to authorization error.");
        }
    }
}


public class FileDownloadServiceClient {
    public static void main(String[] args) {
        // Authorized user
        FileDownloadService authorizedService = new ProxyFileDownloadService("authorizedUser");
        authorizedService.downloadFile("http://example.com/file1");

        // Unauthorized user
        FileDownloadService unauthorizedService = new ProxyFileDownloadService("unauthorizedUser");
        unauthorizedService.downloadFile("http://example.com/file2");
    }
}
