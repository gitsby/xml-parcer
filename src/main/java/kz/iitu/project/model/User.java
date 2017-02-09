package kz.iitu.project.model;

/**
 * Created by Kasyanov Maxim on 2/9/2017.
 */
public class User {

    private String id;

    private String fio;

    private String departmentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
}
