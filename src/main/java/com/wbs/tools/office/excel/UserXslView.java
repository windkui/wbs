package com.wbs.tools.office.excel;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.servlet.view.document.AbstractExcelView;
/**
 * spring操作excel
 * @author windkui
 *
 */
public class UserXslView extends AbstractExcelView {

//	一，创建excel基本操作步骤
//	  
//	　　1. HSSFWorkbook workbook = new HSSFWorkbook();  // 创建新的Excel 工作簿
//	    2. HSSFSheet sheet = workbook.createSheet();   // 在Excel工作簿中建一工作表，其名为缺省值
//	    3. HSSFRow row = sheet.createRow((short)0);   // 在索引0的位置创建行（最顶端的行）
//	    4. HSSFCell cell = row.createCell((short) 0);  　//在索引0的位置创建单元格（左上端）
//	    5. cell.setCellValue("增加值");                 // 在单元格中输入一些内容
//	    6. FileOutputStream fOut = new FileOutputStream(outputFile);
//	    7. workbook.write(fOut);                  // 把相应的Excel 工作簿存盘
//	    8. fOut.flush();
//	    9. fOut.close();
//
//	在其中可以插入以下步骤：
//	      HSSFFont font = workbook.createFont(); //创建字体
//	       font.setFontHeightInPoints((short)8);
//	       font.setFontHeight((short)HSSFFont.BOLDWEIGHT_NORMAL);
//	       font.setColor((short)(HSSFFont.COLOR_RED));
//	      HSSFCellStyle style = workbook.createCellStyle();//创建样式
//	          style.setFont(font);
//	   在第五步之前可设置样式: cell.setCellStyle(title_style);
//
//	二，读取excel的基本步骤
//	   1. HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(fileToBeRead));
//	   2. HSSFSheet sheet = workbook.getSheet("Sheet1");
//	   3. HSSFRow row = sheet.getRow(0);
//	   4. HSSFCell cell = row.getCell((short)0);
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 取得模型数据
        SqlRowSet table = (SqlRowSet) model.get("table");
        String title = model.get("title").toString();
          //创建工作表和标题        
            HSSFSheet sheet = workbook.createSheet(title); //创建工作区
            HSSFRow row_title = sheet.createRow(0);          //创建一行引用对象
            HSSFFont title_font = workbook.createFont(); //创建标题的字体
            
            title_font.setFontHeightInPoints((short)8);
            title_font.setFontHeight((short)HSSFFont.BOLDWEIGHT_NORMAL);
            title_font.setColor((short)(HSSFFont.COLOR_RED));          
           
            HSSFCellStyle title_style = workbook.createCellStyle();//创建样式
            title_style.setFont(title_font);
            
            HSSFCell cell_title = row_title.createCell(1);//创建单元格引用对象  
            cell_title.setCellStyle(title_style);        
            cell_title.setCellValue(title);        
            
          //创建数据表头
            String titles[] = {"学生姓名","性别","年龄","身份证号","出生日期","政治面貌","家庭电话","家庭地址","健康状况"};
            
            HSSFRow row = sheet.createRow((short) 1);
            HSSFCellStyle items_style = workbook.createCellStyle();
            items_style.setAlignment((short)HSSFCellStyle.ALIGN_CENTER);
            
            HSSFFont celltbnamefont = workbook.createFont();
            celltbnamefont.setFontHeightInPoints((short)10);
        
            celltbnamefont.setColor((short)(HSSFFont.COLOR_RED));          
            items_style.setFont(celltbnamefont);
            items_style.setWrapText(true);
            
            for (int i = 0; i < titles.length; i++) {
            HSSFCell cell = row.createCell(i);
            if (i == 3 || i == 6 || i == 2) {
                sheet.setColumnWidth(i,5335);
            } else {
                sheet.setColumnWidth(i,3335);
            }
            cell.setCellValue(titles[i]);
            cell.setCellStyle(items_style);
            }
            
        HSSFCellStyle datestyle = workbook.createCellStyle();
        HSSFDataFormat df = workbook.createDataFormat();
        datestyle.setDataFormat(df.getFormat("yyyy-mm-dd"));
        int i=0;
        while (table.next()){
            HSSFRow dataRow = sheet.createRow((short) (i + 2));
            for(int j=0;j<9;j++){
                HSSFCell cell = dataRow.createCell(j);
                String data=table.getString(j+2);
                cell.setCellStyle(datestyle);
                cell.setCellValue(data);
            }
            i++;
        }
    }

	
}
