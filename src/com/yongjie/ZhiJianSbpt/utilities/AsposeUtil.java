package com.yongjie.ZhiJianSbpt.utilities;


import com.aspose.cells.Workbook;
import com.aspose.words.*;

import java.awt.*;
import java.io.*;

public class AsposeUtil {


    public static void mergeCells(Cell startCell, Cell endCell) {
        Table parentTable = startCell.getParentRow().getParentTable();

        // Find the row and cell indices for the start and end cell.
        Point startCellPos = new Point(startCell.getParentRow().indexOf(startCell), parentTable.indexOf(startCell.getParentRow()));
        Point endCellPos = new Point(endCell.getParentRow().indexOf(endCell), parentTable.indexOf(endCell.getParentRow()));
        // Create the range of cells to be merged based off these indices. Inverse each index if the end cell if before the start cell.
        Rectangle mergeRange = new Rectangle(Math.min(startCellPos.x, endCellPos.x), Math.min(startCellPos.y, endCellPos.y), Math.abs(endCellPos.x - startCellPos.x) + 1,
                Math.abs(endCellPos.y - startCellPos.y) + 1);

        for (Row row : parentTable.getRows()) {
            for (Cell cell : row.getCells()) {
                Point currentPos = new Point(row.indexOf(cell), parentTable.indexOf(row));

                // Check if the current cell is inside our merge range then merge it.
                if (mergeRange.contains(currentPos)) {
                    if (currentPos.x == mergeRange.x)
                        cell.getCellFormat().setHorizontalMerge(CellMerge.FIRST);
                    else
                        cell.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);

                    if (currentPos.y == mergeRange.y)
                        cell.getCellFormat().setVerticalMerge(CellMerge.FIRST);
                    else
                        cell.getCellFormat().setVerticalMerge(CellMerge.PREVIOUS);
                }
            }
        }
    }

    /**
     * 合并单元格
     *
     * @param startCell 开始单元格
     * @param endCell   结束单元格
     * @param table     表格
     */
    public static void mergeCells(Cell startCell, Cell endCell, Table table) {
        Point startCellPos = new Point(startCell.getParentRow().indexOf(startCell), table.indexOf(startCell.getParentRow()));
        Point endCellPos = new Point(endCell.getParentRow().indexOf(endCell), table.indexOf(endCell.getParentRow()));
        Rectangle mergeRange = new Rectangle(Math.min(startCellPos.x,
                endCellPos.x), Math.min(startCellPos.y, endCellPos.y),
                Math.abs(endCellPos.x - startCellPos.x) + 1,
                Math.abs(endCellPos.y - startCellPos.y) + 1);
        for (Row row : table.getRows()) {
            for (Cell cell : row.getCells()) {
                Point currentPos = new Point(row.indexOf(cell), table.indexOf(row));
                if (mergeRange.contains(currentPos)) {
                    if (currentPos.x == mergeRange.x) {
                        cell.getCellFormat().setHorizontalMerge(CellMerge.FIRST);
                    } else {
                        cell.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);
                    }
                    if (currentPos.y == mergeRange.y) {
                        cell.getCellFormat().setVerticalMerge(CellMerge.FIRST);
                    } else {
                        cell.getCellFormat().setVerticalMerge(CellMerge.PREVIOUS);
                    }
                }
            }
        }

    }

    public boolean getLicense() {
        boolean result = false;
        try {
            //引入license.xml文件,去除水印
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("license.xml");
            //注意此处为apose-slides的jar包
            License aposeLic = new License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean getLicenseExcel() {
        boolean result = false;
        try {

            InputStream is = this.getClass().getClassLoader().getResourceAsStream("license.xml");
            //注意此处为对应aspose-cells的jar包
            com.aspose.cells.License aposeLic = new com.aspose.cells.License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void word2Pdf2(String inpath, String outpath) throws Exception {
        if (!getLicense()) {
            System.out.println("非法------------");
            return;
        }

        long old = System.currentTimeMillis();
        File file = new File(outpath);

        FileOutputStream os = new FileOutputStream(file);

        //解决乱码
        //如果是windows执行，不需要加这个
        //TODO 如果是linux执行，需要添加这个*****
        //FontSettings.setFontsFolder("/usr/share/fonts",true);

        Document doc = new Document(inpath);

        //全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
        doc.save(os, SaveFormat.PDF);

        long now = System.currentTimeMillis();
        System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒");
    }

    public void word2pdf(String path, InputStream wordInput, String wordName) throws FileNotFoundException {
        if (!getLicense()) {
            System.out.println("非法");
            return;
        }

        //新建一个空白pdf文档
        long old = System.currentTimeMillis();
        File file = new File(path + wordName + ".pdf");
        FileOutputStream os = new FileOutputStream(file);

        //Address是将要被转化的word文档
        Document doc = null;
        try {
            doc = new Document(wordInput);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            //全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            doc.save(os, SaveFormat.PDF);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long now = System.currentTimeMillis();
        //转化用时
        System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒");
    }

    public void excel2Pdf(String path, String outpath) throws FileNotFoundException {
        FileOutputStream fileOutputStream = null;
        if (!getLicenseExcel()) {
            System.out.println("非法------------");
            return;
        }

        File file = new File(outpath);
        try {
            Workbook wb = new Workbook(path);
            fileOutputStream = new FileOutputStream(file);
            wb.save(fileOutputStream, com.aspose.cells.SaveFormat.PDF);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
