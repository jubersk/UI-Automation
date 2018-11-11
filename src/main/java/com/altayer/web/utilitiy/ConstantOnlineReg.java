package com.altayer.web.utilitiy;

import java.nio.file.Path;
import java.nio.file.Paths;


public class ConstantOnlineReg {
	
	static Path currentRelativePath = Paths.get("");
	static String rootPath = currentRelativePath.toAbsolutePath().toString();
	
	//private static String rootPath = UtilityClass.GetAppSetttingsByKey("s"); 
	
	public static String Path_TestData = rootPath + "\\src\\main\\java\\testData\\";
	
	public static String Path_ScreenShot = rootPath + "\\src\\main\\java\\com\\altayer\\web\\screenshots\\";
		
	public static final String File_TestData = "TestData.xlsx";
	public static final String Payment_Type = "";
	public static final String Applications_Options = "";


	//Test Data Sheet Columns
	public static final int Col_TestCaseName = 0;	
	public static final int Col_Browser = 1;
	public static final int Col_URL = 2;
	public static final int Col_FirstName = 3;
	public static final int Col_LastName = 4;
	public static final int Col_EmailAddress = 5;
	public static final int Col_Password = 6;
	public static final int Col_PhoneNumber = 7;
	public static final int Col_InvalidEmailAddress = 8;
	public static final int Col_InvalidPhoneNumber = 9;
	public static final int Col_NewPhoneNumber = 10;
	public static final int Col_Item1 = 11;
	public static final int Col_Item1Size = 12;
	public static final int Col_Item2 = 13;
	public static final int Col_Item2Size = 14;
	public static final int Col_Item3 = 15;
	public static final int Col_Item3Size = 16;
	public static final int Col_Color = 17;
	public static final int Col_Result = 18;
	
	
	

	


}
