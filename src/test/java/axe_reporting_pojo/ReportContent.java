package axe_reporting_pojo;

public class ReportContent {
	private String testingTool;
	private String url;
	private String timestamp;
	private String content;

	public ReportContent(String testingTool, String url, String timestamp, String content) {
		this.testingTool = testingTool;
		this.url = url;
		this.timestamp = timestamp;
		this.content = content;
	}

	public String getTestingTool() {
		return testingTool;
	}

	public void setTestingTool(String testingTool) {
		this.testingTool = testingTool;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
