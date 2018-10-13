package com.me.boot.util;

import java.nio.file.Path;
import java.util.stream.Stream;

import javax.servlet.http.HttpSession;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorage {
	
	public void store(MultipartFile file);
	public Resource loadFile(String filename);
	public void deleteAll();
	public void init(HttpSession session);
	public Stream<Path> loadFiles();
}