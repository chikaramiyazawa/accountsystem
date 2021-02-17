package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Table(name = "crdits")
@NamedQueries({
    @NamedQuery(
            name = "getMyAccountCredit",
            query = "SELECT c FROM Credit AS  c WHERE c.numbers = :numbers ORDER BY c.id DESC"
                ),
    @NamedQuery(
            name = "getMyAccountPass",
            query = "SELECT c FROM Credit AS  c WHERE c.numbers = :numbers ORDER BY c.id ASC"
            ),
    @NamedQuery(
            name = "getMyAccountCount",
            query = "SELECT COUNT(c) FROM Credit AS  c WHERE c.numbers = :numbers  "
                ),

})
@Entity

public class Credit {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name =  "numbers")
    private Account numbers;

    @Column(name = "remainder")
    private Integer remainder;

    @Column(name = "cash")
    private Integer cash;

    @Column(name = "deposit")
    private Integer deposit;

    @Column(name = "drawer")
    private Integer drawer;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;


    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getNumbers() {
        return numbers;
    }

    public void setNumbers(Account numbers) {
        this.numbers = numbers;
    }

    public Integer getRemainder() {
        return remainder;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    public Integer getDrawer() {
        return drawer;
    }

    public void setDrawer(Integer drawer) {
        this.drawer = drawer;
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

    public Timestamp getCreated_at() {
        return created_at;
    }


}
