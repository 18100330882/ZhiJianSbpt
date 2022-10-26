package com.yongjie.ZhiJianSbpt.utilities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.StringTokenizer;

/**
 * 条形码
 */
public class BarCodeImage {

    private BarCode barcode;

    public BarCodeImage() {
    }

    /**
     * 将arrayList转换成byte数组
     *
     * @param code
     * @return
     */
    private BarCode getChart(String code) {
        if (barcode == null)
            barcode = new BarCode();
        try {
            barcode.code = code;
        } catch (Exception e) {
            e.printStackTrace();
            barcode.code = "Parameter Error";
        }
        return barcode;
    }

    /**
     * 传code,实现条形码
     */
    public byte[] getTiaoMaImage(String code) {
        try {
            BarCode barcode1 = getChart(code);//获取条形码字符
            barcode1.setSize(barcode1.width, barcode1.height);
            BufferedImage bufferedimage = null;
            if (barcode1.autoSize) {
                bufferedimage = new BufferedImage(barcode1.getSize().width, barcode1.getSize().height,
                        13);
                Graphics2D graphics2d = bufferedimage.createGraphics();
                barcode1.paint(bufferedimage.createGraphics());
                barcode1.invalidate();
                graphics2d.dispose();
            }
            bufferedimage = new BufferedImage(barcode1.getSize().width, barcode1.getSize().height, 1);
            Graphics2D graphics2d1 = bufferedimage.createGraphics();
            barcode1.paint(graphics2d1);
            barcode1.invalidate();
            graphics2d1.dispose();
            //将图片转换成二进制流 add by hyh
            byte[] image = FileUtil.imageToByte(bufferedimage, "jpeg");
            return image;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Font convertFont(String s) {
        StringTokenizer stringtokenizer = new StringTokenizer(s, "|");
        String s1 = stringtokenizer.nextToken();
        String s2 = stringtokenizer.nextToken();
        String s3 = stringtokenizer.nextToken();
        byte byte0 = -1;
        if (s2.trim().toUpperCase().equals("PLAIN"))
            byte0 = 0;
        else if (s2.trim().toUpperCase().equals("BOLD"))
            byte0 = 1;
        else if (s2.trim().toUpperCase().equals("ITALIC"))
            byte0 = 2;
        return new Font(s1, byte0, (new Integer(s3)).intValue());
    }

    private Color convertColor(String s) {
        Color color = null;
        if (s.trim().toUpperCase().equals("RED"))
            color = Color.red;
        else if (s.trim().toUpperCase().equals("BLACK"))
            color = Color.black;
        else if (s.trim().toUpperCase().equals("BLUE"))
            color = Color.blue;
        else if (s.trim().toUpperCase().equals("CYAN"))
            color = Color.cyan;
        else if (s.trim().toUpperCase().equals("DARKGRAY"))
            color = Color.darkGray;
        else if (s.trim().toUpperCase().equals("GRAY"))
            color = Color.gray;
        else if (s.trim().toUpperCase().equals("GREEN"))
            color = Color.green;
        else if (s.trim().toUpperCase().equals("LIGHTGRAY"))
            color = Color.lightGray;
        else if (s.trim().toUpperCase().equals("MAGENTA"))
            color = Color.magenta;
        else if (s.trim().toUpperCase().equals("ORANGE"))
            color = Color.orange;
        else if (s.trim().toUpperCase().equals("PINK"))
            color = Color.pink;
        else if (s.trim().toUpperCase().equals("WHITE"))
            color = Color.white;
        else if (s.trim().toUpperCase().equals("YELLOW"))
            color = Color.yellow;
        return color;
    }
}
