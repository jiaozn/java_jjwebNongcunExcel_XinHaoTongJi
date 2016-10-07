package com.jjweb.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;

import com.jjweb.model.UploadFileDTO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport {  
	  
    private static final long serialVersionUID = 1L;  
    private File file;// ��Ӧ�ļ����file����װ�ļ�����   c:....+�ļ���.txt
    private String fileStr;
    private String fileContentType;// ��װ�ļ�����  
    private String fileFileName;// ��װ�ļ���  
    private String savePath;// ����·��  c:....Ŀ¼
    private String title;// �ļ�����  
    private UploadFileDTO uploadFileDTO;
    private List<UploadFileDTO> uploadFileDTOs=new ArrayList<UploadFileDTO>();
    private InputStream dwInputStream;
    private String listDirStr;
    @Override  
    public String execute() throws Exception {  
        if (file != null) {  
             //System.out.println(getFileContentType());  
            // ��ȡ�ļ����ݵ�InputStream��  
            InputStream is = new FileInputStream(getFile());  
            // ������������������ļ�  
            OutputStream os = new FileOutputStream(getSavePath() + "//" + getFileFileName());  
            // ��InputStream���byte������OutputStream  
            IOUtils.copy(is, os);  
            os.flush();  
            IOUtils.closeQuietly(is);  
            IOUtils.closeQuietly(os);  
            return SUCCESS;  
        }  
        return INPUT;  
    }  
    
    public String listAll(){
    	File listDir=new File(getSavePath());
    	File[] tempList=listDir.listFiles();
    	for(int i=0;i<tempList.length;i++){
    		try {uploadFileDTO=new UploadFileDTO();
    		uploadFileDTO.setFileName(tempList[i].getName());//�ļ�����txt
			uploadFileDTO.setFile(tempList[i].getAbsolutePath());
    		uploadFileDTO.setSize(tempList[i].length());
    		uploadFileDTOs.add(uploadFileDTO);} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	listDirStr=listDir.toString();
    	//Map request =(Map)ActionContext.getContext().get("request");
		//request.put("uploadFileDTOs1", uploadFileDTOs);
		//request.put("listDirStr", listDirStr);
		return "list_All";
    	
    }
    public String downLoad(){
    	try {
    		File fileTem=new File(fileStr);
    		fileFileName=fileTem.getName();
    	//	System.out.println(fileFileName);
			dwInputStream=new FileInputStream(fileTem);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return "down_load";
    }
    public String delete(){
    	file=new File(fileStr);
    	file.delete();
    	
    	File listDir=new File(getSavePath());
    	File[] tempList=listDir.listFiles();
    	for(int i=0;i<tempList.length;i++){
    		try {uploadFileDTO=new UploadFileDTO();
    		uploadFileDTO.setFileName(tempList[i].getName());//�ļ�����txt
			uploadFileDTO.setFile(tempList[i].getAbsolutePath());
    		uploadFileDTO.setSize(tempList[i].length());
    		uploadFileDTOs.add(uploadFileDTO);} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	String listDirStr=listDir.toString();
    	//Map request =(Map)ActionContext.getContext().get("request");
		//request.put("uploadFileDTOs1", uploadFileDTOs);
		//request.put("listDirStr", listDirStr);
    	return "list_All";
    }
  
    public File getFile() {  
        return file;  
    }  
  
    public String getFileContentType() {  
        return fileContentType;  
    }  
  
    public String getFileFileName() {  
        return fileFileName;  
    }  
  
    public String getSavePath() {  
        // �����·��ת���ɾ���·��  
    //System.out.println(ServletActionContext.getServletContext().getRealPath(savePath));
        //return ServletActionContext.getServletContext().getRealPath(savePath);
    	//System.out.println(System.getProperty("user.dir")+"\\uploadFiles");
    	return ServletActionContext.getServletContext().getRealPath(savePath);
    }  
  
    public String getTitle() {  
        return title;  
    }  
  
    public void setFile(File file) {  
        this.file = file;  
    }  
  
    public void setFileContentType(String fileContentType) {  
        this.fileContentType = fileContentType;  
    }  
  
//    public void setFileFileName(String fileFileName) {  
//        this.fileFileName = fileFileName;  
//    }  
    public void setFileFileName(String fileFileName) {  
    	this.fileFileName=fileFileName;
    }  
  
    public void setSavePath(String savePath) {  
        this.savePath = savePath;  
    }  
  
    public void setTitle(String title) {  
        this.title = title;  
    }

	public List<UploadFileDTO> getUploadFileDTOs() {
		return uploadFileDTOs;
	}

	public void setUploadFileDTOs(List<UploadFileDTO> uploadFileDTOs) {
		this.uploadFileDTOs = uploadFileDTOs;
	}

	public UploadFileDTO getUploadFileDTO() {
		return uploadFileDTO;
	}

	public void setUploadFileDTO(UploadFileDTO uploadFileDTO) {
		this.uploadFileDTO = uploadFileDTO;
	}

	public InputStream getDwInputStream() {

		return dwInputStream;
	}

	public void setDwInputStream(InputStream dwInputStream) {
		this.dwInputStream = dwInputStream;
	}

	public String getFileStr() {
		return fileStr;
	}

	public void setFileStr(String fileStr) {
		this.fileStr = fileStr;
	}

}
