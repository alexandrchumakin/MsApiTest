import models.impl.UsersUnirest;
import models.repo.User;
import org.junit.Assert;
import org.junit.Test;

public class TestUsers {
    @Test
    public void testGetUser(){
        User user = new UsersUnirest().getUser("57b378da202bba0005a61b87");
        Assert.assertEquals(user.getName(), "Manuel");
        Assert.assertEquals(user.getLastname(), "Morejón");
    }
}
