package model.domain;

public class Category {
    private String title;
    private String description;
    private int score;
    //subcategory ??? hoe  ??? pag 7 puntje 2

    public Category(String title, String description){
        setTitle(title);
        setDescription(description);
        this.score = 0;

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

    public int getScore() {
        return score + 1;
    }
    public void addToScore() {
        this.score += 1;
    }


    public void setScore(int score) {
        this.score = score;
    }

    public String format() {
        return this.getTitle() + ";" + this.getDescription();
    }
    @Override
    public boolean equals(Object o) {
        if(o instanceof Category) {
            Category c = (Category) o;
            if(this.getTitle().equals(c.getTitle())) {
                return true;
            }
        }
        return false;
    }
}
