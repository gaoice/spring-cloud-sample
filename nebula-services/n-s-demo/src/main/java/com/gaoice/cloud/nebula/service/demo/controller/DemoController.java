package com.gaoice.cloud.nebula.service.demo.controller;

import com.gaoice.cloud.nebula.module.web.controller.AbstractController;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * @author gaoice
 */

@RestController
@RequestMapping("/demo")
public class DemoController extends AbstractController {

    @Operation(summary = "hello")
    @RequestMapping("/hello")
    public Map<?, ?> hello(@RequestParam(required = false) String name) {
        return Collections.singletonMap("hello", name);
    }
}
