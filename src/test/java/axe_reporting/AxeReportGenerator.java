package axe_reporting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Iterator;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import axe_reporting_pojo.MainContent;
import axe_reporting_pojo.ReportContent;
import axe_reporting_pojo.TableContent;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

@SuppressWarnings("unchecked")
public class AxeReportGenerator {

	static TableContent tableContentBean;
	static MainContent mainContentBean;
	static ReportContent reportContentBean;
	static String f = System.getProperty("user.dir") + File.separator + "target" + File.separator  + "axe-report.html";

	public static void axe_report_generator(String testingTool, String url, String timestamp, String json_violations) {
		try {
			Version v = Configuration.VERSION_2_3_30;
			Configuration configuration = new Configuration(v);
			InputStream tableContentFile = AxeReportGenerator.class.getResourceAsStream("/tableContent.html");
			InputStream contentFile = AxeReportGenerator.class.getResourceAsStream("/content.html");
			InputStream reportFile = AxeReportGenerator.class.getResourceAsStream("/reportTemplate.html");

			@SuppressWarnings("deprecation")
			String tableContent = IOUtils.toString(tableContentFile);
			@SuppressWarnings("deprecation")
			String content = IOUtils.toString(contentFile);
			@SuppressWarnings("deprecation")
			String reportContent = IOUtils.toString(reportFile);
			Template tableContentTemp = new Template("tableContent", tableContent, configuration);
			Template contentTemp = new Template("tableContent", content, configuration);
			Template reportTemp = new Template("tableContent", reportContent, configuration);
			

			JSONParser jsonParser = new JSONParser();
			JSONArray violationsArray = (JSONArray) jsonParser.parse(json_violations);
			Iterator<JSONObject> iter_violations = violationsArray.iterator();
			String content_output = "";
			while (iter_violations.hasNext()) {
				JSONObject obj = iter_violations.next();
				String issueType = (String) obj.get("id");
				JSONArray nodes = (JSONArray) obj.get("nodes");
				Iterator<JSONObject> node_iter = nodes.iterator();
				String table_output = "";
				while (node_iter.hasNext()) {
					JSONObject node = node_iter.next();
					String nodesImpacted = (String) node.get("target").toString();
					String impact = node.get("impact").toString();
					String failureSummary = node.get("failureSummary").toString();
					tableContentBean = new TableContent(impact, nodesImpacted, failureSummary);
					StringWriter sw = new StringWriter();
					tableContentTemp.process(tableContentBean, sw);
					sw.flush();
					table_output += sw.toString();
					sw.close();
				}

				StringWriter sw = new StringWriter();
				mainContentBean = new MainContent(issueType, table_output);
				contentTemp.process(mainContentBean, sw);
				sw.flush();
				content_output += sw.toString();
				sw.close();

			}
			reportContentBean = new ReportContent(testingTool, url, timestamp, content_output);
			StringWriter sw = new StringWriter();
			reportTemp.process(reportContentBean, sw);
			sw.flush();
			String file_content = sw.toString();
			sw.close();
			File file = new File(f);
			FileWriter fw = new FileWriter(file);
			fw.write(file_content);

			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
