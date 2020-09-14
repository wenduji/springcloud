package com.example.activiti;

import com.example.activiti.business.context.ActivitiContext;
import org.activiti.engine.form.FormProperty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author hjs
 * @date 2020/9/3
 * @description
 */
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class FormTest {

    @Test
    public void formData() {
        String processDefinitionKey = "form";
        String processDefinitionId = ActivitiContext.getRepositoryService()
                .createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey)
                .singleResult().getId();
        List<FormProperty> formPropertyList = ActivitiContext.getFormService()
                .getStartFormData(processDefinitionId).getFormProperties();
        formPropertyList.stream().forEach(s -> System.out.println(s.getId() + s.getName()));
    }

}
