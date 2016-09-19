package org.springweb.controller;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/")
@Controller
public class homecontroller {
	@RequestMapping(value="index.html", method = RequestMethod.GET)
    public String index() {
        return "home";
    }
	@RequestMapping(value="index.html", method = RequestMethod.POST)
    public String index_post(HttpServletRequest request) throws IOException, ServletException {
		request.getParts();
        return "redirect:index.html";
    }
	@RequestMapping(value="download/{type}", method = RequestMethod.GET)
	public void download(HttpServletResponse response, HttpServletRequest request, @PathVariable("type") String type) throws Exception{
		Download download = new Download();
		request.getSession().setAttribute("download_progress", download);
		download.FileContent(response);
		
		download.SetDone();
	}
	@RequestMapping(value="download/{type}", method = RequestMethod.HEAD)
	public void download_progress(HttpServletResponse response, HttpServletRequest request, @PathVariable("type") String type) throws Exception{		
		Download download = (Download)request.getSession().getAttribute("download_progress");
		
		response.setContentType("application/json");
		response.setIntHeader("download_progress", download!=null?download.GetDone():-1);
		
	}
}