package AllListForder.Object;

public class MainCategory {
    private int idMainCategory,mainCategoryAvatar;
    private String codeIdMainCategori, mainCategoryName;

    public MainCategory(int idMainCategory, String codeIdMainCategori, String mainCategoryName, int mainCategoryAvatar) {
        this.idMainCategory = idMainCategory;
        this.codeIdMainCategori = codeIdMainCategori;
        this.mainCategoryName = mainCategoryName;
        this.mainCategoryAvatar = mainCategoryAvatar;
    }

    public int getIdMainCategory() {
        return idMainCategory;
    }

    public void setIdMainCategory(int idMainCategory) {
        this.idMainCategory = idMainCategory;
    }

    public String getCodeIdMainCategori() {
        return codeIdMainCategori;
    }

    public void setCodeIdMainCategori(String codeIdMainCategori) {
        this.codeIdMainCategori = codeIdMainCategori;
    }

    public String getMainCategoryName() {
        return mainCategoryName;
    }

    public void setMainCategoryName(String mainCategoryName) {
        this.mainCategoryName = mainCategoryName;
    }

    public int getMainCategoryAvatar() {
        return mainCategoryAvatar;
    }

    public void setMainCategoryAvatar(int mainCategoryAvatar) {
        this.mainCategoryAvatar = mainCategoryAvatar;
    }
}
