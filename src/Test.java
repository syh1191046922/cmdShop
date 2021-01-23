import java.io.InputStream;
import java.util.Scanner;

public class Test {
    static Product carts[]=new Product[3];
    static int count=0;
    public static void main(String args[]) throws ClassNotFoundException {
        boolean bool = true;
        while (bool) {
            System.out.println("�������û�����");
            Scanner sc = new Scanner(System.in);
            String username = sc.next();
            System.out.println("����������");
            String password = sc.next();

            InputStream in = Class.forName("Test").getResourceAsStream("/users.xlsx");
            InputStream inPro = Class.forName("Test").getResourceAsStream("/Product.xlsx");
            ReadUserExcel readExcel = new ReadUserExcel();
            User users[] = readExcel.readExcel(in);
            for (int i = 0; i < users.length; i++) {
                if (username.equals(users[i].getUsername()) && password.equals(users[i].getPassword())) {
                    System.out.println("��½�ɹ�");
                    bool = false;
                    shopping(sc);
                    while (true) {
                        System.out.println("�鿴���ﳵ����1��");
                        System.out.println("������������2");
                        System.out.println("�����밴3");
                        System.out.println("�˳��밴4");
                        int choose = sc.nextInt();
                        if (choose == 1) {
                            for (Product product : carts) {
                                if (product != null) {
                                    System.out.println("\t" + product.getpID());
                                    System.out.println("\t" + product.getpName());
                                    System.out.println("\t" + product.getPrice());
                                    System.out.println("\t" + product.getpDesc());
                                }
                            }
                        } else if (choose == 2) {
                            shopping(sc);
                        } else if (choose == 3) {
                            /*
                            1.��������
                            2.��POI����Order.xlsx�ļ�
                            3.�ѹ��ﳵ�����Ʒд��Order.xlsx�ļ�
                             */
                            Order order= new Order();
                            order.setUser(users[i]);//���������û�
                            Product products[]=new Product[count];
                            int num=0;
                            //��carts�е�2��Product�������products
                            for(int j=0;j<carts.length;j++){
                                if(carts[j]!=null) {
                                products[j]=carts[j];
                                }
                            }
                            order.setProducts(products);//����������Ʒ
                            //��ι�����������Ʒ

                            //�¶���������Excel��

                            CreateOrder.createOrder(order);
                        } else if (choose == 4) {
                            break;
                        }
                    }
                    break;
                } else {
                    System.out.println("��¼ʧ��");
                }
            }
        }
    }
    public static void shopping(Scanner sc)throws ClassNotFoundException {
        InputStream inPro = Class.forName("Test").getResourceAsStream("/product.xlsx");//  /��ʾ�ľ���classpath
        ReadProductExcel readProductExcel = new ReadProductExcel();
        Product products[] = readProductExcel.getAllProduct(inPro);
        for (Product product : products) {
            System.out.print(product.getpID());
            System.out.print("\t" + product.getpName());
            System.out.print("\t\t" + product.getPrice());
            System.out.println("\t\t" + product.getpDesc());
        }
        System.out.println("��������ƷID���Ѹ���Ʒ���빺�ﳵ��");
        String pId = sc.next();
        ReadProductExcel readProductExcel1 = new ReadProductExcel();
        inPro = null;
        inPro = Class.forName("Test").getResourceAsStream("/product.xlsx");//  /��ʾ�ľ���classpath
        Product product = readProductExcel1.getProductById(pId, inPro);
        if (product != null) {
            carts[count++] = product;
        }
    }
}

