package com.dpc.service;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.joda.time.DateTime;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
@Service
public class StorageService {
	

	private final Path rootLocation = Paths.get("upload-dir");

	static String ISO_Date() {
		return DateTime.now().toString("yyyy_MM_dd_HH_mm_ss");
	}

	public void store(MultipartFile file) {

		try {
			// Timestamp tmstamp = new Timestamp(System.currentTimeMillis());
			Files.copy(file.getInputStream(), this.rootLocation.resolve( file.getOriginalFilename()));

		} catch (Exception e) {
			throw new RuntimeException("FAIL!");
		}
	}

	public Resource loadFile(String filename) {
		try {

			Path file = rootLocation.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!");
		}
	}

	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	public void init() {
		try {
			Files.createDirectory(rootLocation);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize storage!");
		}
	}

}
