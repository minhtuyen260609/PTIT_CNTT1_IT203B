package Bai4;
import java.util.*;

public class BenhNhanDTO {
    int maBenhNhan;
    String tenBenhNhan;
    List<DichVu> dsDichVu = new ArrayList<>();

    public BenhNhanDTO(int maBenhNhan, String tenBenhNhan) {
        this.maBenhNhan = maBenhNhan;
        this.tenBenhNhan = tenBenhNhan;
    }
}
