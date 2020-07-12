package util;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.excel.EasyExcel;

import bean.DownloadData;

/**
 *  操作excel
 * @author chenws
 *  jdk 1.8 以上
 */
public class EasyPOIUtil {
	
	
	public static void exportExcle(HttpServletResponse response,List<?> list, Class head, String fileName) throws IOException {
		   // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), head).autoCloseStream(Boolean.FALSE).sheet("sheet1")
                .doWrite(list);
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            //response.getWriter().println(JSON.toJSONString(map)); // fastjson，不引入
            response.getWriter().println(map);
        }
	}
	
	// 不创建对象的写法
	public static void exportExcleN(HttpServletResponse response,List<Map<String, Object>> list, String[] eName, String[] cName, String fileName) throws IOException {
		try {
			response.setContentType("application/vnd.ms-excel");
			response.setCharacterEncoding("utf-8");
			// 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
			
			List<List<String>> headList = new ArrayList<List<String>>();
		//	List<String> head = new ArrayList<String>();  
			for (String name : cName) {
				List<String> head = new ArrayList<String>();
				head.add(name);
				headList.add(head);
			}
			// 数据格式转换
			List<List<Object>> dataList = new ArrayList<List<Object>>();
			for (Map<String, Object> map : list) {
				List<Object> data = new ArrayList<Object>();
				for (String name : eName) {
					data.add(map.get(name));
				}
				dataList.add(data);
			}
			// 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
			//EasyExcel.write(fileName).head(headList).sheet("sheet1").doWrite(dataList);
			
			
			 EasyExcel.write(response.getOutputStream()).head(headList).autoCloseStream(Boolean.FALSE).sheet("sheet1")
             .doWrite(dataList);
		} catch (Exception e) {
			response.reset();
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			Map<String, String> map = new HashMap<String, String>();
			map.put("status", "failure");
			map.put("message", "下载文件失败" + e.getMessage());
			// response.getWriter().println(JSON.toJSONString(map)); // fastjson，不引入
			response.getWriter().println(map);
			
		}
   
	}
	
	


}
