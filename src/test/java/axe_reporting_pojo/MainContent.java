package axe_reporting_pojo;

public class MainContent {
	private String issueType;
	private String tableContent;
	
	public MainContent(String issueType, String tableContent) {
		this.issueType = issueType;
		this.tableContent = tableContent;
	}

	public String getIssueType() {
		return issueType;
	}

	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	public String getTableContent() {
		return tableContent;
	}

	public void setTableContent(String tableContent) {
		this.tableContent = tableContent;
	}

}
