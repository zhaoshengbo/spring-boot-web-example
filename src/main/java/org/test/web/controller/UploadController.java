package org.test.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadController {

	@RequestMapping("upload")
	public ModelAndView upload(MultipartFile file) throws IOException {
		File serverFile = this.createFile(file.getOriginalFilename());
		this.writeFileContent(serverFile, file);

		ModelAndView mv = new ModelAndView("index");
		mv.addObject("ok", "true");

		return mv;
	}

	@ExceptionHandler(value = { IOException.class })
	public ModelAndView exceptionHandler(IOException ex) {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("ok", false);
		mv.addObject("errMsg", ex.getLocalizedMessage());

		return mv;
	}

	private File createFile(String fileName) throws IOException {
		File file = new File(fileName);
		file.createNewFile();

		return file;
	}

	private void writeFileContent(File serverFile, MultipartFile uploadFile) throws IOException {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(serverFile);
			InputStream uis = uploadFile.getInputStream();
			byte[] buffer = new byte[1024];
			int read = -1;
			do {
				read = uis.read(buffer);
				if (read != -1) {
					fos.write(buffer, 0, read);
				}
			} while (read != -1);
			fos.flush();
		} finally {
			fos.close();
		}

	}
}
