package nickgao.com.SlidingTabLayout;

public class PersonalTabModel {

    public int id;
    public int type;
    public String name;
    public String url;
    public int index;

    public void setIndex(int index) {
        this.index = index;
        this.id = this.index;
    }

}
