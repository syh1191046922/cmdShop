import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

public class Test {
    static Product carts[]=new Product[3];
    static int count=0;
    public static void main(String args[]) throws ClassNotFoundException {
        boolean bool = true;
        while (bool) {
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
                    bool = false;
                    shopping(sc);
                    while (true) {
                        System.out.println("查看购物车输入1：");
                        System.out.println("继续购物输入2");
                        System.out.println("结账请按3");
                        System.out.println("退出请按4");
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
                        } else if (choose == 4) {
                            break;
                        }
                    }
                    break;
                } else {
                    System.out.println("登录失败");
                }
            }
        }
    }
    public static void shopping(Scanner sc)throws ClassNotFoundException {
        InputStream inPro = Class.forName("Test").getResourceAsStream("/product.xlsx");//  /表示的就是classpath
        ReadProductExcel readProductExcel = new ReadProductExcel();
        Product products[] = readProductExcel.getAllProduct(inPro);
        for (Product product : products) {
            System.out.print(product.getpID());
            System.out.print("\t" + product.getpName());
            System.out.print("\t\t" + product.getPrice());
            System.out.println("\t\t" + product.getpDesc());
        }
        System.out.println("请输入商品ID，把该商品加入购物车：");
        String pId = sc.next();
        ReadProductExcel readProductExcel1 = new ReadProductExcel();
        inPro = null;
        inPro = Class.forName("Test").getResourceAsStream("/product.xlsx");//  /表示的就是classpath
        Product product = readProductExcel1.getProductById(pId, inPro);
        if (product != null) {
            carts[count++] = product;
        }
    }
}

