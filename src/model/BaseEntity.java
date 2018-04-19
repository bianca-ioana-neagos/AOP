package model;


public class BaseEntity<ID> {

    private ID id;

    public ID getId(){
        return id;
    }
    public void setId(ID id){
        this.id=id;
    }
    public String toString(){
        return "id ="+id;
    }
}
