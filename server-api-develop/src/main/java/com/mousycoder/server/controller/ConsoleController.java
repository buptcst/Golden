package com.mousycoder.server.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import com.mousycoder.server.constants.ErrorType;
import com.mousycoder.server.dto.ResultDTO;
import com.mousycoder.server.dto.commodity.CommoditiesDTO;
import com.mousycoder.server.param.BaseParam;
import com.mousycoder.server.service.CommodityService;
import com.mousycoder.server.url.RestUrl;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Api(value = "console", description = "Retrieve4 the Console Outputs", produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
public class ConsoleController {

	// @Autowired
	// private CommodityService commodityService;

	@RequestMapping(value = "/consoleLogs/{job}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Getting4 jenkins console outputs", notes = "Getting4 jenkins console outputs")
	@ResponseBody
	public String getConsoleOutputs(@ApiParam(value = "url", required = true) @PathVariable(value = "job") String job)
			throws Exception {
		// StringBuilder result = new StringBuilder();
		// URL url = new URL("http://localhost:8080/job/" + job +
		// "/lastBuild/logText/progressiveText?start=0");
		// HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// conn.setRequestMethod("GET");
		// conn.setConnectTimeout(30000);
		// BufferedReader rd = new BufferedReader(new
		// InputStreamReader(conn.getInputStream()));
		// String line;
		// while ((line = rd.readLine()) != null) {
		// result.append(line);
		// }
		// rd.close();
		// return result.toString();

		StringBuilder result = new StringBuilder();

		BufferedReader br = null;

		String command = null;

		try {

			command =

					"curl -D- -k -u "

							+ "daniel" + ":"

							+ "Diablo98765"

							+ " -X GET "

							+ "http://localhost:8080/job/" + job + "/lastBuild/logText/progressiveText?start=0";

			Process p = Runtime.getRuntime().exec(command);

			br = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = null;

			while ((line = br.readLine()) != null) {

				System.out.println(line);
				result.append(line);
			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			if (br != null) {

				try {

					br.close();

				} catch (Exception e) {

					e.printStackTrace();

				}

			}

		}

		return result.toString();

	}

}
