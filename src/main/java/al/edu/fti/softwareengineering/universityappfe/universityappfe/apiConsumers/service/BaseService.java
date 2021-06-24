package al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.common.BaseModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BaseService<MODEL extends BaseModel, ID> {
    ResponseEntity<MODEL> findById(ID id);

    ResponseEntity<Void> save(MODEL model);

    ResponseEntity<MODEL[]> findAllPageable(int pageNumber);
}
