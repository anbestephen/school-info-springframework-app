package com.eritechno.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

public class UploadUtils {
	private static Logger logger = org.slf4j.LoggerFactory
			.getLogger(UploadUtils.class.getName());
	public static final String DESTINATION_DIRECTORY_PROPERTY = "catalina.home";
	public static final String PHOTOS_ROOT = "photos";
	public static final String STUDENT_DIRECTORY = "student";
	public static final String IMAGE_EXTENTION = ".jpg";
	public static final String DEFAULT_PHOTO = "images/user.jpg";

	/**
	 * 
	 * return the images root Location
	 * 
	 * @param directory
	 * @return
	 */
	public static String getImageDestinationLocation(String directory) {
		Assert.hasLength(directory, "directory is required");
		return System.getProperty(DESTINATION_DIRECTORY_PROPERTY)
				+ File.separator + PHOTOS_ROOT + File.separator + directory;
	}

	/**
	 * 
	 * main image upload method
	 * 
	 * @param studentImage
	 * @param directory
	 * @param id
	 * @return
	 */
	public static String uploadImage(MultipartFile studentImage,
			String directory, long id) {
		Assert.notNull(studentImage, "studentImage is required");
		Assert.notNull(directory, "directory is required");
		File dir = getImagesDirectory(directory);
		saveImage(studentImage, dir, id);
		return "You successfully uploaded file=";

	}

	/**
	 * 
	 * saves the image with id as file name
	 * 
	 * @param studentImage
	 * @param destination
	 * @param id
	 */
	private static void saveImage(MultipartFile studentImage, File destination,
			long id) {
		Assert.notNull(studentImage, "studentImage is required");
		Assert.notNull(destination, "destination is required");
		File serverFile = new File(destination.getAbsolutePath()
				+ File.separator + "" + id + ""
				+ getFileExtention(studentImage.getOriginalFilename()));
		try {
			IOUtils.write(studentImage.getBytes(), new FileOutputStream(
					serverFile));
		} catch (Exception e) {
			logger.error("Error writing uploaded image", e);
		}
	}

	/**
	 * 
	 * returns images directory as File object
	 * 
	 * @param directoryName
	 * @return
	 */
	private static File getImagesDirectory(String directoryName) {
		Assert.hasLength(directoryName, "directory is required");
		File directory = new File(getImageDestinationLocation(directoryName));
		if (!directory.exists())
			directory.mkdirs();
		return directory;
	}

	/**
	 * 
	 * returns image as <code>byte</code> array
	 * 
	 * @param studentId
	 * @return
	 */
	public static byte[] getStudentPhoto(long studentId) {
		byte[] image = null;
		try {
			File photo = new File(getStudentPhotoFullPath(studentId));
			if (!photo.exists()) {
				photo = getDefaultProfilePhoto();
			}
			image = IOUtils.toByteArray(new FileInputStream(photo));
		} catch (Exception e) {
			logger.error("Error reading image", e);
		}
		return image;
	}

	private static File getDefaultProfilePhoto() {
		Resource resource = new ClassPathResource(DEFAULT_PHOTO);
		File file = null;
		try {
			file = resource.getFile();
		} catch (IOException e) {
			logger.error("Error reading file", e);
		}
		return file;
	}

	/**
	 * 
	 * return full image path for a student with specific id
	 * 
	 * @param studentId
	 * @return
	 */
	private static String getStudentPhotoFullPath(long studentId) {
		return getImageDestinationLocation(STUDENT_DIRECTORY) + File.separator
				+ studentId + IMAGE_EXTENTION;
	}

	public static String getFileExtention(String fileName) {
		Assert.hasLength(fileName, "fileName is required");
		return fileName.substring(fileName.lastIndexOf("."));
	}
}
