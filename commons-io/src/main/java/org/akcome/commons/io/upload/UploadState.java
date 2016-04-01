package org.akcome.commons.io.upload;

/** 
 * <b>function:</b> 文件上传状态 
 * @package org.epbcommons.io.upload
 * @fileName UploadState.java 
 * @createDate 2015-11-26  
 * @author peng_wang 
 */ 
public enum UploadState {
	 	UPLOAD_SUCCSSS(0, "上传文件成功！"),   
	    UPLOAD_FAILURE(1, "上传文件失败！"),   
	    UPLOAD_TYPE_ERROR(2, "上传文件类型错误！"),   
	    UPLOAD_OVERSIZE(3, "上传文件过大！"),  
	    UPLOAD_ZEROSIZE(4, "上传文件为空！"),  
	    UPLOAD_NOTFOUND(5, "上传文件路径错误！");  
	      
	    private String state;  
	    private int flag;  
	    public String getState() {  
	        return this.state;  
	    }  
	      
	    public int getFlag() {  
	        return this.flag;  
	    }  
	    UploadState(int flag, String state) {  
	        this.state = state;  
	        this.flag = flag;  
	    }  
}
