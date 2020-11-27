package cn.web.servlet;

import cn.web.entity.Good;
import cn.web.service.GoodsService;
import cn.web.service.impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-27
 */
@WebServlet(name = "GoodsServlet", urlPatterns = "/servlet/goods")
public class GoodsServlet extends BaseServlet {

    private GoodsService goodsService = new GoodsServiceImpl();

    public void listGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Good> goods = goodsService.listGoods();
        request.setAttribute("goods", goods);
        request.getRequestDispatcher("/goods.jsp").forward(request, response);
    }

    public void goodDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String goodId = request.getParameter("goodId");
        Good goodDetail = goodsService.getGoodDetail(goodId);
        request.setAttribute("goodDetail",goodDetail);
        request.getRequestDispatcher("/goods_detail.jsp").forward(request,response);
    }
}
