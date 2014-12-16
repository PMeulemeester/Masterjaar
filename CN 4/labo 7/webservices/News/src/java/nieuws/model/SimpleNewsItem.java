/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nieuws.model;

/**
 *
 * @author vongenae
 */
public class SimpleNewsItem {  

    public SimpleNewsItem(NewsItem item) {
        this.id = item.getId();
        this.title = item.getTitle();
    }
    private Long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    private String title;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    } 
}
