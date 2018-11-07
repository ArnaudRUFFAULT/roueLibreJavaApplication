/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author arnau
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findByIdUser", query = "SELECT u FROM User u WHERE u.idUser = :idUser")
    , @NamedQuery(name = "User.findByFirstname", query = "SELECT u FROM User u WHERE u.firstname = :firstname")
    , @NamedQuery(name = "User.findByLastname", query = "SELECT u FROM User u WHERE u.lastname = :lastname")
    , @NamedQuery(name = "User.findByPhoto", query = "SELECT u FROM User u WHERE u.photo = :photo")
    , @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email")
    , @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")
    , @NamedQuery(name = "User.findByGender", query = "SELECT u FROM User u WHERE u.gender = :gender")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUser")
    private Integer idUser;
    
    @Basic(optional = false)
    @Column(name = "firstname")
    private String firstname;
    
    @Basic(optional = false)
    @Column(name = "lastname")
    private String lastname;
    
    @Basic(optional = true)//Optional par default value = true
    @Column(name = "photo")
    private String photo;
    
    @Basic(optional = true)
    @Lob
    @Column(name = "description")
    private String description;
    
    @Basic(optional = false)
    @Column(name = "email", unique = true)
    private String email;
    
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    
    public enum Gender{MALE,FEMALE}
    
    @Enumerated (value = EnumType.STRING)
    private Gender gender;
    
    @ManyToMany(mappedBy = "userCollection", fetch = FetchType.LAZY)
    private Collection<Adress> adressCollection;
    

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser", fetch = FetchType.LAZY)
    private Collection<Product> productCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUserisAbout", fetch = FetchType.LAZY)
    private Collection<Review> reviewCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser", fetch = FetchType.LAZY)
    private Collection<Review> reviewCollection1;
    

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser", fetch = FetchType.LAZY)
    private Collection<Rent> rentCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUserborrow", fetch = FetchType.LAZY)
    private Collection<Rent> rentCollection1;
    
    public User() {
    }

    public User(Integer idUser) {
        this.idUser = idUser;
    }

    public User(String firstname, String lastname, String description, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.description = description;
        this.email = email;
        this.password = password;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @XmlTransient
    public Collection<Adress> getAdressCollection() {
        return adressCollection;
    }

    public void setAdressCollection(Collection<Adress> adressCollection) {
        this.adressCollection = adressCollection;
    }

    @XmlTransient
    public Collection<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product> productCollection) {
        this.productCollection = productCollection;
    }

    @XmlTransient
    public Collection<Review> getReviewCollection() {
        return reviewCollection;
    }

    public void setReviewCollection(Collection<Review> reviewCollection) {
        this.reviewCollection = reviewCollection;
    }

    @XmlTransient
    public Collection<Review> getReviewCollection1() {
        return reviewCollection1;
    }

    public void setReviewCollection1(Collection<Review> reviewCollection1) {
        this.reviewCollection1 = reviewCollection1;
    }

    @XmlTransient
    public Collection<Rent> getRentCollection() {
        return rentCollection;
    }

    public void setRentCollection(Collection<Rent> rentCollection) {
        this.rentCollection = rentCollection;
    }

    @XmlTransient
    public Collection<Rent> getRentCollection1() {
        return rentCollection1;
    }

    public void setRentCollection1(Collection<Rent> rentCollection1) {
        this.rentCollection1 = rentCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.User[ idUser=" + idUser + " ]";
    }
    
}
