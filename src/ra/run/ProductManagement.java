package ra.run;

import ra.businessImp.Product;
import ra.config.CONSOLECOLORS;
import ra.config.InputMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductManagement
{
    private static final List<Product> productList = new ArrayList<>();

    public static void main(String[] args)
    {
        while (true)
        {
            System.out.println("""
                    ****************JAVA-HACKATHON-05-BASIC-MENU***************
                    1. Nhập số sản phẩm và nhập thông tin sản phẩm
                    2. Hiển thị thông tin các sản phẩm
                    3. Sắp xếp sản phẩm theo lợi nhuận tăng dần
                    4. Xóa sản phẩm theo mã sản phẩm
                    5. Tìm kiếm sản phẩm theo tên sản phẩm
                    6. Thay đổi trạng thái của sản phẩm theo mã sản phẩm
                    7. Thoát""");
            System.out.println("Nhập thao tác muốn thực hiện theo các lựa chọn ở trên");
            byte choice = InputMethods.nextByte();
            switch (choice)
            {
                case 1:
                    addProduct();
                    break;
                case 2:
                    displayAllProduct();
                    break;
                case 3:
                    sortByInterest();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    searchByName();
                    break;
                case 6:
                    changeProductStatus();
                    break;
                case 7:
                    return;
            }
        }
    }

    private static void addProduct()
    {
        System.out.print("Nhập số lượng sản phẩm mà bạn muốn thêm:");
        byte n = InputMethods.nextByte();
        for (int i = 0; i < n; i++)
        {
            System.out.println("Mời nhập thông tin cho sản phẩm thứ: " + (i + 1));
            Product newProduct = new Product();
            newProduct.inputData();
            productList.add(newProduct);
            System.out.println(CONSOLECOLORS.GREEN + "Đã thêm sản phẩm thành công" + CONSOLECOLORS.RESET);
        }
    }

    private static void displayAllProduct()
    {
        if (productList.isEmpty())
        {
            System.out.println(CONSOLECOLORS.RED + "Hiện không có sản phẩm nào" + CONSOLECOLORS.RESET);
            return;
        }
        System.out.println(CONSOLECOLORS.YELLOW + "Danh sách các sản phẩm hiện có:" + CONSOLECOLORS.RESET);
        productList.forEach(p -> p.displayData());
        System.out.println(CONSOLECOLORS.GREEN + "====================================================================================" + CONSOLECOLORS.RESET);
    }

    private static void sortByInterest()
    {
        if (productList.isEmpty())
        {
            System.out.println(CONSOLECOLORS.RED + "Hiện không có sản phẩm nào" + CONSOLECOLORS.RESET);
            return;
        }
        //Sort dựa theo comparable đã triển khai trong class Product
        productList.sort(Product::compareTo);
        //Hiển thị lại danh sách sau sắp xếp
        System.out.println(CONSOLECOLORS.GREEN + "Sắp xếp thành công:" + CONSOLECOLORS.RESET);
        productList.forEach(Product::displayBasicData);
        System.out.println(CONSOLECOLORS.GREEN + "==========================================================================" + CONSOLECOLORS.RESET);
    }

    private static void deleteProduct()
    {
        if (productList.isEmpty())
        {
            System.out.println(CONSOLECOLORS.RED + "Hiện không có sản phẩm nào" + CONSOLECOLORS.RESET);
            return;
        }
        System.out.println("Nhập mã sản phẩm cần xóa");
        //Lấy index của sản phẩm trong danh sách
        int indexDelete = getIndexById(InputMethods.nextInt());
        if (indexDelete != -1)
        {
            productList.remove(indexDelete);
            System.out.println(CONSOLECOLORS.GREEN + "Đã xóa thành công" + CONSOLECOLORS.RESET);
        } else
        {
            System.out.println(CONSOLECOLORS.RED + "Không tìm thấy sản phẩm cần xóa" + CONSOLECOLORS.RESET);
        }
    }

    private static void searchByName()
    {
        if (productList.isEmpty())
        {
            System.out.println(CONSOLECOLORS.RED + "Hiện không có sản phẩm nào" + CONSOLECOLORS.RESET);
            return;
        }
        System.out.println("Nhập tên của sản phẩm mà bạn muốn tìm kiếm:");
        String searchName = InputMethods.nextLine();
        //Lưu đệm vào danh sách để format thêm các câu dẫn cho rõ ràng hơn thay vì dùng forEach
        List<Product> nameList = productList.stream().filter(p -> p.getProductName().contains(searchName)).toList();
        if (nameList.isEmpty())
        {
            System.out.println(CONSOLECOLORS.RED + "Không tìm thấy sản phẩm nào" + CONSOLECOLORS.RESET);
        } else
        {
            System.out.println(CONSOLECOLORS.YELLOW + "Danh sách các sản phẩm có tên phù hợp:" + CONSOLECOLORS.RESET);
            nameList.forEach(p -> p.displayBasicData());
        }
    }

    private static void changeProductStatus()
    {
        if (productList.isEmpty())
        {
            System.out.println(CONSOLECOLORS.RED + "Hiện không có sản phẩm nào" + CONSOLECOLORS.RESET);
            return;
        }
        System.out.println("Nhập mã sản phẩm cần thay đổi trạng thái");
        //Lấy index của sản phẩm trong danh sách
        int updateIndex = getIndexById(InputMethods.nextInt());
        if (updateIndex != -1)
        {
            //Lưu đệm sản phẩm cần cập nhật để giảm bớt việc phải gọi hàm get(index)
            Product updateTempProduct = productList.get(updateIndex);
            //Set về trạng thái ngược lại so với hiện tại
            updateTempProduct.setProductStatus(!updateTempProduct.getProductStatus());
            System.out.println(CONSOLECOLORS.GREEN + "Cập nhật thành công trạng thái sản phẩm" + CONSOLECOLORS.RESET);
            System.out.println("Trạng thái mới của sản phẩm là: " +
                    (updateTempProduct.getProductStatus() ? "Đang bán" : "Không bán"));
        } else
        {
            System.out.println(CONSOLECOLORS.RED + "Không tìm được sản phẩm cần cập nhật" + CONSOLECOLORS.RESET);
        }
    }

    //Method hỗ trợ việc tìm index của sản phẩm trong danh sách
    //searchId = productId của sản phẩm cần tìm
    //return: index của sản phẩm trong danh sách, hoặc -1 nếu không tìm thấy
    private static int getIndexById(int searchId)
    {
        if (productList.isEmpty())
        {
            return -1;
        }
        for (int i = 0; i < productList.size(); i++)
        {
            if (productList.get(i).getProductId() == searchId)
                return i;
        }
        return -1;
    }
}
