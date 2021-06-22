package al.edu.fti.softwareengineering.universityappfe.universityappfe.util.impl;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class MessageUtilImpl implements MessageUtil {
    @Autowired
    private MessageSource messageSource;

    private MessageSourceAccessor accessor;

    @PostConstruct
    public void init() {
        accessor = new MessageSourceAccessor(messageSource);
    }

    @Override
    public String getMessageWithParams(String code, Object[] args) {
        return accessor.getMessage(code, args);
    }

    @Override
    public String getMessage(String code) {
        return accessor.getMessage(code);
    }
}
