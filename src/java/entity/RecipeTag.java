/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author filip
 */
@Entity
@Table(name = "recipe_tag")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RecipeTag.findAll", query = "SELECT r FROM RecipeTag r")
    , @NamedQuery(name = "RecipeTag.findById", query = "SELECT r FROM RecipeTag r WHERE r.id = :id")
    , @NamedQuery(name = "RecipeTag.findByRecipeID", query = "SELECT r FROM RecipeTag r WHERE r.recipeID = :recipeID")
    , @NamedQuery(name = "RecipeTag.findByTagID", query = "SELECT r FROM RecipeTag r WHERE r.tagID = :tagID")})
public class RecipeTag implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "recipeID")
    private int recipeID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tagID")
    private int tagID;

    public RecipeTag() {
    }

    public RecipeTag(Integer id) {
        this.id = id;
    }

    public RecipeTag(Integer id, int recipeID, int tagID) {
        this.id = id;
        this.recipeID = recipeID;
        this.tagID = tagID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    public int getTagID() {
        return tagID;
    }

    public void setTagID(int tagID) {
        this.tagID = tagID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecipeTag)) {
            return false;
        }
        RecipeTag other = (RecipeTag) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RecipeTag[ id=" + id + " ]";
    }
    
}
