package easypoi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;

public class EYPOUI {

    /**
     * 针对较少的记录数(20W以内大概)可以调用该方法一次性查出然后写入到EXCEL的一个SHEET中
     * 注意： 一次性查询出来的记录数量不宜过大，不会内存溢出即可。
     *
     * @throws IOException
     */
    public static void writeExcelOneSheetOnceWrite() throws IOException {
 
        // 生成EXCEL并指定输出路径
        OutputStream out = new FileOutputStream("D:\\temp\\test.xlsx");
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
 
        // 设置SHEET
        Sheet sheet = new Sheet(1, 0);
        sheet.setSheetName("sheet1");
       // sheet.getAutoWidth();
        sheet.setAutoWidth(true);
        // 设置标题
        Table table = new Table(1);
        List<List<String>> titles = new ArrayList<List<String>>();
        titles.add(Arrays.asList("用户ID"));
        titles.add(Arrays.asList("名称"));
        titles.add(Arrays.asList("年龄"));
        titles.add(Arrays.asList("生日"));
        table.setHead(titles);
 
        // 查询数据导出即可 比如说一次性总共查询出100条数据
        List<List<String>> userList = new ArrayList<List<String>>();
        for (int i = 0; i < 190000; i++) {
            userList.add(Arrays.asList("ID_" + i, "小明" + i, String.valueOf(i), new Date().toString()));
        }
 
        writer.write0(userList, sheet, table);
        writer.finish();
    }
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		writeExcelOneSheetOnceWrite();
	}

	
}
