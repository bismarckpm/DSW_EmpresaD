import mercadeoucab.JWT.JWT;
import org.junit.Test;

public class JWTTest {

    @Test
    public void createJWTTest() throws Exception{
        String token = JWT.createJWT(1,"mail@mail.com");
        System.out.println(token);
    }

    @Test
    public void parseJWTTest() throws Exception{
        JWT.parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaWF0IjoxNjEyMjIwNTA3LCJzdWIiOiJtYWlsQG1haWwuY29tIiwiaXNzIjoibWVyY2FkZW9VY2FiIiwiZXhwIjoxNjEyMjMxMzA3fQ.fjfg4aIEobQ_p3jOgfB9KXvF-LaNetJQ7HQjmizKxvE");
    }
}
