package models;

public class SyrupMedicine extends Medicine{
    private int volume;

    public SyrupMedicine(String id, String name, int volume, int stock, int price) {
        super(id, name, stock, price);
        this.volume = volume;
    }
    public int getVolume() {
        return volume;
    }
    public void setVolume(int volume) {
        this.volume = volume;
    }
}
