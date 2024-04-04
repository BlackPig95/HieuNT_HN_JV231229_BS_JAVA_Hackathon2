package ra.businessImp;

import ra.business.IProduct;
import ra.config.CONSOLECOLORS;
import ra.config.CONSTANT;
import ra.config.InputMethods;

public class Product implements IProduct, Comparable<Product>
{
    private int productId;
    private String productName;
    private String title;
    private String description;
    private float importPrice;
    private float exportPrice;
    private float interest;
    private Boolean productStatus;

    public Product()
    {
    }

    public Product(int productId, String productName, String title, String description, float importPrice, float exportPrice, float interest, Boolean productStatus)
    {
        this.productId = productId;
        this.productName = productName;
        this.title = title;
        this.description = description;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.interest = interest;
        this.productStatus = productStatus;
    }

    public int getProductId()
    {
        return productId;
    }

    public void setProductId(int productId)
    {
        this.productId = productId;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public float getImportPrice()
    {
        return importPrice;
    }

    public void setImportPrice(float importPrice)
    {
        this.importPrice = importPrice;
    }

    public float getExportPrice()
    {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice)
    {
        this.exportPrice = exportPrice;
    }

    public float getInterest()
    {
        return interest;
    }

    public void setInterest(float interest)
    {
        this.interest = interest;
    }

    public Boolean getProductStatus()
    {
        return productStatus;
    }

    public void setProductStatus(Boolean productStatus)
    {
        this.productStatus = productStatus;
    }

    @Override
    public void inputData()
    {
        inputProductId();
        inputProductName();
        inputProductTitle();
        inputProductDescription();
        inputImportPrice();
        inputExportPrice();
        this.interest = this.exportPrice - this.importPrice;
        inputProductStatus();
    }

    @Override
    public void displayData()
    {
        System.out.printf("Mã sản phẩm: %d | Tên sản phẩm: %s | Tiêu đề sản phẩm: %s\n",
                this.productId, this.productName, this.title);
        System.out.printf("Mô tả sản phẩm: %s\n", this.description);
        System.out.printf("Giá nhập vào: %.2f | Giá bán ra: %.2f\n", this.importPrice, this.exportPrice);
        System.out.printf("Lợi nhuận khi bán sản phẩm: %.2f\n", this.interest);
        System.out.printf("Trạng thái sản phẩm: %s\n", this.productStatus ? "Đang bán" : "Không bán");
        System.out.println("==================================================================================");
    }

    //Hàm hiển thị các thông tin cơ bản thay vì toàn bộ các chi tiết,
    // để tiện cho việc hiển thị thông tin tìm kiếm
    public void displayBasicData()
    {
        System.out.printf("Mã sản phẩm: %d | Tên sản phẩm: %s\n", this.productId, this.productName);
        System.out.printf("Lợi nhuận khi bán sản phẩm: %.2f\n", this.interest);
        System.out.println("==================================================================================");
    }

    private void inputProductId()
    {
        System.out.print("Mời nhập mã cho sản phẩm:");
        this.productId = InputMethods.nextInt();
    }

    private void inputProductName()
    {
        System.out.print("Mời nhập tên cho sản phẩm này:");
        this.productName = InputMethods.nextLine();
    }

    private void inputProductTitle()
    {
        System.out.print("Nhập tiêu đề của sản phẩm này:");
        this.title = InputMethods.nextLine();
    }

    private void inputProductDescription()
    {
        System.out.print("Nhập mô tả về sản phẩm này:");
        this.description = InputMethods.nextLine();
    }

    private void inputImportPrice()
    {
        while (true)
        {
            System.out.print("Giá nhập vào của sản phẩm này là:");
            this.importPrice = InputMethods.nextFloat();
            if (this.importPrice <= 0)
            {
                System.out.println(CONSOLECOLORS.RED + "Giá nhập vào phải lớn hơn 0. "
                        + CONSTANT.INPUT_AGAIN + CONSOLECOLORS.RESET);
            } else break;
        }

    }

    private void inputExportPrice()
    {
        while (true)
        {
            System.out.print("Giá bán ra của sản phẩm này là:");
            this.exportPrice = InputMethods.nextFloat();
            if (this.exportPrice < this.importPrice)
            {
                System.out.println(CONSOLECOLORS.RED + "Giá bán ra không thể thấp hơn giá nhập vào. "
                        + CONSTANT.INPUT_AGAIN + CONSOLECOLORS.RESET);
            } else break;
        }
    }

    private void inputProductStatus()
    {
        System.out.println("Nhập trạng thái của sản phẩm: true-Đang bán, false-Không bán");
        this.productStatus = InputMethods.nextBoolean();
    }

    @Override
    public int compareTo(Product otherProduct)
    {
        return Float.compare(this.interest, otherProduct.interest);
    }
}
