import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.io.FileOutputStream;


public class CreateOrder {
    /** Excel �ļ�Ҫ��ŵ�λ�ã��ٶ���F����*/
    public static String outputFile = "F:\\test.xls";
    public static void createOrder(Order order) {
        try {
            // �����µ�Excel ������
            HSSFWorkbook workbook = new HSSFWorkbook();
            // ��Excel�������н�һ����������Ϊȱʡֵ
            // ��Ҫ�½�һ��Ϊ"Ч��ָ��"�Ĺ����������Ϊ��
            HSSFSheet sheet = workbook.createSheet("ѧ���ɼ�");

            CellStyle style = workbook.createCellStyle();
            //�ؼ��� IndexedColors.AQUA.getIndex() ��Ӧ��ɫ
            style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            HSSFRow firstrow=sheet.createRow((short)0);
            HSSFCell cell01=firstrow.createCell((short)1);
            HSSFCell cell02=firstrow.createCell((short)2);
            HSSFCell cell03=firstrow.createCell((short)3);
            HSSFCell cell04=firstrow.createCell((short)4);
            HSSFCell cell05=firstrow.createCell((short)5);
            HSSFCell cell06=firstrow.createCell((short)6);

            cell01.setCellValue("�û�");
            cell02.setCellValue("��Ʒ");
            cell03.setCellValue("��������");
            cell04.setCellValue("��Ʒ�ܼ�");
            cell05.setCellValue("ʵ����");
            cell06.setCellValue("�µ�ʱ��");

            //��ѭ���Ĵ����͹��ﳵ�й�
            for(int i=0;i<order.getProducts().length;i++){
				// ������0��λ�ô����У���˵��У�
				HSSFRow row = sheet.createRow((short)i+1);
				for (int j=0;j<6;j++){
                    HSSFCell cell=firstrow.createCell((short)j);
                    if (j==0) {
                        cell.setCellValue(order.getUser().getUsername());

                    }

                }
			}
            // �½�һ����ļ���
            FileOutputStream fOut = new FileOutputStream(outputFile);
            // ����Ӧ��Excel ����������
            workbook.write(fOut);
            fOut.flush();
            // �����������ر��ļ�
            fOut.close();
            System.out.println("�ļ�����...");
        } catch (Exception e) {
            System.out.println("������ xlCreate() : " + e);
        }
    }
}