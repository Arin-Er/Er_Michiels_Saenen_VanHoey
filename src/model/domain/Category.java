package model.domain;

public class Category {
    private String title;
    private String description;
    //subcategory ??? hoe  ??? pag 7 puntje 2

    public Category(String title, String description){
        setTitle(title);
        setDescription(description);
    }

    public void setTitle(String title) {
        if(title == null || title.isEmpty()){
            throw new DomainException("Name can not be empty");
        }
        this.title = title;
    }

    public void setDescription(String description) {
        if(description == null || description.isEmpty()){
            throw new DomainException("Description can not be empty");
        }
        this.description = description;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
