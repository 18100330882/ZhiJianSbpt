package com.yongjie.ZhiJianSbpt.module.util.pdf2word;

public class Main {
    public static void main(String[] args) {

        boolean res = new PdfToWord().pdftoword("D:\\a.pdf");
        System.out.println(res);
    }

}
