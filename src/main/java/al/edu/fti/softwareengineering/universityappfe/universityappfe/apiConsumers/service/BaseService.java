package al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.common.BaseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BaseService<MODEL extends BaseModel, ID> {
    ResponseEntity<MODEL> findById(String url, ID id);

    ResponseEntity<Void> add(String url, MODEL model);

    ResponseEntity<MODEL[]> findAllPageable(String url, int pageNumber);
}
