package com.ant.jobgod.jobgod.util;

import android.content.Context;
import java.io.File;
import java.util.HashMap;

/**
 * Created by Mr.Jude on 2015/6/12.
 */
public class FileManager {
    private static FileManager instance;
    public enum Dir{
        Image,Object
    }

    private HashMap<Dir,File> fileHashMap;



    public static FileManager getInstance(){
        if (instance == null){
            instance = new FileManager();
        }
        return instance;
    }

    public void init(Context ctx){
        File root = ctx.getFilesDir();
        fileHashMap = new HashMap<>();
        for (Dir dir: Dir.values()){
            File dirFile = new File(root,dir.name());
            if (!dirFile.exists())dirFile.mkdir();
            fileHashMap.put(dir,dirFile);
        }
    }

    public void clearDir(Dir dir){
        fileHashMap.get(dir).delete();
        fileHashMap.get(dir).mkdir();
    }

    public File getChild(Dir dir,String name){
        return new File(fileHashMap.get(dir),name);
    }

    public void deletChild(Dir dir,String name){
        new File(fileHashMap.get(dir),name).delete();
    }

}
