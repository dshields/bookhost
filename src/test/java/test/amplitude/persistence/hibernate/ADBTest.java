package test.amplitude.persistence.hibernate;

import amplitude.model.IAlbum;
import amplitude.persistence.hibernate.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class ADBTest {
    @PersistenceContext
    private EntityManager em;

    @Deployment
    public static Archive<?> createDeploymentPackage() {
        return ShrinkWrap.create(WebArchive.class, "UserPersistenceTest.war").addPackage(User.class.getPackage()).addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml");
    }

    @Test
    public void canGetAlbum() {
        Query query = em.createQuery("select a from Album a where a.albumId = :albumId", IAlbum.class);
        query.setParameter("albumId", 1);
        IAlbum album = (IAlbum) query.getSingleResult();
        assertNotNull(album);
    }
}
