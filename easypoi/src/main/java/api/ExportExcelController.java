package api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
		
		EasyPOIUtil.exportExcle(response, list, DownloadData.class, "测试");;
	}
	

}
