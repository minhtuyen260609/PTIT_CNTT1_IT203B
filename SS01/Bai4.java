import java.io.IOException;

public class Bai4{

    public static void saveToFile() throws IOException{
        throw new IOException("Lỗi khi ghi file!");
    }

    public static void processUserData() throws IOException{
        saveToFile();
    }

    public static void main(String[]args){
        try{
            processUserData();
        }catch(IOException e){
            System.out.println("Đã xảy ra lỗi: "+e.getMessage());
        }
        System.out.println("Chương trình vẫn tiếp tục chạy...");
    }
}