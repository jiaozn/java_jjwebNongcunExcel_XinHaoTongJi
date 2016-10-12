package com.jjweb.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.apache.poi.ss.usermodel.Cell;

import com.jjweb.model.Nongcun;

/**
 * ����Excel���Ĺ�����
 */
@Service
public class ExcelReader {
    private POIFSFileSystem fs;
    private HSSFWorkbook wb;
    private HSSFSheet sheet;
    private HSSFRow row;

//    public ExcelReader() {
//		super();
//	}

	/**
     * ��ȡExcel����ͷ������
     * @param InputStream
     * @return String ��ͷ���ݵ�����
     */
//    public String[] readExcelTitle(InputStream is) {
//        try {
//            fs = new POIFSFileSystem(is);
//            wb = new HSSFWorkbook(fs);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        sheet = wb.getSheetAt(0);
//        row = sheet.getRow(0);
//        // ����������
//        int colNum = row.getPhysicalNumberOfCells();
//        System.out.println("colNum:" + colNum);
//        String[] title = new String[colNum];
//        for (int i = 0; i < colNum; i++) {
//            //title[i] = getStringCellValue(row.getCell((short) i));
//            title[i] = getCellFormatValue(row.getCell((short) i));
//        }
//        return title;
//    }

    /**
     * ��ȡExcel��������
     * @param InputStream
     * @return Map ������Ԫ���������ݵ�Map����
     */
//    public Map<Integer, String> readExcelContent(InputStream is) {
//        Map<Integer, String> content = new HashMap<Integer, String>();
//        String str = "";
//        try {
//            fs = new POIFSFileSystem(is);
//            wb = new HSSFWorkbook(fs);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        sheet = wb.getSheetAt(0);
//        // �õ�������
//        int rowNum = sheet.getLastRowNum();
//        row = sheet.getRow(0);
//        int colNum = row.getPhysicalNumberOfCells();
//        // ��������Ӧ�ôӵڶ��п�ʼ,��һ��Ϊ��ͷ�ı���
//        for (int i = 1; i <= rowNum; i++) {
//            row = sheet.getRow(i);
//            int j = 0;
//            while (j < colNum) {
//                // ÿ����Ԫ�������������"-"�ָ���Ժ���Ҫʱ��String���replace()������ԭ����
//                // Ҳ���Խ�ÿ����Ԫ����������õ�һ��javabean�������У���ʱ��Ҫ�½�һ��javabean
//                // str += getStringCellValue(row.getCell((short) j)).trim() +
//                // "-";
//                str += getCellFormatValue(row.getCell((short) j)).trim() + "    ";
//                j++;
//            }
//            content.put(i, str);
//            str = "";
//        }
//        return content;
//    }

    public List<Nongcun> readExcelContentL(InputStream is) {
        List<Nongcun> listNongcun=new ArrayList<Nongcun>();
        try {
            fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        // �õ�������
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // ��������Ӧ�ôӵڶ��п�ʼ,��һ��Ϊ��ͷ�ı���
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            	Nongcun nongcun=new Nongcun();
            	nongcun.setDishi(getStringCellValue(row.getCell(1)));
            	nongcun.setQuxian(getStringCellValue(row.getCell(2)));
            	nongcun.setXiangzhen(getStringCellValue(row.getCell(3)));
            	nongcun.setXiangzhenleixing(getStringCellValue(row.getCell(4)));
            	nongcun.setXingzhengcunming(getStringCellValue(row.getCell(5)));
            	nongcun.setShinei2g(getStringCellValue(row.getCell(6)));
            	nongcun.setShiwai2g(getStringCellValue(row.getCell(7)));
            	nongcun.setShinei3g(getStringCellValue(row.getCell(8)));
            	nongcun.setShiwai3g(getStringCellValue(row.getCell(9)));
            	nongcun.setShinei4g(getStringCellValue(row.getCell(10)));
            	nongcun.setShiwai4g(getStringCellValue(row.getCell(11)));
            	nongcun.setShifouguihua(getStringCellValue(row.getCell(12)));
            	nongcun.setGuihuazhanming(getStringCellValue(row.getCell(13)));
            	nongcun.setTime(new Timestamp(new Date().getTime()));
            	listNongcun.add(nongcun);
        }
        return listNongcun;
    }

    
    
    /**
     * ��ȡ��Ԫ����������Ϊ�ַ������͵�����
     * 
     * @param cell Excel��Ԫ��
     * @return String ��Ԫ����������
     */
    private String getStringCellValue(HSSFCell cell) {
        String strCell = "";
        switch (cell.getCellType()) {
        case HSSFCell.CELL_TYPE_STRING:
            strCell = cell.getStringCellValue();
            break;
        case HSSFCell.CELL_TYPE_NUMERIC:
            strCell = String.valueOf(cell.getNumericCellValue());
            break;
        case HSSFCell.CELL_TYPE_BOOLEAN:
            strCell = String.valueOf(cell.getBooleanCellValue());
            break;
        case HSSFCell.CELL_TYPE_BLANK:
            strCell = "";
            break;
        default:
            strCell = "";
            break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        if (cell == null) {
            return "";
        }
        return strCell;
    }

    /**
     * ��ȡ��Ԫ����������Ϊ�������͵�����
     * 
     * @param cell
     *            Excel��Ԫ��
     * @return String ��Ԫ����������
     */
//    private String getDateCellValue(HSSFCell cell) {
//        String result = "";
//        try {
//            int cellType = cell.getCellType();
//            if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
//                Date date = cell.getDateCellValue();
//                result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
//                        + "-" + date.getDate();
//            } else if (cellType == HSSFCell.CELL_TYPE_STRING) {
//                String date = getStringCellValue(cell);
//                result = date.replaceAll("[����]", "-").replace("��", "").trim();
//            } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
//                result = "";
//            }
//        } catch (Exception e) {
//            System.out.println("���ڸ�ʽ����ȷ!");
//            e.printStackTrace();
//        }
//        return result;
//    }

//    /**
//     * ����HSSFCell������������
//     * @param cell
//     * @return
//     */
//    private String getCellFormatValue(HSSFCell cell) {
//        String cellvalue = "";
//        if (cell != null) {
//            // �жϵ�ǰCell��Type
//            switch (cell.getCellType()) {
//            // �����ǰCell��TypeΪNUMERIC
//            case HSSFCell.CELL_TYPE_NUMERIC:
//            case HSSFCell.CELL_TYPE_FORMULA: {
//                // �жϵ�ǰ��cell�Ƿ�ΪDate
//                if (HSSFDateUtil.isCellDateFormatted(cell)) {
//                    // �����Date������ת��ΪData��ʽ
//                    
//                    //����1�������ӵ�data��ʽ�Ǵ�ʱ����ģ�2011-10-12 0:00:00
//                    //cellvalue = cell.getDateCellValue().toLocaleString();
//                    
//                    //����2�������ӵ�data��ʽ�ǲ�����ʱ����ģ�2011-10-12
//                    Date date = cell.getDateCellValue();
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//                    cellvalue = sdf.format(date);
//                    
//                }
//                // ����Ǵ�����
//                else {
//                    // ȡ�õ�ǰCell����ֵ
//                    cellvalue = String.valueOf(cell.getNumericCellValue());
//                }
//                break;
//            }
//            // �����ǰCell��TypeΪSTRIN
//            case HSSFCell.CELL_TYPE_STRING:
//                // ȡ�õ�ǰ��Cell�ַ���
//                cellvalue = cell.getRichStringCellValue().getString();
//                break;
//            // Ĭ�ϵ�Cellֵ
//            default:
//                cellvalue = " ";
//            }
//        } else {
//            cellvalue = "";
//        }
//        return cellvalue;
//
//    }
}