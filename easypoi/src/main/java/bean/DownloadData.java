package bean;

import java.util.Date;

import com.alibaba.excel.annotation.ExcelProperty;


public class DownloadData {
    @ExcelProperty("字符串标题")
    private String string;
    @ExcelProperty("日期标题")
    private Date date;
	@ExcelProperty("数字标题")
    private Double doubleData;
	
    @Override
	public String toString() {
		return "DownloadData [string=" + string + ", date=" + date + ", doubleData=" + doubleData + "]";
	}
	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Double getDoubleData() {
		return doubleData;
	}
	public void setDoubleData(Double doubleData) {
		this.doubleData = doubleData;
	}
	public DownloadData(String string, Date date, Double doubleData) {
		super();
		this.string = string;
		this.date = date;
		this.doubleData = doubleData;
	}
	public DownloadData() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((doubleData == null) ? 0 : doubleData.hashCode());
		result = prime * result + ((string == null) ? 0 : string.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DownloadData other = (DownloadData) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (doubleData == null) {
			if (other.doubleData != null)
				return false;
		} else if (!doubleData.equals(other.doubleData))
			return false;
		if (string == null) {
			if (other.string != null)
				return false;
		} else if (!string.equals(other.string))
			return false;
		return true;
	}

}