package al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.impl;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.BaseService;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.RestCaller;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.common.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

public abstract class BaseServiceImpl<MODEL extends BaseModel, ID> implements BaseService<MODEL, ID> {

    @Autowired
    protected RestCaller restCaller;

    private Class<MODEL> classOfModel;
    private Class<MODEL[]> classOfArrayModel;

    public BaseServiceImpl(Class<MODEL> classOfModel, Class<MODEL[]> classOfArrayModel) {
        this.classOfModel = classOfModel;
        this.classOfArrayModel = classOfArrayModel;
    }

    @Override
    public ResponseEntity<MODEL> findById(String url, ID id) {
        return restCaller.getExchange(url + "/" + id, classOfModel);
    }

    @Override
    public ResponseEntity<Void> add(String url, MODEL model) {
        return restCaller.postExchange(url, new HttpEntity<>(model), Void.class);
    }

    @Override
    public ResponseEntity<MODEL[]> findAllPageable(String url, int pageNumber) {
        return restCaller.getExchange(url + "/" + pageNumber, classOfArrayModel);
    }
}
