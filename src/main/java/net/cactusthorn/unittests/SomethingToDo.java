package net.cactusthorn.unittests;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SomethingToDo {

    private static final Logger LOG = LoggerFactory.getLogger(SomethingToDo.class);

    private final HttpServletRequest request;

    public SomethingToDo(HttpServletRequest request) {
        this.request = request;
    }

    public String doIt(HttpServletRequest request) {
        return (String) request.getAttribute("XYZ");
    }

    public String doIt() {
        String value = (String) request.getAttribute("XYZ");
        LOG.info("Test logging: {}", value);
        return value;
    }

    public String doItMultiLog() {
        String value = (String) request.getAttribute("XYZ");
        LOG.info("Test logging: {}", value);
        LOG.error("{} ERROR logging", value);
        return value;
    }

}
