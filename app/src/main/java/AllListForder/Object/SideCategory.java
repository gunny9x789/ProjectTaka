package AllListForder.Object;

public class SideCategory {
    private int  idSideCategory;
    private String codeId, sideCategoryName,codeMainCategory,sideCategoryAvatar;

    public SideCategory(String codeMainCategory, int idSideCategory, String codeId, String sideCategoryName, String sideCategoryAvatar) {
        this.codeMainCategory = codeMainCategory;
        this.idSideCategory = idSideCategory;
        this.codeId = codeId;
        this.sideCategoryName = sideCategoryName;
        this.sideCategoryAvatar = sideCategoryAvatar;
    }

    public String getIdMainCategory() {
        return codeMainCategory;
    }

    public void setIdMainCategory(String codeMainCategory) {
        this.codeMainCategory = codeMainCategory;
    }

    public int getIdSideCategory() {
        return idSideCategory;
    }

    public void setIdSideCategory(int idSideCategory) {
        this.idSideCategory = idSideCategory;
    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getSideCategoryName() {
        return sideCategoryName;
    }

    public void setSideCategoryName(String sideCategoryName) {
        this.sideCategoryName = sideCategoryName;
    }

    public String getSideCategoryAvatar() {
        return sideCategoryAvatar;
    }

    public void setSideCategoryAvatar(String sideCategoryAvatar) {
        this.sideCategoryAvatar = sideCategoryAvatar;
    }
}
