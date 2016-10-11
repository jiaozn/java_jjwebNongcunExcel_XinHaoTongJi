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
        	//基于myFile创建一个文件输入流  
			InputStream is = new FileInputStream(myFile);
			// 设置上传文件目录  
	        String uploadPath = ServletActionContext.getServletContext()  
	                .getRealPath("/upload");  
	        //上传的文件的文件名
	        String str4File=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒SSS-").format(new Date().getTime())+this.getMyFileFileName();
	     // 设置目标文件  
	        File toFile = new File(uploadPath, str4File); 
	     // 创建一个输出流  
	        OutputStream os = new FileOutputStream(toFile);  
	      //设置缓存  
	        byte[] buffer = new byte[102400];  
	  
	        int length = 0;  
	      //读取myFile文件输出到toFile文件中  
	        while ((length = is.read(buffer)) > 0) {  
	            os.write(buffer, 0, length);  
	        }  
	        //关闭输入流  
	        is.close();  
	          
	        //关闭输出流  
	        os.close();  
	        
	        System.out.println("上传结束,下面开始取出表格数据"+toFile.toString());
	        
	        //ExcelReader excelReader = new ExcelReader();
	        System.out.println("新建excelreader成功");
	        InputStream is2 = new FileInputStream(toFile);
	        listNongcun=excelReader.readExcelContentL(is2);
            System.out.println("获得Excel表格的内容，下面存入或更新到数据库");
            for (int i=0;i<listNongcun.size();i++){
            	nongcunDAO.saveOrUpdateOnName(listNongcun.get(i));
            }
            System.out.println("已全部存入数据库，下面取出所有数据");
            
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
		                 // 下载的文件格式
		                 "contentType", "application/octet-stream",   
		                 // 调用action对应的方法
		                 "inputName", "inputStream",   
		                 // HTTP协议，使浏览器弹出下载窗口
		                 "contentDisposition", "attachment;filename=\"${myFileFileName}\"",   
		                 // 文件大小
		                 "bufferSize", "102400"},   
		                 // result 名
		                 name = "download", 
	                 // result 类型
		                 type = "stream")   
	})
	
	public String nongcun_download(){
		listNongcun=nongcunDAO.findAll();
		
		
		 // 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("农村234G覆盖信息");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
  
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("序号");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 1);  
        cell.setCellValue("地市");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 2);  
        cell.setCellValue("区县");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 3);  
        cell.setCellValue("乡镇");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 4);  
        cell.setCellValue("乡镇类型");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 5);  
        cell.setCellValue("行政村名称");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 6);  
        cell.setCellValue("2G室内");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 7);  
        cell.setCellValue("2G室外");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 8);  
        cell.setCellValue("3G室内");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 9);  
        cell.setCellValue("3G室外");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 10);  
        cell.setCellValue("4G室内");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 11);  
        cell.setCellValue("4G室外");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 12);  
        cell.setCellValue("操作账号");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 13);  
        cell.setCellValue("修改时间");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 14);  
        cell.setCellValue("备注");  
        cell.setCellStyle(style);  
  
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
        List list = listNongcun;  
  
        for (int i = 0; i < list.size(); i++)  
        {  
            row = sheet.createRow((int) i + 1);  
            //Student stu = (Student) list.get(i);
            Nongcun nongcunTemp=(Nongcun) list.get(i);
            // 第四步，创建单元格，并设置值  
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
//            cell.setCellValue(new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒SSS-").format(nongcun.getTime()));  
//            }
            row.createCell((short) 14).setCellValue((String) nongcunTemp.getBeizhu());  
        }  
        // 第六步，将文件存到指定位置  
        try  
        {  
        	// 设置下载文件目录  
	        String downloadPath = ServletActionContext.getServletContext()  
	                .getRealPath("/download");  
	        //下载的文件的文件名
	        String str4File=new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date().getTime())+"Export.xls";
	     // 设置目标文件  
	        downToFile = new File(downloadPath, str4File); 
        	
            FileOutputStream fout = new FileOutputStream(downToFile);  
            wb.write(fout);  
            fout.close();  
            myFileFileName=str4File;
            
            //有文件了。下面开始下载
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
		
		return "download";
	}
	
	
	@Action(value="nongcun_templetDownload",results={   
	         @Result(params = {
	                 // 下载的文件格式
	                 "contentType", "application/octet-stream",   
	                 // 调用action对应的方法
	                 "inputName", "inputStream",   
	                 // HTTP协议，使浏览器弹出下载窗口
	                 "contentDisposition", "attachment;filename=\"${myFileFileName}\"",   
	                 // 文件大小
	                 "bufferSize", "102400"},   
	                 // result 名
	                 name = "download", 
                // result 类型
	                 type = "stream")   
})

public String nongcun_templetDownload(){
		myFileFileName="templet.xls";
		String downloadPath = ServletActionContext.getServletContext()  
                .getRealPath("/templet");  
        //下载的文件的文件名
        String str4File=myFileFileName;
     // 设置目标文件  
        downToFile = new File(downloadPath, str4File); 
		return "download";
}
	
	
	 /**  
     * 获取下载流
     * 对应 annotation 注解里面的 "inputName", "inputStream"
     * 假如 annotation 注解改为 "inputName", "myStream"，则下面的方法则应改为：getMyStream
     * @return InputStream  
     */  
    public InputStream getInputStream() {   
         
//        // 文件所放的文件夹 
//        String path = getServletContext().getRealPath("/")+"\\DownLoadFile\\";
//         
//        // 下载路径
//        String downLoadPath = path + fileName;
         
        // 输出
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
