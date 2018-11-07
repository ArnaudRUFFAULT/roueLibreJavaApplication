/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author arnau
 */
@Entity
@Table(name = "adress")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adress.findAll", query = "SELECT a FROM Adress a")
    , @NamedQuery(name = "Adress.findByIdAdress", query = "SELECT a FROM Adress a WHERE a.idAdress = :idAdress")
    , @NamedQuery(name = "Adress.findByCountry", query = "SELECT a FROM Adress a WHERE a.country = :country")
    , @NamedQuery(name = "Adress.findByStreetName", query = "SELECT a FROM Adress a WHERE a.streetName = :streetName")
    , @NamedQuery(name = "Adress.findByStreetNumber", query = "SELECT a FROM Adress a WHERE a.streetNumber = :streetNumber")
    , @NamedQuery(name = "Adress.findByZipCode", query = "SELECT a FROM Adress a WHERE a.zipCode = :zipCode")})
public class Adress implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAdress")
    private Integer idAdress;
    
    @Basic(optional = false)
    @Column(name = "country")
    private String country;
    
    @Basic(optional = false)
    @Column(name = "streetName")
    private String streetName;
    
    @Basic(optional = false)
    @Column(name = "streetNumber")
    private int streetNumber;
    
    @Lob
    @Column(name = "additionalAddressDetails")
    private String additionalAddressDetails;
    
    @Basic(optional = false)
    @Column(name = "zipCode")
    private String zipCode;
    
    @JoinTable(name = "liveat", joinColumns = {
        @JoinColumn(name = "idAdress", referencedColumnName = "idAdress")}, inverseJoinColumns = {
        @JoinColumn(name = "idUser", referencedColumnName = "idUser")})
    @ManyToMany
    private Collection<User> userCollection;
    
    @JoinTable(name = "location", joinColumns = {
        @JoinColumn(name = "idAdress", referencedColumnName = "idAdress")}, inverseJoinColumns = {
        @JoinColumn(name = "idProduct", referencedColumnName = "idProduct")})
    @ManyToMany
    private Collection<Product> productCollection;

    public Adress() {
    }

    public Adress(Integer idAdress) {
        this.idAdress = idAdress;
    }

    public Adress(Integer idAdress, String country, String streetName, int streetNumber, String zipCode) {
        this.idAdress = idAdress;
        this.country = country;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
    }

    public Integer getIdAdress() {
        return idAdress;
    }

    public void setIdAdress(Integer idAdress) {
        this.idAdress = idAdress;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getAdditionalAddressDetails() {
        return additionalAddressDetails;
    }

    public void setAdditionalAddressDetails(String additionalAddressDetails) {
        this.additionalAddressDetails = additionalAddressDetails;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @XmlTransient
    public Collection<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product> productCollection) {
        this.productCollection = productCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAdress != null ? idAdress.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adress)) {
            return false;
        }
        Adress other = (Adress) object;
        if ((this.idAdress == null && other.idAdress != null) || (this.idAdress != null && !this.idAdress.equals(other.idAdress))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Adress[ idAdress=" + idAdress + " ]";
    }
    
}
