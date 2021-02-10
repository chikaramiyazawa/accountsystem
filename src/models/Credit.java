package models;

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
            name = "getAllCredit",
            query = "SELECT c FROM Credit AS  c  ORDER BY c.id DESC"
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

    public void setRemainder(Integer remainder) {
        this.remainder = remainder;
    }

    public Integer getCash() {
        return cash;
    }

    public void setCash(Integer cash) {
        this.cash = cash;
    }

}
