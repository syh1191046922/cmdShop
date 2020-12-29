import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

public class Test {
    public static void main(String args[]) throws ClassNotFoundException {
        boolean bo = true;
        while (bo) {
            System.out.println("请输入用户名：");
            Scanner sc = new Scanner(System.in);
            String username = sc.next();
            System.out.println("请输入密码");
            String password = sc.next();

            InputStream in = Class.forName("Test").getResourceAsStream("/users.xlsx");
            InputStream inPro = Class.forName("Test").getResourceAsStream("/Product.xlsx");
            ReadUserExcel readExcel = new ReadUserExcel();
            User users[] = readExcel.readExcel(in);
            for (int i = 0; i < users.length; i++) {
                if (username.equals(users[i].getUsername()) && password.equals(users[i].getPassword())) {
                    ReadProductExcel readProductExcel = new ReadProductExcel();
                    Product products[] = readProductExcel.readExcel(inPro);
                    for (Product product : products) {
                        System.out.print(product.getpID());
                        System.out.print(product.getpName());
                        System.out.print(product.getPrice());
                        System.out.print(product.getpDesc());
                    }
                    System.out.println("请输入商品ID把该商品加入购物车");
                    String pID=sc.next();
                    int count=0;
                    Product productes[]=new Product[3];
                    inPro=null;
                    inPro = Class.forName("Test").getResourceAsStream("/Product.xlsx");
                    Product product=readProductExcel.getProductById(pID,inPro);
                    if(product!=null){
                        productes[count++]=product;
                    }
                    bo = false;
                    break;
                } else {
                    System.out.println("登录失败");
                }
            }
        }
    }
}

