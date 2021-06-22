package al.edu.fti.softwareengineering.universityappfe.universityappfe.util;

public interface MessageUtil {
    String getMessage(String code);

    String getMessageWithParams(String code, Object[] params);
}
