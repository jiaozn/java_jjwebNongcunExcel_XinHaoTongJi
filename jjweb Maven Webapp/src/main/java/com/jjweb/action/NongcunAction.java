package com.jjweb.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.jjweb.model.Nongcun;
import com.jjweb.model.NongcunDAO;
import com.jjweb.model.PageBean;
import com.jjweb.model.User;
import com.jjweb.util.ExcelReader;
import com.opensymphony.xwork2.ActionSupport;

public class NongcunAction extends ActionSupport{
	
	@Resource
	private ExcelReader excelReader;
	private List<Nongcun> listNongcun;
	private File myFile;
	private String myFileFileName;
	private Nongcun nongcun;
	@Resource
	private NongcunDAO nongcunDAO;
	private User user;
	private File downToFile;
	
	private PageBean pageBean;
	private int pagenum = 0;
	
	public ExcelReader getExcelReader() {
		return excelReader;
	}




	public List<Nongcun> getListNongcun() {
		return listNongcun;
	}




	public File getMyFile() {
		return myFile;
	}
	
	

	
	public String getMyFileFileName() {
		return myFileFileName;
	}

	public Nongcun getNongcun() {
		return nongcun;
	}

	public NongcunDAO getNongcunDAO() {
		return nongcunDAO;
	}

	public User getUser() {
		return user;
	}

	@Action(value="nongcun_showAll",results={
			@Result(name="success",location = "/WEB-INF/content/business_nongcun_showAll.jsp")})
	public String nongcun_showAll(){
		return SUCCESS;
	}
	@Action(value="nongcun_showAll2",results={
			@Result(name="success",location = "/WEB-INF/content/business_nongcun_showAll2.jsp")})
	public String nongcun_showAll2(){
		listNongcun=nongcunDAO.findAll();
		return SUCCESS;
	}
	
	@Action(value="nongcun_showAll3",results={
			@Result(name="success",location = "/WEB-INF/content/business_nongcun_showAll3.jsp")})
	public String nongcun_showAll3(){
		pageBean=nongcunDAO.listpage(pagenum);
		listNongcun=pageBean.getList();
		return SUCCESS;
	}
	
	
	public File getDownToFile() {
		return downToFile;
	}




	public void setDownToFile(File downToFile) {
		this.downToFile = downToFile;
	}




	public PageBean getPageBean() {
		return pageBean;
	}




	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}




	public int getPagenum() {
		return pagenum;
	}




	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}




	@Action(value="nongcun_introduction",results={
			@Result(name="success",location = "/WEB-INF/content/business_nongcun_introduction.jsp")})
	public String nongcun_introduction(){
		return SUCCESS;
	}
	@Action(value="nongcun_upload_commit",results={
			@Result(name="success",location = "/WEB-INF/content/business_nongcun_inputCommit.jsp")})
	public String nongcun_upload_commit(){
        try {
        	//����myFile����һ���ļ�������  
			InputStream is = new FileInputStream(myFile);
			// �����ϴ��ļ�Ŀ¼  
	        String uploadPath = ServletActionContext.getServletContext()  
	                .getRealPath("/upload");  
	        //�ϴ����ļ����ļ���
	        String str4File=new SimpleDateFormat("yyyy��MM��dd�� HHʱmm��ss��SSS-").format(new Date().getTime())+this.getMyFileFileName();
	     // ����Ŀ���ļ�  
	        File toFile = new File(uploadPath, str4File); 
	     // ����һ�������  
	        OutputStream os = new FileOutputStream(toFile);  
	      //���û���  
	        byte[] buffer = new byte[102400];  
	  
	        int length = 0;  
	      //��ȡmyFile�ļ������toFile�ļ���  
	        while ((length = is.read(buffer)) > 0) {  
	            os.write(buffer, 0, length);  
	        }  
	        //�ر�������  
	        is.close();  
	          
	        //�ر������  
	        os.close();  
	        
	        System.out.println("�ϴ�����,���濪ʼȡ���������"+toFile.toString());
	        
	        //ExcelReader excelReader = new ExcelReader();
	        System.out.println("�½�excelreader�ɹ�");
	        InputStream is2 = new FileInputStream(toFile);
	        listNongcun=excelReader.readExcelContentL(is2);
            System.out.println("���Excel�������ݣ�����������µ����ݿ�");
            for (int i=0;i<listNongcun.size();i++){
            	nongcunDAO.saveOrUpdateOnName(listNongcun.get(i));
            }
            System.out.println("��ȫ���������ݿ⣬����ȡ����������");
            
            is2.close();
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block8520459
			e.printStackTrace();
		} 
		return SUCCESS;
	}
	
	
	@Action(value="nongcun_upload_input",results={
			@Result(name="success",location = "/WEB-INF/content/business_nongcun_input.jsp")})
	public String nongcun_upload_input(){
		return SUCCESS;
	}
	
	
	@Action(value="nongcun_download",results={   
		         @Result(params = {
		                 // ���ص��ļ���ʽ
		                 "contentType", "application/octet-stream",   
		                 // ����action��Ӧ�ķ���
		                 "inputName", "inputStream",   
		                 // HTTPЭ�飬ʹ������������ش���
		                 "contentDisposition", "attachment;filename=\"${myFileFileName}\"",   
		                 // �ļ���С
		                 "bufferSize", "102400"},   
		                 // result ��
		                 name = "download", 
	                 // result ����
		                 type = "stream")   
	})
	
	public String nongcun_download(){
		listNongcun=nongcunDAO.findAll();
		
		
		 // ��һ��������һ��webbook����Ӧһ��Excel�ļ�  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // �ڶ�������webbook�����һ��sheet,��ӦExcel�ļ��е�sheet  
        HSSFSheet sheet = wb.createSheet("ũ��234G������Ϣ");  
        // ����������sheet����ӱ�ͷ��0��,ע���ϰ汾poi��Excel����������������short  
        HSSFRow row = sheet.createRow((int) 0);  
        // ���Ĳ���������Ԫ�񣬲�����ֵ��ͷ ���ñ�ͷ����  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ����һ�����и�ʽ  
  
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("���");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 1);  
        cell.setCellValue("����");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 2);  
        cell.setCellValue("����");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 3);  
        cell.setCellValue("����");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 4);  
        cell.setCellValue("��������");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 5);  
        cell.setCellValue("����������");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 6);  
        cell.setCellValue("2G����");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 7);  
        cell.setCellValue("2G����");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 8);  
        cell.setCellValue("3G����");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 9);  
        cell.setCellValue("3G����");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 10);  
        cell.setCellValue("4G����");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 11);  
        cell.setCellValue("4G����");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 12);  
        cell.setCellValue("�����˺�");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 13);  
        cell.setCellValue("�޸�ʱ��");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 14);  
        cell.setCellValue("��ע");  
        cell.setCellStyle(style);  
  
        // ���岽��д��ʵ������ ʵ��Ӧ������Щ���ݴ����ݿ�õ���  
        List list = listNongcun;  
  
        for (int i = 0; i < list.size(); i++)  
        {  
            row = sheet.createRow((int) i + 1);  
            //Student stu = (Student) list.get(i);
            Nongcun nongcunTemp=(Nongcun) list.get(i);
            // ���Ĳ���������Ԫ�񣬲�����ֵ  
            row.createCell((short) 0).setCellValue(nongcunTemp.getId());  
            row.createCell((short) 1).setCellValue((String) nongcunTemp.getDishi());  
            row.createCell((short) 2).setCellValue((String) nongcunTemp.getQuxian());  
            row.createCell((short) 3).setCellValue((String) nongcunTemp.getXiangzhen());  
            row.createCell((short) 4).setCellValue((String) nongcunTemp.getXiangzhenleixing());  
            row.createCell((short) 5).setCellValue((String) nongcunTemp.getXingzhengcunming());  
            row.createCell((short) 6).setCellValue((String) nongcunTemp.getShinei2g());  
            row.createCell((short) 7).setCellValue((String) nongcunTemp.getShiwai2g());  
            row.createCell((short) 8).setCellValue((String) nongcunTemp.getShinei3g());  
            row.createCell((short) 9).setCellValue((String) nongcunTemp.getShiwai3g());  
            row.createCell((short) 10).setCellValue((String) nongcunTemp.getShinei4g());  
            row.createCell((short) 11).setCellValue((String) nongcunTemp.getShiwai4g());  
            row.createCell((short) 12).setCellValue((String) nongcunTemp.getEditor());  
            cell = row.createCell((short) 13);
//            if (nongcun.getTime()){
            	cell.setCellValue(0);
//            }else{
//            cell.setCellValue(new SimpleDateFormat("yyyy��MM��dd�� hhʱmm��ss��SSS-").format(nongcun.getTime()));  
//            }
            row.createCell((short) 14).setCellValue((String) nongcunTemp.getBeizhu());  
        }  
        // �����������ļ��浽ָ��λ��  
        try  
        {  
        	// ���������ļ�Ŀ¼  
	        String downloadPath = ServletActionContext.getServletContext()  
	                .getRealPath("/download");  
	        //���ص��ļ����ļ���
	        String str4File=new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date().getTime())+"Export.xls";
	     // ����Ŀ���ļ�  
	        downToFile = new File(downloadPath, str4File); 
        	
            FileOutputStream fout = new FileOutputStream(downToFile);  
            wb.write(fout);  
            fout.close();  
            myFileFileName=str4File;
            
            //���ļ��ˡ����濪ʼ����
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
		
		return "download";
	}
	
	
	@Action(value="nongcun_templetDownload",results={   
	         @Result(params = {
	                 // ���ص��ļ���ʽ
	                 "contentType", "application/octet-stream",   
	                 // ����action��Ӧ�ķ���
	                 "inputName", "inputStream",   
	                 // HTTPЭ�飬ʹ������������ش���
	                 "contentDisposition", "attachment;filename=\"${myFileFileName}\"",   
	                 // �ļ���С
	                 "bufferSize", "102400"},   
	                 // result ��
	                 name = "download", 
                // result ����
	                 type = "stream")   
})

public String nongcun_templetDownload(){
		myFileFileName="templet.xls";
		String downloadPath = ServletActionContext.getServletContext()  
                .getRealPath("/templet");  
        //���ص��ļ����ļ���
        String str4File=myFileFileName;
     // ����Ŀ���ļ�  
        downToFile = new File(downloadPath, str4File); 
		return "download";
}
	
	
	 /**  
     * ��ȡ������
     * ��Ӧ annotation ע������� "inputName", "inputStream"
     * ���� annotation ע���Ϊ "inputName", "myStream"��������ķ�����Ӧ��Ϊ��getMyStream
     * @return InputStream  
     */  
    public InputStream getInputStream() {   
         
//        // �ļ����ŵ��ļ��� 
//        String path = getServletContext().getRealPath("/")+"\\DownLoadFile\\";
//         
//        // ����·��
//        String downLoadPath = path + fileName;
         
        // ���
        try {   
            return new FileInputStream(downToFile);   
        } catch (FileNotFoundException e) {   
            e.printStackTrace();   
        }   
        return null;   
    }  
	
	

	public void setExcelReader(ExcelReader excelReader) {
		this.excelReader = excelReader;
	}

	public void setListNongcun(List<Nongcun> listNongcun) {
		this.listNongcun = listNongcun;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	public void setNongcun(Nongcun nongcun) {
		this.nongcun = nongcun;
	}

	public void setNongcunDAO(NongcunDAO nongcunDAO) {
		this.nongcunDAO = nongcunDAO;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
