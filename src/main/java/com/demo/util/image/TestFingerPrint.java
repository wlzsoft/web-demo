package com.demo.util.image;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

public class TestFingerPrint {
	
    
    public void testCompare() throws IOException{
        FingerPrint fp1 = new FingerPrint(ImageIO.read(new File("d:\\tmp\\111.png")));
        FingerPrint fp2 =new FingerPrint(ImageIO.read(new File("d:\\tmp\\113.png")));
     //   System.out.println(fp1.toString(true));
        System.out.printf("sim=%f",fp1.compare(fp2));
    }

}
