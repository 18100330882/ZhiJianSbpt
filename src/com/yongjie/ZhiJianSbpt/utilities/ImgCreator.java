/**
 * @Author: 王景仟
 * @Createtime: 2016年8月29日
 * @Copyright: Copyright (c) 2016
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.utilities;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 条形码图片
 */
public class ImgCreator {

    public Graphics g;
    private Image img;

    public ImgCreator() {
    }

    public Image getImage(int i, int j) {
        int k = j <= i ? i : j;
        img = new BufferedImage(k, k, 13);
        g = ((BufferedImage) img).createGraphics();
        return img;
    }

    public Graphics getGraphics() {
        return g;
    }
}
