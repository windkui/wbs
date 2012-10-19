package com.wbs.tools.office.excel;

import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ReadXlsController implements Controller {

    private String text[][];
    private int columns;
    private int rows;


    public ModelAndView handleRequest(HttpServletRequest request,
            HttpServletResponse reponse) throws Exception {
        String pp = request.getParameter("path");
        String path = new String(pp.getBytes("iso-8859-1"), "gb2312");
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(path));
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow row = sheet.getRow(sheet.getLastRowNum());
        rows = sheet.getLastRowNum()+1;
        columns = row.getLastCellNum();
        text=new String[rows][columns];
        for (int i = 0; i < rows; i++) {
            row = sheet.getRow(i);
            for (int j = 0; j < columns; j++) {
                if(row==null){
                    text[i][j]=null;
                    continue;
                }
                HSSFCell cell;
                cell = row.getCell(j);
                if (cell != null) {
                    text[i][j] = cell.toString();
                } else {
                    text[i][j] = null;
                }
            }
        }
        return new ModelAndView("display","list",text);
    }

}
