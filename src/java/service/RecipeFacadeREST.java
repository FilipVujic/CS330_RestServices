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

/**
 *
 * @author filip
 */
@Stateless
@Path("entity.recipe")
public class RecipeFacadeREST extends AbstractFacade<Recipe> {

    @PersistenceContext(unitName = "CS330_PZ_RestServicesPU")
    private EntityManager em;

    public RecipeFacadeREST() {
        super(Recipe.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Recipe entity) {
        super.create(entity);
       
    }
    
//    @POST
//    @Path("createAndReturnId/")
//    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public Integer createAndReturnId(Recipe entity) {
//        super.create(entity);
//        System.out.println(entity.getId());
//        return entity.getId();
//    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Recipe entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Recipe find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Recipe> findAll() {
        return super.findAll();
    }
    
        //
    @GET
    @Path("queryObjList/{username}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Recipe> findAllWithUsername(@PathParam("username") String username) {
        List<Recipe> allRecipes = super.findAll();
        List<Recipe> resultRecipes = new LinkedList<>();
        
        for (Recipe recipe : allRecipes) {
            if (recipe.getUsername().equals(username)) {
                resultRecipes.add(recipe);
            }
        }

        
        return resultRecipes;
    }
    
    @GET
    @Path("queryObj/{username}/{date}")
    @Produces({MediaType.APPLICATION_JSON})
    public Recipe findAllWithUsernameAndDate(@PathParam("username") String username, @PathParam("date") String date) {
        List<Recipe> allRecipes = super.findAll();
        Recipe resultRecipe = new Recipe();
        
        for (Recipe recipe : allRecipes) {
            if (recipe.getUsername().equals(username) && recipe.getDateInserted().equals(date)) {
                resultRecipe = recipe;
            }
        }

        
        return resultRecipe;
    }
    
    @GET
    @Path("queryID/{username}/{date}")
    @Produces({MediaType.APPLICATION_JSON})
    public Integer findIdWithUsernameAndDate(@PathParam("username") String username, @PathParam("date") String date) {
        List<Recipe> allRecipes = super.findAll();
        Integer result = -1;
        
        for (Recipe recipe : allRecipes) {
            if (recipe.getUsername().equals(username) && recipe.getDateInserted().equals(date)) {
                result = recipe.getId();
            }
        }
            System.out.println(result);
        return result;
    }
    
    @GET
    @Path("queryTag/{tag}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Recipe> findByTag(@PathParam("tag") String tag) {
        List<Recipe> allRecipes = super.findAll();
        List<Recipe> resultList = new LinkedList<>();
        
        for (Recipe recipe : allRecipes) {
            if (recipe.getTitle().toLowerCase().contains(tag.toLowerCase()) || 
                    recipe.getSynopsis().toLowerCase().contains(tag.toLowerCase())){
                resultList.add(recipe);
                System.out.println(recipe.toString());
            }
        }

        return resultList;
    }
    
    //

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Recipe> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
