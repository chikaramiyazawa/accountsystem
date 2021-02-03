package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Table(name = "accounts")
@NamedQueries({
    @NamedQuery(
            name = "getAllAccount",
            query = "SELECT a FROM Account AS a ORDER BY a.id DESC"
                ),
    @NamedQuery(
            name = "getAccountCount",
            query ="SELECT COUNT(a) FROM Account AS a"
            ),
    @NamedQuery(
            name = "checkNumber",
            query ="SELECT COUNT(a)FROM Account AS a WHERE a.numbers = :numbers"
            ),
    @NamedQuery(
            name = "checkNumberAndPassword",
            query = "SELECT a FROM Account AS a WHERE  a.numbers = :numbers AND a.password = :password"
            ),

})
@Entity
public class Account {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name =  "numbers")
    private String numbers;

    @Column(name = "password")
    private String password;

    @Column(name = "remainder")
    private Integer remainder;

    @Column(name = "cash")
    private Integer cash;

    @Column(name = "admin_flag")
    private Integer admin_flag;

    @Column(name = "updated_at" , nullable = false )
    private Timestamp updated_at;

    @Column(name = "created_at" , nullable = false)
    private Timestamp created_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumbers() {
        return numbers;
    }

    public void setNumbers(String numbers) {
        this.numbers = numbers;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public Integer getRemainder() {
        return remainder;
    }

    public void setRemainder(Integer remainder) {
        this.remainder = remainder;
    }

    public Integer getCash() {
        return cash;
    }

    public void setCash(Integer cash) {
        this.cash = cash;
    }

    public Integer getAdmin_flag() {
        return admin_flag;
    }

    public void setAdmin_flag(Integer admin_flag) {
        this.admin_flag = admin_flag;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
}