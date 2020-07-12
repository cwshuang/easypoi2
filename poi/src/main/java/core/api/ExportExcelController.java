package core.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import core.util.EasyPOIUtil;
@RestController
@RequestMapping("/ExportExcelController")
public class ExportExcelController {
	
	@RequestMapping("/exportExcel")
	public void exportExcel (HttpServletResponse response) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < 100000; i++) {
		    Map<String, Object> map = new HashedMap<String, Object>();
			map.put("NAME", "张三四大佛山的哈佛山大佛"+i);
			map.put("AGE", 26+i);
			map.put("GRADE", "12312"+i);
			list.add(map);
		}
		String[] eName = {"NAME","AGE","GRADE"};
		String[] cName = {"姓名","年龄","成绩"};
		EasyPOIUtil.exportExcel(response, list, eName, cName, "无创建对象");
	}
	

	

}
