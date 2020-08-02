package cn.jiyun.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="dept")
public class Dept {
    @Id
    private Integer did;

    private String dname;

    private String location;

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "did=" + did +
                ", dname='" + dname + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
