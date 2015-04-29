package org.codeyn.smartend.framework.enums;

/**
 * 选择状态
 * 
 * @author parcel
 */
public enum SelectStatus {

	notSelected(0, "未选中"), selected(1, "选中");

	private SelectStatus(Integer flag, String title) {
		this.flag = flag;
		this.title = title;
	}

	private Integer flag;

	private String title;

	public Integer getValue() {
		return flag;
	}

	public String getTitle() {
		return title;
	}

	public static SelectStatus get(Integer flag) {
		for (SelectStatus temp : SelectStatus.values()) {
			if (temp.flag.equals(flag)) {
				return temp;
			}
		}
		return null;
	}
}
