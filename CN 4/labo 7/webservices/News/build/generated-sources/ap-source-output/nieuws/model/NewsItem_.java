package nieuws.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-12-14T12:47:45")
@StaticMetamodel(NewsItem.class)
public class NewsItem_ { 

    public static volatile SingularAttribute<NewsItem, String> author;
    public static volatile SingularAttribute<NewsItem, Date> publishDate;
    public static volatile SingularAttribute<NewsItem, Long> id;
    public static volatile SingularAttribute<NewsItem, String> message;
    public static volatile SingularAttribute<NewsItem, String> title;

}