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
		String[] checkList = request.getParameterValues("deleteItemCheck"); 		// check 된 박스 값들을 받아온다

		String path = "";
		if (checkList!=null) {														//갖고온 체크박스가 없을경우 예외처리

			List<ItemDTO> itemList = new ArrayList<>();

			for (int i = 0; i < checkList.length; i++) { 							// check 박스로 받아오게 될시 String[]로 받아와야함으로 변환이 필요하다.
				itemDTO = new ItemDTO();
				itemDTO.setItemNo(Integer.parseInt(checkList[i])); 					// list에 값을 한개씩 담아준다

				itemList.add(itemDTO);
			}

			int deleteResult = new MngWelfareService().deleteItem(itemList);		//itemList에 해당하는 y값과 n값을 업데이트해준다.
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