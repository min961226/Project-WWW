package com.qs.www.common.attachment.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FileDown")
public class FileDownController  extends HttpServlet{

	
	private static final long serialVersionUID = 4357653489057931185L;
	
	public FileDownController() {
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = request.getParameter("fileName");
		
		// 서버에 올라간 경로를 가져옴
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath("upload/report");
		String filePath = uploadFilePath + File.separator + fileName;
		
		byte[] b = new byte[4096];														//byte에 공간할당
		FileInputStream fileInputStream = new FileInputStream(filePath);
		
		String mimeType = getServletContext().getMimeType(filePath);					//경ㄴ로 설정
		if(mimeType == null) {
			mimeType = "application/octet-stream";
		}
		response.setContentType(mimeType);
		
        // 파일명 UTF-8로 인코딩(한글일 경우를 대비)
        String sEncoding = new String(fileName.getBytes("UTF-8"));
        response.setHeader("Content-Disposition", "attachment; fileName= " + sEncoding);
        
        // 파일 쓰기 OutputStream
        ServletOutputStream servletOutStream = response.getOutputStream();				//outputstream으로 실행
        
        int read;
        while((read = fileInputStream.read(b,0,b.length))!= -1){
            servletOutStream.write(b,0,read);            
        }
        
        servletOutStream.flush();														//할당된 데이터 반납
        servletOutStream.close();
        fileInputStream.close();
	}
}