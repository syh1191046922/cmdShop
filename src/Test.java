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
                    Product products[] = readProductExcel.getAllProduct(inPro);
                    for (Product product : products) {
                        System.out.print("\t" + product.getpID());
                        System.out.print("\t" + product.getpName());
                        System.out.print("\t" + product.getPrice());
                        System.out.print("\t" + product.getpDesc() + "\n");
                    }

                    int count = 0;
                    Product productes[] = new Product[3];//创建购物车（用数组模拟）
                    System.out.println("请输入商品ID，把该商品加入购物车：");
                    String pID = sc.next();
                    ReadProductExcel readProductExcel1 = new ReadProductExcel();
                    inPro = null;
                    inPro = Class.forName("Test").getResourceAsStream("/product.xlsx");
                    Product product = readProductExcel1.getProductById(pID, inPro);
                    if (product != null) {
                        productes[count++] = product;
                    }

                    System.out.println("查看购物车输入1：");
                    System.out.println("继续购物输入2");
                    int choose = sc.nextInt();

                    if (choose == 1) {
                        for (Product product1 : productes) {
                            if (productes != null) {
                                System.out.println("\t" + product1.getpID());
                                System.out.println("\t" + product1.getpName());
                                System.out.println("\t" + product1.getPrice());
                                System.out.println("\t" + product1.getpDesc());
                            }
                        }
                    }
                    else if (choose == 2) {
                        System.out.println("请输入商品ID，把该商品加入购物车：");
                        inPro = null;
                        inPro = Class.forName("Test").getResourceAsStream("/product.xlsx");
                        Product product1 = readProductExcel1.getProductById(pID, inPro);
                        if (product != null) {
                            productes[count++] = product;
                        }

                }
                break;
            } else{
                System.out.println("登录失败");
            }
        }
    }
}
}

