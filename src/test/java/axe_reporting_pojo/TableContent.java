package axe_reporting_pojo;

public class TableContent {
	private String impact;
	private String nodesImpacted;
	private String failureSummary;

	public TableContent(String impact, String nodesImpacted, String failureSummary) {
		this.impact = impact;
		this.nodesImpacted = nodesImpacted;
		this.failureSummary = failureSummary;
	}

	public String getImpact() {
		return impact;
	}

	public void setImpact(String impact) {
		this.impact = impact;
	}

	public String getNodesImpacted() {
		return nodesImpacted;
	}

	public void setNodesImpacted(String nodesImpacted) {
		this.nodesImpacted = nodesImpacted;
	}

	public String getFailureSummary() {
		return failureSummary;
	}

	public void setFailureSummary(String failureSummary) {
		this.failureSummary = failureSummary;
	}

}
