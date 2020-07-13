package com.sunnysnow.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import jxl.Cell;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**����jxl.jar;*�������书�ܣ�sheet2���Ӳ��Ա���չ�֣�------��ʵ�֣�*/
public class CWOutputFile {
    
    public static void main(String[] args) throws RowsExceededException, WriteException, BiffException, IOException{
        CWOutputFile t=new CWOutputFile();
        String File=t.cOutputFile("����");
    }
    
    /** wOutputFile����д����ļ�* wOutputFile(�ļ�·����������ţ�������֤�㣬Ԥ�ڽ����ʵ�ʽ���������룬״̬�룬��Ӧ���)*/
    public void wOutputFile(String filepath, String caseNo,String testPoint, String preResult, String fresult, String errCode,String status, String respond) throws IOException,RowsExceededException, WriteException, BiffException 
            {
                File output = new File(filepath);
                String result = "";
                InputStream instream = new FileInputStream(filepath);
                Workbook readwb = Workbook.getWorkbook(instream);
                WritableWorkbook wbook = Workbook.createWorkbook(output, readwb); // �����ļ�����һ����������
                WritableSheet readsheet = wbook.getSheet(0);
                //int rsColumns = readsheet.getColumns(); //��ȡSheet������������������
                int rsRows = readsheet.getRows(); 
                // ��ȡSheet������������������
                /********************************������ʽ���� ****************************/
                WritableFont font = new WritableFont(WritableFont.createFont("����"), 10,WritableFont.NO_BOLD);// ������ʽ
                WritableCellFormat wcf = new WritableCellFormat(font);
                /***********************************************************************/
                Cell cell1 = readsheet.getCell(0, rsRows);
                if (cell1.getContents().equals("")) {
                    Label labetest1 = new Label(0, rsRows, caseNo);// ��1��--������ţ�
                    Label labetest2 = new Label(1, rsRows, testPoint); // ��2��--��֤���Ե㣻
                    Label labetest3 = new Label(2, rsRows, preResult); // ��3��--Ԥ�ڽ����
                    Label labetest4 = new Label(3, rsRows, fresult);// ��4��--ʵ�ʽ����
                    Label labetest5 = new Label(4, rsRows, errCode);// ��5��--�����룻
                    if (preResult == fresult) {
                        result = "ͨ��";wcf.setBackground(Colour.BRIGHT_GREEN); // ͨ��������ע��ɫ
                        } 
                    else {result = "��ͨ��";wcf.setBackground(Colour.RED);// ��ͨ��������ע��ɫ
                    }
                    Label labetest6 = new Label(5, rsRows, result, wcf); // ��6��--ִ�н����
                    Label labetest7 = new Label(6, rsRows, status); // ��7��--״̬��
                    Label labetest8 = new Label(7, rsRows, respond);// ��8��--��Ӧ���
                    readsheet.addCell(labetest1);
                    readsheet.addCell(labetest2);
                    readsheet.addCell(labetest3);
                    readsheet.addCell(labetest4);
                    readsheet.addCell(labetest5);
                    readsheet.addCell(labetest6);
                    readsheet.addCell(labetest7);
                    readsheet.addCell(labetest8);
                    }
                wbook.write();
                wbook.close();
                }
    /** cOutputFile������������ļ����������Ϊ�������ͣ��翪���ȣ�* cOutputFile���������ļ�·������ΪwOutputFile����Σ�*/
    public String cOutputFile(String tradeType) 
            throws IOException, WriteException {
                String temp_str = "";
                Date dt = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                temp_str = sdf.format(dt); // ��ȡʱ���// ���·��Ĭ��Ϊ apache-jmeter-3.1\bin
                String filepath = "D:\\\\"+tradeType+"_output_" + "_" + temp_str + ".xls"; // ��ʱ�����������ļ���ȷ��Ψһ
                File output = new File(filepath);
                if (!output.isFile()) {
                    output.createNewFile(); // ���ָ���ļ������ڣ����½����ļ�
                    WritableWorkbook writeBook = Workbook.createWorkbook(output);
                    WritableSheet Sheet = writeBook.createSheet("������", 0); // createSheet(sheet����,�ڼ���sheet)
                    WritableFont headfont = new WritableFont(WritableFont.createFont("����"), 11, WritableFont.BOLD); // ������ʽ
                    WritableCellFormat headwcf = new WritableCellFormat(headfont);
                    headwcf.setBackground(Colour.GRAY_25); // ��ɫ��ɫ
                    Sheet.setColumnView(0, 11); // �����п��setColumnView(�кţ����)
                    Sheet.setColumnView(1, 30);
                    Sheet.setColumnView(2, 35);
                    Sheet.setColumnView(3, 35);
                    Sheet.setColumnView(4, 18);
                    Sheet.setColumnView(5, 11);
                    Sheet.setColumnView(6, 11);
                    Sheet.setColumnView(7, 50);
                    headwcf.setAlignment(Alignment.CENTRE); // �������־��ж��뷽ʽ;
                    headwcf.setVerticalAlignment(VerticalAlignment.CENTRE); // ���ô�ֱ����;
                    Label labe00 = new Label(0, 0, "�������", headwcf); // Label(�к�,�к�, ����)
                    Label labe10 = new Label(1, 0, "��֤���Ե�", headwcf);
                    Label labe20 = new Label(2, 0, "Ԥ�ڽ��", headwcf);
                    Label labe30 = new Label(3, 0, "ʵ�ʽ��", headwcf);
                    Label labe40 = new Label(4, 0, "������", headwcf);
                    Label labe50 = new Label(5, 0, "ִ�н��", headwcf);
                    Label labe60 = new Label(6, 0, "����״̬", headwcf);
                    Label labe70 = new Label(7, 0, "��Ӧ���", headwcf);
                    Sheet.addCell(labe00);
                    Sheet.addCell(labe10);
                    Sheet.addCell(labe20);
                    Sheet.addCell(labe30);
                    Sheet.addCell(labe40);
                    Sheet.addCell(labe50);
                    Sheet.addCell(labe60);
                    Sheet.addCell(labe70);
                    writeBook.write();
                    writeBook.close();
                    }
                return filepath;
                }
    }
