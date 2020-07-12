package core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;

/**
 * 
 * @author chenws
 *    支持jdk1.6
 */
public class EasyPOIUtil {
	

    public static void exportExcel(HttpServletResponse response,List<Map<String, Object>> list, String[] eName, String[] cName, String fileName) throws Exception {
 
        ServletOutputStream out = null;
        ExcelWriter writer = null;
        try {
            out = response.getOutputStream();
            writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
 
            // 设置EXCEL名称
           // String fileName = new String(("SystemExcel").getBytes(), "UTF-8");
 
            // 设置SHEET名称
            Sheet sheet = new Sheet(1, 0);
            sheet.setSheetName("系统列表sheet1");
 
            // 设置标题
            Table table = new Table(1);
			List<List<String>> headList = new ArrayList<List<String>>();
        	for (String name : cName) {
				List<String> head = new ArrayList<String>();
				head.add(name);
				headList.add(head);
			}
        	   table.setHead(headList);
            // 查数据写EXCEL
            List<List<Object>> dataList = new ArrayList<List<Object>>();
            
            if (!CollectionUtils.isEmpty(list)) {
            	
            	for (Map<String, Object> map : list) {
    				List<Object> data = new ArrayList<Object>();
    				for (String name : eName) {
    					data.add(map.get(name));
    				}
    				dataList.add(data);
    			}
               
            }
            writer.write1(dataList, sheet, table);
 
            // 下载EXCEL
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName).getBytes("gb2312"), "ISO-8859-1") + ".xls");
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");
          
            out.flush();
 
        }catch (Exception e) {
        	response.reset();
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			Map<String, String> map = new HashMap<String, String>();
			map.put("status", "failure");
			map.put("message", "下载文件失败" + e.getMessage());
			// response.getWriter().println(JSON.toJSONString(map)); // fastjson，不引入
			response.getWriter().println(map);
		} finally {
			if (writer !=null ) {
				writer.finish();
			}
        	
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
 
    }

}
