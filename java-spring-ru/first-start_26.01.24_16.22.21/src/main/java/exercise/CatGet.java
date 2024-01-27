package exercise;

public class CatGet {

    private Cat cat;

    private String catName;

    public CatGet() {
    }

    public CatGet(Cat cat) {
        System.out.println("catget");
        this.cat = cat;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
