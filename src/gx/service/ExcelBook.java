package gx.service;

import java.io.File;  
import java.io.IOException;  
import java.sql.ResultSet;
import java.util.ArrayList;    
import jxl.Cell;  
import jxl.Sheet;  
import jxl.Workbook;  
import jxl.read.biff.BiffException;  
import jxl.write.Label;  
import jxl.write.WritableSheet;  
import jxl.write.WritableWorkbook;  
import jxl.write.WriteException;  
  
public class ExcelBook {  
  
    //�����ݵ�����Excel  
    public void excelOut(String path, ResultSet rs, Object[] params) {  
        WritableWorkbook bWorkbook = null;  
        try {  
            // ����Excel����
            bWorkbook = Workbook.createWorkbook(new File(path));  
            //  ͨ��Excel���󴴽�һ��ѡ�����
            WritableSheet sheet = bWorkbook.createSheet("sheet1", 0);  
            ////ʹ��ѭ�������ݶ��� 
            int j=0;
            for (int i = 0; i < params.length; i++) {
            	Label label=new Label(i,j,(String)params[i]);
            	sheet.addCell(label); 
			}
            
            j++;
        	while(rs!=null && rs.next()){        
        		for (int i = 0; i < params.length; i++) {
                	Label label=new Label(i,j,rs.getString(i+1));
                	sheet.addCell(label);
    			}
           /* Label label=new Label(0,j,String.valueOf(rs.getString(1)));  
            Label label1=new Label(1,j,String.valueOf(rs.getString(2)));  
            Label label2=new Label(2,j,String.valueOf(rs.getString(3)));            
            sheet.addCell(label);  
            sheet.addCell(label1);  
            sheet.addCell(label2);*/
            j++;
            }     	 
                                   
            // ����һ����Ԫ����󣬵�һ��Ϊ�У��ڶ���Ϊ�У�������Ϊֵ 
            //Label label = new Label(0, 2, "test");  
            // �������õĵ�Ԫ�����ѡ���
            //sheet.addCell(label);   
            // д��Ŀ��·��
            bWorkbook.write();  
  
        } catch (Exception e) {  
            // TODO Auto-generated catch block   
            e.printStackTrace();  
        } finally {  
            try {  
                bWorkbook.close();  
            } catch (Exception e) {  
                // TODO Auto-generated catch block   
                e.printStackTrace();  
            }  
        }  
  
    }  
      
      
    ////excel����
    public ArrayList<String[]> ExcelIn(String path){  
        ArrayList<String[]>arrayList=new ArrayList<String[]>();  
        Workbook bWorkbook=null;  
        try {  
            bWorkbook=Workbook.getWorkbook(new File(path));  
            Sheet sheet=bWorkbook.getSheet(0);  
            for (int i = 0; i < sheet.getRows(); i++) {
                 String[] str=new String[sheet.getColumns()];
                //��ȡ��Ԫ����� 
                //Cell cell =sheet.getCell(0,i);  
                //  ��ȡ��Ԫ���ֵ 

                for(int j=0; j<sheet.getColumns(); j++) 
                {
                	str[j] = sheet.getCell(j,i).getContents();
                }
                arrayList.add(str);                  
            }  
              
        } catch (BiffException e) {  
            // TODO Auto-generated catch block   
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block   
            e.printStackTrace();  
        }finally {  
            bWorkbook.close();  
        }  
        return arrayList;  
    }  
      
  
    public static void main(String[] args) {  
          
        //�����ݵ�����Excel��
    	/*MysqlConn db = new MysqlConn();
    	ResultSet rs = null;
        ExcelBook book = new ExcelBook();   
        rs = db.doQuery("select *from student", new Object[]{});
     
	    book.excelOut("D:/ѧ����Ϣ.xls",rs, new Object[]{"ѧ��","����", "����","�Ա�","רҵ","����Ա"});   
	    System.out.println("�����ɹ�");*/
    	//�����ݴ�Excel�е���
	    ExcelBook book = new ExcelBook();
	    MysqlConn db = new MysqlConn();
        ArrayList<String[]> list = book.ExcelIn("D:/ѧ����Ϣ.xls");  
        
        for(int i = 0 ; i < list.size() ; i++) 
        { 
        	for(int j = 0 ; j < list.get(i).length ; j++) 
        	{
        	    System.out.print(list.get(i)[j]+" ");
        	}
        	db.doExecute("insert into stu values(?,?,?,?,?,?)", list.get(i));
        	System.out.println();
        }
        
        
    }  
}  
