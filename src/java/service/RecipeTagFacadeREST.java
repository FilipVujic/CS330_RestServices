/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Recipe;
import entity.RecipeTag;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import service.AbstractFacade;

/**
 *
 * @author filip
 */
@Stateless
@Path("entity.recipetag")
public class RecipeTagFacadeREST extends AbstractFacade<RecipeTag> {

    @PersistenceContext(unitName = "CS330_PZ_RestServicesPU")
    private EntityManager em;

    public RecipeTagFacadeREST() {
        super(RecipeTag.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(RecipeTag entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, RecipeTag entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public RecipeTag find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<RecipeTag> findAll() {
        return super.findAll();
    }
    
    //
    @GET
    @Path("queryTag/{tag}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Integer> findAllReturnIDs(@PathParam("tag") String tag) {
        List<RecipeTag> recipeTags = super.findAll();
        List<Integer> resultList = new LinkedList<>();
        
        for(RecipeTag recipeTag : recipeTags) {
            if(recipeTag.getTagName().toLowerCase().equals(tag.toLowerCase())) {
                
                resultList.add(recipeTag.getRecipeID());
            }
        }
        return resultList;
    }
    //

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<RecipeTag> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
