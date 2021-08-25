package com.qs.www.mng.welfare.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qs.www.mng.welfare.model.dto.ItemDTO;
import com.qs.www.mng.welfare.model.service.MngWelfareService;

@WebServlet("/mng/welfare/laptopRental/deleteComplete")
public class DeleteCompleteMngLaptopRentalWelfareServlet extends HttpServlet {
	ItemDTO itemDTO;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/* check 된 박스 값 TRUE OR FALSE */
		String[] checkList = request.getParameterValues("deleteItemCheck"); 					

		String path = "";
		
		/* 갖고온 체크박스가 없을경우 예외처리 */
		if (checkList!=null) {														

			List<ItemDTO> itemList = new ArrayList<>();
			/* check 박스로 받아오게 될시 getParametervalues의 리턴 값인 String[]로 받아와야함으로 변환이 필요하다.*/
			for (int i = 0; i < checkList.length; i++) { 							
				itemDTO = new ItemDTO();
				/* DTO에 값을 한개씩 담아준다 */
				itemDTO.setItemNo(Integer.parseInt(checkList[i])); 					

				itemList.add(itemDTO);
			}
			
			/*itemList에 해당하는 y값과 n값을 업데이트해준다.*/
			int deleteResult = new MngWelfareService().deleteItem(itemList);
			
			if (deleteResult > 0) {
				path = "/WEB-INF/views/common/success.jsp";
				request.setAttribute("successCode", "deleteItem");
			} else {
				path = "/WEB-INF/views/common/failed.jsp";
				request.setAttribute("failedCode", "deleteItem");
			}
		} else {
			path = "/WEB-INF/views/common/success.jsp";
			request.setAttribute("successCode", "deleteItem");
		}
		request.getRequestDispatcher(path).forward(request, response);
	}
}