package org.springweb.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletConfig;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import springws.usecase.Client;


@MultipartConfig()
public class UploadServlet extends HttpServlet {
	final static Logger LOGGER = Logger.getLogger(UploadServlet.class.getName());
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		long startTime = System.nanoTime();
		List<Part> fileParts = (List<Part>) request.getParts();
		for (Part filePart : fileParts) {
			String name = filePart.getName();
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix
			LOGGER.info(String.format("File Upload Name: %s", fileName));
			long size = filePart.getSize();	
			LOGGER.info(String.format("Uploaded File Size: %d", size));
			InputStream fileContent = filePart.getInputStream();
			ReadableByteChannel in = Channels.newChannel(fileContent);
			
			WritableByteChannel out = Channels.newChannel(new FileOutputStream( "/dev/zero" ));
			
			ByteBuffer buffer = ByteBuffer.allocate(4096);
			
			while (in.read(buffer) != -1) {
			      buffer.flip( );
			      out.write(buffer);
			      buffer.clear( );
			}
		}
		long estimatedTime = System.nanoTime() - startTime;
		LOGGER.info(String.format("Upload Elapesed: %f Seconds",  estimatedTime/1000000000.0));
		response.setStatus(response.SC_MOVED_TEMPORARILY);
		response.addHeader("Location","index.html");
		return;
	}

}
