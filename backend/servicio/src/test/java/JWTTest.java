import org.junit.Test;

public class JWTTest {

    @Test
    public void createJWTTest() throws Exception{
        System.out.println(String.valueOf(1));
        String token = JWT.createJWT(1,"mail@mail.com");
        System.out.println(token);
    }

    @Test
    public void parseJWTTest() throws Exception{
        JWT.parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIiwiaWF0IjoxNjExOTM5MzIyLCJzdWIiOiJtYWlsQG1haWwuY29tIiwiaXNzIjoibWVyY2FkZW9VY2FiIiwiZXhwIjoxNjExOTUwMTIyfQ.hqAeUxMwXp1pZPEHyByB7z5K7wozHMRgm8rUeRluPok");
    }
}
