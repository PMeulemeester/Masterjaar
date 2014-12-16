/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nieuws.web.service;

import java.util.ArrayList;
import java.util.Date;
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
import nieuws.model.NewsItem;
import nieuws.model.SimpleNewsItem;

/**
 *
 * @author vongenae
 */
@Stateless
@Path("news")
public class NewsItemFacadeREST extends AbstractFacade<NewsItem> {
    @PersistenceContext(unitName = "NewsPU")
    private EntityManager em;

    public NewsItemFacadeREST() {
        super(NewsItem.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(NewsItem entity) {
        entity.setPublishDate(new Date());
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(NewsItem entity) {
        entity.setPublishDate(new Date());
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public NewsItem find(@PathParam("id") Long id) {
        return super.find(id);
    }

    
    @Override
    public List<NewsItem> findAll() {
        return super.findAll();
    }
    
    @GET
    @Produces({"application/xml", "application/json"})
     public List<SimpleNewsItem> findAllSimple() {
        List<SimpleNewsItem> nieuwsItems = new ArrayList<>();
        for (NewsItem item : super.findAll()) {
            nieuwsItems.add(new SimpleNewsItem(item));
        }
        return nieuwsItems;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<NewsItem> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
