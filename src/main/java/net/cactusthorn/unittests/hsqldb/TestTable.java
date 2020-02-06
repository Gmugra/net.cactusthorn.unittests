package net.cactusthorn.unittests.hsqldb;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TESTTABLE")
@Access(AccessType.FIELD)
public class TestTable implements Serializable {

    private static final long serialVersionUID = 0L;

    @Id
    @Column(name = "ID")
    private long id;
    
    @Column(name = "COL1")
    private String col1;

    public TestTable() {
    }

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

}
