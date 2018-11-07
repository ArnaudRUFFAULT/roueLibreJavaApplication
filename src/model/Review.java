/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Calendar;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author arnau
 */
@Entity
@Table(name = "review")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Review.findAll", query = "SELECT r FROM Review r")
    , @NamedQuery(name = "Review.findByIdReview", query = "SELECT r FROM Review r WHERE r.idReview = :idReview")
    , @NamedQuery(name = "Review.findByReview", query = "SELECT r FROM Review r WHERE r.review = :review")
    , @NamedQuery(name = "Review.findByRecommendation", query = "SELECT r FROM Review r WHERE r.recommendation = :recommendation")
    , @NamedQuery(name = "Review.findByDate", query = "SELECT r FROM Review r WHERE r.date = :date")
    , @NamedQuery(name = "Review.findByRating", query = "SELECT r FROM Review r WHERE r.rating = :rating")})
public class Review implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idReview")
    private Integer idReview;
    
    @Column(name = "review")
    private String review;
    
    @Basic(optional = false)
    @Column(name = "recommendation")
    private boolean recommendation;
    
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal (TemporalType.DATE)
    private Calendar date;//A TESTER
    
    @Basic(optional = false)
    @Column(name = "rating")
    private int rating;
    
    @JoinColumn(name = "idUser_isAbout", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User idUserisAbout;
    
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    @ManyToOne(optional = false)
    private User idUser;

    public Review() {
    }

    public Review(Integer idReview) {
        this.idReview = idReview;
    }

    public Review(Integer idReview, boolean recommendation, Calendar date, int rating) {
        this.idReview = idReview;
        this.recommendation = recommendation;
        this.date = date;
        this.rating = rating;
    }

    public Integer getIdReview() {
        return idReview;
    }

    public void setIdReview(Integer idReview) {
        this.idReview = idReview;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public boolean getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(boolean recommendation) {
        this.recommendation = recommendation;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getIdUserisAbout() {
        return idUserisAbout;
    }

    public void setIdUserisAbout(User idUserisAbout) {
        this.idUserisAbout = idUserisAbout;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReview != null ? idReview.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Review)) {
            return false;
        }
        Review other = (Review) object;
        if ((this.idReview == null && other.idReview != null) || (this.idReview != null && !this.idReview.equals(other.idReview))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Review[ idReview=" + idReview + " ]";
    }
    
}
