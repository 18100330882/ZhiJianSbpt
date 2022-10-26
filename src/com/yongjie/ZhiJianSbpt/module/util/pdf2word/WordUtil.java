/**
 * @Author: 朱卫士
 * @Createtime: 2020年11月10日 下午6:27:43
 * @Copyright: Copyright (c) 2017
 * @Company: 北京永杰友信科技有限公司
 * @Version：1.0
 * @Description:
 */
package com.yongjie.ZhiJianSbpt.module.util.pdf2word;

import org.apache.poi.xwpf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class WordUtil {

    private static FileInputStream in;
    private static FileOutputStream out;

    /**替换word中的字符串
     *
     * @param filePath 文件路径
     * @param map    其中，key--替换的标记    value--替换的值
     */
    public static void replaceAll(String filePath, Map<String, String> map) {

        try {
            in = new FileInputStream(filePath);
            XWPFDocument doc = new XWPFDocument(in);

            //处理段落
            //------------------------------------------------------------------
            List<XWPFParagraph> paragraphs = doc.getParagraphs();
            for (XWPFParagraph paragraph : paragraphs) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    String text = run.getText(0);
                    if (text != null) {
                        boolean isSetText = false;
                        for (Entry<String, String> entry : map.entrySet()) {
                            String key = entry.getKey();
                            String value = entry.getValue();
                            if (text.indexOf(key) != -1) {
                                isSetText = true;
                                text = text.replaceAll(key, value);
                            }
                            if (isSetText) {
                                run.setText(text, 0);
                            }
                        }

                    }

                }
            }

            //------------------------------------------------------------------

            //处理表格
            //------------------------------------------------------------------
            List<XWPFTable> tables = doc.getTables();
            for (XWPFTable table : tables) {
                List<XWPFTableRow> rows = table.getRows();
                for (XWPFTableRow row : rows) {
                    List<XWPFTableCell> cells = row.getTableCells();
                    for (XWPFTableCell cell : cells) {

                        String text = cell.getText();
                        if (text != null) {
                            for (Entry<String, String> entry : map.entrySet()) {
                                String key = entry.getKey();
                                String value = entry.getValue();
                                if (text.equals(key)) {
                                    //删除原单元格值
                                    cell.removeParagraph(0);
                                    //设置新单元格的值
                                    cell.setText(value);
                                }
                            }
                        }
                    }

                }
            }
            //------------------------------------------------------------------

            out = new FileOutputStream(filePath);
            doc.write(out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
