package com.example.activiti.editor.main;

import com.example.common.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;

@RestController
@RequestMapping("/service")
@Slf4j
public class StencilsetRestResource {

    @GetMapping(value = "/editor/stencilset", produces = "application/json;charset=utf-8")
    public String getStencilset() {
        InputStream stencilsetStream = this.getClass().getClassLoader().getResourceAsStream("stencilset.json");
        try {
            return IOUtils.toString(stencilsetStream, "utf-8");
        } catch (Exception e) {
            log.error("Error service", e);
            throw new ApiException(e);
        }
    }
}
