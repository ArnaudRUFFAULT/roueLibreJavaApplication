/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author arnau
 */
@Entity
@Table(name = "rent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rent.findAll", query = "SELECT r FROM Rent r")
    , @NamedQuery(name = "Rent.findByIdRent", query = "SELECT r FROM Rent r WHERE r.idRent = :idRent")
    , @NamedQuery(name = "Rent.findByStart", query = "SELECT r FROM Rent r WHERE r.start = :start")
    , @NamedQuery(name = "Rent.findByEnd", query = "SELECT r FROM Rent r WHERE r.end = :end")
    , @NamedQuery(name = "Rent.findByState", query = "SELECT r FROM Rent r WHERE r.state = :state")})
public class Rent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rent")
    private Integer idRent;
    @Basic(optional = false)
    @Column(name = "start")
    @Temporal(TemporalType.DATE)
    private Date start;
    @Basic(optional = false)
    @Column(name = "end")
    @Temporal(TemporalType.DATE)
    private Date end;
    @Column(name = "state")
    private String state;
    @JoinColumn(name = "idProduct", referencedColumnName = "idProduct")
    @OneToOne(optional = false)
    private Product idProduct;
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User idUser;
    @JoinColumn(name = "idUser_borrow", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User idUserborrow;

    public Rent() {
    }

    public Rent(Integer idRent) {
        this.idRent = idRent;
    }

    public Rent(Integer idRent, Date start, Date end) {
        this.idRent = idRent;
        this.start = start;
        this.end = end;
    }

    public Integer getIdRent() {
        return idRent;
    }

    public void setIdRent(Integer idRent) {
        this.idRent = idRent;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Product getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Product idProduct) {
        this.idProduct = idProduct;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public User getIdUserborrow() {
        return idUserborrow;
    }

    public void setIdUserborrow(User idUserborrow) {
        this.idUserborrow = idUserborrow;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRent != null ? idRent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rent)) {
            return false;
        }
        Rent other = (Rent) object;
        if ((this.idRent == null && other.idRent != null) || (this.idRent != null && !this.idRent.equals(other.idRent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Rent[ idRent=" + idRent + " ]";
    }
    
}
