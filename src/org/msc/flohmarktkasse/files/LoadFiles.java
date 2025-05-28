package org.msc.flohmarktkasse.files;

import java.net.URL;

import javax.swing.ImageIcon;



public class LoadFiles {
    private static LoadFiles obj=null;
    private static Class cl = null;
    public static LoadFiles getObject() {
	if(obj==null) {
	    obj=new LoadFiles();
	}
	return obj;
    }
    public static Class getTheClass() {
	if(obj==null) {
	    obj=new LoadFiles();
	}
	return obj.getClass();
    }
    public static URL getURL(String filename) {
	if(obj==null) {
	    obj=new LoadFiles();
	}
	return obj.getClass().getResource(filename);
    }
    public static ImageIcon getImage(String filename) {
	if(cl==null) {
	    cl=new LoadFiles().getClass();
	}
	return new ImageIcon(cl.getResource(filename));
    }
}
