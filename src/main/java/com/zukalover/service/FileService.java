package com.zukalover.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zukalover.entity.FileEntity;
import com.zukalover.repository.FileRepository;

@Service
public class FileService {

	@Autowired
	FileRepository fileRepository;
	
	private String admin="admin";
	private String alfrescoURL="http://localhost:8080/alfresco/api/-default-/public/cmis/versions/1.1/atom";
	
	public void saveFile(FileEntity file)
	{
		fileRepository.save(file);
	}
	
	public FileEntity findById(Integer id)
	{
		return fileRepository.getOne(id);
	}
	
	public void deleteById(Integer id)
	{
		fileRepository.deleteById(id);
	}
	
	public List<FileEntity> findAll()
	{
		return fileRepository.findAll();
	}
	
	public FileEntity findByFilename(String filename)
	{
		return fileRepository.findByFilename(filename);
	}
	
	
	public List<FileEntity> findByUserId( Integer userId)
	{
		return fileRepository.findByUserId( userId);
	}
	
	public String sendToAlfresco(String filename)
	{
		 SessionFactory factory = SessionFactoryImpl.newInstance();
	        Map<String, String> parameter = new HashMap<>();
	 
	        // user credentials
	        parameter.put(SessionParameter.USER, admin);
	        parameter.put(SessionParameter.PASSWORD, admin);
	 
	        // connection settings
	        parameter.put(SessionParameter.ATOMPUB_URL, alfrescoURL);
	        parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
	 
	        // create session
	        Session session = factory.getRepositories(parameter).get(0).createSession();
	        Folder root = session.getRootFolder();
	 
	        // properties
	        Map<String, Object> properties = new HashMap<>();
	        properties.put(PropertyIds.OBJECT_TYPE_ID, "cmis:folder");
	        properties.put(PropertyIds.NAME, filename); // folder name
	 
	        // create the folder
	        Folder parent = root.createFolder(properties);
	 

	       session.clear();
	       /** String extention = FilenameUtils.getExtension(fileP);
	        // properties
	        //Map<String, Object> properties2 = new HashMap<String, Object>();
	        //properties2.put(PropertyIds.OBJECT_TYPE_ID, "cmis:document");
	        //properties2.put(PropertyIds.NAME, name);
	        
	        //NEW ADDITIONS -2
	       // ContentStream contentStream = new ContentStreamImpl(filename,BigInteger.valueOf(fileContent.length),"binary/octet-stream", new ByteArrayInputStream(fileContent));
	        
	        //Document newDoc = parent.createDocument(properties2, contentStream, VersioningState.MAJOR);
	       */
	       
	       return parent.getId();
	}
	/**
	public void sendToAlfresco(String filename)
	{
		 SessionFactory factory = SessionFactoryImpl.newInstance();
	        Map<String, String> parameter = new HashMap<String, String>();
	 
	        // user credentials
	        parameter.put(SessionParameter.USER, "admin");
	        parameter.put(SessionParameter.PASSWORD, "admin");
	 
	        // connection settings
	        parameter.put(SessionParameter.ATOMPUB_URL, "http://127.0.0.1:8080/alfresco/api/-default-/public/cmis/versions/1.1/atom");
	        parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
	 
	        // create session
	        Session session = factory.getRepositories(parameter).get(0).createSession();
	        Folder root = session.getRootFolder();
	 
	        // properties
	        Map<String, Object> properties = new HashMap<String, Object>();
	        properties.put(PropertyIds.OBJECT_TYPE_ID, "cmis:folder");
	        properties.put(PropertyIds.NAME, filename); // folder name
	 
	        // create the folder
	        Folder parent = root.createFolder(properties);
	 

	       
	       // String extention = FilenameUtils.getExtension(fileP);
	        // properties
	        //Map<String, Object> properties2 = new HashMap<String, Object>();
	        //properties2.put(PropertyIds.OBJECT_TYPE_ID, "cmis:document");
	        //properties2.put(PropertyIds.NAME, name);
	        
	        //NEW ADDITIONS -2
	       // ContentStream contentStream = new ContentStreamImpl(filename,BigInteger.valueOf(fileContent.length),"binary/octet-stream", new ByteArrayInputStream(fileContent));
	        
	        //Document newDoc = parent.createDocument(properties2, contentStream, VersioningState.MAJOR);
	}
	*/
	
	
	
	//GETTING ALFRESCO ID BY USERID AND FILE ID 27 JAN 2020
	
	public String findFileAlfrescoId(Integer userid, Integer fileid)
	{
		return fileRepository.findFileAlfrescoid(userid, fileid);
	}
	
	
	//CLEAR DIRECTORIES THAT WE WILL BE WORKING WITH
	public void clearDirectories(String imagePath,String docPath, String outputPath, String alfrescoOutput)
	{
		File imageDir = new File(imagePath);
		File docDir = new File(docPath);
		File outputDir = new File(outputPath);
		File alfrescoDir = new File(alfrescoOutput);
		
		File[] imageFiles = imageDir.listFiles();
		File[] docFiles = docDir.listFiles();
		File[] outputFiles = outputDir.listFiles();
		File[] alfrescoFiles = alfrescoDir.listFiles();
		
		for(File file: imageFiles)
		{
			file.delete();
		}
		
		for(File file: docFiles)
		{
			file.delete();
		}
		
		for(File file : outputFiles)
		{
			file.delete();
		}
		
		for(File file: alfrescoFiles)
		{
			file.delete();
		}
	}
	
	
	public void deleteFromAlfresco(FileEntity fileEntity)
	{
		SessionFactory factory = SessionFactoryImpl.newInstance();
        Map<String, String> parameter = new HashMap<>();
 
        // user credentials
        parameter.put(SessionParameter.USER, admin);
        parameter.put(SessionParameter.PASSWORD, admin);
 
        // connection settings
        parameter.put(SessionParameter.ATOMPUB_URL, alfrescoURL);
        parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
 
        // create session
        Session session = factory.getRepositories(parameter).get(0).createSession();
        Folder root = session.getRootFolder();
        
        
        Folder theFolder = (Folder) session.getObject(fileEntity.getAlfrescoid());
        
        theFolder.delete();
        session.clear();
	}
}
