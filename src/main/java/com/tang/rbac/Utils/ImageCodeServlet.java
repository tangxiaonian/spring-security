package com.tang.rbac.Utils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * 生成验证码图片的servlet
 */
public class ImageCodeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setDateHeader("Expires", 0);
        resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        resp.addHeader("Cache-Control", "post-check=0, pre-check=0");
        resp.setHeader("Pragma", "no-cache");
        resp.setContentType("image/jpeg");

        Map<String, Object> objectMap = ImageCodeUtils.getImageCode(120, 40);

        BufferedImage image = (BufferedImage) objectMap.get("image");

        String strEnsure = (String) objectMap.get("strEnsure");
//        保存密码
        req.getSession().setAttribute("imagecode",strEnsure.toLowerCase());
//        输出到前端
        ImageIO.write(image, "jpg", resp.getOutputStream());
    }
}
