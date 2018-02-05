package nitin.thecrazyprogrammer.generics.Models;

/**
 * Created by Nitin Khurana on 2/4/2018.
 */
public class IntroScreenData {

    private int icon;
    private String title;
    private String desc;
    private int color;

    public IntroScreenData(int icon, String title, String desc, int color) {
        this.icon = icon;
        this.title = title;
        this.desc = desc;
        this.color = color;
    }

    public int getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public int getColor() {
        return color;
    }
}
