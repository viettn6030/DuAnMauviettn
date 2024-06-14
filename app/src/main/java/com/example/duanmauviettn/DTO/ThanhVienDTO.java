package com.example.duanmauviettn.DTO;

public class ThanhVienDTO {
    private int matv;
    private String hoten;
    private String namsinh;

    private String cccd;

    public ThanhVienDTO() {
    }

    public ThanhVienDTO(int matv, String hoten, String namsinh, String cccd) {
        this.matv = matv;
        this.hoten = hoten;
        this.namsinh = namsinh;
        this.cccd = cccd;
    }

    public int getMatv() {
        return matv;
    }

    public void setMatv(int matv) {
        this.matv = matv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(String namsinh) {
        this.namsinh = namsinh;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }
}
