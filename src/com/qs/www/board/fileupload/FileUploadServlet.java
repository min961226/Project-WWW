package com.qs.www.board.fileupload;

import java.io.File;  
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


@WebServlet("/upload/formdata")
public class FileUploadServlet extends HttpServlet {
	
	private String rootLocation;
	private int maxFileSize;
	private String encodingType;
	
	@Override
	public void init() throws ServletException {
		
		ServletContext context = getServletContext();

		rootLocation = context.getInitParameter("upload-location");
		maxFileSize = Integer.parseInt(context.getInitParameter("max-file-size"));
		encodingType = context.getInitParameter("encoding-type");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(ServletFileUpload.isMultipartContent(request)) {

			System.out.println("파일 저장 ROOT 경로 : " + rootLocation);
			System.out.println("최대 업로드 파일 용량 : " + maxFileSize);
			System.out.println("인코딩 방식 : " + encodingType);

			String fileUploadDirectory = rootLocation + "/commons";

			File directory = new File(fileUploadDirectory);
			
			//파일 저장경로 존재하지 않는 경우 디렉토리 생성
			if(!directory.exists()) {
				System.out.println("폴더 생성 : " + directory.mkdirs());
			}
			
			//map에는 사진 외의 다른 파라미터의 정보를 담는다.
			Map<String, String> parameter = new HashMap<>();
			List<Map<String, String>> fileList = new ArrayList<>();
			
			//파일을 업로드할 시 최대 크기나 임시 저장할 폴더 등의 경로 등을 포함하기 위한 인스턴스
			DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
			fileItemFactory.setRepository(new File(fileUploadDirectory));
			fileItemFactory.setSizeThreshold(maxFileSize);

			ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);

			try {
				List<FileItem> fileItems = fileUpload.parseRequest(request);

				for(FileItem item : fileItems) {
					System.out.println(item);
				}

				for(int i = 0; i < fileItems.size(); i++) {
					FileItem item = fileItems.get(i);

					if(!item.isFormField()) {

						if(item.getSize() > 0) {

							String filedName = item.getFieldName();
							String originFileName = item.getName();

							int dot = originFileName.lastIndexOf(".");
							String ext = originFileName.substring(dot);
							//랜덤제목
							String randomFileName = UUID.randomUUID().toString().replace("-", "") + ext;

							File storeFile = new File(fileUploadDirectory + "/" + randomFileName);
							//저장한다.
							item.write(storeFile);

							Map<String, String> fileMap = new HashMap<>();
							fileMap.put("filedName", filedName);
							fileMap.put("originFileName", originFileName);
							fileMap.put("savedFileName", randomFileName);
							fileMap.put("savePath", fileUploadDirectory);

							fileList.add(fileMap);

						}

					} else {
						parameter.put(item.getFieldName(), item.getString());
						parameter.put(item.getFieldName(), new String(item.getString().getBytes("ISO-8859-1"), "UTF-8"));

					}
				}

				System.out.println("parameter : " + parameter);
				System.out.println("fileList : " + fileList);
				
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				
				out.print("사진 업로드 성공");
				
				out.flush();
				out.close();

			} catch (Exception e) {
				//어떤 종류의 Exception이 발생 하더라도실패 시 파일을 삭제해야 한다.
				int cnt = 0;
				for(int i = 0; i < fileList.size(); i++) {
					Map<String, String> file = fileList.get(i);

					File deleteFile = new File(fileUploadDirectory + "/" + file.get("savedFileName"));
					boolean isDeleted = deleteFile.delete();

					if(isDeleted) {
						cnt++;
					}
				}

				if(cnt == fileList.size()) {
					System.out.println("업로드에실패한 모든 사진 삭제 완료!");
				} else {
					e.printStackTrace();
				}

			} 
		}
	
	}

}
