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
                    System.out.println("登陆成功");
                    bo = false;
                    /*显示商品*/
                    ReadProductExcel readProductExcel = new ReadProductExcel();
                    Product products[] = readProductExcel.readExcel(inPro);
                    for (Product product : products) {
                        System.out.print(product.getpID());
                        System.out.print("/t" + product.getpName());
                        System.out.print("/t" + product.getPrice());
                        System.out.print("/t" + product.getpDesc());
                    }
                    System.out.println("请输入商品ID把该商品加入购物车");
                    String pID = sc.next();
                    int count = 0;
                    /*创建购物车数组*/
                    Product productes[] = new Product[3];

                    inPro = null;
                    inPro = Class.forName("Test").getResourceAsStream("/Product.xlsx");
                    Product product = readProductExcel.getProductById(pID, inPro);
                    System.out.println("想要购买商品的价格：" + product.getPrice());
                    if (product != null) {
                        productes[count++] = product;
                    }

                    break;
                } else {
                    System.out.println("登录失败");
                }
            }
        }
    }
}

