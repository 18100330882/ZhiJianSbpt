package com.yongjie.ZhiJianSbpt.action;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSONObject;
import com.aspose.words.*;
import com.aspose.words.net.System.Data.DataRow;
import com.aspose.words.net.System.Data.DataSet;
import com.aspose.words.net.System.Data.DataTable;
import com.oreilly.servlet.MultipartRequest;
import com.yongjie.ZhiJianSbpt.action.base.BaseAction;
import com.yongjie.ZhiJianSbpt.model.*;
import com.yongjie.ZhiJianSbpt.module.util.NumberTransfrom;
import com.yongjie.ZhiJianSbpt.service.*;
import com.yongjie.ZhiJianSbpt.utilities.*;
import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.List;

@Controller("ApiShiYsJyjcNlAction")
@Scope("prototype")
public class ApiShiYsJyjcNlAction extends BaseAction<ApiShiYsJyjcNl> {

    @Resource(name = ApiShiYsJyjcNlService.SERVICE_NAME)
    private ApiShiYsJyjcNlService service;

    @Resource(name = ApiShiYsJyjcNlSbptService.SERVICE_NAME)
    private ApiShiYsJyjcNlSbptService nlsbptservice;

    @Resource(name = ApiShiYsJyjcYqsbSbptService.SERVICE_NAME)
    private ApiShiYsJyjcYqsbSbptService yqsbsbptservice;

    @Resource(name = ShiYsJyjcXchcPsbgNlHzService.SERVICE_NAME)
    private ShiYsJyjcXchcPsbgNlHzService psbgNlHzService;

    @Resource(name = ApiShiYsJyjcCdService.SERVICE_NAME)
    private ApiShiYsJyjcCdService cdService;


    public void checkMore() throws Exception {
        String serialNumber = req.getParameter("serialNumber");
        String cdId = req.getParameter("cdId");
        String calOrCma = req.getParameter("calOrCma");
        String type = req.getParameter("type");
        String nlOrSb = req.getParameter("nlOrSb");
        if (StringUtil.isNullOrEmptyZf(serialNumber)) {
            response.getWriter().write("-1");
            return;
        }
        String isSp = req.getParameter("isSp");
        if ("1".equals(isSp)) {
            isSp = "食品";
        } else if ("2".equals(isSp)) {
            isSp = "非食品";
        }

        ArrayList listPsbgNl = psbgNlHzService.getListByFlowInstId(serialNumber, cdId, calOrCma, type, nlOrSb, isSp);
        if (listPsbgNl.size() > 0) {//已经存在，编辑
            response.getWriter().write("1");
            return;
        }
        response.getWriter().write("0");
    }

    public void getNlFileList() throws Exception {
        String resultJson = "";
        String serialNumber = req.getParameter("serialNumber");
        String nlorSb = req.getParameter("nlorSb");
        HashMap<Object, Object> reqMap = new HashMap<>();
        reqMap.put("serialNumber", serialNumber);
        reqMap.put("nlorSb", nlorSb);
        Map<String, Object> nlFileList = psbgNlHzService.getNlFileList(reqMap);
        resultJson = R.page(nlFileList);
        response.getWriter().write(resultJson);
    }

    public void deleteEvent() throws Exception {
        String resultJson = "";
        String id = req.getParameter("id");
        if (StringUtil.isNullOrEmpty(id)) {
            resultJson = R.ok("删除失败，参数有误！");
            response.getWriter().write(resultJson);
            return;
        }
        ShiYsJyjcXchcPsbgNlHz psbgEntity = psbgNlHzService.queryById(Long.parseLong(id));
        if (psbgEntity == null || StringUtil.isNullOrEmpty(psbgEntity.getSerialNumber())) {
            resultJson = R.ok("删除失败，数据不存在，请刷新后再试！");
            response.getWriter().write(resultJson);
            return;
        }
        HashMap<String, Object> reqMap = new HashMap();
        reqMap.put("serialNumber", psbgEntity.getSerialNumber());
        reqMap.put("flag", psbgEntity.getNlOrSb());
        reqMap.put("typeName", psbgEntity.getIsSp());
        reqMap.put("siteName", psbgEntity.getCddz());
        service.deleteEvent(reqMap);
        psbgNlHzService.delete(Long.parseLong(id));
        resultJson = R.ok("操作成功！");
        response.getWriter().write(resultJson);
    }

    public void getDetailList() throws Exception {
        String resultJson = "";
        String psbgId = req.getParameter("psbgId");
        if (StringUtil.isNullOrEmpty(psbgId)) {
            resultJson = R.ok("查询失败，参数有误！");
            response.getWriter().write(resultJson);
            return;
        }
        ShiYsJyjcXchcPsbgNlHz psbgEntity = psbgNlHzService.queryById(Long.valueOf(psbgId));
        HashMap<String, Object> reqMap = new HashMap<>();
        reqMap.put("serialNumber", psbgEntity.getSerialNumber());
        reqMap.put("flag", psbgEntity.getNlOrSb());
        reqMap.put("typeName", psbgEntity.getIsSp());
        reqMap.put("siteName", psbgEntity.getCddz());
        Map<String, Object> nlDetailList = service.getDetailList(reqMap);
        String json = JSON.Encode(nlDetailList.get(R.DATA_NAME));
        resultJson = json.substring(json.indexOf('['), json.indexOf(']') + 1);
        response.getWriter().write(resultJson);
    }

    public void deleteRow() throws Exception {
        String resultJson = "";
        String id = req.getParameter("id");
        String psbgId = req.getParameter("psbgId");
        if (StringUtil.isNullOrEmpty(id)) {
            resultJson = R.ok("删除失败，参数有误！");
            response.getWriter().write(resultJson);
            return;
        }
        service.deleteRow(Long.parseLong(id));
        // [检验检测能力] 重新 设置 序号
        ShiYsJyjcXchcPsbgNlHz psbgEntity = psbgNlHzService.queryById(Long.valueOf(psbgId));
        HashMap<String, Object> reqMap = new HashMap<>();
        reqMap.put("serialNumber", psbgEntity.getSerialNumber());
        reqMap.put("flag", psbgEntity.getNlOrSb());
        reqMap.put("typeName", psbgEntity.getIsSp());
        reqMap.put("siteName", psbgEntity.getCddz());
        if (psbgEntity.getNlOrSb() == 0) {
            List<ApiShiYsJyjcNl> entityList = service.reSetXh(reqMap);
            service.updateList(entityList);
        } else {
            List<ApiShiYsJyjcNl> entityList = service.reSetXhYqsb(reqMap);
            service.updateList(entityList);
        }

        resultJson = R.ok("操作成功！");
        response.getWriter().write(resultJson);
    }

    public void saveRow() throws Exception {
        String resultJson = "";
        String id = req.getParameter("id");
        String psbgId = req.getParameter("psbgId");
        String reqJson = req.getParameter("row");
        if (StringUtil.isNullOrEmpty(psbgId)) {
            resultJson = R.ok("保存失败，参数有误！");
            response.getWriter().write(resultJson);
            return;
        }
        ApiShiYsJyjcNl entity = RequestDataCheckUtil.saveRow(reqJson);
        // 更新、新增
        if (!StringUtil.isNullOrEmpty(id) && Long.parseLong(id) > 0) {
            entity.setId(Long.parseLong(id));
            service.updateRow(entity);
        } else {
            ShiYsJyjcXchcPsbgNlHz psbgEntity = psbgNlHzService.queryById(Long.valueOf(psbgId));
            // 设置 公共参数
            entity.setSerialNumber(psbgEntity.getSerialNumber());
            entity.setSiteName(psbgEntity.getCddz());
            entity.setTypeName(psbgEntity.getIsSp());
            entity.setFlag(String.valueOf(psbgEntity.getNlOrSb()));
            entity.setStatus("0");
            service.addRow(entity);
        }

        // [检验检测能力] 重新 设置 序号
        HashMap<String, Object> reqMap = new HashMap<>();
        reqMap.put("serialNumber", entity.getSerialNumber());
        reqMap.put("flag", entity.getFlag());
        reqMap.put("typeName", entity.getTypeName());
        reqMap.put("siteName", entity.getSiteName());
        if ("0".equals(entity.getFlag())) {
            List<ApiShiYsJyjcNl> entityList = service.reSetXh(reqMap);
            service.updateList(entityList);
        } else {
            List<ApiShiYsJyjcNl> entityList = service.reSetXhYqsb(reqMap);
            service.updateList(entityList);
        }
        resultJson = R.ok("操作成功！");
        response.getWriter().write(resultJson);
    }

    public void initLoad() throws Exception {
        String resultJson = "";
        String psbgId = req.getParameter("psbgId");
        ShiYsJyjcXchcPsbgNlHz psbgEntity = psbgNlHzService.queryById(Long.valueOf(psbgId));
        resultJson = R.page(psbgEntity);
        response.getWriter().write(resultJson);
    }

    public void sbdscnlword() throws Exception {
        try {

            InputStream is = AsposeWordsPrintDocument.class.getClassLoader().getResourceAsStream("license.xml");
            License aposeLic = new License();
            aposeLic.setLicense(is);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String resultJson = "";
        String cddz = req.getParameter("cddz");
        String serialNumber = req.getParameter("serialNumber");
        String isSp = req.getParameter("isSp");
        String id = req.getParameter("id");
        //status为1是删除，0是正常，因此需要查询0
        String status = "0";


        ArrayList<Map<String, String>> listLeib = psbgNlHzService.listLeib(serialNumber, cddz, status, isSp);
        //String srcurl="D:/sxzjUpload/files/jyjcnlword/sbdjyjcnl.docx";
        String srcurl = req.getSession().getServletContext().getRealPath("jsp/jyjc/rzrk/zzrd/doc/sbdjyjcnl.docx");
        //目标word
        //选定位置
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 0);
        int year1 = calendar.get(Calendar.YEAR);// 月份
        int month1 = calendar.get(Calendar.MONTH) + 1;// 日期
        int date1 = calendar.get(Calendar.DATE);
        String date = year1 + "-" + month1 + "-" + date1;//2017-2-4
        File dir = new File(SysFinalRecource.SECOND_LEVEL_DIRECTORY + date + "/"); //D:/sxzjUpload/files/2017-2-4
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String fileName = SysFinalRecource.SECOND_LEVEL_DIRECTORY + date + "/" + UUID.randomUUID().toString() + ".doc";

        String filePath = date + "/" + UUID.randomUUID().toString() + ".doc";


        try {
            com.aspose.words.Document doc = new com.aspose.words.Document(new FileInputStream(srcurl));

            DataSet dataSet = new DataSet();


            DataTable staffTable = new DataTable("table1");

            staffTable.getColumns().add("xh");
            staffTable.getColumns().add("cplb");
            staffTable.getColumns().add("cpxh");
            staffTable.getColumns().add("cpmc");
            staffTable.getColumns().add("yjbz");
            staffTable.getColumns().add("xzfw");
            staffTable.getColumns().add("sm");


            int xh = 0;
            int dxh = 1;
            for (int j = 0; j < listLeib.size(); j++) {//leib

                Map<String, String> map = listLeib.get(j);
                String leibName = map.get("YJFL");


                //根据大类别查所有对应的数据
                ArrayList<ApiShiYsJyjcNlSbpt> nlList2 = psbgNlHzService.listCpmc1(serialNumber, leibName, cddz, status, isSp);

                for (int i = 0; i < nlList2.size(); i++) {

                    DataRow row = staffTable.newRow();

                    if (!org.apache.commons.lang3.StringUtils.isEmpty(nlList2.get(0).getEjfl())) {
                        if (i == 0) {
                            //大类别横合并
                            DataRow row1 = staffTable.newRow();
                            row1.set(0, NumberTransfrom.transfrom((j + 1) + ""));
                            row1.set(1, leibName);
                            row1.set(2, leibName);
                            row1.set(3, leibName);
                            row1.set(4, leibName);
                            row1.set(5, leibName);
                            row1.set(6, leibName);
                            staffTable.getRows().add(row1);
                            dxh++;
                        }
                        if (i == 0) { //第一行不判断
                            xh++;
                            row.set(0, xh);

                        } else {

                            String yileibe = "";
                            String erleibe = "";
                            if (!org.apache.commons.lang3.StringUtils.isEmpty(nlList2.get(i).getEjfl())) {
                                yileibe = nlList2.get(i).getEjfl();
                            }
                            if (!org.apache.commons.lang3.StringUtils.isEmpty(nlList2.get(i - 1).getEjfl())) {
                                erleibe = nlList2.get(i - 1).getEjfl();
                            }

                            if (yileibe.equals(erleibe)) {  //判断这个和上一个相等,相等
                                row.set(0, xh);
                            } else {
                                xh++;
                                row.set(0, xh);
                            }

                        }
                        row.set(1, nlList2.get(i).getEjfl());
                        row.set(2, nlList2.get(i).getSortingNumber());
                        row.set(3, nlList2.get(i).getSijfl());
                        row.set(4, nlList2.get(i).getYjbz());
                        row.set(5, nlList2.get(i).getLimits());
                        row.set(6, nlList2.get(i).getInstructions());
                        staffTable.getRows().add(row);
                    } else {
                        if (i == 0) { //第一行不判断
                            xh++;
                            row.set(0, xh);

                        } else {

                            String yileibe = "";
                            String erleibe = "";
                            if (!org.apache.commons.lang3.StringUtils.isEmpty(nlList2.get(i).getEjfl())) {
                                yileibe = nlList2.get(i).getEjfl();
                            }
                            if (!org.apache.commons.lang3.StringUtils.isEmpty(nlList2.get(i - 1).getEjfl())) {
                                erleibe = nlList2.get(i - 1).getEjfl();
                            }

                            if (yileibe.equals(erleibe)) {  //判断这个和上一个相等,相等
                                row.set(0, xh);
                            } else {
                                xh++;
                                row.set(0, xh);
                            }

                        }
                        row.set(1, nlList2.get(i).getYjfl());
                        row.set(2, nlList2.get(i).getSortingNumber());
                        row.set(3, nlList2.get(i).getSijfl());
                        row.set(4, nlList2.get(i).getYjbz());
                        row.set(5, nlList2.get(i).getLimits());
                        row.set(6, nlList2.get(i).getInstructions());
                        staffTable.getRows().add(row);
                    }
                }
            }


            dataSet.getTables().add(staffTable);
            String aaa = "这家机构地址";

            doc.getMailMerge().execute(new String[]{"jgdz"}, new String[]{cddz});

            doc.getMailMerge().executeWithRegions(staffTable);

            com.aspose.words.Table table = (com.aspose.words.Table) doc.getChild(NodeType.TABLE, 0, true);//获取第一个table

            DocumentBuilder builder = new DocumentBuilder(doc);


            int j = 0;
            for (int i = 4; i < table.getRows().getCount(); i++) {  //模板从第四行开始

                if (i == 4) { //第一行不判断

                } else {
                    //System.out.println("本行:" + table.getRows().get(i).getCells().get(2).getText());
                    //System.out.println("上一行:" + table.getRows().get(i - 1).getCells().get(2).getText());

                    String yileibe = "";
                    String erleibe = "";
                    if (!org.apache.commons.lang3.StringUtils.isEmpty(table.getRows().get(i).getCells().get(2).getText())) {
                        yileibe = table.getRows().get(i).getCells().get(2).getText();
                    }
                    if (!org.apache.commons.lang3.StringUtils.isEmpty(table.getRows().get(i).getCells().get(2).getText())) {
                        erleibe = table.getRows().get(i - 1).getCells().get(2).getText();
                    }


                    //不是最后一行
                    if (i != table.getRows().getCount() - 1 && yileibe.equals(erleibe)) {  //判断这个和上一个相等,相等:合并行数+1;
                        j++;

                        // 判断本行是否是最后一行
                    } else if (i == table.getRows().getCount() - 1 && !yileibe.equals(erleibe)) {
                        Cell cellStartRange = table.getRows().get(i - j - 1).getCells().get(2); //开始合并行数: 当前行数-相同行数
                        Cell cellEndRange = table.getRows().get(i - 1).getCells().get(2); //    最后一行和上面相等:   结束合并行数: 当前行数+1
                        AsposeUtil.mergeCells(cellStartRange, cellEndRange);  //  合并:  开始行数- 结束行数


                        builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);    //垂直居中对齐
                        builder.getParagraphFormat().setAlignment(com.aspose.words.ParagraphAlignment.CENTER);         //水平居中对齐

                    } else {

                        String text1 = table.getRows().get(i - 1).getCells().get(1).getText();
                        String text2 = table.getRows().get(i - 1).getCells().get(2).getText();
                        String text3 = table.getRows().get(i - 1).getCells().get(3).getText();
                        if (text1.equals(text2) && text1.equals(text3)) {  //防止大类别和产品类别相等,合并出错

                        } else {

                            Cell cellStartRange = table.getRows().get(i - j - 1).getCells().get(2); //开始合并行数: 当前行数-相同行数
                            Cell cellEndRange = table.getRows().get(i - 1).getCells().get(2); // 结束合并行数: 当前行数
                            AsposeUtil.mergeCells(cellStartRange, cellEndRange);

                            builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);    //垂直居中对齐
                            builder.getParagraphFormat().setAlignment(com.aspose.words.ParagraphAlignment.CENTER);         //水平居中对齐
                            j = 0;

                        }
                    }
                }
            }

            int m = 0;
            for (int i = 4; i < table.getRows().getCount(); i++) {  //模板从第四行开始


                if (i == 4) { //第一行不判断
                    String text1 = table.getRows().get(i).getCells().get(1).getText();
                    String text2 = table.getRows().get(i).getCells().get(2).getText();
                    String text3 = table.getRows().get(i).getCells().get(3).getText();
                    if (text1.equals(text2) && text1.equals(text3)) {  //防止大类别和产品类别相等,合并出错
                        AsposeUtil.mergeCells(table.getRows().get(i).getCells().get(1), table.getRows().get(i).getCells().get(6), table);
                    }


                } else {
                    //System.out.println("本行:" + table.getRows().get(i).getCells().get(1).getText());
                    //System.out.println("上一行:" + table.getRows().get(i - 1).getCells().get(1).getText());

                    String yileibe = "";
                    String erleibe = "";
                    if (!org.apache.commons.lang3.StringUtils.isEmpty(table.getRows().get(i).getCells().get(1).getText())) {
                        yileibe = table.getRows().get(i).getCells().get(1).getText();
                    }
                    if (!org.apache.commons.lang3.StringUtils.isEmpty(table.getRows().get(i).getCells().get(1).getText())) {
                        erleibe = table.getRows().get(i - 1).getCells().get(1).getText();
                    }
                    String text1 = table.getRows().get(i - 1).getCells().get(1).getText();
                    String text2 = table.getRows().get(i - 1).getCells().get(2).getText();
                    String text3 = table.getRows().get(i - 1).getCells().get(3).getText();
                    if (text1.equals(text2) && text1.equals(text3)) {  //防止大类别和产品类别相等,合并出错
                        yileibe += "合并";
                        AsposeUtil.mergeCells(table.getRows().get(i - 1).getCells().get(1), table.getRows().get(i - 1).getCells().get(6), table);
                    }


                    //不是最后一行
                    if (i != table.getRows().getCount() - 1 && yileibe.equals(erleibe)) {  //判断这个和上一个相等,相等:合并行数+1;
                        m++;

                        // 判断本行是否是最后一行
                    } else if (i == table.getRows().getCount() - 1 && !yileibe.equals(erleibe)) {
                        Cell cellStartRange = table.getRows().get(i - m - 1).getCells().get(1); //开始合并行数: 当前行数-相同行数
                        Cell cellEndRange = table.getRows().get(i - 1).getCells().get(1); //    最后一行和上面相等:   结束合并行数: 当前行数+1
                        AsposeUtil.mergeCells(cellStartRange, cellEndRange);  //  合并:  开始行数- 结束行数

                        Cell cellStartRange2 = table.getRows().get(i - m - 1).getCells().get(0); //开始合并行数: 当前行数-相同行数
                        Cell cellEndRange2 = table.getRows().get(i - 1).getCells().get(0); //    最后一行和上面相等:   结束合并行数: 当前行数+1

                        AsposeUtil.mergeCells(cellStartRange2, cellEndRange2);  //  合并:  开始行数- 结束行数

                        builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);    //垂直居中对齐
                        builder.getParagraphFormat().setAlignment(com.aspose.words.ParagraphAlignment.CENTER);         //水平居中对齐

                    } else {


                        Cell cellStartRange = table.getRows().get(i - m - 1).getCells().get(1); //开始合并行数: 当前行数-相同行数
                        Cell cellEndRange = table.getRows().get(i).getCells().get(1); // 结束合并行数: 当前行数
                        AsposeUtil.mergeCells(cellStartRange, cellEndRange);

                        Cell cellStartRange2 = table.getRows().get(i - m - 1).getCells().get(0); //开始合并行数: 当前行数-相同行数
                        Cell cellEndRange2 = table.getRows().get(i).getCells().get(0); //    最后一行和上面相等:   结束合并行数: 当前行数+1
                        AsposeUtil.mergeCells(cellStartRange2, cellEndRange2);  //  合并:  开始行数- 结束行数

                        builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);    //垂直居中对齐
                        builder.getParagraphFormat().setAlignment(com.aspose.words.ParagraphAlignment.CENTER);         //水平居中对齐
                        m = 0;
                    }
                }

            }


            doc.save(fileName);

            resultJson = R.ok("生成word成功！");

        } catch (Exception e) {
            e.printStackTrace();
            resultJson = R.error(e.getMessage());
        }

        filePath = fileName.substring(20);

        if (!org.apache.commons.lang3.StringUtils.isEmpty(id)) {
            ShiYsJyjcXchcPsbgNlHz model = psbgNlHzService.queryById(Long.parseLong(id));
            model.setFilePathWord(filePath);
            model.setIsSave("1");
            psbgNlHzService.edit(model);
        }
        response.getWriter().print(resultJson);

    }

    public void scyqsbword() throws Exception {
        try {

            InputStream is = AsposeWordsPrintDocument.class.getClassLoader().getResourceAsStream("license.xml");
            License aposeLic = new License();
            aposeLic.setLicense(is);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String resultJson = "";
        String cddz = req.getParameter("cddz");
        String serialNumber = req.getParameter("serialNumber");
        String isSp = req.getParameter("isSp");
        String id = req.getParameter("id");
        //status为1是删除，0是正常，因此需要查询0
        String status = "0";


        String srcurl = req.getSession().getServletContext().getRealPath("jsp/jyjc/rzrk/zzrd/doc/sbdyqsb.docx");

        //目标word
        //选定位置
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 0);
        int year1 = calendar.get(Calendar.YEAR);// 月份
        int month1 = calendar.get(Calendar.MONTH) + 1;// 日期
        int date1 = calendar.get(Calendar.DATE);
        String date = year1 + "-" + month1 + "-" + date1;//2017-2-4
        File dir = new File(SysFinalRecource.SECOND_LEVEL_DIRECTORY + date + "/"); //D:/sxzjUpload/files/2017-2-4
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String fileName = SysFinalRecource.SECOND_LEVEL_DIRECTORY + date + "/" + UUID.randomUUID().toString() + ".doc";

        String filePath = date + "/" + UUID.randomUUID().toString() + ".doc";


        try {
            com.aspose.words.Document doc = new com.aspose.words.Document(new FileInputStream(srcurl));

            DataSet dataSet = new DataSet();

            DataTable staffTable = new DataTable("table1");

            staffTable.getColumns().add("xh");
            staffTable.getColumns().add("cplb");
            staffTable.getColumns().add("cpxh");
            staffTable.getColumns().add("cpmc");
            staffTable.getColumns().add("yjbz");
            staffTable.getColumns().add("yqmc");
            staffTable.getColumns().add("yqxh");
            staffTable.getColumns().add("clfw");
            staffTable.getColumns().add("syfs");
            staffTable.getColumns().add("yxrq");
            staffTable.getColumns().add("jg");

            int xh = 0;
            ArrayList<ApiShiYsJyjcYqsbSbpt> nlList2 = psbgNlHzService.yqsblist(serialNumber, cddz, status, isSp);
            for (int i = 0; i < nlList2.size(); i++) {

                DataRow row = staffTable.newRow();

                if (i == 0) { //第一行不判断
                    xh++;
                    row.set(0, xh);

                } else {

                    String yileibe = "";
                    String erleibe = "";
                    if (!org.apache.commons.lang3.StringUtils.isEmpty(nlList2.get(i).getEjfl())) {
                        yileibe = nlList2.get(i).getEjfl();
                    }
                    if (!org.apache.commons.lang3.StringUtils.isEmpty(nlList2.get(i - 1).getEjfl())) {
                        erleibe = nlList2.get(i - 1).getEjfl();
                    }
                    if (yileibe.equals(erleibe)) {  //判断这个和上一个相等,相等
                        row.set(0, xh);
                    } else {
                        xh++;
                        row.set(0, xh);
                    }

                }
                row.set(1, nlList2.get(i).getEjfl());
                row.set(2, nlList2.get(i).getSortingNumber());
                row.set(3, nlList2.get(i).getProductName());
                row.set(4, nlList2.get(i).getYjbz());
                row.set(5, nlList2.get(i).getBzwzName());
                row.set(6, nlList2.get(i).getIdentifier());
                row.set(7, nlList2.get(i).getBzwzClfw());
                row.set(8, nlList2.get(i).getSyfs());
                row.set(9, nlList2.get(i).getYqsbYxrq());
                row.set(10, nlList2.get(i).getQrjg());
                staffTable.getRows().add(row);

            }

            HashMap map = new HashMap();
            map.put("serialNumber", serialNumber);
            map.put("status", status);
            map.put("siteName", cddz);
            map.put("typeName", isSp);
            ArrayList<ApiShiYsJyjcYqsbSbpt> beizhuList = psbgNlHzService.yqsblistToBeizhu(map);
            String beizhu = "";
            for (int i = 0; i < beizhuList.size(); i++) {
                beizhu += beizhuList.get(i).getRemark();
            }
            if (!StringUtils.isNullOrEmpty(beizhu) && !beizhu.contains("申请时，为了简化此表的填写，参数相同的不重复填写，序号可以不连续")) {//最后添加备注行
                DataRow row1 = staffTable.newRow();
                row1.set(0, beizhu);
                row1.set(1, beizhu);
                row1.set(2, beizhu);
                row1.set(3, beizhu);
                row1.set(4, beizhu);
                row1.set(5, beizhu);
                row1.set(6, beizhu);
                row1.set(7, beizhu);
                row1.set(8, beizhu);
                row1.set(9, beizhu);
                row1.set(10, beizhu);
                staffTable.getRows().add(row1);
            }


            dataSet.getTables().add(staffTable);

            doc.getMailMerge().execute(new String[]{"jgdz"}, new String[]{cddz});

            doc.getMailMerge().executeWithRegions(staffTable);

            com.aspose.words.Table table = (com.aspose.words.Table) doc.getChild(NodeType.TABLE, 0, true);//获取第一个table

            DocumentBuilder builder = new DocumentBuilder(doc);


            int m = 0;
            for (int i = 4; i < table.getRows().getCount(); i++) {  //模板从第四行开始


                if (i == 4) { //第一行不判断


                } else {
                    //System.out.println("本行:" + table.getRows().get(i).getCells().get(1).getText());
                    //System.out.println("上一行:" + table.getRows().get(i - 1).getCells().get(1).getText());

                    String yileibe = "";
                    String erleibe = "";
                    if (!org.apache.commons.lang3.StringUtils.isEmpty(table.getRows().get(i).getCells().get(1).getText())) {
                        yileibe = table.getRows().get(i).getCells().get(1).getText();
                    }
                    if (!org.apache.commons.lang3.StringUtils.isEmpty(table.getRows().get(i).getCells().get(1).getText())) {
                        erleibe = table.getRows().get(i - 1).getCells().get(1).getText();
                    }


                    //不是最后一行
                    if (i != table.getRows().getCount() - 1 && yileibe.equals(erleibe)) {  //判断这个和上一个相等,相等:合并行数+1;
                        m++;

                        // 判断本行是否是最后一行
                    } else if (i == table.getRows().getCount() - 1 && !yileibe.equals(erleibe)) {
                        Cell cellStartRange = table.getRows().get(i - m - 1).getCells().get(1); //开始合并行数: 当前行数-相同行数
                        Cell cellEndRange = table.getRows().get(i - 1).getCells().get(1); //    最后一行和上面相等:   结束合并行数: 当前行数+1
                        AsposeUtil.mergeCells(cellStartRange, cellEndRange);  //  合并:  开始行数- 结束行数

                        Cell cellStartRange2 = table.getRows().get(i - m - 1).getCells().get(0); //开始合并行数: 当前行数-相同行数
                        Cell cellEndRange2 = table.getRows().get(i - 1).getCells().get(0); //    最后一行和上面相等:   结束合并行数: 当前行数+1

                        AsposeUtil.mergeCells(cellStartRange2, cellEndRange2);  //  合并:  开始行数- 结束行数

                        builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);    //垂直居中对齐
                        builder.getParagraphFormat().setAlignment(com.aspose.words.ParagraphAlignment.CENTER);         //水平居中对齐

                    } else {


                        Cell cellStartRange = table.getRows().get(i - m - 1).getCells().get(1); //开始合并行数: 当前行数-相同行数
                        Cell cellEndRange = table.getRows().get(i).getCells().get(1); // 结束合并行数: 当前行数
                        AsposeUtil.mergeCells(cellStartRange, cellEndRange);

                        Cell cellStartRange2 = table.getRows().get(i - m - 1).getCells().get(0); //开始合并行数: 当前行数-相同行数
                        Cell cellEndRange2 = table.getRows().get(i).getCells().get(0); //    最后一行和上面相等:   结束合并行数: 当前行数+1
                        AsposeUtil.mergeCells(cellStartRange2, cellEndRange2);  //  合并:  开始行数- 结束行数

                        builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);    //垂直居中对齐
                        builder.getParagraphFormat().setAlignment(com.aspose.words.ParagraphAlignment.CENTER);         //水平居中对齐
                        m = 0;
                    }
                }

            }


            int j = 0;
            for (int i = 4; i < table.getRows().getCount(); i++) {  //模板从第四行开始

                if (i == 4) { //第一行不判断

                } else {
                    System.out.println("本行:" + table.getRows().get(i).getCells().get(2).getText());
                    System.out.println("上一行:" + table.getRows().get(i - 1).getCells().get(2).getText());

                    String yileibe = "";
                    String erleibe = "";
                    if (!org.apache.commons.lang3.StringUtils.isEmpty(table.getRows().get(i).getCells().get(2).getText())) {
                        yileibe = table.getRows().get(i).getCells().get(2).getText();
                    }
                    if (!org.apache.commons.lang3.StringUtils.isEmpty(table.getRows().get(i).getCells().get(2).getText())) {
                        erleibe = table.getRows().get(i - 1).getCells().get(2).getText();
                    }


                    //不是最后一行
                    if (i != table.getRows().getCount() - 1 && yileibe.equals(erleibe)) {  //判断这个和上一个相等,相等:合并行数+1;
                        j++;

                        // 判断本行是否是最后一行
                    } else if (i == table.getRows().getCount() - 1 && !yileibe.equals(erleibe)) {
                        Cell cellStartRange = table.getRows().get(i - j - 1).getCells().get(2); //开始合并行数: 当前行数-相同行数
                        Cell cellEndRange = table.getRows().get(i - 1).getCells().get(2); //    最后一行和上面相等:   结束合并行数: 当前行数+1
                        AsposeUtil.mergeCells(cellStartRange, cellEndRange);  //  合并:  开始行数- 结束行数


                        builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);    //垂直居中对齐
                        builder.getParagraphFormat().setAlignment(com.aspose.words.ParagraphAlignment.CENTER);         //水平居中对齐

                    } else {

                        String text1 = table.getRows().get(i - 1).getCells().get(1).getText();
                        String text2 = table.getRows().get(i - 1).getCells().get(2).getText();
                        String text3 = table.getRows().get(i - 1).getCells().get(3).getText();
                        if (text1.equals(text2) && text1.equals(text3)) {  //防止大类别和产品类别相等,合并出错

                        } else {

                            Cell cellStartRange = table.getRows().get(i - j - 1).getCells().get(2); //开始合并行数: 当前行数-相同行数
                            Cell cellEndRange = table.getRows().get(i - 1).getCells().get(2); // 结束合并行数: 当前行数
                            AsposeUtil.mergeCells(cellStartRange, cellEndRange);

                            builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);    //垂直居中对齐
                            builder.getParagraphFormat().setAlignment(com.aspose.words.ParagraphAlignment.CENTER);         //水平居中对齐
                            j = 0;

                        }


                    }
                }

            }

            if (!StringUtils.isNullOrEmpty(beizhu) && !beizhu.contains("申请时，为了简化此表的填写，参数相同的不重复填写，序号可以不连续")) {//最后合并备注行
                AsposeUtil.mergeCells(table.getRows().get(table.getRows().getCount() - 1).getCells().get(0), table.getRows().get(table.getRows().getCount() - 1).getCells().get(10), table);
            }

            doc.save(fileName);

            System.out.println(111);
            //resultJson = R.ok("生成word成功！");
        } catch (Exception e) {
            e.printStackTrace();
            resultJson = R.error(e.getMessage());
        }
        filePath = fileName.substring(20);
        /*if (!org.apache.commons.lang3.StringUtils.isEmpty(id)) {
            ShiYsJyjcXchcPsbgNlHz model = psbgNlHzService.queryById(Long.parseLong(id));
            model.setFilePathWord(filePath);
            model.setIsSave("1");
            psbgNlHzService.edit(model);
        }*/

        String fileType = "doc";
        String resulte = "jsp/jyjc/rzrk/common/yqsbShowDoc.jsp" + "?filePath=" + filePath + "&fileType=" + fileType + "&psbgId=" + id;

        response.getWriter().write(resulte);

    }

    public void deleteYqsbSbptEvent() throws Exception {
        String resultJson = "";
        String id = req.getParameter("id");
        if (StringUtil.isNullOrEmpty(id)) {
            resultJson = R.ok("删除失败，参数有误！");
            response.getWriter().write(resultJson);
            return;
        }
        ShiYsJyjcXchcPsbgNlHz psbgEntity = psbgNlHzService.queryById(Long.parseLong(id));
        if (psbgEntity == null || StringUtil.isNullOrEmpty(psbgEntity.getSerialNumber())) {
            resultJson = R.ok("删除失败，数据不存在，请刷新后再试！");
            response.getWriter().write(resultJson);
            return;
        }
        HashMap<String, Object> reqMap = new HashMap();
        reqMap.put("serialNumber", psbgEntity.getSerialNumber());
        reqMap.put("flag", psbgEntity.getNlOrSb());
        reqMap.put("typeName", psbgEntity.getIsSp());
        reqMap.put("siteName", psbgEntity.getCddz());
        yqsbsbptservice.deleteEvent(reqMap);
        psbgNlHzService.delete(Long.parseLong(id));
        resultJson = R.ok("操作成功！");
        response.getWriter().write(resultJson);
    }

    public void deleteNlsbptEvent() throws Exception {
        String resultJson = "";
        String id = req.getParameter("id");
        if (StringUtil.isNullOrEmpty(id)) {
            resultJson = R.ok("删除失败，参数有误！");
            response.getWriter().write(resultJson);
            return;
        }
        ShiYsJyjcXchcPsbgNlHz psbgEntity = psbgNlHzService.queryById(Long.parseLong(id));
        if (psbgEntity == null || StringUtil.isNullOrEmpty(psbgEntity.getSerialNumber())) {
            resultJson = R.ok("删除失败，数据不存在，请刷新后再试！");
            response.getWriter().write(resultJson);
            return;
        }
        HashMap<String, Object> reqMap = new HashMap();
        reqMap.put("serialNumber", psbgEntity.getSerialNumber());
        reqMap.put("flag", psbgEntity.getNlOrSb());
        reqMap.put("typeName", psbgEntity.getIsSp());
        reqMap.put("siteName", psbgEntity.getCddz());
        nlsbptservice.deleteEvent(reqMap);
        psbgNlHzService.delete(Long.parseLong(id));
        resultJson = R.ok("操作成功！");
        response.getWriter().write(resultJson);
    }

    public void delnl() {
        String params = req.getParameter("params");
        service.delnl(params);
    }

    public void updateNl() throws Exception {
        String resultJson = "";
        String json = req.getParameter("json");
        HashMap<String, Object> map = JSONObject.parseObject(json, HashMap.class);
        if (map.size() <= 0) {
            resultJson = R.error("保存失败");
            response.getWriter().print(resultJson);
            return;
        }
        ApiShiYsJyjcNl apiShiYsJyjcNl = service.getById(Long.valueOf(map.get("ID").toString()));
        apiShiYsJyjcNl = (ApiShiYsJyjcNl) HashmapAndEntityTransfer.hashmapTransferToEntity2(apiShiYsJyjcNl, map);
        service.update(apiShiYsJyjcNl);
    }

    /**
     * 保存生成word
     *
     * @throws Exception
     */
    public void returnNlWord() throws Exception {
        try {
            InputStream is = AsposeWordsPrintDocument.class.getClassLoader().getResourceAsStream("license.xml");
            License aposeLic = new License();
            aposeLic.setLicense(is);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String psbgId = req.getParameter("psbgId");
        if (StringUtil.isNullOrEmpty(psbgId)) {
            response.getWriter().write(R.ok("参数有误"));
            return;
        }

        //获取上传附件的信息
        ShiYsJyjcXchcPsbgNlHz psbgEntity = psbgNlHzService.queryById(Long.valueOf(psbgId));
        HashMap<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("serialNumber", psbgEntity.getSerialNumber());
        reqMap.put("flag", psbgEntity.getNlOrSb());
        reqMap.put("typeName", psbgEntity.getIsSp());
        reqMap.put("siteName", psbgEntity.getCddz());

        //获取一级分类(大类别)
        List yjflList = nlsbptservice.queryYjfl(reqMap);
        String srcurl = req.getSession().getServletContext().getRealPath("jsp/jyjc/rzrk/zzrd/doc/jyjcnl.docx");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 0);
        int year1 = calendar.get(Calendar.YEAR);// 月份
        int month1 = calendar.get(Calendar.MONTH) + 1;// 日期
        int date1 = calendar.get(Calendar.DATE);
        String date = year1 + "-" + month1 + "-" + date1;//2017-2-4
        File dir = new File(SysFinalRecource.SECOND_LEVEL_DIRECTORY + date + "/"); //D:/sxzjUpload/files/2017-2-4
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String fileName = SysFinalRecource.SECOND_LEVEL_DIRECTORY + date + "/" + UUID.randomUUID().toString() + ".doc";
        String filePath = fileName.substring(20);
        Document doc = new Document(new FileInputStream(srcurl));
        DataSet dataSet = new DataSet();
        DataTable staffTable = new DataTable("table1");
        staffTable.getColumns().add("xh");
        staffTable.getColumns().add("cplb");
        staffTable.getColumns().add("cpxh");
        staffTable.getColumns().add("cpmc");
        staffTable.getColumns().add("yjbz");
        staffTable.getColumns().add("xzfw");
        staffTable.getColumns().add("sm");
        int xh = 0;
        int dxh = 1;
        //根据一级分类插入数据
        try {
            for (int j = 0; j < yjflList.size(); j++) {
                HashMap map = (HashMap) yjflList.get(j);
                String yjflName = map.get("YJFL").toString();
                //根据一级分类查所有对应的数据
                reqMap.put("yjflName", yjflName);
                ArrayList<ApiShiYsJyjcNlSbpt> nlList = nlsbptservice.queryListByTj(reqMap);
                //循环插入
                for (int i = 0; i < nlList.size(); i++) {
                    DataRow row = staffTable.newRow();
                    if (!StringUtils.isNullOrEmpty(nlList.get(0).getEjfl())) {
                        if (i == 0) {
                            //大类别横合并
                            DataRow row1 = staffTable.newRow();
                            row1.set(0, NumberTransfrom.transfrom((j + 1) + ""));
                            row1.set(1, yjflName);
                            row1.set(2, yjflName);
                            row1.set(3, yjflName);
                            row1.set(4, yjflName);
                            row1.set(5, yjflName);
                            row1.set(6, yjflName);
                            staffTable.getRows().add(row1);
                            dxh++;
                        }
                        if (i == 0) { //第一行不判断
                            xh++;
                            row.set(0, xh);

                        } else {

                            String yileibe = "";
                            String erleibe = "";
                            if (!StringUtil.isNullOrEmpty(nlList.get(i).getEjfl())) {
                                yileibe = nlList.get(i).getEjfl();
                            }
                            if (!StringUtil.isNullOrEmpty(nlList.get(i - 1).getEjfl())) {
                                erleibe = nlList.get(i - 1).getEjfl();
                            }

                            if (yileibe.equals(erleibe)) {  //判断这个和上一个相等,相等
                                row.set(0, xh);
                            } else {
                                xh++;
                                row.set(0, xh);
                            }

                        }
                        row.set(1, nlList.get(i).getEjfl());//二级分类
                        row.set(2, nlList.get(i).getSortingNumber());//序号
                        row.set(3, nlList.get(i).getSanjfl());//名称
                        row.set(4, nlList.get(i).getYjbz());//依据标准
                        row.set(5, nlList.get(i).getLimits());//限制范围
                        row.set(6, nlList.get(i).getInstructions());//说明
                        staffTable.getRows().add(row);
                    } else {
                        if (i == 0) { //第一行不判断
                            xh++;
                            row.set(0, xh);

                        } else {

                            String yileibe = "";
                            String erleibe = "";
                            if (!org.apache.commons.lang3.StringUtils.isEmpty(nlList.get(i).getEjfl())) {
                                yileibe = nlList.get(i).getEjfl();
                            }
                            if (!org.apache.commons.lang3.StringUtils.isEmpty(nlList.get(i - 1).getEjfl())) {
                                erleibe = nlList.get(i - 1).getEjfl();
                            }

                            if (yileibe.equals(erleibe)) {  //判断这个和上一个相等,相等
                                row.set(0, xh);
                            } else {
                                xh++;
                                row.set(0, xh);
                            }

                        }
                        row.set(1, nlList.get(i).getYjfl());//一级分类
                        row.set(2, nlList.get(i).getSortingNumber());//序号
                        row.set(3, nlList.get(i).getSanjfl());//名称
                        row.set(4, nlList.get(i).getYjbz());//依据标准
                        row.set(5, nlList.get(i).getLimits());//限制范围
                        row.set(6, nlList.get(i).getInstructions());//说明
                        staffTable.getRows().add(row);
                    }
                }
            }
            String beizhu = "";//备注
            reqMap.put("selectTj", "REMARK");
            List beizhuList = nlsbptservice.queryListBySelect(reqMap);
            for (int i = 0; i < beizhuList.size(); i++) {
                HashMap beizhuMap = (HashMap) beizhuList.get(i);
                String remake = beizhuMap.get("REMARK") == null ? "" : beizhuMap.get("REMARK").toString();
                beizhu += remake;
            }
            if (!StringUtils.isNullOrEmpty(beizhu) && !beizhu.contains("申请时，为了简化此表的填写，参数相同的不重复填写，序号可以不连续")) {//最后添加备注行
                DataRow row1 = staffTable.newRow();
                row1.set(0, beizhu);
                row1.set(1, beizhu);
                row1.set(2, beizhu);
                row1.set(3, beizhu);
                row1.set(4, beizhu);
                row1.set(5, beizhu);
                row1.set(6, beizhu);
                staffTable.getRows().add(row1);
            }
            dataSet.getTables().add(staffTable);
            doc.getMailMerge().execute(new String[]{"jgdz"}, new String[]{psbgEntity.getCddz()});
            doc.getMailMerge().executeWithRegions(staffTable);
            Table table = (Table) doc.getChild(NodeType.TABLE, 0, true);//获取第一个table
            DocumentBuilder builder = new DocumentBuilder(doc);

            int m = 0;
            for (int i = 4; i < table.getRows().getCount(); i++) {  //模板从第四行开始


                if (i == 4) { //第一行不判断
                    String text1 = table.getRows().get(i).getCells().get(1).getText();
                    String text2 = table.getRows().get(i).getCells().get(2).getText();
                    String text3 = table.getRows().get(i).getCells().get(3).getText();
                    if (text1.equals(text2) && text1.equals(text3)) {  //防止大类别和产品类别相等,合并出错
                        AsposeUtil.mergeCells(table.getRows().get(i).getCells().get(1), table.getRows().get(i).getCells().get(6), table);
                    }


                } else {
                    //System.out.println("本行:" + table.getRows().get(i).getCells().get(1).getText());
                    //System.out.println("上一行:" + table.getRows().get(i - 1).getCells().get(1).getText());

                    String yileibe = "";
                    String erleibe = "";
                    if (!org.apache.commons.lang3.StringUtils.isEmpty(table.getRows().get(i).getCells().get(1).getText())) {
                        yileibe = table.getRows().get(i).getCells().get(1).getText();
                    }
                    if (!org.apache.commons.lang3.StringUtils.isEmpty(table.getRows().get(i).getCells().get(1).getText())) {
                        erleibe = table.getRows().get(i - 1).getCells().get(1).getText();
                    }
                    String text1 = table.getRows().get(i - 1).getCells().get(1).getText();
                    String text2 = table.getRows().get(i - 1).getCells().get(2).getText();
                    String text3 = table.getRows().get(i - 1).getCells().get(3).getText();
                    if (text1.equals(text2) && text1.equals(text3)) {  //防止大类别和产品类别相等,合并出错
                        yileibe += "合并";
                        AsposeUtil.mergeCells(table.getRows().get(i - 1).getCells().get(1), table.getRows().get(i - 1).getCells().get(6), table);
                    }


                    //不是最后一行
                    if (i != table.getRows().getCount() - 1 && yileibe.equals(erleibe)) {  //判断这个和上一个相等,相等:合并行数+1;
                        m++;

                        // 判断本行是否是最后一行
                    } else if (i == table.getRows().getCount() - 1 && !yileibe.equals(erleibe)) {
                        Cell cellStartRange = table.getRows().get(i - m - 1).getCells().get(1); //开始合并行数: 当前行数-相同行数
                        Cell cellEndRange = table.getRows().get(i - 1).getCells().get(1); //    最后一行和上面相等:   结束合并行数: 当前行数+1
                        AsposeUtil.mergeCells(cellStartRange, cellEndRange);  //  合并:  开始行数- 结束行数

                        Cell cellStartRange2 = table.getRows().get(i - m - 1).getCells().get(0); //开始合并行数: 当前行数-相同行数
                        Cell cellEndRange2 = table.getRows().get(i - 1).getCells().get(0); //    最后一行和上面相等:   结束合并行数: 当前行数+1

                        AsposeUtil.mergeCells(cellStartRange2, cellEndRange2);  //  合并:  开始行数- 结束行数

                        //builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);    //垂直居中对齐
                        //builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);         //水平居中对齐

                    } else {


                        Cell cellStartRange = table.getRows().get(i - m - 1).getCells().get(1); //开始合并行数: 当前行数-相同行数
                        Cell cellEndRange = table.getRows().get(i).getCells().get(1); // 结束合并行数: 当前行数
                        AsposeUtil.mergeCells(cellStartRange, cellEndRange);

                        Cell cellStartRange2 = table.getRows().get(i - m - 1).getCells().get(0); //开始合并行数: 当前行数-相同行数
                        Cell cellEndRange2 = table.getRows().get(i).getCells().get(0); //    最后一行和上面相等:   结束合并行数: 当前行数+1
                        AsposeUtil.mergeCells(cellStartRange2, cellEndRange2);  //  合并:  开始行数- 结束行数

                        //builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);    //垂直居中对齐
                        //builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);         //水平居中对齐
                        m = 0;
                    }
                }

            }

            int j = 0;
            for (int i = 4; i < table.getRows().getCount(); i++) {  //模板从第四行开始

                if (i == 4) { //第一行不判断

                } else {
                    //System.out.println("本行:" + table.getRows().get(i).getCells().get(2).getText());
                    //System.out.println("上一行:" + table.getRows().get(i - 1).getCells().get(2).getText());

                    String yileibe = "";
                    String erleibe = "";
                    if (!org.apache.commons.lang3.StringUtils.isEmpty(table.getRows().get(i).getCells().get(2).getText())) {
                        yileibe = table.getRows().get(i).getCells().get(2).getText();
                    }
                    if (!org.apache.commons.lang3.StringUtils.isEmpty(table.getRows().get(i).getCells().get(2).getText())) {
                        erleibe = table.getRows().get(i - 1).getCells().get(2).getText();
                    }


                    //不是最后一行
                    if (i != table.getRows().getCount() - 1 && yileibe.equals(erleibe)) {  //判断这个和上一个相等,相等:合并行数+1;
                        j++;

                        // 判断本行是否是最后一行
                    } else if (i == table.getRows().getCount() - 1 && !yileibe.equals(erleibe)) {
                        Cell cellStartRange = table.getRows().get(i - j - 1).getCells().get(2); //开始合并行数: 当前行数-相同行数
                        Cell cellEndRange = table.getRows().get(i - 1).getCells().get(2); //    最后一行和上面相等:   结束合并行数: 当前行数+1
                        AsposeUtil.mergeCells(cellStartRange, cellEndRange);  //  合并:  开始行数- 结束行数


                        //builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);    //垂直居中对齐
                        //builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);         //水平居中对齐

                    } else {

                        String text1 = table.getRows().get(i - 1).getCells().get(1).getText();
                        String text2 = table.getRows().get(i - 1).getCells().get(2).getText();
                        String text3 = table.getRows().get(i - 1).getCells().get(3).getText();
                        if (text1.equals(text2) && text1.equals(text3)) {  //防止大类别和产品类别相等,合并出错

                        } else {

                            Cell cellStartRange = table.getRows().get(i - j - 1).getCells().get(2); //开始合并行数: 当前行数-相同行数
                            Cell cellEndRange = table.getRows().get(i - 1).getCells().get(2); // 结束合并行数: 当前行数
                            AsposeUtil.mergeCells(cellStartRange, cellEndRange);

                            //builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);    //垂直居中对齐
                            //builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);         //水平居中对齐
                            j = 0;

                        }
                    }
                }
            }

            int w = 0;
            for (int i = 4; i < table.getRows().getCount(); i++) {  //模板从第四行开始

                if (i == 4) { //第一行不判断

                } else {
                    //System.out.println("本行:" + table.getRows().get(i).getCells().get(3).getText());
                    //System.out.println("上一行:" + table.getRows().get(i - 1).getCells().get(3).getText());

                    String yileibe = "";
                    String erleibe = "";
                    if (!org.apache.commons.lang3.StringUtils.isEmpty(table.getRows().get(i).getCells().get(3).getText())) {
                        yileibe = table.getRows().get(i).getCells().get(3).getText();
                    }
                    if (!org.apache.commons.lang3.StringUtils.isEmpty(table.getRows().get(i).getCells().get(3).getText())) {
                        erleibe = table.getRows().get(i - 1).getCells().get(3).getText();
                    }


                    //不是最后一行
                    if (i != table.getRows().getCount() - 1 && yileibe.equals(erleibe)) {  //判断这个和上一个相等,相等:合并行数+1;
                        w++;

                        // 判断本行是否是最后一行
                    } else if (i == table.getRows().getCount() - 1 && !yileibe.equals(erleibe)) {
                        Cell cellStartRange = table.getRows().get(i - w - 1).getCells().get(3); //开始合并行数: 当前行数-相同行数
                        Cell cellEndRange = table.getRows().get(i - 1).getCells().get(3); //    最后一行和上面相等:   结束合并行数: 当前行数+1
                        AsposeUtil.mergeCells(cellStartRange, cellEndRange);  //  合并:  开始行数- 结束行数


                        //builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);    //垂直居中对齐
                        //builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);         //水平居中对齐

                    } else {

                        String text1 = table.getRows().get(i - 1).getCells().get(1).getText();
                        String text2 = table.getRows().get(i - 1).getCells().get(2).getText();
                        String text3 = table.getRows().get(i - 1).getCells().get(3).getText();
                        if (text1.equals(text2) && text1.equals(text3)) {  //防止大类别和产品类别相等,合并出错

                        } else {

                            Cell cellStartRange = table.getRows().get(i - w - 1).getCells().get(3); //开始合并行数: 当前行数-相同行数
                            Cell cellEndRange = table.getRows().get(i - 1).getCells().get(3); // 结束合并行数: 当前行数
                            AsposeUtil.mergeCells(cellStartRange, cellEndRange);

                            //builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);    //垂直居中对齐
                            //builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);         //水平居中对齐
                            w = 0;

                        }


                    }
                }

            }

            if (!StringUtils.isNullOrEmpty(beizhu) && !beizhu.contains("申请时，为了简化此表的填写，参数相同的不重复填写，序号可以不连续")) {//最后合并备注行
                AsposeUtil.mergeCells(table.getRows().get(table.getRows().getCount() - 1).getCells().get(0), table.getRows().get(table.getRows().getCount() - 1).getCells().get(6), table);
            }
            doc.save(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String fileType = "doc";
        String resulte = "jsp/jyjc/rzrk/common/nlShowDoc.jsp" + "?filePath=" + filePath + "&fileType=" + fileType + "&psbgId=" + psbgId;

        response.getWriter().write(resulte);
    }
}
