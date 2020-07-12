package core.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bean.DownloadData;
import util.EasyPOIUtil;

@RestController
@RequestMapping("/ExportExcelController")
public class ExportExcelController {
	
	@GetMapping("/exportExcel")
	public void exportExcel (HttpServletResponse response) throws IOException {
		List<DownloadData> list = new ArrayList<DownloadData>();
		DownloadData downloadData = new DownloadData();
		for (int i = 0; i < 10; i++) {
			downloadData.setDoubleData(123.4);
			downloadData.setDate(new Date());
			downloadData.setString("我的excel数据");	
			list.add(downloadData);
		}
		
		EasyPOIUtil.exportExcle(response, list, DownloadData.class, "测试");
	}
	
	@GetMapping("/exportExcelN")
	public void exportExcelN (HttpServletResponse response) throws IOException {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < 10; i++) {
		    Map<String, Object> map = new HashedMap<String, Object>();
			map.put("NAME", "张三四大佛山的哈佛山大佛"+i);
			map.put("AGE", 26+i);
			map.put("GRADE", "12312"+i);
			list.add(map);
		}
		String[] eName = {"NAME","AGE","GRADE"};
		String[] cName = {"姓名","年龄","成绩"};
		EasyPOIUtil.exportExcleN(response, list, eName, cName, "无创建对象");
	}
	

}
