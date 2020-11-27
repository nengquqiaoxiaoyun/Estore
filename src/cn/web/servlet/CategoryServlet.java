package cn.web.servlet;

import cn.web.entity.Category;
import cn.web.service.CategoryService;
import cn.web.service.impl.CategoryServiceImpl;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-27
 */
@WebServlet(name = "CategoryServlet", urlPatterns = "/servlet/category")
public class CategoryServlet extends BaseServlet {

    private CategoryService categoryService = new CategoryServiceImpl();

    public void listCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String categoryJson = categoryService.listCategory();
        response.getWriter().write(categoryJson);
    }
}
