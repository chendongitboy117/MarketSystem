package com.hcyzzl.mks.backend.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.hcyzzl.mks.backend.common.SingleObjectUtil.*;

/**
 * Controller 基类
 * @Author chendong
 * @create 2019/4/7 18:12
 */
public abstract  class BaseController {
    /**
     * Controller 的日志记录实例
     */
    protected final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * HttpServlet 的一些参数
     */
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    /**
     * HttpSession 是所有页面都有的，所以只需要注入一次就可以了
     */
    @Autowired
    protected HttpSession session;
    /**
     * SpringMVC 自动注入的对象
     */
    protected Model model;

    /**
     * 此处的 @ModelAttribute 表示请求该类的每个 Action 前都会首先执行它, 也可以将一些准备数据的操作放置在该方法里面
     *
     * @param request  由 SpringMVC 负责自动注入的 request
     * @param response 由 SpringMVC 负责自动注入的 response
     * @param model    由 SpringMVC 负责自动注入的 response
     */
    @ModelAttribute
    public void beforeMethodParam(
            HttpServletRequest request,
            HttpServletResponse response,
            Model model
    ) {
        this.request = request;
        this.response = response;
        this.model = model;
    }

    /**
     * 初始化数据绑定
     *
     * @param binder 数据绑定器，用以处理 servlet 参数到 Bean 实体的绑定
     */
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        /*
         * 防止 XSS 攻击
         */
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public String getAsText() {
                Object value = getValue();
                return value != null ? value.toString() : "";
            }

            @Override
            public void setAsText(String text) {
                setValue(text == null ? null : HtmlUtils.htmlEscape(text));
            }
        });
        binder.registerCustomEditor(LocalDateTime.class, CUSTOM_LOCAL_DATE_TIME_EDITOR);
        binder.registerCustomEditor(LocalDate.class, CUSTOM_LOCAL_DATE_EDITOR);
        binder.registerCustomEditor(LocalTime.class, CUSTOM_LOCAL_TIME_EDITOR);
    }

}
