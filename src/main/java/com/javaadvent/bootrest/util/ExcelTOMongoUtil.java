package com.javaadvent.bootrest.util;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class ExcelTOMongoUtil{
	public static void loadDataFromExcel() {
		try {
		System.out.println("Viola!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		DB db = mongoClient.getDB("test");
		System.out.println("Connected to Database successfully");
		DBCollection coll = db.getCollection("attendee");
		System.out.println("Collection your_collection name selected successfully");

		DBCollection OR = db.getCollection("attendee");
		System.out.println("Collection Device_Details selected successfully");
		OR.drop();
		DBObject arg1 = null;
		//coll.update(query, update);
		DBCollection OR_UPLOAD = 
				db.createCollection("attendee", arg1);
		String path ="D:\\UnConference\\ucdata.xlsx";

		File myFile = new File(path);
		FileInputStream inputStream = new FileInputStream(myFile);
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		int number=workbook.getNumberOfSheets();
		System.out.println("NumberOfSheets "+number);

		//for(int i=0;i<number;i++)
		//{
			XSSFSheet sheet = workbook.getSheetAt(1);
			int col_value=sheet.getRow(0).getLastCellNum();
			int row_num= sheet.getLastRowNum();
			System.out.println("row_num "+row_num);
			List<String> DBheader = new ArrayList<String>();
			List<String> Data = new ArrayList<String>();

			for(int z=1;z<=row_num;z++){
				DBheader.clear();
				Data.clear();
				for(int j=0;j<col_value;j++)
				{
					if(sheet.getRow(0).getCell(j).toString()!=null || sheet.getRow(0)!=null)
					{
						String cel_value = sheet.getRow(0).getCell(j).toString();
						DBheader.add(cel_value.trim());
					}
					else{
						break;

					}
				}
				for(int k=0;k<col_value;k++){
					String data =" ";   
					if(sheet.getRow(z).getCell(k)!=null)
					{
						data =  sheet.getRow(z).getCell(k).toString();
					}
					Data.add(data.trim());

				}
				BasicDBObject doc = new BasicDBObject();
				System.out.println("Data.size() "+Data.size());

				int l=0;
				for(String headers:DBheader)
				{ 
					if(l>Data.size()){break;}
					doc.append(headers, Data.get(l));
					l++;
				}
				OR_UPLOAD.insert(doc);
			}

		//}
		System.out.println("File Upload Done");
		mongoClient.close();}
		catch(Exception e) {
			e.printStackTrace();
			
		}
	}
}                 
