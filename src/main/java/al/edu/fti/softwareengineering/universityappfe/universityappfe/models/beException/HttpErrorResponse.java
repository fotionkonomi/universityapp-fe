package al.edu.fti.softwareengineering.universityappfe.universityappfe.models.beException;

import lombok.Data;

import java.util.Date;

@Data
public class HttpErrorResponse {
    private int errorCode;
    private Date timestamp;
    private String path;
    private String message;
    private String localizedMessage;
}
