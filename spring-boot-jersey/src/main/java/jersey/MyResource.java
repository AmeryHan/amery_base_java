package jersey;

import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author ameryhan
 * @date 2019/7/30 20:21
 */

@Component
@Singleton
@Path("/resource")
public class MyResource {

    /**
     * http://localhost:8080/resource
     *
     * @return
     */
    @GET
    public String hello() {
        return "Hello World";
    }

}