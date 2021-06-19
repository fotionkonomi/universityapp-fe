package al.edu.fti.softwareengineering.universityappfe.universityappfe.controller.util;

public interface PageUtil {
    <T> String getContextPage(Class<T> clazz);

    <T> String getAddPage(Class<T> clazz);
}
