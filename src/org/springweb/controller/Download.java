package org.springweb.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.servlet.http.HttpServletResponse;

public class Download {
	final static int BLOCK_SIZE = 64;
	private int bytesRead = 0;
	private int download_length = 0;
	private int blocksRead = -1;
	public int  GetProgress(){ 
		return blocksRead; 
	}
	public int GetDone(){
		return download_length/(BLOCK_SIZE*blocksRead);
	}
	public void SetDone(){
		bytesRead = -1;
	}
	void FileContent(HttpServletResponse response) throws Exception{
		String fileURI = "/home/sherwinp/workspace/docx4j/CHANGELOG.md";
		File file = new File(fileURI);
		download_length = (int)file.length();
        file = null;
		String mimeType = "application/octet-stream";
        response.setContentType(mimeType);
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Disposition","attachment; filename=\"" + "test.txt\"");
        response.setContentLength(download_length);
        
	    RandomAccessFile aFile = new RandomAccessFile("/home/sherwinp/workspace/docx4j/CHANGELOG.md", "r");
	    FileChannel inChannel = aFile.getChannel();

	    ByteBuffer buf = ByteBuffer.allocate(BLOCK_SIZE);

	    bytesRead = inChannel.read(buf);
	    blocksRead = 1;
	    while (bytesRead != -1) {

	      buf.flip();

	      while(buf.hasRemaining()){
	    	  response.getOutputStream().write(buf.get());
	      }
	      response.flushBuffer();
	      buf.clear();
	      bytesRead = inChannel.read(buf);
	      if( bytesRead > -1 ){
	    	  blocksRead = blocksRead + 1;
	      }else{
	    	  aFile.close();
	      }
	      Thread.currentThread().sleep(8);
	    }
	   
	}
}
