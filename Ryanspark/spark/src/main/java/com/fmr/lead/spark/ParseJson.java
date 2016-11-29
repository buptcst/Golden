package com.fmr.lead.spark;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.spark.api.java.function.FlatMapFunction;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;
import org.codehaus.jettison.json.JSONObject;

class ParseJson implements FlatMapFunction<Iterator<String>, Issue> {

	public Iterator<Issue> call(Iterator<String> lines) throws Exception {
		ArrayList<Issue> issues = new ArrayList<Issue>();
		ObjectMapper mapper = new ObjectMapper();
		while (lines.hasNext()) {
			String line = lines.next();
			JSONObject issueJson = new JSONObject(line);
			String issueId = issueJson.optString("key");
			Issue issue = new Issue();
			issue.setIssueId(issueId);
			issues.add(issue);
			try {
				//issues.add(mapper.readValue(line, Issue.class));
			} catch (Exception e) {
				// skip records on failure
			}
		}
		return issues.iterator();
	}

}
