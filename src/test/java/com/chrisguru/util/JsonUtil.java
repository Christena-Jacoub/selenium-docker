package com.chrisguru.util;

import com.chrisguru.tests.vendorPortal.model.VendorPortalTestData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class JsonUtil {
    public static final Logger log= LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper mapper=new ObjectMapper();

    /*public static VendorPortalTestData getTestData(String path){
        try(InputStream stream= ResourceLoader.getResource(path)){
            return  mapper.readValue(stream,VendorPortalTestData.class);
        }catch (Exception e){
            log.error("There an error in reading the file {}",path, e);
        }
        return null;
    }*/

    //// To be more generic, I used <T>T --> this means that I will return any class name
    /// based on the name that I recieved in tha parameter (Class<T> classType)
    public static <testDataClass> testDataClass getTestData(String path, Class<testDataClass> classType){
        try(InputStream stream= ResourceLoader.getResource(path)){
            return  mapper.readValue(stream,classType);
        }catch (Exception e){
            log.error("There an error in reading the file {}",path, e);
        }
        return null;
    }



}
